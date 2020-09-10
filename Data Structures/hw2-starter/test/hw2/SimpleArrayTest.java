package hw2;

public class SimpleArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {
    return new SimpleArray<>(LENGTH, INITIAL);
  }

}
