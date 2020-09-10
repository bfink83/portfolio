package hw3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public abstract class SortingAlgorithmTest {
  private Array<Integer> array;
  private SortingAlgorithm<Integer> algorithm;
  public abstract SortingAlgorithm<Integer> createSortingAlgorithm();

  @Before
  public void setUp() throws Exception {
    array = new SimpleArray<>(7, 0);
    array.put(0, 4);
    array.put(1, 0);
    array.put(2, 3);
    array.put(3, 1);
    array.put(4, 2);
    array.put(5, 2);
    array.put(6, 3);
    algorithm = createSortingAlgorithm();
  }

  @Test
  public void sortWorks() {
    algorithm.sort(array);
    assertTrue(Util.isSorted(array));
  }
}
