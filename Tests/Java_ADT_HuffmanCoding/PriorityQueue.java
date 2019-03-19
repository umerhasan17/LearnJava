public class PriorityQueue<E extends Comparable<E>> implements PriorityQueueInterface<E>{

	private E[] items;    //a heap of HuffmanTrees
	private final static int max_size = 256;
	private int size;    //number of HuffManTrees in the heap.
	
	
	public PriorityQueue( ) {
        // constructor which creates an empty heap
		items = (E[]) new Comparable[max_size];
		size = 0;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int getSize(){
		return size;
	}

	public E getMin(){
		E root = null;
		if (!isEmpty()) root = items[0];
		return root;
	}
	
	public void add(E newEntry) throws PriorityQueueException{
	// post: Adds a new entry to the priority queue according to 
        // the priority value.
	if (size < max_size) {
		items[size] = newEntry;
		size ++;
		int i = size - 1;
		while (i > 0 && items[i].compareTo(items[(i - 1) / 2]) < 0) {
			swap(i, (i - 1) / 2);
			i --;
		}
	} else {
		throw new PriorityQueueException("Queue full.");
	}
	}
 				
 	public E removeMin(){
	// post: Removes the minimum valued item from the PriorityQueue
		E root = null;
		if (!isEmpty()){
			root = items[0];
			items[0] = items[size-1];
			size--;
			heapRebuild(0);
		}
		return root;
	}
	
	private void heapRebuild(int root) {
		// Rebuild heap to keep it ordered
		int i = root;
		int left = i * 2 + 1;
		int right = i * 2 + 2;
		int minChild = -1;
		while (right < size &&
					(items[right].compareTo(items[i]) < 0 ||
					 items[left].compareTo(items[i]) < 0)) {
			if (items[right].compareTo(items[left]) < 0) {
				minChild = right;
			} else {
				minChild = left;
			}
			swap(i, minChild);
			i = minChild;
			left = i * 2 + 1;
			right = i * 2 + 1;
		}
		if (left < size) {
			if (items[left].compareTo(items[i]) < 0) {
				swap(i, left);
			}
		}
	}


	private void swap(int i, int j) {
		E temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}
}
