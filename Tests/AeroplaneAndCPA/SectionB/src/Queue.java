public class Queue<T> implements QueueInterface<T>{

	private Node<T> first;
	private Node<T> last;
	
	public boolean isEmpty() {
		return last == null;
	}
	
	//post: Adds the given item to the queue
	public void enqueue(T item) {
		if (isEmpty()) {
			last = new Node<>(item);
			first = last;
		} else {
			last.setNext(new Node<>(item));
			last = last.getNext();
		}
	}
	
	//post: Removes and returns the head of the queue. It throws an 
	//      exception if he queue is empty.
	public T dequeue() throws QueueException {
		if (isEmpty()) {
			throw new QueueException("Queue empty");
		} else {
			T result = first.getItem();
			if (first.getNext() == null) {
				last = null;
			} else {
				Node<T> next = first.getNext();
				first = next;
			}
			return result;
		}
	}
	
}
