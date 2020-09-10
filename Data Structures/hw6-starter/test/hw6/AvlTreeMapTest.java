package hw6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to AVL Tree.
 */
@SuppressWarnings("All")
public class AvlTreeMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new AvlTreeMap<>();
  }

  @Test
  public void insertLeftRotation() {
    map.insert("1", "a");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a
     */

    map.insert("2", "b");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a,
        null 2:b
     */

    map.insert("3", "c"); // it must do a right rotation here!
    // System.out.println(avl.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertRightRotation() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("1", "a");

    String[] expected = new String[]{
            "2:b",
            "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertDoubleRotationLeftRight() {
    map.insert("3", "c");
    map.insert("1", "a");
    map.insert("2", "b");

    String[] expected = new String[]{
            "2:b",
            "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertDoubleRotationRightLeft() {
    map.insert("1", "a");
    map.insert("3", "c");
    map.insert("2", "b");

    String[] expected = new String[]{
            "2:b",
            "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertSingleRotationLeftNonRoot() {
    map.insert("h", "8");
    map.insert("d", "4");
    map.insert("l", "12");
    map.insert("b", "2");
    map.insert("f", "6");
    map.insert("j", "10");
    map.insert("n", "14");
    map.insert("o", "15");
    map.insert("p", "16");


    String[] expected = new String[]{
            "h:8",
            "d:4 l:12",
            "b:2 f:6 j:10 o:15",
            "null null null null null null n:14 p:16"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertSingleRotationRightNonRoot() {
    map.insert("h", "8");
    map.insert("d", "4");
    map.insert("l", "12");
    map.insert("b", "2");
    map.insert("f", "6");
    map.insert("j", "10");
    map.insert("p", "16");
    map.insert("o", "15");
    map.insert("n", "14");


    String[] expected = new String[]{
            "h:8",
            "d:4 l:12",
            "b:2 f:6 j:10 o:15",
            "null null null null null null n:14 p:16"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertDoubleRotationRightLeftNonRoot() {
    map.insert("h", "8");
    map.insert("d", "4");
    map.insert("l", "12");
    map.insert("b", "2");
    map.insert("f", "6");
    map.insert("j", "10");
    map.insert("n", "14");
    map.insert("p", "16");
    map.insert("o", "15");

    String[] expected = new String[]{
            "h:8",
            "d:4 l:12",
            "b:2 f:6 j:10 o:15",
            "null null null null null null n:14 p:16"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertDoubleRotationLeftRightNonRoot() {
    map.insert("h", "8");
    map.insert("d", "4");
    map.insert("l", "12");
    map.insert("b", "2");
    map.insert("f", "6");
    map.insert("j", "10");
    map.insert("p", "16");
    map.insert("n", "14");
    map.insert("o", "15");

    String[] expected = new String[]{
            "h:8",
            "d:4 l:12",
            "b:2 f:6 j:10 o:15",
            "null null null null null null n:14 p:16"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeSingleRotationRight() {
    map.insert("j", "10");
    map.insert("e", "5");
    map.insert("o", "15");
    map.insert("c", "3");
    map.remove("o");

    String[] expected = new String[]{
            "e:5",
            "c:3 j:10"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeSingleRotationLeft() {
    map.insert("j", "10");
    map.insert("e", "5");
    map.insert("o", "15");
    map.insert("t", "20");
    map.remove("e");

    String[] expected = new String[]{
            "o:15",
            "j:10 t:20"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeDoubleRotationLeftRight() {
    map.insert("j", "10");
    map.insert("e", "5");
    map.insert("o", "15");
    map.insert("g", "7");
    map.remove("o");

    String[] expected = new String[]{
            "g:7",
            "e:5 j:10"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeDoubleRotationRightLeft() {
    map.insert("j", "10");
    map.insert("e", "5");
    map.insert("o", "15");
    map.insert("m", "13");
    map.remove("e");

    String[] expected = new String[]{
            "m:13",
            "j:10 o:15"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeSingleRotationRightNonRoot() {
    map.insert("t", "20");
    map.insert("j", "10");
    map.insert("w", "23");
    map.insert("e", "5");
    map.insert("o", "15");
    map.insert("u", "21");
    map.insert("z", "26");
    map.insert("c", "3");
    map.remove("o");

    String[] expected = new String[]{
            "t:20",
            "e:5 w:23",
            "c:3 j:10 u:21 z:26"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeSingleRotationLeftNonRoot() {
    map.insert("t", "20");
    map.insert("j", "10");
    map.insert("w", "23");
    map.insert("e", "5");
    map.insert("o", "15");
    map.insert("u", "21");
    map.insert("z", "26");
    map.insert("q", "17");
    map.remove("e");

    String[] expected = new String[]{
            "t:20",
            "o:15 w:23",
            "j:10 q:17 u:21 z:26"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
}
