package hw3;

public class GnomeSortTest extends SortingAlgorithmTest {
  @Override
  public SortingAlgorithm<Integer> createSortingAlgorithm() {
    return new GnomeSort<>();
  }
}
