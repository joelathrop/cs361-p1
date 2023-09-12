package fa;

import fa.dfa.DFAState;

public abstract class State {
	/**
	 * The state label.
	 * It should be a unique name set by 
	 * the concrete class constructor.
	 * @author elenasherman
	 */
	private String name;
	
	/**
	 * All concrete consturctors must
	 * invoke this one as super(name).
	 * This way name instance variable is 
	 * correctly set.
	 */
	public State(String name) {
		this.name = name;
	}
	
	/**
	 * getter for the string label
	 * @return returns the state label.
	 */
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}


	public abstract boolean equals(DFAState s);
}
