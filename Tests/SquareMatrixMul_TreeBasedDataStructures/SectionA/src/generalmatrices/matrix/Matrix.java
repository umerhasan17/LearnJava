package generalmatrices.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

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

  public Matrix<T> sum(Matrix<T> other, BinaryOperator<T> elementSum) {
    List<T> matrix = new ArrayList<>();
    for (int i = 0; i < getOrder(); i ++) {
      for (int j = 0; j < getOrder(); j ++) {
        matrix.add(elementSum.apply(this.get(i, j), other.get(i, j)));
      }
    }
    return new Matrix<>(matrix);
  }

  public Matrix<T> product(Matrix<T> other, BinaryOperator<T> elementSum,
                             BinaryOperator<T> elementProduct) {
    List<T> matrix = new ArrayList<>();

    for (int i = 0; i < getOrder(); i ++) {
      for (int j = 0; j < getOrder(); j++) {
        T accumulator = null;
        for (int k = 0; k < getOrder(); k++) {
          T product = elementProduct.apply(this.get(i, k), other.get(k, j));
          if (accumulator == null) {
            accumulator = product;
          } else {
            accumulator = elementSum.apply(accumulator, product);
          }
        }
        matrix.add(accumulator);
      }
    }



    return new Matrix<>(matrix);
  }
}
