package hw3;

import java.util.Iterator;

/**
 * A utility class for sorting related operations.
 */
public final class Util {

  private Util() {
    // This class is not meant to be instantiated!
  }

  /**
   * Checks the elements in <pre>arr</pre> are sorted in <b>ascending order</b>.
   *
   * @param arr input array to check if it is sorted.
   * @param <T> base type of array (must be Comparable)
   * @return <pre>true</pre> if <pre>arr</pre> is sorted.
   */
  public static <T extends Comparable<T>> boolean isSorted(Array<T> arr) {
    // Use iterator so to not affect the operation count in arr.
    Iterator<T> it = arr.iterator();
    T prev = it.hasNext() ? it.next() : null;
    while (it.hasNext()) {
      T current = it.next();
      if (prev.compareTo(current) > 0) {
        return false;
      }
      prev = current;
    }
    return true;
  }
}
