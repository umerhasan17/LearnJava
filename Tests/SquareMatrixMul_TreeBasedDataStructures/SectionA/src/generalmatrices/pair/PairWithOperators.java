package generalmatrices.pair;

import generalmatrices.operators.RingElement;

public class PairWithOperators extends Pair implements RingElement {

  public PairWithOperators(Integer coordX, Integer coordY) {
    super(coordX, coordY);
  }

  @Override
  public PairWithOperators sum(Object other) {
    PairWithOperators otherPair = (PairWithOperators) other;
    return new PairWithOperators(
            this.getCoordX() + otherPair.getCoordX(),
            this.getCoordY() + otherPair.getCoordY() );
  }

  @Override
  public PairWithOperators product(Object other) {
    PairWithOperators otherPair = (PairWithOperators) other;
    return new PairWithOperators(
            this.getCoordX() * otherPair.getCoordX(),
            this.getCoordY() * otherPair.getCoordY() );
  }

}
