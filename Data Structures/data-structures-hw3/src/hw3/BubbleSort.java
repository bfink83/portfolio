package hw3;


/**
 * The Bubble Sort algorithm with the optimized "quick" break to exit
 * if the array is sorted.
 *
 * @param <T> The type being sorted.
 */
public final class BubbleSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

  @Override
  public void sort(Array<T> array) {
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (int i = 0; i < array.length() - 1; ++i) {
        T thisVal = array.get(i);
        T nextVal = array.get(i + 1);
        if (thisVal.compareTo(nextVal) > 0) {
          swapped = true;
          array.put(i, nextVal);
          array.put(i + 1, thisVal);
        }
      }
    }
  }

  @Override
  public String name() {
    return "Bubble Sort";
  }
}
