package fa.dfa;

import fa.State;

import java.util.LinkedHashSet;
import java.util.Set;

public class DFA implements DFAInterface {
    /* 5-tuple instance variables */
    public LinkedHashSet<String> sigma;

    public LinkedHashSet<String> states;
    public LinkedHashSet<String> finalStates;
    public LinkedHashSet<String> startStates;

    // transition function?

    /**
     * Constructor for Deterministic Finite Automata (DFA)
     * Instantiates 5-tuple sets
     */
    public DFA() {
        sigma = new LinkedHashSet<String>();
        states = new LinkedHashSet<String>();
        finalStates = new LinkedHashSet<String>();
        startStates = new LinkedHashSet<String>();
    }
    @Override
    public boolean addState(String name) {
        // search set for state name
        if (states.contains(name)) {
            return false;
        } else {
            states.add(name);
            return true;
        }
    }

    @Override
    public boolean setFinal(String name) {
        return false;
    }

    @Override
    public boolean setStart(String name) {
        return false;
    }

    @Override
    public void addSigma(char symbol) {

    }

    @Override
    public boolean accepts(String s) {
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        return null;
    }

    @Override
    public State getState(String name) {
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return false;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        return false;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }
}
