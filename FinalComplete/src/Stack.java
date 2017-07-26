
import java.util.Arrays;

public class Stack<T> implements StackInterface<T> {
	private T[] stack; // array of stack entries
	private int topIndex = 0; // index of top entry
	private static final int CAPACITY = 5;

	public Stack() {
		this(CAPACITY);
	} // end default constructor

	public Stack(int initialCapacity) {
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity];
		stack = tempStack;
	} // end constructor
	

	public void push(T newEntry) {
		ensureCapacity();
		stack[topIndex]= newEntry;
		topIndex++;
	} // end push

	
	private void ensureCapacity() {
		if (isFull()) // if array is full,
			stack = Arrays.copyOf(stack, 2 * stack.length);
	} // end ensureCapacity
	
	
	public T pop() {
		T top = null;
		if (!isFull()) {
			top = stack[topIndex];
			stack[topIndex]=null;
			topIndex--;
		} // end if
		return top;
	} // end pop
	
	public int entries(){
		return topIndex;
	}


	public T peek() {
		T top = null;
		if (!isFull()){
			top = stack[topIndex];
		}
		return top;
	} // end peek
	
	
	public boolean isFull() {
		if(topIndex == stack.length)
			return true;
		
		return false;
	}

	public void clear() {
		for(int i = 0; i<=stack.length;i++){
			this.pop();
		}		
	}
}