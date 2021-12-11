package day10;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class Day10Part1Test {

	Day10 day10 = new Day10();
	
	@Test
	void use_different_input_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day10.setFileToUse(new File(fileName.getPath()));
		day10.getInputs();
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		ArrayList<String> expectedLine1 = new ArrayList<>(Arrays.asList("[", "(", "{", "(", "<", "(", "(", ")", ")", "[", "]", ">", "[", "[", "{", "[", "]", "{", "<", "(", ")", "<", ">", ">"));
		expected.add(expectedLine1);
		ArrayList<String> expectedLine2 = new ArrayList<>(Arrays.asList("[", "(", "(", ")", "[", "<", ">", "]", ")", "]", "(", "{", "[", "<", "{", "<", "<", "[", "]", ">", ">", "("));
		expected.add(expectedLine2);
		ArrayList<String> expectedLine3 = new ArrayList<>(Arrays.asList("{", "(", "[", "(", "<", "{", "}", "[", "<", ">", "[", "]", "}", ">", "{", "[", "]", "{", "[", "(", "<", "(", ")", ">"));
		expected.add(expectedLine3);
		ArrayList<String> expectedLine4 = new ArrayList<>(Arrays.asList("(", "(", "(", "(", "{", "<", ">", "}", "<", "{", "<", "{", "<", ">", "}", "{", "[", "]", "{", "[", "]", "{", "}"));
		expected.add(expectedLine4);
		ArrayList<String> expectedLine5 = new ArrayList<>(Arrays.asList("[", "[", "<", "[", "(", "[", "]", ")", ")", "<", "(", "[", "[", "{", "}", "[", "[", "(", ")", "]", "]", "]"));
		expected.add(expectedLine5);
		ArrayList<String> expectedLine6 = new ArrayList<>(Arrays.asList("[", "{", "[", "{", "(", "{", "}", "]", "{", "}", "}", "(", "[", "{", "[", "{", "{", "{", "}", "}", "(", "[", "]"));
		expected.add(expectedLine6);
		ArrayList<String> expectedLine7 = new ArrayList<>(Arrays.asList("{", "<", "[", "[", "]", "]", ">", "}", "<", "{", "[", "{", "[", "{", "[", "]", "{", "(", ")", "[", "[", "[", "]"));
		expected.add(expectedLine7);
		ArrayList<String> expectedLine8 = new ArrayList<>(Arrays.asList("[", "<", "(", "<", "(", "<", "(", "<", "{", "}", ")", ")", ">", "<", "(", "[", "]", "(", "[", "]", "(", ")"));
		expected.add(expectedLine8);
		ArrayList<String> expectedLine9 = new ArrayList<>(Arrays.asList("<", "{", "(", "[", "(", "[", "[", "(", "<", ">", "(", ")", ")", "{", "}", "]", ">", "(", "<", "<", "{", "{"));
		expected.add(expectedLine9);
		ArrayList<String> expectedLine10 = new ArrayList<>(Arrays.asList("<", "{", "(", "[", "{", "{", "}", "}", "[", "<", "[", "[", "[", "<", ">", "{", "}", "]", "]", "]", ">", "[", "]", "]"));
		expected.add(expectedLine10);

		assertEquals(expected, day10.getOpenCloseInputs());
	}
	
	MyStack stack;
	@BeforeEach
	public void setup() {
		stack = new MyStack();
	}
	
	@Test
	void myStack_starts_empty() throws Exception {
		assertTrue(stack.isEmpty());
	}
	@Test
	void after_pushing_element_myStack_is_not_empty() throws Exception {
		stack.push("{");
		assertTrue(!stack.isEmpty());
	}
	@Test
	void after_one_push_one_pop_myStack_is_empty_again() throws Exception {
		stack.push("{");
		stack.pop();
		assertTrue(stack.isEmpty());
	}
	@Test
	void after_two_push_and_one_pop_myStack_is_not_empty() throws Exception {
		stack.push("{");
		stack.push("(");
		stack.pop();
		assertTrue(!stack.isEmpty());
	}
	@Test
	void after_push_x_will_pop_x() throws Exception {
		String x = "(";
		stack.push(x);
		assertEquals(x, stack.pop());
	}
	@Test
	void can_not_pop_empty_myStack() throws Exception {
		Assertions.assertThrows(RuntimeException.class, () -> stack.pop());
	}
	@Test
	void can_not_push_more_than_max_elements_myStack() throws Exception {
		for(int x=0; x<MyStack.MAX_ELEMENTS; x++) {
			stack.push("(");
		}
		Assertions.assertThrows(RuntimeException.class, () -> stack.push("("));
	}
	@Test
	void after_pushing_y_then_x_will_pop_x_then_y() throws Exception {
		String x = "(";
		String y = ")";
		stack.push(x);
		stack.push(y);
		assertEquals(y, stack.pop());
		assertEquals(x, stack.pop());
	}
	
	@Test
	void processInput_will_push_any_opens_and_pop_for_any_closes_error_anything_else() throws Exception {
		MyStack mockStack = Mockito.mock(MyStack.class);
		URL fileName = getClass().getResource("SampleInput.txt");
		day10.setFileToUse(new File(fileName.getPath()));
		day10.getInputs();
		day10.setMyStack(mockStack);
		
		day10.processInput("(");
		Mockito.verify(mockStack).push("(");
		day10.processInput("[");
		Mockito.verify(mockStack).push("[");
		day10.processInput("{");
		Mockito.verify(mockStack).push("{");
		day10.processInput("<");
		Mockito.verify(mockStack).push("<");
		
		Mockito.when(mockStack.pop()).thenReturn("(");
		day10.processInput(")");
		Mockito.when(mockStack.pop()).thenReturn("[");
		day10.processInput("]");
		Mockito.when(mockStack.pop()).thenReturn("{");
		day10.processInput("}");
		Mockito.when(mockStack.pop()).thenReturn("<");
		day10.processInput(">");
		Mockito.verify(mockStack, Mockito.times(4)).pop();
		
		Assertions.assertThrows(RuntimeException.class, () -> day10.processInput("a"));
	}
	
	@Test
	void when_processInput_if_thing_popped_is_not_the_corresponding_open_add_to_exceptionPops() throws Exception {
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("}");
		day10.processInput("(");
		day10.processInput("}");
		assertEquals(expected, day10.getExceptionPops());
	}
	
	@Test
	void verify_each_exceptionPop_is_worth_correct_points() throws Exception {
		assertEquals(3, day10.getPointsForException(")"));
		assertEquals(57, day10.getPointsForException("]"));
		assertEquals(1197, day10.getPointsForException("}"));
		assertEquals(25137, day10.getPointsForException(">"));
	}
	
	@Test
	void part1_sample_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day10.setFileToUse(new File(fileName.getPath()));
		day10.getInputs();
		for (ArrayList<String> inputLine : day10.getOpenCloseInputs()) {
			int curExceptionCount = day10.getExceptionPops().size();
			int curInputIndex = 0;
			do {
				String curInput = inputLine.get(curInputIndex);
				day10.processInput(curInput);
				curInputIndex++;
			}while(day10.getExceptionPops().size()==curExceptionCount && curInputIndex<inputLine.size());
			day10.setMyStack(new MyStack());
		}
		assertEquals(5, day10.getExceptionPops().size());
		int totalScore = 0;
		for (String exceptionPop : day10.getExceptionPops()) {
			totalScore+=day10.getPointsForException(exceptionPop);
		}
		assertEquals(26397, totalScore);
	}
	
	@Test
	void part1_for_real() throws Exception {
		day10 = new Day10();
		day10.getInputs();
		for (ArrayList<String> inputLine : day10.getOpenCloseInputs()) {
			int curExceptionCount = day10.getExceptionPops().size();
			int curInputIndex = 0;
			do {
				String curInput = inputLine.get(curInputIndex);
				day10.processInput(curInput);
				curInputIndex++;
			}while(day10.getExceptionPops().size()==curExceptionCount && curInputIndex<inputLine.size());
			day10.setMyStack(new MyStack());
		}
		System.out.println("There are [" + day10.getExceptionPops().size() + "] exceptions");
//		assertEquals(5, day10.getExceptionPops().size());
		int totalScore = 0;
		for (String exceptionPop : day10.getExceptionPops()) {
			totalScore+=day10.getPointsForException(exceptionPop);
		}
		System.out.println("Total score is: " + totalScore);
//		assertEquals(26397, totalScore);
	}
}
