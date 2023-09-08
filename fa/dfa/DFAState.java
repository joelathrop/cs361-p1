package fa.dfa;

import fa.State;
import java.util.LinkedHashMap;

public class DFAState extends State {

    /* 5-tuple instance variables */
    public LinkedHashMap<Character, String> transitionTable;

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

    public void addTransition(char value, String state) {
        // map values hold where it's going and how it's getting there,
        // ex: going to B on '0'
        transitionTable.put(value, state);
    }
}
