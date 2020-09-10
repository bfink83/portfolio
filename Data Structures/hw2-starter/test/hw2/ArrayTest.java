package hw2;

import exceptions.IndexException;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("CheckStyle")
public abstract class ArrayTest {

  protected static final int LENGTH = 113;
  protected static final int INITIAL = 7;
  private Array<Integer> array;

  public abstract Array<Integer> createArray();

  @Before
  public void setup() {
    array = createArray();
  }

  @Test
  public void testConstructor() {
    assertEquals(LENGTH, array.length());
  }

  @Test
  public void testPut() {
    array.put(0, 20);
    assertEquals(20, array.get(0).intValue());
  }

  @Test(expected = IndexException.class)
  public void testPutThrowsException() {
    array.put(-1, 30);
  }

  //additional tests

  @Test
  public void testGet() {
    array.put(0,20);
    array.put(1,10);
    assertEquals(7, array.get(2).intValue());
  }

  @Test
  public void testRemove() {
    array.put(0,20);
    array.put(1,10);
    array.put(2, 15);
    array.put(1,7);
    assertEquals(7, array.get(1).intValue());
  }

  @Test
  public void testHasNext() {
    array.put(0,20);
    array.put(1,10);
    array.put(6, 54);
    Iterator<Integer> iterator = array.iterator();
    assertTrue(iterator.hasNext());
    iterator.next();
    iterator.next();
    assertTrue(iterator.hasNext());
  }

  @Test
  public void testIterator() {
    array.put(0,20);
    array.put(1,10);
    array.put(6, 54);
    Iterator<Integer> iterator = array.iterator();
    assertEquals(20, iterator.next().intValue());
    assertEquals(10, iterator.next().intValue());
    assertEquals(INITIAL, iterator.next().intValue());
    assertEquals(INITIAL, iterator.next().intValue());
    assertEquals(INITIAL, iterator.next().intValue());
    assertEquals(INITIAL, iterator.next().intValue());
    assertEquals(54, iterator.next().intValue());
    assertEquals(INITIAL, iterator.next().intValue());
  }

  @Test
  public void testCompleteIteration() {
    Iterator<Integer> iterator = array.iterator();
    for (Integer i : array) {
      assertEquals(INITIAL, iterator.next().intValue());
    }
    assertFalse(iterator.hasNext());
  }

  @Test (expected = NoSuchElementException.class)
  public void testNext() {
    array.put(LENGTH - 1, 100);
    Iterator<Integer> iterator = array.iterator();

    for (int i = 0; i < array.length() - 1; i++) {
      assertEquals(INITIAL, iterator.next().intValue());
    }
    assertEquals(100, iterator.next().intValue());
    iterator.next();
  }

}
