# Project 1: Modeling Deterministic Finite Automata

* Author: Joe Lathrop, Clara Arnold
* Class: CS361
* Semester: Fall 2023

## Overview

This is a Java program that models a deterministic finite automaton. We implemented the DFA.java, 
DFAState.java, and DFATest.java classes by using linked hash maps and linked hash sets to organize
the DFA's language, states, and transitions.

## Reflection

We began this project by creating a GitHub repository so we could work in an agile environment. Once we 
had that set up we started with understanding the DFATest.java tests and drawing out each of the DFA 
systems for the three given tests. We then added tests for transitions that lead to qerr, for a system 
where the start state is the same as the final state, and for a system that has more than two symbols in 
its alphabet. From there we began working on DFA.java and DFAState.java. 

We struggled a bit with the accepts() method due to not fully understanding what a State includes in
this project. Once we understood that a State is an object that holds a Hashmap within it that includes
all its transitions we were able to figure out accepts(). We also struggled a bit with swap() because we
haven't had to use the deep copying technique for a few semesters so we had to re-learn that aspect in 
order to complete the method. Another method that caused us a bit of a stress was the toString() because
all of the given tests had different spacing when creating the toString(). We learned that the whitespace 
will be ignored when the program is tested.

Overall, we really enjoyed this project because it aligns with what we've learned in class and it wasn't 
too challenging to think about because we have been doing so much practice with DFA's. We ran into a few 
issues but with lots of time and thought we were able to figure them out!

## Compiling and Using

To run the program, we will be directly running the test class. Run the following commands in the onyx terminal:

- $javac -d out/production/p1Files fa/dfa/*.java test/dfa/*.java

## Sources used

- Lecture notes, slides
- Set documentation
- Map documentation