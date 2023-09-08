package fa.dfa;

import fa.State;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class DFA implements DFAInterface {
    /* 5-tuple instance variables */
    public LinkedHashSet<Character> sigma;

    public LinkedHashSet<String> states;
    public LinkedHashSet<String> finalStates;
    public String startState;
    public LinkedHashSet<DFAState> transitions;
    public String currentState;
    public Iterator<DFAState> it;

    // transition function?

    /**
     * Constructor for Deterministic Finite Automata (DFA)
     * Instantiates 5-tuple sets
     */
    public DFA() {
        currentState = "";
        startState = "";
        sigma = new LinkedHashSet<Character>();
        states = new LinkedHashSet<String>();
        finalStates = new LinkedHashSet<String>();
        transitions = new LinkedHashSet<DFAState>();
    }
    @Override
    public boolean addState(String name) {
        if (states.contains(name)) {
            return false;
        } else {
            states.add(name);
            transitions.add(new DFAState(name));
            return true;
        }
    }

    @Override
    public boolean setFinal(String name) {
        if (states.contains(name)) {
            finalStates.add(name);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setStart(String name) {
        if (states.contains(name)) {
            startState = name;
            currentState = name;
            return true;
        } else {
            return false;
        }
    }

    private String getStart() {
        return startState;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        boolean retVal = false;
        // loop thru string
        for (int i = 0; i < s.length(); i++) {
            // check if in alphabet
            if (!sigma.contains(s.charAt(i))) {
                return false;
            }
            // transition to state

            retVal = true;
        }
        return retVal;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        if (!states.contains(name)) {
            return null;
        }
        return new DFAState(name);
    }

    @Override
    public boolean isFinal(String name) {
        return finalStates.contains(name);
    }

    @Override
    public boolean isStart(String name) {
        return startState.equals(name);
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        String start = getStart();
        it = transitions.iterator();
        if (states.contains(fromState) && states.contains(toState) && sigma.contains(onSymb)) {
            while (it.hasNext()) {
                DFAState state = it.next();
                if (state.toString().equals(fromState)) {
                    state.addTransition(onSymb, toState);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }
}
