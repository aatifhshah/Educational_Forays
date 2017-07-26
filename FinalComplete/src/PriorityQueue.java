
public class PriorityQueue {
	
	
	private Crimes[] heap;
	private int lastIndex;
	private static final int DEFAULT_INITIAL_CAPACITY = 25;
	private int IC;
	private int entries;
	Crimes chicken = new Crimes();
	
	public PriorityQueue(){
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public PriorityQueue(int initialCapacity) {
		IC = initialCapacity;
		@SuppressWarnings("unchecked")
		Crimes[] tempHeap = new Crimes[initialCapacity + 1];
		heap = tempHeap;
		
		for(int i =0; i<heap.length;i++){
			heap[i] = new Crimes();
		}
		lastIndex = 0;
	}
	
	public void push(Crimes newEntry){
		lastIndex++;
		ensureCapacity();
		int newIndex = lastIndex;
		int parentIndex = newIndex/2;

		while((parentIndex > 0) && newEntry.compareTo(heap[parentIndex].getnum()) > 0){
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex/2;
			
		}
		
		heap[newIndex] = newEntry;
		entries++;
	}
	
	public String pop(){
		Crimes root = null;
		
		if(entries != 0){
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
			entries--;
			return root.getCrime();
		}
		else{
			return "End";
		}		
	}
	
	public Crimes getMax(){
		
		Crimes root = null;
		if(!isEmpty()){
			root = heap[1];
		}
		return root;
		
	}
	
	public boolean isEmpty(){
		if(lastIndex == 0){
			return true;
		}
		return false;
	}
	
	public int getSize(){
		return lastIndex;
	}
	
	public boolean ensureCapacity(){
		if(lastIndex == IC ){
			return false;
		}
		return true;
	}
	
	private void reheap(int rootIndex){
		boolean done = false;
		Crimes orphan = heap[rootIndex];
		int leftChildIndex = 2*rootIndex;
		
		while(!done && (leftChildIndex <= lastIndex)){
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			
			if((rightChildIndex <= lastIndex)&&heap[rightChildIndex].compareTo(heap[largerChildIndex].getnum())>0){
				largerChildIndex = rightChildIndex;
			}
		
		
			if (orphan.compareTo(heap[largerChildIndex].getnum()) < 0){
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
			}
			else
				done = true;
	
		}
		
		heap[rootIndex] = orphan;
	}
}
