package hw6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Map implemented as an AvlTree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V>
    implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int size;

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
    } else if (comp > 0) {
      n.right = insert(n.right, k, v);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }
    return balance(n);
  }

  private Node<K, V> balance(Node<K, V> n) {
    if (n == null) {
      return n;
    }

    if (height(n.right) - height(n.left) > 1) { //check balance factor
      if (height(n.right.right) >= height(n.right.left)) {
        n = singleL(n);
      } else {
        n = doubleRL(n);
      }
    } else if (height(n.right) - height(n.left) < -1) {
      if (height(n.left.left) >= height(n.left.right)) {
        n = singleR(n);
      } else {
        n = doubleLR(n);
      }
    }
    n.height = maxHeight(n);
    return n;
  }

  private Node<K, V> singleL(Node<K, V> n) {
    Node<K, V> newTop = n.right;
    n.right = newTop.left;
    newTop.left = n;

    n.height = maxHeight(n);
    newTop.height = maxHeight(newTop);

    return newTop;
  }

  private Node<K, V> singleR(Node<K, V> n) {
    Node<K, V> newTop = n.left;
    n.left = newTop.right;
    newTop.right = n;

    n.height = maxHeight(n);
    newTop.height = maxHeight(newTop);

    return newTop;
  }

  private Node<K, V> doubleLR(Node<K, V> n) {
    n.left = singleL(n.left);
    n = singleR(n);

    return n;
  }

  private Node<K, V> doubleRL(Node<K, V> n) {
    n.right = singleR(n.right);
    n = singleL(n);

    return n;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    Node<K, V> n = checkFound(k);
    root = remove(root, n);
    size--;
    return n.value;
  }

  private Node<K, V> remove(Node<K, V> subRoot, Node<K,V> replace) {
    int comp = subRoot.key.compareTo(replace.key);

    if (comp < 0) {
      subRoot.right = remove(subRoot.right, replace);
    } else if (comp > 0) {
      subRoot.left = remove(subRoot.left, replace);
    } else {
      return (remove(subRoot));
    }
    return balance(subRoot);
  }

  private Node<K, V> remove(Node<K, V> n) {
    if (n.left == null) {
      return n.right;
    } else if (n.right == null) {
      return n.left;
    }

    Node<K, V> swap = swapSearch(n);
    n.key = swap.key;   //n takes all the fields of the swap node
    n.value = swap.value;
    n.left = remove(n.left, swap);  //removes swap node from end

    return n;
  }

  private Node<K, V> swapSearch(Node<K, V> n) { //finds max on left subtree to
    n = n.left;                               //replace node being removed
    while (n.right != null) {
      n = n.right;
    }
    return n;
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
      return false;
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

  private Node<K,V> find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
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
    Node<K, V> check = find(k);

    if (check == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    } //if find returns null then exception is thrown b/c key isn't found
    //checkFound() function had to be separated from find() function in order
    //to implement has() function
    return check;
  }

  private int height(Node<K, V> n) {
    if (n == null) {
      return 0;
    } else {
      return n.height;
    }
  }

  private int maxHeight(Node<K, V> n) {
    int leftHeight = (height(n.left)) + 1;
    int rightHeight = (height(n.right)) + 1;

    if (rightHeight > leftHeight) {
      return rightHeight;
    } else {
      return leftHeight;
    }
  }


  /*** Do not change this function's name or modify its code. ***/
  // Breadth first traversal that prints binary tree out level by level.
  // Each existing node is printed as follows: 'node.key:node.value'.
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
   * Just avoid changing any existing names or deleting any existing variables.
   * ***/
  // Inner node class, each holds a key (which is what we sort the
  // BST by) as well as a value. We don't need a parent pointer as
  // long as we use recursive insert/remove helpers.
  // Do not change the name of this class
  private static class Node<K, V> {
    /***  Do not change variable names in this section. ***/
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int height = 1;

    /*** End of section. ***/

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
    }

    // Just for debugging purposes.
    public String toString() {
      return "Node<key: " + key
          + "; value: " + value
          + ">";
    }
  }

}
