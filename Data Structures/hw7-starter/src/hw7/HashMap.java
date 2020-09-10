package hw7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* My hash table implements open addressing with cuckoo hashing to
   resolve collisions.
 */
public class HashMap<K, V> implements Map<K, V> {

  private static class Entry<K, V> {
    K key;
    V value;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private Entry<K, V>[] tableOne; //two tables for cuckoo hashing
  private Entry<K, V>[] tableTwo;
  private int entries;
  private int cap;

  /**
   * Default HashMap constructor with predetermined capacity.
   */
  public HashMap() {
    entries = 0;
    cap = 13;
    tableOne = new Entry[cap];
    tableTwo = new Entry[cap];
  }

  /**
   * HashMap constructor with capacity paramater.
   * @param cap capacity of newly constructed HashMap
   */
  public HashMap(int cap) {
    entries = 0;
    this.cap = cap;
    tableOne = new Entry[this.cap];
    tableTwo = new Entry[this.cap];
  }

  private int abs(int n) {  //abs function to deal w/ null pointer exc
    int ans = n;            //from hashcode()
    if (ans < 0) {
      ans = ans * -1;
    }
    return ans;
  }

  private int hashOne(K k) {  //basic hashcode()
    int i = abs(k.hashCode());
    return i % cap;
  }

  private int hashTwo(K k) {  //altered hashcode() for second table
    int i = abs(k.hashCode() * 17) + 11;
    return i % cap;
  }

  private void resize() { //resize creates temp HashMap and rehashes every entry
    cap = cap * 2 + 17;   //rehashing works because with new cap, ...
    HashMap<K, V> temp = new HashMap<>(cap);  //new mod gives diff index in new
    for (int i = 0; i < tableOne.length; i++) {   //tables
      if (tableOne[i] != null) {
        if (!temp.has(tableOne[i].key)) {
          temp.insert(tableOne[i].key, tableOne[i].value);
        }
      }
    }
    for (int i = 0; i < tableTwo.length; i++) {
      if (tableTwo[i] != null) {
        if (!temp.has(tableTwo[i].key)) {
          temp.insert(tableTwo[i].key, tableTwo[i].value);
        }
      }
    }
    tableOne = temp.tableOne;
    tableTwo = temp.tableTwo;
  }


  //insert must break when nothing is bumped, but also when there are
  //too many bumps because it could loop infinitely
  //pre-determined stop at cap / 4 because while loop swaps twice
  //before returning to condition (once in each table)
  //since load <= 0.5, loop should exit at 0.5 / 2 = cap / 4
  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    Entry<K, V> origEntry = find(k);
    Entry<K, V> newEntry = new Entry<>(k, v);
    boolean cuckood = true;
    int swaps = 0;

    if (has(k)) {
      throw new IllegalArgumentException();
    }

    while (swaps <= (cap / 4) && cuckood) {
      tableOne[hashOne(k)] = newEntry;
      if (origEntry == null) {
        cuckood = false;
      } else {
        cuckood = insertTwo(origEntry.key, origEntry.value);
      }
      swaps++;
    }
    entries++;
    if (loadFactor() > 0.5) {
      resize();
    }
  }

  //calculates load factor
  private double loadFactor() {
    return (double)entries / (double)cap;
  }

  //insert helper function to insert in second table
  private boolean insertTwo(K k, V v) throws IllegalArgumentException {
    Entry<K, V> origEntry = findTwo(k);
    Entry<K, V> newEntry = new Entry<>(k, v);
    boolean cuckood = true;

    if (origEntry == null) {
      tableOne[hashTwo(k)] = newEntry;
      cuckood = false;
    } else {
      tableOne[hashTwo(k)] = newEntry;
      insert(origEntry.key, origEntry.value);
    }
    return cuckood;
  }

  //remove must ensure that it is checking the index in the right
  //table before it removes, hence the if/else statement
  @Override
  public V remove(K k) throws IllegalArgumentException {
    Entry<K, V> entry = find(k);
    if (entry == null) {
      throw new IllegalArgumentException();
    }
    int index = hashOne(k);
    if (tableOne[index] == entry) {
      tableOne[index] = null;
    } else {
      index = hashTwo(k);
      tableTwo[index] = null;
    }
    entries--;
    return entry.value;
  }

  //put is standard because its based on find()
  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Entry<K, V> entry = find(k);
    if (entry == null) {
      throw new IllegalArgumentException();
    }
    entry.value = v;
  }

  //get is standard because its based on find()
  @Override
  public V get(K k) throws IllegalArgumentException {
    Entry<K, V> entry = find(k);
    if (entry != null) {
      return entry.value;
    }
    throw new IllegalArgumentException();
  }

  //has() must check both tables
  @Override
  public boolean has(K k) {
    return find(k) != null || findTwo(k) != null;
  }

  //find must check both tables and return the correct entry
  //so, entry is initialized at the beginning and given a value
  //when it is found in either table
  private Entry<K, V> find(K k) throws IllegalArgumentException {
    Entry<K, V> e;
    if (k == null) {
      throw new IllegalArgumentException();
    }
    int index = hashOne(k);
    if (tableOne[index] != null) {
      e = tableOne[index];
    } else {
      e = findTwo(k);
    }
    return e;
  }

  //find() helper method
  private Entry<K, V> findTwo(K k) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    }
    int index = hashTwo(k);
    return tableTwo[index];
  }

  //standard
  @Override
  public int size() {
    return entries;
  }

  @Override
  public Iterator<K> iterator() {
    List<K> keys = new ArrayList<K>();
    iteratorHelper(keys);
    return keys.iterator();
  }

  //iterates over tableOne then tableTwo
  private void iteratorHelper(List<K> keys) {
    for (int i = 0; i < tableOne.length; i++) {
      if (tableOne[i] != null) {
        keys.add(tableOne[i].key);
      }
      if (tableTwo[i] != null) {
        keys.add(tableTwo[i].key);
      }
    }
  }
}
