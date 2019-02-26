import java.util.ArrayList;
import java.util.List;

public class StringStackList {

  private List<String> stack;

  public StringStackList() {
    this.stack = new ArrayList<>();
  }

  public void push(String s) {
    stack.add(s);
  }

  public String pop() {
    return stack.remove(stack.size() - 1);
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }
}
