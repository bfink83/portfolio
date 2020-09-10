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

      //find index to move current value to (binary(active) and linear searches)
      int swapIndex = binarySearch(sortIndex, array);
      //int swapIndex = linearSearch(sortIndex, array);

      //create temp to hold current value
      T temp = array.get(sortIndex);

      //push each value in array over
      //doesnt swap if returned index is the same as current value's index
      if (swapIndex != sortIndex) {
        for (int i = sortIndex; i > swapIndex; i--) {
          array.put(i, array.get(i - 1));
        }
        //insert current value in the proper index of sorted part of array
        array.put(swapIndex, temp);
      }
    }
  }

  private int linearSearch(int index, Array<T> array) {
    //set the end of sorted part of array to index before current
    int rightEnd = index - 1;

    //compare current value to each value in sorted part of array until
    //proper index is found
    for (int i = rightEnd; i >= 0; i--) {
      T indexVal = array.get(index);
      T currVal = array.get(i);
      if (indexVal.compareTo(currVal) >= 0) {
        return i + 1;
      }
    }
    return 0;
  }

  private int binarySearch(int index, Array<T> array) {
    //create variables for both left and right side of sorted array
    //as either can change in binary search depending on which half contains
    //the value
    int leftStart = 0;
    int rightEnd = index - 1;

    //binary search implementation, checks middle value then determines
    //if value is higher or lower and repeats for corresponding half of sorted
    // part of array
    while (leftStart <= rightEnd) {
      int mid = (int) (java.lang.Math.ceil((leftStart + (rightEnd - 1)) / 2.0));

      //find method actually compares current value to mid
      //implemented for checkstyle purposes
      int returnIndex = find(index, mid, array);

      //necessary because find method doesn't have direct access to
      //rightEnd and leftStart variables
      if (returnIndex == -1) {
        rightEnd = mid - 1;
      } else if (returnIndex == -2) {
        leftStart = mid + 1;
      } else {
        return returnIndex;
      }
    }
    return index;
  }

  //compares mid and mid + 1 to current value and if mid is in between
  //returns mid + 1
  //else returns marker to use outside of method to change end of sorted array
  //to cut list in half
  private int find(int index, int mid, Array<T> array) {
    if (array.get(index).compareTo(array.get(mid)) >= 0) {
      if (array.get(index).compareTo(array.get(mid + 1)) < 0) {
        return mid + 1;
      } else if (array.get(index).compareTo(array.get(mid + 1)) >= 0) {
        return -2;
      }
    } else if (array.get(index).compareTo(array.get(mid)) <= 0) {
      if (mid == 0) {
        return 0;
      } else if (array.get(index).compareTo(array.get(mid - 1)) >= 0) {
        return mid;
      } else if (array.get(index).compareTo(array.get(mid - 1)) <= 0) {
        return -1;
      }
    }
    //if no index is found to stop, returns current values index
    return index;
  }


  @Override
  public String name() {
    return "Insertion Sort";
  }
}