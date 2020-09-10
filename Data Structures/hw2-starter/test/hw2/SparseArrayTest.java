package hw2;

public class SparseArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {
    return new SparseArray<>(LENGTH, INITIAL);
  }

}