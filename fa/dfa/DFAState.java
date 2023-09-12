package fa.dfa;

import fa.State;
import java.util.LinkedHashMap;

public class DFAState extends State {

    /* 5-tuple instance variables */
//    public LinkedHashMap<Character, DFAState> transitionTable;
    /* Map: state maps to a map, which maps a character to a state
        example: State A maps to a map with keys (transitions) 0 and 1,
        which map to State A and State B respectively. */
    public LinkedHashMap<DFAState, LinkedHashMap<Character, DFAState>> transitionTable;

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
//        transitionTable.put(key, name);
    }

    /**
     * addTransition in DFAState adds a transition to a Map.
     * transitionTable maps a state to a map of a transition and a state.
     *
     * @param fromState - current state
     * @param toState - state transitioning to
     * @param value - how it's getting there
     */
    public void addTransition(DFAState fromState, char value, DFAState toState) {
        transitionTable
                .computeIfAbsent(fromState, k -> new LinkedHashMap<>())
                .put(value, toState);
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
