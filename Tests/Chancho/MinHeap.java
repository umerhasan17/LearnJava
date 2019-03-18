/**
 * This class implements a min-heap abstract data type (as described by the
 * generic interface IMinHeap<T extends Comparable<T>>) using a fixed array of
 * size MinHeap.MAXIMUM_HEAP_SIZE.
 */
public class MinHeap<T extends Comparable<T>> implements IMinHeap<T> {

  public static final int MAX_HEAP_SIZE = 52;
  private final T[] items;
  private int size;

  public MinHeap() {
    items = (T[]) new Comparable[MAX_HEAP_SIZE];
    size = 0;
  }

  @Override
  public void add(T element) throws HeapException {
    if (size == MAX_HEAP_SIZE) {
      throw new HeapException("Heap full");
    }
    items[size] = element;
    int i = size;
    while (i > 0 && items[i].compareTo(items[(i-1) / 2]) < 0) {
      swap(i, (i -1) / 2);
      i = (i - 1) / 2;
    }
    size ++;
  }

  @Override
  public T removeMin() {
    if (isEmpty()) {
      throw new HeapException("Heap empty");
    }
    T item = getMin();
    size --;
    items[0] = items[size];
    rebuildHeap(0);
    return item;
  }

  @Override
  public T getMin() {
    if (isEmpty()) {
      throw new HeapException("Heap empty");
    }
    return items[0];
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  private void swap (int i, int j) {
    T temp = items[i];
    items[i] = items[j];
    items[j] = temp;
  }

  private void rebuildHeap(int i) {
    int left = 2*i + 1;
    int right = 2*i + 2;
    int compareTo;
    if (right < size) {
      compareTo = items[left].compareTo(items[right]) <= left ? left : right;
    } else {
      compareTo = left;
    }
    if (left < size && items[i].compareTo(items[compareTo]) < 0) {
      swap(i, compareTo);
      rebuildHeap(compareTo);
    }
  }

}