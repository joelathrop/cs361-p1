package fa.dfa;

import fa.State;
import java.util.LinkedHashMap;

/**
 * DFAState class creates a DFAState object which has a transition table
 * so that it can correctly map to the next state on a given transition symbol
 * from the alphabet sigma.
 *
 * @author joelathrop
 */
public class DFAState extends State {

    public LinkedHashMap<Character, DFAState> transitionTable;

    /**
     * All concrete consturctors must
     * invoke this one as super(name).
     * This way name instance variable is
     * correctly set.
     *
     * @param name - name of state
     */
    public DFAState(String name) {
        super(name);
        transitionTable = new LinkedHashMap<>();
    }

    /**
     * addTransition in DFAState adds a transition to a Map.
     * transitionTable maps a state to a map of a transition and a state.
     *
     * @param toState - state transitioning to
     * @param value - how it's getting there
     */
    public void addTransition(char value, DFAState toState) {
        transitionTable.put(value, toState);
    }

    /**
     * getNextState returns the next state based on the current state
     * (which is just the DFAState object we're in) and it's transition value
     *
     * @param symb - transition symbol from valid alphabet
     * @return - following DFAState based on transition value
     */
    public DFAState getNextState(char symb) {
        return transitionTable.get(symb);
    }

    @Override
    public boolean equals(DFAState s) {
        if (this == s) {
            return true; // If it's the same object in memory, they are equal
        }
        if (s == null || getClass() != s.getClass()) {
            return false; // If s is null or of a different class, they are not equal
        }
//        DFAState otherState = (DFAState) s;
        return this.getName().equals(s.getName());
    }
}
