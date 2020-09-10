package hw5;

import exceptions.EmptyException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the PriorityQueue interface.
 */
public abstract class PriorityQueueTest {
  private PriorityQueue<Integer> unit;

  protected abstract PriorityQueue<Integer> createUnit();

  protected abstract PriorityQueue<Integer> createUnit(Comparator<Integer> comp);

  @Before
  public void setupTests() {
    unit = this.createUnit();
  }

  @Test
  public void newQueueEmpty() {
    assertTrue(unit.empty());
  }


  //insert: DONE
  //remove: DONE
  //best: DONE
  //empty: DONE

  @Test
  public void emptyReturnsFalse() {
    unit.insert(1);
    assertFalse(unit.empty());
  }

  @Test (expected = EmptyException.class)
  public void bestReturnsEmptyException() {
    unit.best();
  }

  @Test (expected = EmptyException.class)
  public void removeReturnsEmptyException() {
    unit.remove();
  }

  //testing best() when using default comparator
  @Test
  public void bestWorks() {
    unit.insert(1);
    unit.insert(2);
    assertEquals(2, unit.best().intValue());
    unit.insert(3);
    assertEquals(3, unit.best().intValue());
  }

  @Test
  public void insertWorks() {
    assertTrue((unit.empty()));
    unit.insert(1);
    assertEquals(1, unit.best().intValue());
    assertFalse(unit.empty());
    unit.insert(2);
    assertEquals(2, unit.best().intValue());
    unit.insert(3);
    assertEquals(3, unit.best().intValue());
  }

  @Test
  public void bestWorksAnyOrder() {
    unit.insert(10);
    assertEquals(10, unit.best().intValue());
    unit.insert(5);
    assertEquals(10, unit.best().intValue());
    unit.insert(15);
    assertEquals(15,unit.best().intValue());
  }

  @Test
  public void removeWorks() {
    unit.insert(1);
    unit.insert(2);
    unit.insert(3);
    assertEquals(3, unit.best().intValue());
    unit.remove();
    assertEquals(2, unit.best().intValue());
    unit.remove();
    assertEquals(1, unit.best().intValue());
    unit.remove();
    assertTrue(unit.empty());
  }

  @Test
  public void repeatNumbersAreAllInsertedAndRemoved() {
    unit.insert(1);
    unit.insert(2);
    unit.insert(3);
    assertEquals(3, unit.best().intValue());
    unit.insert(3);
    unit.insert(3);
    assertEquals(3, unit.best().intValue());
    unit.remove();
    assertEquals(3, unit.best().intValue());
    unit.remove();
    assertEquals(3, unit.best().intValue());
    unit.remove();
    assertEquals(2, unit.best().intValue());
  }
}
