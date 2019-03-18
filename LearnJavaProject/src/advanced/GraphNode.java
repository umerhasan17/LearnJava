package advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphNode<E> implements Cloneable {

  private E key;
  private List<GraphNode<E>> successors;

  public GraphNode() {
    successors = new ArrayList<GraphNode<E>>();
  }

  public int getNumberOfSuccessors() {
    return successors.size();
  }

  public GraphNode<E> getSuccessor(int successorIndex) {
    return successors.get(successorIndex);
  }

  public void addSuccessor(GraphNode<E> successor) {
    successors.add(successor);
  }

  public E getKey() {
    return key;
  }

  public void setKey(E key) {
    this.key = key;
  }

  @Override
  public GraphNode<E> clone() {
    Map<GraphNode<E>, GraphNode<E>> oldToNew = new HashMap<>();


    return internalClone();
  }

  private GraphNode<E> internalClone() {
    try {
      return (GraphNode) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }

}