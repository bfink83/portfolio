package hw5;

import exceptions.EmptyException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Priority queue implemented as a binary heap.
 *
 * <p> Use the ranked array representation of a binary heap!
 * Keep the arithmetic simple by sticking a null into slot 0 of the
 * ArrayList; wasting one reference is an okay price to pay for nicer
 * code.</p>
 *
 * @param <T> Element type.
 */
public class BinaryHeapPriorityQueue<T extends Comparable<T>>
    implements PriorityQueue<T> {

  private Comparator<T> cmp;
  private ArrayList<T> data;

  /**
   * A binary heap using the "natural" ordering of T.
   */
  public BinaryHeapPriorityQueue() {
    this(new DefaultComparator<>());
  }

  /**
   * A binary heap using the given comparator for T.
   *
   * @param cmp Comparator to use.
   */
  public BinaryHeapPriorityQueue(Comparator<T> cmp) {
    this.cmp = cmp;
    this.data = new ArrayList<T>();
    this.data.add(null); //sentinel spot at index zero
  }

  private boolean worse(int i, int j) {
    return cmp.compare(data.get(i), data.get(j)) < 0;
    //naming method worse() instead of less() is clearer to me
  }

  @Override
  public void insert(T t) {
    int i = data.size();
    data.add(t);

    while (i > 1 && worse(i / 2, i)) { //swaps parent is worse
      data.set(i, data.get(i / 2));
      data.set(i / 2, t);
      i /= 2;
    }
  }

  @Override
  public void remove() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }

    //int lastIndex = data.size() - 1;   //variable gets last element added
    //T topVal = data.get(data.size() - 1);
    data.set(1, data.get(data.size() - 1));
    //moves last element to front, removing best value

    //^commented out initial variable declarations to comply with checkstyle

    int curr = 1;

    //now the value added to index 1 must be sunk down to where it belongs
    while (data.size() - 1 > curr * 2) {   //makes sure parent node has children
      int child = curr * 2;

      if (worse(child, child + 1)) {  //comps children 1st to swap w better one
        child++;    //increments child if left child is worse than right
      }
      if (worse(child, data.size() - 1)) { //if child worse than orig last value
        break;                  //loop is terminated because value is fully sunk
      }
      data.set(curr, data.get(child));
      curr = child; //if better, moves child node up
      //swap isn't completed till loop is terminated to improve efficiency
    }
    data.set(curr, data.get(data.size() - 1));   //final step of swap
    data.remove(data.size() - 1);   //finally, remove extra value at end
    //another option is to remove the extra value at the beginning
    //and perform full swap within each iteration of the loop
  }

  @Override
  public T best() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return data.get(1);
  }

  @Override
  public boolean empty() {
    return data.size() == 1;  //accounting for sentinel spot
  }

  // The default comparator uses the "natural" ordering.
  private static class DefaultComparator<T extends Comparable<? super T>>
      implements Comparator<T> {
    public int compare(T t1, T t2) {
      return t1.compareTo(t2);
    }
  }

}
