package hw9;

import hw9.question3.OrderedSet;
import hw9.question3.TrainTrackListSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderedSetTest {

  private OrderedSet<String> unit;

  @Before
  public void setupTests() {
    unit = new TrainTrackListSet<>();
  }

  @Test
  public void newSetEmpty() {
    assertEquals(0, unit.size());
  }

  @Test
  public void newSetEmptyIterator() {
    int c = 0;
    for (String s : unit) {
      c++;
    }
    assertEquals(0, c);
  }

  @Test
  public void insertNotEmpty() {
    unit.insert("Magda");
    assertEquals(1, unit.size());
  }

  @Test
  public void insertDuplicateSizeConsistent() {
    unit.insert("one");
    unit.insert("one");
    assertEquals(1, unit.size());
  }

  @Test
  public void insertHas() {
    assertFalse(unit.has("one"));
    unit.insert("one");
    assertTrue(unit.has("one"));
  }

  @Test
  public void insertRemoveHas() {
    unit.insert("one");
    unit.remove("one");
    assertFalse(unit.has("one"));
  }

  @Test
  public void manyInsertOneRemove() {
    unit.insert("one");
    unit.insert("two");
    unit.remove("one");
    unit.insert("three");
    assertEquals(2, unit.size());
    assertFalse(unit.has("one"));
    assertTrue(unit.has("two"));
    assertTrue(unit.has("three"));
  }

  @Test
  public void insertManySizeConsistent() {
    unit.insert("one");
    unit.insert("two");
    unit.insert("three");
    assertEquals(3, unit.size());
  }

  @Test
  public void iteratorWorks() {
    String[] data = {"Peter", "Paul", "Mary", "Beverly"};
    for (String d : data) {
      unit.insert(d);
    }
    for (String s : unit) {
      int count = 0;
      for (String d : data) {
        if (s.equals(d)) {
          count += 1;
        }
      }
      assertEquals(1, count);
    }
  }

  @Test
  public void orderPreserved() {

    String[] nums = new String[]{"01", "02", "05", "10"};

    unit.insert("02");
    unit.insert("05");
    unit.insert("10");
    unit.insert("01");

    int index = 0;
    for (String num: unit) {
      assertEquals(nums[index++], num);
    }
  }


}
