package hw3;

import exceptions.IndexException;


/**
 * An Array that is able to report the number of accesses and mutations,
 * as well as reset those statistics.
 *
 * @param <T> The type of the array.
 */
public class MeasuredArray<T> extends SimpleArray<T> implements Measured<T> {
  private int accessCt;
  private int mutateCt;

  /**
   * Constructor for a MeasuredArray that calls the SimpleArray constructor.
   *
   * @param n The size of the array.
   * @param t The initial value to set every object to in the array..
   */
  public MeasuredArray(int n, T t) {
    super(n, t);
    this.accessCt = 0;
    this.mutateCt = 0;
  }

  @Override
  public T get(int i) throws IndexException {
    T gottenVar = super.get(i);
    this.accessCt += 1; //skipped if get throws exception
    return gottenVar;
  }

  @Override
  public void put(int i, T t) throws IndexException {
    super.put(i, t);
    this.mutateCt += 1; //skipped if put throws exception
  }

  @Override
  public void reset() {
    this.accessCt = 0;
    this.mutateCt = 0;
  }

  @Override
  public int accesses() {
    return this.accessCt;
  }

  @Override
  public int mutations() {
    return this.mutateCt;
  }

  @Override
  public int count(T t) {
    int totCount = 0;
    for (int i = 0; i < this.length(); ++i) {
      if (t.equals(this.get(i))) { //use get to account for access
        ++totCount;
      }
    }
    return totCount;
  }
  //  private class MeasureArrayIterator extends ArrayIterator {
  //  }
}
