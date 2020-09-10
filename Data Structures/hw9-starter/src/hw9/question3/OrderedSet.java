package hw9.question3;

/**
 * Sets of arbitrary values.
 * Iteration in ascending (natural) order.
 *
 * @param <T> Element type.
 */
public interface OrderedSet<T extends Comparable<T>> extends Iterable<T> {
  /**
   * Insert a value.
   * Set doesn't change if we try to insert an existing value.
   *
   * @param t Value to insert.
   */
  void insert(T t);

  /**
   * Remove a value.
   * Set doesn't change if we try to remove a non-existent value.
   *
   * @param t Value to remove.
   */
  void remove(T t);

  /**
   * Test membership of a value.
   *
   * @param t Value to test.
   * @return True if t is in the set, false otherwise.
   */
  boolean has(T t);

  /**
   * Number of values.
   *
   * @return Number of values in the set, always greater or equals to 0.
   */
  int size();
}
