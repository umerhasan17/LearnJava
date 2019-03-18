public class Demo {

  public static void main(String[] args) {
    StringStack stack1 = new StringStackArray();
    StringStack stack2 = new StringStackList();

    stack1.push("Hello");
    stack1.push("World");
    stack1.push("It's");
    stack1.push("Java");

    transferStacks(stack2, stack1);

    System.out.println(stack1.isEmpty());
  }

  public static void transferStacks(StringStack dst, StringStack src) {
    while (!src.isEmpty()) {
      dst.push(src.pop());
    }
  }

}
