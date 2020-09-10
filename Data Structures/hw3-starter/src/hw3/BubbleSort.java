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
    boolean swapped = true; //starts at true to allow for entry to loop
    while (swapped) { //break early if no swaps made
      swapped = false;
      T thisVal;
      T nextVal;
      T lastVal = array.get(0); // initialize before any swaps made
      for (int i = 0; i < array.length() - 1; ++i) {
        thisVal = lastVal;
        nextVal = array.get(i + 1);
        if (thisVal.compareTo(nextVal) > 0) {
          swapped = swap(array, thisVal, nextVal, i); //gets true if swapped
          lastVal = thisVal; //if swap, lastVal will be thisVal
        } else {
          lastVal = nextVal; //if no swap, lastVal will be nextVal
        }
      }
    }
  }

  private boolean swap(Array<T> array, T thisVal, T nextVal, int i) {
    array.put(i, nextVal);
    array.put(i + 1, thisVal);
    return true; //let sort know swapped (for early break)
  }

  @Override
  public String name() {
    return "Bubble Sort";
  }
}
