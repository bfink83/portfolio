package hw6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Map implemented as a Treap.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class TreapMap<K extends Comparable<K>, V>
    implements OrderedMap<K, V> {

  /*** Do not change variable name of 'rand'. ***/
  private static Random rand;
  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int size;

  /**
   * Make a TreapMap.
   */
  public TreapMap() {
    rand = new Random();
  }

  /**
   * Make a TreapMap using a predefined seed for Random() function.
   * @param random creates a random seed from user input
   */
  public TreapMap(int random) {
    rand = new Random(random);
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }

    root = insert(root, k, v);
    size++;
  }

  private Node<K, V> insert(Node<K, V> n, K k, V v) {
    if (n == null) {
      return new Node<K, V>(k, v);
    }

    int comp = k.compareTo(n.key);

    if (comp < 0) {
      n.left = insert(n.left, k, v);
      if (n.left.priority < n.priority) {
        n = singleR(n);
      }
    } else if (comp > 0) {
      n.right = insert(n.right, k, v);
      if (n.right.priority < n.priority) {
        n = singleL(n);
      }
    } else {
      throw new IllegalArgumentException("Duplicate key.");
    }
    return n;
  }

  private Node<K, V> singleR(Node<K, V> n) {
    Node<K, V> newTop = n.left;
    n.left = newTop.right;
    newTop.right = n;

    return newTop;
  }

  private Node<K, V> singleL(Node<K, V> n) {
    Node<K, V> newTop = n.right;
    n.right = newTop.left;
    newTop.left = n;

    return newTop;
  }


  @Override
  public V remove(K k) throws IllegalArgumentException {
    Node<K, V> n = checkFound(k);
    root = remove(root, n);
    size--;
    return n.value;

  }

  private Node<K, V> remove(Node<K, V> subRoot, Node<K, V> replace) {
    int comp = subRoot.key.compareTo(replace.key);

    if (comp < 0) {
      subRoot.right = remove(subRoot.right, replace);
    } else if (comp > 0) {
      subRoot.left = remove(subRoot.left, replace);
    } else {
      if (subRoot.left == null) {
        return subRoot.right;
      } else if (subRoot.right == null) {
        return subRoot.left;
      } else if (subRoot.left.priority > subRoot.right.priority) {
        subRoot = singleL(subRoot);   //min priority heap
        subRoot.left = remove(subRoot.left, replace);
      } else {
        subRoot = singleR(subRoot);
        subRoot.right = remove(subRoot.right, replace);
      }
    }
    return subRoot;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Node<K, V> n = checkFound(k);
    n.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    Node<K, V> n = checkFound(k);
    return n.value;
  }

  @Override
  public boolean has(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    return find(k) != null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    List<K> keys = new ArrayList<K>();
    iteratorHelper(root, keys);
    return keys.iterator();
  }

  private void iteratorHelper(Node<K, V> n, List<K> keys) {
    if (n == null) {
      return;
    }
    iteratorHelper(n.left, keys);
    keys.add(n.key);
    iteratorHelper(n.right, keys);
  }

  private Node<K, V> find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("Key cannot be null.");
    }

    Node<K, V> curr = root;

    while (curr != null) {
      int comp = k.compareTo(curr.key);  //compare search key to curr key

      if (comp < 0) {   //traverse left or right based on comp
        curr = curr.left;
      } else if (comp > 0) {
        curr = curr.right;
      } else {
        return curr;
      }
    }    //if while loop is exited, key isn't found, returns null
    return null;
  }

  private Node<K, V> checkFound(K k) {  //equivalent of findForSure
    Node<K, V> n = find(k);

    if (n == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return n;
  }

  /*** Do not change this function's name or modify its code. ***/
  // Breadth first traversal that prints binary tree out level by level.
  // Each existing node is printed as follows:
  // 'node.key:node.value:node.priority'.
  // Non-existing nodes are printed as 'null'.
  // There is a space between all nodes at the same level.
  // The levels of the binary tree are separated by new lines.
  // Returns empty string if the root is null.
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    Queue<Node<K, V>> q = new LinkedList<>();

    q.add(root);
    boolean onlyNullChildrenAdded = root == null;
    while (!q.isEmpty() && !onlyNullChildrenAdded) {
      onlyNullChildrenAdded = true;

      int levelSize = q.size();
      while (levelSize-- > 0) {
        boolean nonNullChildAdded = handleNextNodeToString(q, s);
        if (nonNullChildAdded) {
          onlyNullChildrenAdded = false;
        }
        s.append(" ");
      }

      s.deleteCharAt(s.length() - 1);
      s.append("\n");
    }

    return s.toString();
  }

  /*** Do not change this function's name or modify its code. ***/
  // Helper function for toString() to build String for a single node
  // and add its children to the queue.
  // Returns true if a non-null child was added to the queue, false otherwise
  private boolean handleNextNodeToString(Queue<Node<K, V>> q, StringBuilder s) {
    Node<K, V> n = q.remove();
    if (n != null) {
      s.append(n.key);
      s.append(":");
      s.append(n.value);
      s.append(":");
      s.append(n.priority);

      q.add(n.left);
      q.add(n.right);

      return n.left != null || n.right != null;
    } else {
      s.append("null");

      q.add(null);
      q.add(null);

      return false;
    }
  }

  /*** Do not change the name of the Node class.
   * Feel free to add whatever you want to the Node class (e.g. new fields).
   * Just avoid changing what we've provided already.
   * ***/
  // Inner node class, each holds a key (which is what we sort the
  // BST by) as well as a value. We don't need a parent pointer as
  // long as we use recursive insert/remove helpers. Since this
  // is a node class for a Treap we also include a priority field.
  private static class Node<K, V> {
    /***  Do not change variable names in this section. ***/
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int priority;

    /*** End of section. ***/

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      priority = generateRandomInteger();
    }

    // Use this function to generate random values
    // to use as node priorities as you insert new
    // nodes into your TreapMap.
    private int generateRandomInteger() {
      return rand.nextInt();
    }

    // Just for debugging purposes.
    public String toString() {
      return "Node<key: " + key
          + "; value: " + value
          + ">";
    }
  }
}
