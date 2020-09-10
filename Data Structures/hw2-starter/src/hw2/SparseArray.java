package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;



/**
 * This program creates a sparse array, best used
 * if most of the elements in an array are the same
 * default value to save storage.
 * The array contains private nodes, inaccessible to the client.
 * The array contains a linked list.
 * @author bailey.finkelberg
 *        JHED: bfinkel3
 *        Date: 2/11/2020
 *
 * @param <T> Element type.
 */
public class SparseArray<T> implements Array<T> {

  private int len;
  private T defaultVal;
  private Node<T> firstNode;

  private static class Node<T> {
    T nodeVal;
    Node<T> next;
    Node<T> previous;
    int index;
  }

  /**
   * An array that is meant to be filled primarily with a default value
   * that is not going to change - with the benefit of that default
   * value not being stored numerous times as opposed to once.
   *
   * @param length       The number of indexes the array should have.
   * @param defaultValue The default value for the array.
   * @throws LengthException Throws exception when given length is negative.
   */
  public SparseArray(int length, T defaultValue) throws LengthException {
    if (length <= 0) {
      throw new LengthException();
    }
    len = length;
    defaultVal = defaultValue;
  }

  @Override
  public int length() {
    return len;
  }

  private Node<T> find(int i) {
    Node<T> n = this.firstNode;

    while (n != null && n.index != i) {
      n = n.next;
    }
    return n;
  }

  @Override
  public T get(int i) throws IndexException {
    if (i < 0 || i >= len) {
      throw new IndexException();
    }

    Node<T> n = find(i);

    if (n == null) {
      return defaultVal;
    } else {
      return n.nodeVal;
    }

  }

  @Override
  public void put(int i, T t) throws IndexException {
    if (i < 0 || i >= len) {
      throw new IndexException();
    }
    Node<T> n = find(i);
    if (firstNode != null) {
      if (n == null) {
        addNode(i,t);
      } else if (t != defaultVal) {
        n.nodeVal = t;
      } else {
        removeNode(i);
      }
    } else {
      Node<T> addNode = new Node<T>();
      addNode.index = i;
      addNode.nodeVal = t;
      this.firstNode = addNode;
    }
  }

  private void removeNode(int i) {
    Node<T> n = find(i);
    Node<T> nextNode = n.next;
    Node<T> prevNode = n.previous;

    if (nextNode == null && prevNode != null) {
      n = null;
    } else if (nextNode != null && prevNode != null) {
      n = null;
      prevNode.next = nextNode;
      nextNode.previous = prevNode;
    } else if (nextNode != null) {
      n = null;
      firstNode = nextNode;
    } else {
      n = null;
      firstNode = null;
    }
  }

  private void addNode(int i, T t) {
    Node<T> addNode = new Node<T>();
    addNode.index = i;
    addNode.nodeVal = t;
    addNode.next = this.firstNode;
    firstNode.previous = addNode;
    this.firstNode = addNode;
  }

  @Override
  public Iterator<T> iterator() {
    return new SparseArrayIterator();
  }


  private class SparseArrayIterator implements Iterator<T> {
    private int curr;

    private SparseArrayIterator() {
      this.curr = 0;
    }

    @Override
    public boolean hasNext() {
      return curr < len;
    }

    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      Node<T> n = find(curr);
      curr++;

      if (n != null) {
        return n.nodeVal;
      } else {
        return defaultVal;
      }
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
