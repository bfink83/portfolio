package hw5;

import exceptions.EmptyException;
import java.util.Comparator;

/**
 * Priority queue implemented using our (unordered) abstract List,
 * specifically with a SentinelList implementation.
 *
 * @param <T> Element type.
 */
public class ListPriorityQueue<T extends Comparable<T>>
    implements PriorityQueue<T> {

  private List<T> list;
  private Comparator<T> cmp;

  /**
   * An unordered List PQ using the "natural" ordering of T.
   */
  public ListPriorityQueue() {
    this(new DefaultComparator<>());
  }

  /**
   * An unordered List PQ using the given comparator for T.
   *
   * @param cmp Comparator to use.
   */
  public ListPriorityQueue(Comparator<T> cmp) {
    list = new SentinelList<>();
    this.cmp = cmp;
  }

  @Override
  public void insert(T t) {
    list.insertFront(t);
  }

  @Override
  public void remove() throws EmptyException {
    if (list.empty()) {
      throw new EmptyException();
    }

    list.remove(bestPosition());
  }

  private Position<T> bestPosition() {
    Position<T> best = list.front();
    Position<T> curr = list.front();

    for (T val : list) {
      if (worse(best.get(), val)) {
        best = curr;
      }

      if (curr != list.back()) {
        curr = list.next(curr);
      }
    }
    return best;
  }

  private boolean worse(T t1, T t2) {
    //calling method worse() instead of less() is clearer to me
    return cmp.compare(t1, t2) < 0;
  }

  @Override
  public T best() throws EmptyException {
    if (list.empty()) {
      throw new EmptyException();
    }
    return bestPosition().get();
    //.get() is necessary to convert pos into generic
  }

  @Override
  public boolean empty() {
    return list.empty();
  }

  // The default comparator uses the "natural" ordering.
  private static class DefaultComparator<T extends Comparable<? super T>>
      implements Comparator<T> {
    public int compare(T t1, T t2) {
      return t1.compareTo(t2);
    }
  }
}
