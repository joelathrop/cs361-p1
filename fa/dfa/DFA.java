package fa.dfa;

import fa.State;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class DFA implements DFAInterface {
    /* 5-tuple instance variables */
    public LinkedHashSet<Character> sigma;
    public LinkedHashSet<Character> copySigma;

    public LinkedHashSet<DFAState> states;
    public LinkedHashSet<DFAState> copyStates;
    public LinkedHashSet<DFAState> finalStates;
    public LinkedHashSet<DFAState> copyFinals;
    public LinkedHashSet<DFAState> transitions;
    public String startState;
    public String copyStart;
    public String totalStates = "";
    public String alphabet = "";

    // transition tables
    public LinkedHashMap<DFAState, LinkedHashMap<Character, DFAState>> transitionTable;
    public LinkedHashMap<DFAState, DFAState> copyMap;

    /**
     * Constructor for Deterministic Finite Automata (DFA)
     * Instantiates 5-tuple sets
     */
    public DFA() {
        startState = "";
        sigma = new LinkedHashSet<>();
        copySigma = new LinkedHashSet<>();
        states = new LinkedHashSet<>();
        copyStates = new LinkedHashSet<>();
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
        DFAState current = new DFAState("");
        for (DFAState st : states) {
            if (isStart(st.toString())){
                current = st;
            } else {
                // if it doesn't exist
            }
        }

        // loop through string
        for (int i = 0; i < s.length(); i++) {
            // check if in alphabet
            if (!sigma.contains(s.charAt(i)) && !copySigma.contains(s.charAt(i))) {
                return false;
            }
            // this is null once it gets to b, is that because .getNextState
            // is not returning the right address to b? it thinks b's
            // DFAState transition table is empty.
            try {
                current = current.getNextState(s.charAt(i));
            } catch (NullPointerException e) {
                return false;
            }
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
        DFAState current = new DFAState("");
        DFAState to = new DFAState("");

        // set from state
        for (DFAState s : states) {
            if (s.getName().equals(fromState)) {
                current = s;
            }
        }

        // set toState
        for (DFAState s : states) {
            if (s.getName().equals(toState)) {
                to = s;
            }
        }

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
                        s.addTransition(onSymb, to);
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

        // create a deep copy
        DFA newDFA = new DFA();
        LinkedHashMap<DFAState, DFAState> copyMap = new LinkedHashMap<>();
        // add all states to the new DFA
        for (DFAState oldState : states) {
            DFAState newState = new DFAState(oldState.getName());
            if (isFinal(oldState.getName())) {
                newDFA.setFinal(oldState.getName());
            }
            newDFA.addState(newState.getName());
            copyMap.put(oldState, newState);
            if (isStart(oldState.getName())) {
                newDFA.setStart(newState.getName());
            }
            copyStates.add(newState);
        }

        // add alphabet
        // this becomes a problem if swap is called multiple times
        newDFA.sigma.addAll(sigma);
        // add final states
        newDFA.finalStates.addAll(finalStates);
//        newDFA.copySigma.addAll(sigma);

        // iterate over all states in the new DFA and update transitions
        for (DFAState state : copyStates) {
            if (state.transitionTable.get(symb1) != null) {
                state.updateTransition(symb1, symb2);
            }
        }

        return newDFA;
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
                + alphabet.toString() + "\n";
        for (DFAState s : states) {
            ret += s.getName();

            for (Character c : alphabet.toCharArray()) {
                ret += s.transitionTable.get(c).toString();
            }
        }


        // q0 : start state portion
        ret += "\nq0 = " + startState + "\n";

        // F : final states portion
        String fStates = "";
        for (DFAState f : finalStates) {
            fStates = f.toString() + " ";
        }
        ret += "F = { " + fStates + "}";
        return ret;
    }
}
