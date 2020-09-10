package hw2;

public class ListArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {
    return new ListArray<>(LENGTH, INITIAL);
  }

}
