package generalmatrices.matrix;

import java.util.List;

public class Matrix<T> {

  private final List<T> elements;
  private final int order;

  public Matrix(List<T> elements) {
    if (elements.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.elements = elements;
    Double sqrt = Math.sqrt(Integer.valueOf(elements.size()).doubleValue());
    this.order = sqrt.intValue();
  }

  public T get(int row, int col) {
    return elements.get(row * getOrder() + col);
  }

  public int getOrder() {
    return order;
  }

  @Override
  public String toString() {
    String res = "[";
    for (int i = 0; i < getOrder(); i ++) {
      res += "[";
      for (int j = 0; j < getOrder(); j ++) {
        if (j == getOrder() - 1) {
          res += get(i, j);
        } else {
          res += get(i, j) + " ";
        }
      }
      res += "]";
    }
    res += "]";
    return res;
  }
}
