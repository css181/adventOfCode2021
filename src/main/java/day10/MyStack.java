package day10;


public class MyStack {
	public static int MAX_ELEMENTS = 50;

	int size = 0;
	String[] elements = new String [MAX_ELEMENTS];
	public boolean isEmpty() {
		return size==0;
	}

	public void push(String string) {
		if(size>=MAX_ELEMENTS) {
			throw new RuntimeException("can't push more than " + MAX_ELEMENTS);
		}
		elements[size++] = string;
	}

	public String pop() {
		if(size<=0 ) {
			throw new RuntimeException("can't pop empty stack");
		}
		return elements[--size];
	}

}
