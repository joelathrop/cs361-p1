package fa.dfa;

import fa.State;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class DFA implements DFAInterface {
    /* 5-tuple instance variables */
    public LinkedHashSet<Character> sigma;

    public LinkedHashSet<DFAState> states;
    public LinkedHashSet<DFAState> finalStates;
    public LinkedHashSet<DFAState> transitions;
    public String startState;
//    public String currentState;
    public Iterator<DFAState> it;

    // transition function?

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
    }
    @Override
    public boolean addState(String name) {
        boolean retVal = false;
        DFAState newState = new DFAState(name);
        if (states.isEmpty()) {
            states.add(newState);
            retVal = true;
        } else {
            for (DFAState s : states) {
                if (s.toString().equals(name)) {
                    return false;
                } else {
                    states.add(newState);
                    transitions.add(new DFAState(name));
                    retVal = true;
                }
            }
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

//    private String getStart() {
//        return startState;
//    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        boolean retVal = false;
        DFAState current = new DFAState(startState);

        // loop thru string
        for (int i = 0; i < s.length(); i++) {
            // check if in alphabet
            if (!sigma.contains(s.charAt(i))) {
                return false;
            }
            // transition to state
            current = current.transitionTable.get(s.charAt(i));
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
//        String start = getStart();
        it = transitions.iterator();
        for (DFAState s : states) {
            if (s.toString().equals(fromState) && s.toString().equals(toState) && sigma.contains(onSymb)) {
                while (it.hasNext()) {
                    DFAState state = it.next();
                    if (state.toString().equals(fromState)) {
                        DFAState add = new DFAState(toState);
                        state.addTransition(onSymb, add);
                    }
                }
                retVal = true;
            } else {
                retVal = false;
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
}
