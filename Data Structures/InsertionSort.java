package hw3;


/**
 * The Insertion Sort algorithm, with minimizing swaps optimization.
 *
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {


  @Override
  public void sort(Array<T> array) {
    for (int sortIndex = 1; sortIndex < array.length(); ++sortIndex) {
      /*for (int j = sortIndex; j > 0; --j) {
        if (array.get(j).compareTo(array.get(j - 1)) < 0)  {
          T temp = array.get(j - 1);

          array.put(j - 1, array.get(j));

          array.put(j, temp);
        }
      }
      //bubble sort commented out
      */
      int swapIndex = binarySearch(sortIndex, array);

      if (swapIndex != sortIndex) {
        T temp = array.get(swapIndex);
        array.put(swapIndex, array.get(sortIndex));
        array.put(sortIndex, temp);
      }
    }
  }

  private int binarySearch(int index, Array<T> array) {
    int leftStart = 0;
    int rightEnd = index - 1;

    while (leftStart <= rightEnd) {
      int mid = (int)(java.lang.Math.ceil((leftStart + (rightEnd - 1)) / 2.0));
      //int mid = (leftStart + (rightEnd - 1)) / 2;

      if (array.get(index).compareTo(array.get(mid)) >= 0) {
        if (array.get(index).compareTo(array.get(mid + 1)) < 0) {
          return mid + 1;
        } else if (array.get(index).compareTo(array.get(mid + 1)) >= 0) {
          leftStart = mid + 1;
        }
      } else if (array.get(index).compareTo(array.get(mid)) <= 0) {
        if (mid == 0) {
          return 0;
        } else if (array.get(index).compareTo(array.get(mid - 1)) >= 0) {
          return mid;
        } else if (array.get(index).compareTo(array.get(mid - 1)) <= 0) {
          rightEnd = mid - 1;
        }
      }
    }
    return index;
  }


  @Override
  public String name() {
    return "Insertion Sort";
  }
}


