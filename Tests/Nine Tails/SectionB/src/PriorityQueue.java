import java.util.Iterator;

/**
  * You must implement the <code>add</code> and <code>PQRebuild</code> methods.
  *
  */

public class PriorityQueue<T extends Comparable<T>> implements
		PriorityQueueInterface<T> {

	private T[] items;             //a minHeap of elements T
	private final static int max_size = 512;
	private int size;              // number of elements in the minHeap.


	public PriorityQueue() {
		items = (T[]) new Comparable[max_size];
		size = 0;
	}

	/**
	 * Returns true if the priority queue is empty. False otherwise.
	*/
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the priority queue.
	*/
	public int getSize() {
		return size;
	}

	/**
	 * Returns the element with highest priority or null if the priority.
	 * queue is empty. The priority queue is left unchanged
	 */
	public T peek() {
		T root = null;
		if (!isEmpty())
			root = items[0];
		return root;
	}

	/**
	 * <strong>Implement this method for Question 2</strong>
	 * Adds a new entry to the priority queue according to the priority value. 
	 * 
	 * @param newEntry
	 *		the new element to add to the priority queue 
	 * @throws PQException if the priority queue is full
	 */
	public void add(T newEntry) throws PQException {
	    if (size < max_size) {
				items[size] = newEntry;
				int currentIndex = size;
				size ++;
				while (currentIndex > 0 && items[currentIndex].compareTo(items[(currentIndex - 1) / 2]) < 0) {
					swap(currentIndex, (currentIndex - 1) / 2);
					currentIndex--;
				}
			} else {
	    	throw new PQException("Queue full.");
			}
	}

	/**
	 * Removes the element with highest priority.
	 */
	public void remove() {
		if (!isEmpty()) {
			items[0] = items[size - 1];
			size--;
			PQRebuild(0);
		}
	}

	/**
	 *
	 * <strong>Implement this method for Question 2</strong>
	 */
	private void PQRebuild(int root) {
//	    int left = 2 * root + 1;
//	    int right = 2 * root + 2;
//	    // check left
//			if (items[left] != null) {
//				while (left < max_size && items[left].compareTo(items[root]) < 0) {
//					PQRebuild(left);
//					swap(left, root);
//				}
//			}
//			// check right
//			if (items[right] != null) {
//				while (right < max_size && items[right].compareTo(items[root]) < 0) {
//					PQRebuild(right);
//					swap(right, root);
//				}
//			}

		int i = root;
		while ((2 * i + 2) < size &&
						(items[i].compareTo(items[2 * i + 1]) > 0 || items[i].compareTo(items[2 * i + 2]) > 0)) {
			int minChildIndex;
			if (items[2 * i + 1].compareTo(items[2 * i + 2]) <= 0) {
				minChildIndex = 2 * i + 1;
			} else {
				minChildIndex = 2 * i + 2;
			}
			T itemsI = items[i];
			items[i] = items[minChildIndex];
			items[minChildIndex] = itemsI;
			i = minChildIndex;
		}
		if ((2 * i + 1) < size && items[i].compareTo(items[2 * i + 1]) > 0) {
			T itemsI = items[i];
			items[i] = items[2 * i + 1];
			items[2 * i + 1] = itemsI;
		}
	}

	private void swap(int i, int j) {
		T temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}

	public Iterator<Object> iterator() {
		return new PQIterator<Object>();
	}

	private class PQIterator<T> implements Iterator<Object> {

		private int position = 0;

		public boolean hasNext() {
			return position < size;
		}

		public Object next() {
			Object temp = items[position];
			position++;
			return temp;
		}

		public void remove() {
			throw new IllegalStateException();
		}

	}

	/**
	 * Returns a priority queue that is a clone of the current priority queue.
	*/
	public PriorityQueue<T> clone() {
		PriorityQueue<T> clone = new PriorityQueue<T>();
		clone.size = this.size;
		clone.items = (T[]) new Comparable[max_size];
		System.arraycopy(this.items, 0, clone.items, 0, size);
		return clone;
	}
	
}
