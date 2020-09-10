package hw3;

import exceptions.IndexException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MeasuredArrayTest {

  private static final int SIZE = 20;
  private static final String VAL = "test";

  private MeasuredArray<String> array;

  @Before
  public void createArray() {
    this.array = new MeasuredArray<>(SIZE, VAL);
  }

  @Test
  public void newArrayZeroMutations() {
    assertEquals(0, array.mutations());
  }

  @Test
  public void  newArrayZeroAccesses() {
    assertEquals(0, array.accesses());
  }

  @Test
  public void basicMeasureTests() {
    assertEquals(SIZE, array.length());
    assertEquals(VAL, array.get(0));
    assertEquals(VAL, array.get(1));
    assertEquals(VAL, array.get(array.length() - 1)); //end constructor tests
    assertEquals(3, array.accesses()); //length does not access or mutate
    assertEquals(0, array.mutations());
    array.put(0, "mutated");
    array.put(array.length() - 1, "mutated");
    assertEquals(3, array.accesses());
    assertEquals(2, array.mutations());
    array.reset();
    assertEquals(0, array.accesses());
    assertEquals(0, array.mutations());
    assertEquals(18, array.count("test")); //count non-edited
    assertEquals(array.length(), array.accesses()); //count updates access
    assertEquals(0, array.mutations());
  }

  @Test (expected = IndexException.class)
  public void exceptionHandleGetTest() {
    String testInput = array.get(-1);
    assertEquals(0, array.accesses());
  }

  @Test (expected = IndexException.class)
  public void exceptionHandleGetTest2() {
    String testInput = array.get(array.length());
    assertEquals(0, array.accesses());
  }

  @Test (expected = IndexException.class)
  public void exceptionHandlePutTest() {
    array.put(-1, "except");
    assertEquals(0, array.mutations());
  }

  @Test (expected = IndexException.class)
  public void exceptionHandlePutTest2() {
    array.put(array.length(),"except");
    assertEquals(0, array.mutations());
  }
}
