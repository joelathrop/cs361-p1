package fa.dfa;

import fa.State;
import java.util.LinkedHashMap;

public class DFAState extends State {

    /* 5-tuple instance variables */
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
//        transitionTable.put(key, name);
    }

    /**
     * addTransition in DFAState adds a transition to a Map, which imitates
     * a transition table
     *
     * @param value - how it's getting there
     * @param state - where it's going
     */
    public void addTransition(char value, DFAState state) {
        // map values hold where it's going and how it's getting there,
        // ex: going to B on '0'
        transitionTable.put(value, state);
    }
}
