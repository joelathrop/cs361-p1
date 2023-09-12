package fa.dfa;

import fa.State;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class DFA implements DFAInterface {
    /* 5-tuple instance variables */
    public LinkedHashSet<Character> sigma;

    public LinkedHashSet<DFAState> states;
    public LinkedHashSet<DFAState> finalStates;
    public LinkedHashSet<DFAState> transitions;
    public String startState;
    public String totalStates = "";
    public String alphabet = "";
//    public Iterator<DFAState> it;

    // transition table
    public LinkedHashMap<DFAState, LinkedHashMap<Character, DFAState>> transitionTable;

    /**
     * Constructor for Deterministic Finite Automata (DFA)
     * Instantiates 5-tuple sets
     */
    public DFA() {
//        currentState = "";
        startState = "";
        sigma = new LinkedHashSet<>();
        states = new LinkedHashSet<>();
        finalStates = new LinkedHashSet<>();
        transitions = new LinkedHashSet<>();
        transitionTable = new LinkedHashMap<>();
    }
    @Override
    public boolean addState(String name) {
        boolean retVal = false;
        DFAState newState = new DFAState(name);
        if (states.isEmpty()) {
            states.add(newState);
            totalStates += name + " ";
            retVal = true;
        } else {
            for (DFAState s : states) {
                if (s.toString().equals(name)) {
                    return false;
                } else {
//                    states.add(newState);
                    transitions.add(new DFAState(name));
                    totalStates += name + " ";
                    retVal = true;
                }
            }
        }
        if (retVal && !states.contains(newState)) {
            states.add(newState);
        }
        return retVal;
    }

    @Override
    public boolean setFinal(String name) {
        boolean retVal = false;
        boolean done = false;
        DFAState newState = new DFAState(name);

        if (states.isEmpty()) {
//            finalStates.add(newState);
            return retVal;
        } else {
            while (!done) {
                for (DFAState s : states) {
                    if (s.toString().equals(name)) {
                        finalStates.add(newState);
                        retVal = true;
                        break;
                    } else {
                        retVal = false;
                    }
                }
                done = true;
            }
        }
        return retVal;
    }

    // TODO: Reset start state on next call
    @Override
    public boolean setStart(String name) {
        boolean retVal = false;
        boolean done = false;

        if (states.isEmpty()) {
            return retVal;
        } else {
            while (!done) {
                for (DFAState s : states) {
                    if (s.toString().equals(name)) {
                        startState = name;
                        retVal = true;
//                        done = true;
                        break;
                    } else {
                        retVal = false;
                    }
                }
                done = true;
            }
        }
        return retVal;
    }

    @Override
    public void addSigma(char symbol) {
        alphabet += symbol + " ";
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        boolean retVal = false;
        DFAState current = new DFAState(startState);

        // loop through string
        for (int i = 0; i < s.length(); i++) {
            // check if in alphabet
            if (!sigma.contains(s.charAt(i))) {
                return false;
            }
            current = current.getNextState(current, s.charAt(i));

            // transition to state
//            if (transitionTable.containsKey(s.charAt(i))) {
//                current = transitionTable.get(s.charAt(i));
//            }
//            current = current.transitionTable.get(s.charAt(i));
        }

        for (DFAState state : finalStates) {
            if (state.equals(current)) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        for (DFAState s : states) {
            if (s.toString().equals(name)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for (DFAState s : finalStates) {
            return s.toString().equals(name);
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return startState.equals(name);
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        boolean retVal = false;
        boolean done = false;
        boolean dneFlagFromState = true;
        boolean dneFlagToState = true;
        DFAState current = new DFAState(fromState);
        DFAState to = new DFAState(toState);

        // fromState check exist
        for (DFAState s : states) {
            if (s.toString().equals(fromState)) {
                dneFlagFromState = false;
            }
        }
        // return if fromState does not exist
        if (dneFlagFromState) {
            return false;
        }

        // toState check exist
        for (DFAState s : states) {
            if (s.toString().equals(toState)) {
                dneFlagToState = false;
            }
        }
        // return if toState does not exist
        if (dneFlagToState) {
            return false;
        }

        // alphabet check exist
        if (sigma.contains(onSymb)) {
            while(!done) {
                for (DFAState s : states) {
                    if (s.toString().equals(fromState)) {
                        s.addTransition(current, onSymb, to);
                        transitionTable
                                .computeIfAbsent(current, k -> new LinkedHashMap<>())
                                .put(onSymb, to);
                        retVal = true;
                        break;
                    }
                }
                done = true;
            }
        }
        return retVal;
    }

    /* TODO */
    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }

    //TODO: toString()
    @Override
    public String toString() {
        String ret = "";
        // Q, Sigma : states & alphabet portion
        ret += " Q = { " + totalStates + "}\n"
                + "Sigma = { " + alphabet + "}\n"

                // delta : transition table portion
                + "delta =\n"
                + "     " + alphabet + "\n";
        ret += transitionTable.toString() + "\n";

//        for (int i = 0; i < totalStates.length(); i++) {
//            ret += totalStates.charAt(i) + " ";
//            // go across the line with the transition states
////            ret += transitionTable.get(alphabet) + " ";
//            transitionTable.toString();
//        }

        // q0 : start state portion
        ret += "q0 = " + startState + "\n";

        // F : final states portion
        String fStates = "";
        for (DFAState f : finalStates) {
            fStates = f.toString() + " ";
        }
        ret += "F = { " + fStates + "}";
        return ret;
    }
}
