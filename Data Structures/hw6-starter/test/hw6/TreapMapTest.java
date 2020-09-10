package hw6;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new TreapMap<>(1);
  }
  //all tests using random seed of 1

  protected ArrayList<Integer> priorities = new ArrayList<>();

  @Before
  public void makeArray() {
    Random rand = new Random(1);
    for (int i = 0; i < 10; i++) {
      priorities.add(rand.nextInt());
    }
  }

  @Test
  public void printPriorityList() {
    for (int i = 0; i < 6; i++) {
      System.out.print(priorities.get(i) + " ");
    }
  }

  @Test
  public void insertTest() {
    map.insert("a", "1");
    map.insert("b", "2");
    map.insert("c", "3");

    String[] expected = new String[]{

            "a:1:" + priorities.get(0),
            "null b:2:" + priorities.get(1),
            "null null null c:3:" + priorities.get(2)
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertWithRotation() {
    map.insert("a", "1");
    map.insert("b", "2");
    map.insert("c", "3");
    map.insert("d", "4");

    String[] expected = new String[]{

            "a:1:" + priorities.get(0),
            "null b:2:" + priorities.get(1),
            "null null null d:4:" + priorities.get(3),
            "null null null null null null c:3:" + priorities.get(2) + " null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeLeaf() {
    map.insert("a", "1");
    map.insert("b", "2");
    map.insert("c", "3");
    map.remove("c");

    String[] expected = new String[]{

            "a:1:" + priorities.get(0),
            "null b:2:" + priorities.get(1)
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeSubtreeRoot() {
    map.insert("j", "10");
    map.insert("o", "15");
    map.insert("t", "20");
    map.insert("y", "25");
    map.insert("m", "13");
    map.remove("o");

    String[] expected = new String[]{

            "j:10:" + priorities.get(0),
            "null m:13:" + priorities.get(4),
            "null null null y:25:" + priorities.get(3),
            "null null null null null null t:20:" + priorities.get(2) + " null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertWithMultipleRotations() {
    map.insert("g", "7");
    map.insert("e", "5");
    map.insert("j", "10");
    map.insert("h", "8");
    map.insert("l","12");

    String[] expected = new String[]{

            "g:7:" + priorities.get(0),
            "e:5:" + priorities.get(1) + " l:12:" + priorities.get(4),
            "null null h:8:" + priorities.get(3) + " null",
            "null null null null null j:10:" + priorities.get(2) + " null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
}