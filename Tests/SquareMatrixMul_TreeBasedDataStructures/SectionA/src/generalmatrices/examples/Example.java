package generalmatrices.examples;

import generalmatrices.matrix.Matrix;
import generalmatrices.pair.PairWithOperators;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Example {

  public static Matrix<PairWithOperators> multiplyPairMatrices(
        List<Matrix<PairWithOperators>> matrices) {
    while (matrices.size() > 1) {
      Matrix matrix1 = matrices.remove(0);
      Matrix matrix2 = matrices.remove(0);
      Matrix product3 = matrix1.product(matrix2, )
      Matrix product2 = matrix1.product(matrix2,
              (PairWithOperators pwo1, PairWithOperators pwo2) -> {
                return pwo1.sum(pwo2);
              },  )
      Matrix product = matrix1.product(matrix2, new BinaryOperator() {
                @Override
                public Object apply(Object o, Object o2) {
                  return ((PairWithOperators) o).sum((PairWithOperators) o2);
                }
              },
              new BinaryOperator() {
                @Override
                public Object apply(Object o, Object o2) {
                  return ((PairWithOperators) o).product((PairWithOperators) o2);
                }
              });
      matrices.add(0, product);
    }
    return matrices.get(0);
  }


}
