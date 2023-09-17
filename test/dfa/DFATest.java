package test.dfa;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import fa.dfa.DFA;

public class DFATest {
	
	
	//------------------- dfa1 tests ----------------------//
	private DFA dfa1() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');
		
		assertTrue(dfa.addState("a"));
		assertTrue(dfa.addState("b"));
		assertTrue(dfa.setStart("a"));
		assertTrue(dfa.setFinal("b"));
		
		assertFalse(dfa.addState("a"));
		assertFalse(dfa.setStart("c"));
		assertFalse(dfa.setFinal("c"));
		
		assertTrue(dfa.addTransition("a", "a", '0'));
		assertTrue(dfa.addTransition("a", "b", '1'));
		assertTrue(dfa.addTransition("b", "a", '0'));
		assertTrue(dfa.addTransition("b", "b", '1'));
		
		assertFalse(dfa.addTransition("c", "b", '1'));
		assertFalse(dfa.addTransition("a", "c", '1'));
		assertFalse(dfa.addTransition("a", "b", '2'));
		
		return dfa;
	}
	
	@Test
	public void test1_1() {
		DFA dfa = dfa1();
		System.out.println("dfa1 instantiation pass");
	}

	@Test
	public void test1_2() {
		DFA dfa = dfa1();
		assertNotNull(dfa.getState("a"));
		assertEquals(dfa.getState("a").getName(),"a");
		assertTrue(dfa.isStart("a"));
		assertNotNull(dfa.getState("b"));
		assertEquals(dfa.getState("b").getName(),"b");
		assertTrue(dfa.isFinal("b"));
		assertEquals(dfa.getSigma(), Set.of('0','1'));
		
		System.out.println("dfa1 correctness pass");
	}
	
	@Test
	public void test1_3() {
		DFA dfa = dfa1();
		
		assertFalse(dfa.accepts("0"));
		assertTrue(dfa.accepts("1"));
		assertFalse(dfa.accepts("00"));
		assertTrue(dfa.accepts("101"));
		assertFalse(dfa.accepts("e"));
		
		System.out.println("dfa1 accept pass");
	}
	
	@Test
	public void test1_4() {
		DFA dfa = dfa1();
		
		String dfaStr = dfa.toString();
		String expStr = " Q = { a b }\n"
				+ "Sigma = { 0 1 }\n"
				+ "delta =\n"
				+ "		0	1\n"
				+ "	a	a	b\n"
				+ "	b	a	b\n"
				+ "q0 = a\n"
				+ "F = { b }";
		
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		
		System.out.println("dfa1 toString pass");
	}
	
	
	
	@Test
	public void test1_5() {
		DFA dfa = dfa1();
		DFA dfaSwap = dfa.swap('1', '0');
		
		//different DFA objects
		assertTrue(dfa != dfaSwap);
		
		//different state objects
		assertTrue(dfa.getState("a") != dfaSwap.getState("a"));
		assertTrue(dfa.getState("b") != dfaSwap.getState("b"));
		assertEquals(dfa.isStart("a"), dfaSwap.isStart("a"));
		
		//the transitions of the original dfa should not change
		assertFalse(dfa.accepts("0"));
		assertTrue(dfa.accepts("1"));
		assertFalse(dfa.accepts("00"));
		assertTrue(dfa.accepts("101"));
		assertFalse(dfa.accepts("e"));
	
		System.out.println("dfa1Swap instantiation pass");
	}
	
	@Test
	public void test1_6() {
		DFA dfa = dfa1();
		DFA dfaSwap = dfa.swap('1', '0');
		assertFalse(dfaSwap.accepts("1"));
		assertTrue(dfaSwap.accepts("0"));
		assertFalse(dfaSwap.accepts("11"));
		assertTrue(dfaSwap.accepts("010"));
		assertFalse(dfaSwap.accepts("e"));
		
		System.out.println("dfa1Swap accept pass");
	}

	//------------------- dfa2 tests ----------------------//
	private DFA dfa2() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');
		
		assertTrue(dfa.addState("3"));
		assertTrue(dfa.setFinal("3"));
		
		assertTrue(dfa.addState("0"));
		assertTrue(dfa.setStart("0"));
		
		assertTrue(dfa.addState("1"));
		assertTrue(dfa.addState("2"));
		
		
		assertFalse(dfa.setFinal("c"));
		assertFalse(dfa.setStart("a"));
		assertFalse(dfa.addState("2"));
		
		assertTrue(dfa.addTransition("0", "1", '0'));
		assertTrue(dfa.addTransition("0", "0", '1'));
		assertTrue(dfa.addTransition("1", "3", '0'));
		assertTrue(dfa.addTransition("1", "2", '1'));
		assertTrue(dfa.addTransition("2", "1", '0'));
		assertTrue(dfa.addTransition("2", "1", '1'));
		assertTrue(dfa.addTransition("3", "3", '0'));
		assertTrue(dfa.addTransition("3", "3", '1'));
		
		assertFalse(dfa.addTransition("3", "a", '1'));
		assertFalse(dfa.addTransition("c", "a", '1'));
		assertFalse(dfa.addTransition("3", "a", '2'));
		
		return dfa;
	}
	
	@Test
	public void test2_1() {
		DFA dfa = dfa2();
		System.out.println("dfa2 instantiation pass");
	}

	@Test
	public void test2_2() {
		DFA dfa = dfa2();
		assertNotNull(dfa.getState("0"));
		assertEquals(dfa.getState("1").getName(),"1");
		assertTrue(dfa.isStart("0"));
		assertNotNull(dfa.getState("3"));
		assertEquals(dfa.getState("3").getName(),"3");
		assertTrue(dfa.isFinal("3"));
		assertEquals(dfa.getSigma(), Set.of('0','1'));
		
		System.out.println("dfa2 correctness pass");
	}
	
	@Test
	public void test2_3() {
		DFA dfa = dfa2();
		assertFalse(dfa.accepts("010"));
		assertTrue(dfa.accepts("00"));
		assertFalse(dfa.accepts("101"));
		assertTrue(dfa.accepts("111011111111110"));
		assertFalse(dfa.accepts("1110111111111010"));
	
		System.out.println("dfa2 accept pass");
	}
	
	@Test
	public void test2_4() {
		DFA dfa = dfa2();
		
		String dfaStr = dfa.toString();
		String expStr = "Q={3 0 1 2}\n"
				+ "Sigma = {0 1}\n"
				+ "delta =\n"
				+ "	0	1\n"
				+ "3	3	3\n"
				+ "0	1	0\n"
				+ "1	3	2\n"
				+ "2	1	1\n"
				+ "q0 = 0\n"
				+ "F={3}\n";
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println("dfa2 toString pass");
	}
	
	
	
	@Test
	public void test2_5() {
		DFA dfa = dfa2();
		DFA dfaSwap = dfa.swap('1', '0');
		//different DFA objects
		assertTrue(dfa != dfaSwap);
		//different DFA states
		assertTrue(dfa.getState("0") != dfaSwap.getState("0"));
		assertTrue(dfa.getState("1") != dfaSwap.getState("1"));
		assertTrue(dfa.getState("3") != dfaSwap.getState("3"));
		assertEquals(dfa.isStart("0"), dfaSwap.isStart("0"));
		assertEquals(dfa.isFinal("3"), dfaSwap.isFinal("3"));
		
		//ensure that the transitions of the original DFA don't change
		assertFalse(dfa.accepts("010"));
		assertTrue(dfa.accepts("00"));
		assertFalse(dfa.accepts("101"));
		assertTrue(dfa.accepts("111011111111110"));
		assertFalse(dfa.accepts("1110111111111010"));
		
		System.out.println("dfa2Swap instantiation pass");
	}
	
	@Test
	public void test2_6() {
		DFA dfa = dfa2();
		DFA dfaSwap = dfa.swap('1', '0');
		assertFalse(dfaSwap.accepts("101"));
		assertTrue(dfaSwap.accepts("11"));
		assertFalse(dfaSwap.accepts("010"));
		assertTrue(dfaSwap.accepts("000100000000001"));
		assertFalse(dfaSwap.accepts("0001000000000101"));
		System.out.println("dfa2Swap accept pass");
	}	

	//------------------- dfa3 tests ----------------------//
	private DFA dfa3() {
		DFA dfa = new DFA();
		dfa.addSigma('2');
		dfa.addSigma('1');
	
		assertTrue(dfa.addState("G"));
		assertTrue(dfa.addState("D"));
	
		assertTrue(dfa.setFinal("G"));
		assertTrue(dfa.setFinal("D"));
	
		assertTrue(dfa.addState("A"));
		assertTrue(dfa.setStart("D"));
		assertTrue(dfa.setStart("A"));
	
		assertTrue(dfa.addState("B"));
		assertTrue(dfa.addState("C"));
		assertTrue(dfa.addState("E"));
		assertTrue(dfa.addState("F"));
	
		assertFalse(dfa.addState("A"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));
	
		assertTrue(dfa.addTransition("A", "B", '1'));
		assertTrue(dfa.addTransition("A", "C", '2'));
	
		assertTrue(dfa.addTransition("B", "D", '1'));
		assertTrue(dfa.addTransition("B", "E", '2'));
	
		assertTrue(dfa.addTransition("C", "F", '1'));
		assertTrue(dfa.addTransition("C", "G", '2'));
	
		assertTrue(dfa.addTransition("C", "F", '1'));
		assertTrue(dfa.addTransition("C", "G", '2'));
	
		assertTrue(dfa.addTransition("D", "D", '1'));
		assertTrue(dfa.addTransition("D", "E", '2'));
	
		assertTrue(dfa.addTransition("E", "D", '1'));
		assertTrue(dfa.addTransition("E", "E", '2'));
	
		assertTrue(dfa.addTransition("F", "F", '1'));
		assertTrue(dfa.addTransition("F", "G", '2'));
	
		assertTrue(dfa.addTransition("G", "F", '1'));
		assertTrue(dfa.addTransition("G", "G", '2'));
	
	
		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));
	
		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));
	
		return dfa;
	}

	@Test
	public void test3_1() {
		DFA dfa = dfa3();
	
		System.out.println("dfa3 instantiation pass");
	}

	@Test
	public void test3_2() {
		DFA dfa = dfa3();
		assertNotNull(dfa.getState("A"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("C").getName(),"C");
		assertTrue(dfa.isStart("A"));
		assertFalse(dfa.isStart("D"));
		assertNotNull(dfa.getState("G"));
		assertEquals(dfa.getState("E").getName(),"E");
		assertTrue(dfa.isFinal("G"));
		assertFalse(dfa.isFinal("B"));
		assertEquals(dfa.getSigma(), Set.of('2','1'));

		System.out.println("dfa3 correctness pass");
	}

	@Test
	public void test3_3() {
		DFA dfa = dfa3();
		assertTrue(dfa.accepts("121212121"));
		assertTrue(dfa.accepts("12221212121"));
		assertFalse(dfa.accepts("12"));
		assertFalse(dfa.accepts("2"));
		assertFalse(dfa.accepts("1212"));

		System.out.println("dfa3 accept pass");
	}

	@Test
	public void test3_4() {
		DFA dfa = dfa3();
	
		String dfaStr = dfa.toString();
		String expStr = "Q={GDABCEF}\n"
				+ "Sigma = {2 1}\n"
				+ "delta =\n"
				+ "	2	1\n"
				+ "G	G	F\n"
				+ "D	E	D\n"
				+ "A	C	B\n"
				+ "B	E	D\n"
				+ "C	G	F\n"
				+ "E	E	D\n"
				+ "F	G	F\n"
				+ "q0 = A\n"
				+ "F = {G D}\n";
	
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println("dfa3 toString pass");
	}



	@Test
	public void test3_5() {
		DFA dfa = dfa3();
		DFA dfaSwap = dfa.swap('2', '1');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("A") != dfaSwap.getState("A"));
		assertTrue(dfa.getState("G") != dfaSwap.getState("G"));
		assertTrue(dfa.getState("E") != dfaSwap.getState("E"));
		assertEquals(dfa.isStart("D"), dfaSwap.isStart("D"));
		assertEquals(dfa.isFinal("A"), dfaSwap.isFinal("A"));
	
		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("121212121"));
		assertTrue(dfa.accepts("12221212121"));
		assertFalse(dfa.accepts("12"));
		assertFalse(dfa.accepts("2"));
		assertFalse(dfa.accepts("1212"));

		System.out.println("df31Swap instantiation pass");
	}

	@Test
	public void test3_6() {
		DFA dfa = dfa3();
		DFA dfaSwap = dfa.swap('2', '1');
		assertTrue(dfaSwap.accepts("212121212"));
		assertTrue(dfaSwap.accepts("21112121212"));
		assertFalse(dfaSwap.accepts("21"));
		assertFalse(dfaSwap.accepts("1"));
		assertFalse(dfaSwap.accepts("2121"));
	
		System.out.println("dfa3Swap accept pass");
	}

	//------------------- dfa4 tests ----------------------//
	private DFA dfa4() {
		DFA dfa = new DFA();
		dfa.addSigma('b');
		dfa.addSigma('a');

		assertTrue(dfa.addState("0"));
		assertTrue(dfa.addState("1"));
		assertTrue(dfa.setStart("0"));
		assertTrue(dfa.setFinal("0"));

		assertFalse(dfa.addState("0"));
		assertFalse(dfa.setStart("2"));
		assertFalse(dfa.setFinal("2"));

		assertTrue(dfa.addTransition("0", "0", 'a'));
		assertTrue(dfa.addTransition("0", "1", 'b'));
		assertTrue(dfa.addTransition("1", "0", 'a'));
		assertTrue(dfa.addTransition("1", "1", 'b'));

		assertFalse(dfa.addTransition("2", "1", 'a'));
		assertFalse(dfa.addTransition("0", "2", 'a'));
		assertFalse(dfa.addTransition("0", "2", 'c'));

		return dfa;
	}

	@Test
	public void test4_1() {
		DFA dfa = dfa4();
		System.out.println("dfa4 instantiation pass");
	}

	@Test
	public void test4_2() {
		DFA dfa = dfa4();
		assertNotNull(dfa.getState("0"));
		assertEquals(dfa.getState("0").getName(),"0");
		assertTrue(dfa.isStart("0"));
		assertTrue(dfa.isFinal("0"));
		assertNotNull(dfa.getState("1"));
		assertFalse(dfa.isFinal("1"));
		assertEquals(dfa.getState("1").getName(),"1");
		assertEquals(dfa.getSigma(), Set.of('b','a'));

		System.out.println("dfa4 correctness pass");
	}

	@Test
	public void test4_3() {
		DFA dfa = dfa4();

		assertTrue(dfa.accepts("a"));
		assertFalse(dfa.accepts("b"));
		assertFalse(dfa.accepts("bb"));
		assertTrue(dfa.accepts("aba"));
		assertFalse(dfa.accepts("0"));

		System.out.println("dfa4 accept pass");
	}

	@Test
	public void test4_4() {
		DFA dfa = dfa4();

		String dfaStr = dfa.toString();
		String expStr = " Q = { 0 1 }\n"
				+ "Sigma = { b a }\n"
				+ "delta =\n"
				+ "		b	a\n"
				+ "	0	1	0\n"
				+ "	1	1	0\n"
				+ "q0 = 0\n"
				+ "F = { 0 }";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));

		System.out.println("dfa4 toString pass");
	}



	@Test
	public void test4_5() {
		DFA dfa = dfa4();
		DFA dfaSwap = dfa.swap('b', 'a');

		//different DFA objects
		assertTrue(dfa != dfaSwap);

		//different state objects
		assertTrue(dfa.getState("0") != dfaSwap.getState("0"));
		assertTrue(dfa.getState("1") != dfaSwap.getState("1"));
		assertEquals(dfa.isStart("0"), dfaSwap.isStart("0"));

		//the transitions of the original dfa should not change
		assertTrue(dfa.accepts("a"));
		assertFalse(dfa.accepts("b"));
		assertFalse(dfa.accepts("bb"));
		assertTrue(dfa.accepts("aba"));
		assertFalse(dfa.accepts("0"));

		System.out.println("dfa4Swap instantiation pass");
	}

	@Test
	public void test4_6() {
		DFA dfa = dfa4();
		DFA dfaSwap = dfa.swap('b', 'a');
		assertTrue(dfa.accepts("b"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("aa"));
		assertTrue(dfa.accepts("bab"));
		assertFalse(dfa.accepts("0"));

		System.out.println("dfa4Swap accept pass");
	}

	//------------------- dfa5 tests ----------------------//
	private DFA dfa5() {
		DFA dfa = new DFA();
		dfa.addSigma('1');
		dfa.addSigma('0');

		assertTrue(dfa.addState("A"));
		assertTrue(dfa.addState("B"));
		assertTrue(dfa.addState("C"));

		assertTrue(dfa.setFinal("A"));

		assertFalse(dfa.addState("A"));
		assertTrue(dfa.setStart("B"));
		assertTrue(dfa.setStart("A"));

		assertFalse(dfa.addState("A"));
		assertFalse(dfa.setFinal("D"));
		assertFalse(dfa.setStart("BD"));

		assertTrue(dfa.addTransition("A", "A", '0'));
		assertTrue(dfa.addTransition("A", "B", '1'));
		assertTrue(dfa.addTransition("B", "C", '1'));
		assertTrue(dfa.addTransition("C", "A", '1'));

		assertFalse(dfa.addTransition("AA", "A", '1'));
		assertFalse(dfa.addTransition("B", "C", '2'));
		assertFalse(dfa.addTransition("C", "C", 'D'));
		assertFalse(dfa.addTransition("A", "D", '7'));

		return dfa;
	}

	@Test
	public void test5_1() {
		DFA dfa = dfa5();

		System.out.println("dfa5 instantiation pass");
	}

	@Test
	public void test5_2() {
		DFA dfa = dfa5();
		assertNotNull(dfa.getState("A"));
		assertNull(dfa.getState("D"));
		assertEquals(dfa.getState("B").getName(),"B");
		assertTrue(dfa.isStart("A"));
		assertFalse(dfa.isStart("B"));
		assertNotNull(dfa.getState("B"));
		assertEquals(dfa.getState("C").getName(),"C");
		assertTrue(dfa.isFinal("A"));
		assertFalse(dfa.isFinal("C"));
		assertEquals(dfa.getSigma(), Set.of('1','0'));

		System.out.println("dfa5 correctness pass");
	}

	@Test
	public void test5_3() {
		DFA dfa = dfa5();
		assertTrue(dfa.accepts("0000111"));
		assertTrue(dfa.accepts("00111001110"));
		assertFalse(dfa.accepts("01"));
		assertFalse(dfa.accepts("0001100"));
		assertFalse(dfa.accepts("110000"));

		System.out.println("dfa5 accept pass");
	}

	@Test
	public void test5_4() {
		DFA dfa = dfa5();

		String dfaStr = dfa.toString();
		String expStr = "Q={ A B C }\n"
				+ "Sigma = { 1 0 }\n"
				+ "delta =\n"
				+ "		1	0\n"
				+ "A	B	A\n"
				+ "B	C	err\n"
				+ "C	A	err\n"
				+ "q0 = A\n"
				+ "F = { A }\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println("dfa5 toString pass");
	}



	@Test
	public void test5_5() {
		DFA dfa = dfa3();
		DFA dfaSwap = dfa.swap('1', '0');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("A") != dfaSwap.getState("A"));
		assertTrue(dfa.getState("B") != dfaSwap.getState("B"));
		assertTrue(dfa.getState("C") != dfaSwap.getState("C"));
		assertEquals(dfa.isStart("A"), dfaSwap.isStart("A"));
		assertEquals(dfa.isFinal("A"), dfaSwap.isFinal("A"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("0000111"));
		assertTrue(dfa.accepts("00111001110"));
		assertFalse(dfa.accepts("01"));
		assertFalse(dfa.accepts("0001100"));
		assertFalse(dfa.accepts("110000"));

		System.out.println("df5Swap instantiation pass");
	}

	@Test
	public void test5_6() {
		DFA dfa = dfa5();
		DFA dfaSwap = dfa.swap('1', '0');
		assertTrue(dfa.accepts("1111000"));
		assertTrue(dfa.accepts("11000110001"));
		assertFalse(dfa.accepts("10"));
		assertFalse(dfa.accepts("1110011"));
		assertFalse(dfa.accepts("001111"));

		System.out.println("dfa5Swap accept pass");
	}

	//------------------- dfa6 tests ----------------------//
	private DFA dfa6() {
		DFA dfa = new DFA();
		dfa.addSigma('b');
		dfa.addSigma('a');

		assertTrue(dfa.addState("q4"));
		assertTrue(dfa.setFinal("q4"));

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.setStart("q0"));

		assertTrue(dfa.addState("q1"));
		assertTrue(dfa.addState("q2"));
		assertTrue(dfa.addState("q3"));


		assertFalse(dfa.setFinal("c"));
		assertFalse(dfa.setStart("a"));
		assertFalse(dfa.addState("q2"));

		assertTrue(dfa.addTransition("q0", "q1", 'a'));
		assertTrue(dfa.addTransition("q0", "q2", 'b'));
		assertTrue(dfa.addTransition("q1", "q3", 'a'));
		assertTrue(dfa.addTransition("q2", "q3", 'a'));
		assertTrue(dfa.addTransition("q3", "q4", 'b'));


		assertFalse(dfa.addTransition("q3", "a", 'b'));
		assertFalse(dfa.addTransition("c", "a", 'b'));
		assertFalse(dfa.addTransition("q3", "a", 'c'));
		// TODO: This would make it an NFA, which we're not checking for
		// maybe ask about this in class?
//		assertFalse(dfa.addTransition("q1", "q0", 'b'));

		return dfa;
	}

	@Test
	public void test6_1() {
		DFA dfa = dfa6();
		System.out.println("dfa6 instantiation pass");
	}

	@Test
	public void test6_2() {
		DFA dfa = dfa6();
		assertNotNull(dfa.getState("q0").getName(),"q0");
		assertTrue(dfa.isStart("q0"));
		assertEquals(dfa.getState("q1").getName(),"q1");
		assertEquals(dfa.getState("q2").getName(),"q2");
		assertNotNull(dfa.getState("q3").getName(),"q3");
		assertEquals(dfa.getState("q4").getName(),"q4");
		assertTrue(dfa.isFinal("q4"));
		assertEquals(dfa.getSigma(), Set.of('b','a'));

		System.out.println("dfa6 correctness pass");
	}

	@Test
	public void test6_3() {
		DFA dfa = dfa6();
		assertFalse(dfa.accepts("aaa"));
		assertTrue(dfa.accepts("bab"));
		assertFalse(dfa.accepts("baa"));
		assertTrue(dfa.accepts("aab"));
		assertFalse(dfa.accepts("ab"));

		System.out.println("dfa6 accept pass");
	}

	@Test
	public void test6_4() {
		DFA dfa = dfa6();

		String dfaStr = dfa.toString();
		String expStr = "Q={ q0 q1 q2 q3 q4 }\n"
				+ "Sigma = { b a }\n"
				+ "delta =\n"
				+ "		b	a\n"
				+ "q0	q2	q1\n"
				+ "q1	err	q3\n"
				+ "q2	err	q3\n"
				+ "q3	q4	err\n"
				+ "q4	err	err\n"
				+ "q0 = q0\n"
				+ "F={ q4 }\n";
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println("dfa6 toString pass");
	}



	@Test
	public void test6_5() {
		DFA dfa = dfa6();
		DFA dfaSwap = dfa.swap('b', 'a');
		//different DFA objects
		assertTrue(dfa != dfaSwap);
		//different DFA states
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertTrue(dfa.getState("q2") != dfaSwap.getState("q2"));
		assertTrue(dfa.getState("q3") != dfaSwap.getState("q3"));
		assertTrue(dfa.getState("q4") != dfaSwap.getState("q4"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q4"), dfaSwap.isFinal("q4"));

		//ensure that the transitions of the original DFA don't change
		assertFalse(dfa.accepts("aaa"));
		assertTrue(dfa.accepts("bab"));
		assertFalse(dfa.accepts("baa"));
		assertTrue(dfa.accepts("aab"));
		assertFalse(dfa.accepts("ab"));

		System.out.println("dfa6Swap instantiation pass");
	}

	@Test
	public void test6_6() {
		DFA dfa = dfa6();
		DFA dfaSwap = dfa.swap('b', 'a');
		assertFalse(dfa.accepts("bbb"));
		assertTrue(dfa.accepts("aba"));
		assertFalse(dfa.accepts("abb"));
		assertTrue(dfa.accepts("bba"));
		assertFalse(dfa.accepts("ba"));
		System.out.println("dfa6Swap accept pass");
	}

	//------------------- dfa7 tests ----------------------//
	private DFA dfa7() {
		DFA dfa = new DFA();
		dfa.addSigma('c');
		dfa.addSigma('b');
		dfa.addSigma('a');

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.setFinal("q0"));
		assertTrue(dfa.setStart("q0"));

		assertTrue(dfa.addState("q1"));

		assertFalse(dfa.setFinal("a"));
		assertFalse(dfa.addState("q0"));

		assertTrue(dfa.addTransition("q0", "q0", 'b'));
		assertTrue(dfa.addTransition("q0", "q0", 'c'));
		assertTrue(dfa.addTransition("q0", "q1", 'a'));
		assertTrue(dfa.addTransition("q1", "q1", 'a'));
		assertTrue(dfa.addTransition("q1", "q1", 'b'));
		assertTrue(dfa.addTransition("q1", "q1", 'c'));


		assertFalse(dfa.addTransition("q3", "a", '0'));
		assertFalse(dfa.addTransition("c", "a", '3'));
		assertFalse(dfa.addTransition("q0", "q3", 'c'));

		return dfa;
	}

	@Test
	public void test7_1() {
		DFA dfa = dfa7();
		System.out.println("dfa7 instantiation pass");
	}

	@Test
	public void test7_2() {
		DFA dfa = dfa7();
		assertNotNull(dfa.getState("q0").getName(),"q0");
		assertTrue(dfa.isStart("q0"));
		assertTrue(dfa.isFinal("q0"));
		assertNotNull(dfa.getState("q1").getName(),"q1");
		assertEquals(dfa.getSigma(), Set.of('c','b','a'));

		System.out.println("dfa7 correctness pass");
	}

	@Test
	public void test7_3() {
		/* This machine should have no a's in it */
		DFA dfa = dfa7();
		assertFalse(dfa.accepts("aaa"));
		assertTrue(dfa.accepts("bcb"));
		assertFalse(dfa.accepts("baa"));
		assertTrue(dfa.accepts("ccb"));
		assertFalse(dfa.accepts("ab"));

		System.out.println("dfa7 accept pass");
	}

	@Test
	public void test7_4() {
		DFA dfa = dfa7();

		String dfaStr = dfa.toString();
		String expStr = "Q={ q0 q1 }\n"
				+ "Sigma = { c b a }\n"
				+ "delta =\n"
				+ "		c	b	a\n"
				+ "q0	q0	q0	q1\n"
				+ "q1	q1	q1	q1\n"
				+ "q0 = q0\n"
				+ "F={ q0 }\n";
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println("dfa7 toString pass");
	}



	@Test
	public void test7_5() {
		DFA dfa = dfa6();
		DFA dfaSwap = dfa.swap('b', 'a');
		//different DFA objects
		assertTrue(dfa != dfaSwap);
		//different DFA states
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q0"), dfaSwap.isFinal("q0"));

		//ensure that the transitions of the original DFA don't change
		assertFalse(dfa.accepts("aaa"));
		assertTrue(dfa.accepts("bcb"));
		assertFalse(dfa.accepts("baa"));
		assertTrue(dfa.accepts("ccb"));
		assertFalse(dfa.accepts("ab"));

		System.out.println("dfa7Swap instantiation pass");
	}

	@Test
	public void test7_6() {
		DFA dfa = dfa7();
		DFA dfaSwap = dfa.swap('b', 'a');
		/* This machine should have no b's */
		assertFalse(dfa.accepts("bbb"));
		assertTrue(dfa.accepts("aca"));
		assertFalse(dfa.accepts("abb"));
		assertTrue(dfa.accepts("cca"));
		assertFalse(dfa.accepts("ba"));
		System.out.println("dfa7Swap accept pass");
	}

	//------------------- dfa8 tests ----------------------//
	private DFA dfa8() {
		DFA dfa = new DFA();
		dfa.addSigma('c');
		dfa.addSigma('b');
		dfa.addSigma('a');

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.setStart("q0"));

		assertTrue(dfa.addState("q1"));
		assertTrue(dfa.addState("q0q2"));
		assertTrue(dfa.setFinal("q0q2"));
		assertTrue(dfa.addState("q2"));
		assertTrue(dfa.setFinal("q2"));
		assertTrue(dfa.addState("q0q1"));
		assertTrue(dfa.addState("q1q2"));
		assertTrue(dfa.setFinal("q1q2"));

		assertFalse(dfa.setFinal("0"));
		assertFalse(dfa.addState("q0q2"));

		assertTrue(dfa.addTransition("q0", "q1", 'b'));
		assertTrue(dfa.addTransition("q0", "q0q2", 'a'));
		assertTrue(dfa.addTransition("q0q2", "q0q2", 'a'));
		assertTrue(dfa.addTransition("q0q2", "q0q1", 'c'));
		assertTrue(dfa.addTransition("q0q2", "q1", 'b'));
		assertTrue(dfa.addTransition("q2", "q0q1", 'c'));
		assertTrue(dfa.addTransition("q0q1", "q1q2", 'c'));
		assertTrue(dfa.addTransition("q1q2", "q2", 'b'));
		assertTrue(dfa.addTransition("q1q2", "q0q1", 'c'));
		assertTrue(dfa.addTransition("q0q1", "q0q2", 'a'));


		assertFalse(dfa.addTransition("q1q0", "q3", 'b'));
		assertFalse(dfa.addTransition("c", "a", '0'));
		assertFalse(dfa.addTransition("q1q0", "q0q2", 'c'));

		return dfa;
	}

	@Test
	public void test8_1() {
		DFA dfa = dfa8();
		System.out.println("dfa8 instantiation pass");
	}

	@Test
	public void test8_2() {
		DFA dfa = dfa8();
		assertNotNull(dfa.getState("q0").getName(),"q0");
		assertTrue(dfa.isStart("q0"));
		assertNotNull(dfa.getState("q1").getName(),"q1");
		assertEquals(dfa.getState("q1").getName(),"q2");
		assertNotNull(dfa.getState("q1").getName(),"q0q2");
		assertEquals(dfa.getState("q1").getName(),"q0q1");
		assertNotNull(dfa.getState("q1").getName(),"q1q2");
		assertTrue(dfa.isFinal("q1q2"));
		assertTrue(dfa.isFinal("q2"));
		assertTrue(dfa.isFinal("q0q2"));
		assertEquals(dfa.getSigma(), Set.of('c','b','a'));

		System.out.println("dfa8 correctness pass");
	}

	@Test
	public void test8_3() {
		DFA dfa = dfa8();
		assertFalse(dfa.accepts("aaccbc"));
		assertTrue(dfa.accepts("aaa"));
		assertFalse(dfa.accepts("bbc"));
		assertTrue(dfa.accepts("bbccbcc"));
		assertFalse(dfa.accepts("aaaaaccbc"));

		System.out.println("dfa8 accept pass");
	}

	@Test
	public void test8_4() {
		DFA dfa = dfa8();

		String dfaStr = dfa.toString();
		String expStr = "Q={ q0 q1 q2 q0q2 q0q2 q1q2 }\n"
				+ "Sigma = { c b a }\n"
				+ "delta =\n"
				+ "			c		b		a\n"
				+ "q0		err		q1		q0q2\n"
				+ "q1		err		q2		err\n"
				+ "q2		err		err		q0q1\n"
				+ "q0q2		q0q2	q1		q0q1\n"
				+ "q0q1		q1q2	err		q0q2\n"
				+ "q1q2		q0q1	q1		err\n"
				+ "q0 = q0\n"
				+ "F={ q0q2 q2 q1q2 }\n";
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println("dfa8 toString pass");
	}



	@Test
	public void test8_5() {
		DFA dfa = dfa8();
		DFA dfaSwap = dfa.swap('b', 'a');
		//different DFA objects
		assertTrue(dfa != dfaSwap);
		//different DFA states
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertTrue(dfa.getState("q2") != dfaSwap.getState("q2"));
		assertTrue(dfa.getState("q0q2") != dfaSwap.getState("q0q2"));
		assertTrue(dfa.getState("q0q1") != dfaSwap.getState("q0q1"));
		assertTrue(dfa.getState("q1q2") != dfaSwap.getState("q1q2"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q0q2"), dfaSwap.isFinal("q0q2"));
		assertEquals(dfa.isFinal("q2"), dfaSwap.isFinal("q2"));
		assertEquals(dfa.isFinal("q1q2"), dfaSwap.isFinal("q1q2"));

		//ensure that the transitions of the original DFA don't change
		assertFalse(dfa.accepts("aaccbc"));
		assertTrue(dfa.accepts("aaa"));
		assertFalse(dfa.accepts("bbc"));
		assertTrue(dfa.accepts("bbccbcc"));
		assertFalse(dfa.accepts("aaaaaccbc"));

		System.out.println("dfa8Swap instantiation pass");
	}

	@Test
	public void test8_6() {
		DFA dfa = dfa8();
		DFA dfaSwap = dfa.swap('b', 'a');
		assertFalse(dfa.accepts("bbccac"));
		assertTrue(dfa.accepts("bbb"));
		assertFalse(dfa.accepts("aac"));
		assertTrue(dfa.accepts("aaccacc"));
		assertFalse(dfa.accepts("bbbbbccac"));
		System.out.println("dfa8Swap accept pass");
	}
}
