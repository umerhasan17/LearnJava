public class StringStackArray {

  private String[] stack;
  private int head;

  // Creates an empty string stack
  public StringStackArray() {
    stack = new String[100];
    head = 0;
  }

  // If the stack is full, does nothing.
  // Otherwise, pushes the given String on to the top of the stack
  public void push(String s) {
    if (head == 99) {

    } else {
      stack[head] = s;
      head ++;
    }
  }

  // If the stack is empty, leaves the stack unchanged and returns
  // null.  Otherwise, removes the string that is on the top of
  // the stack and returns it
  public String pop() {
    if (head == 0) {
      return null;
    } else {
      String ans = stack[head - 1];
      stack[head - 1] = null;
      head --;
      return ans;
    }
  }

  // Returns true iff the stack is empty
  public boolean isEmpty() {
    return head == 0;
  }

}
