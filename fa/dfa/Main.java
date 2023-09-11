package fa.dfa;

public class Main {

    public static void main(String[] args) {
        DFA dfa = new DFA();

        dfa.addSigma('0');
        dfa.addSigma('1');

        dfa.addState("a");
        dfa.addState("b");
        dfa.setStart("a");
        dfa.setFinal("b");

        dfa.addTransition("a", "a", '0');
        dfa.addTransition("a", "b", '1');
        dfa.addTransition("b", "a", '0');
        dfa.addTransition("b", "b", '1');

        System.out.println(dfa.toString());
    }
}
