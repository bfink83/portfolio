package hw9.question3;

import java.util.Iterator;
import java.util.Stack;

/**
 * TrainTrackListSet is inspired by Ali's commute between
 * Baltimore/Penn and Union station.
 *
 * @param <E> base type.
 */
public class TrainTrackListSet<E extends Comparable<E>>
    implements OrderedSet<E> {

  public static final int MAX_TRACKS = 6;
  public static final int SLOWEST_TRACK = 1;
  private Station<E> baltimorePenn; // Sentinel node
  private int numStations;

  /**
   * Construct a TrainTrackListSet data structures.
   */
  public TrainTrackListSet() {
    baltimorePenn = new Station<E>(null, MAX_TRACKS);
    numStations = 0;
  }

  @Override
  public int size() {
    return numStations;
  }

  @Override
  public boolean has(E data) {
    if (data == null) {
      return false;
    }
    Station<E> station = find(data).pop().getNext(SLOWEST_TRACK);
    if (station == null) {
      return false;
    }
    return station.getData() != null && station.getData().equals(data);
  }

  @Override
  public void insert(E data) {
    if (data == null || has(data)) {
      return;
    }

    int numTracks = LoadedDie.roll();
    Station<E> insertStation = new Station<>(data, numTracks);
    Stack<Station<E>> prevStack = find(data);

    for (int i = 1; i <= numTracks; i++) {
      Station<E> curr = prevStack.pop();
      insertStation.setNext(i, curr.getNext(i));
      curr.setNext(i, insertStation);
    }

    numStations++;
  }

  @Override
  public void remove(E data) {
    if (data == null || !has(data)) {
      return;
    }

    Stack<Station<E>> pervs = find(data);
    for (int track = SLOWEST_TRACK; track <= MAX_TRACKS; track++) {
      Station<E> prev = pervs.empty() ? null : pervs.pop();
      if (prev != null) {
        Station<E> toRemove = prev.getNext(track);
        if (toRemove != null && toRemove.getData().equals(data)) {
          prev.setNext(track, toRemove.getNext(track));
          toRemove.setNext(track, null);
        }
      }
    }

    numStations--;
  }

  /**
   * Returns a stack of stations, one per track (slowest-track
   * at the top of the stack).
   *
   * <p>The Station at track i either points to the station
   * containing the search item, or to the first station on
   * track i containing an item bigger than the search item
   * (or null if there is no such bigger item on track i).</p>
   *
   * @param toFind The search item.
   * @return The stack of nodes immediately previous to the item on each level.
   */
  private Stack<Station<E>> find(E toFind) {
    Stack<Station<E>> prev = new Stack<>();
    Station<E> curr = baltimorePenn;

    for (int i = MAX_TRACKS; i > 0; i--) {
      while (curr.getNext(i) != null
              && curr.getNext(i).getData().compareTo(toFind) < 0) {
        curr = curr.getNext(i);
      }
      prev.push(curr);
    }
    return prev;
  }

  @Override
  public String toString() {
    return TrainTrackListSetPrinter.toString(this, baltimorePenn);
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private Station<E> cur = baltimorePenn.getNext(SLOWEST_TRACK);

      @Override
      public boolean hasNext() {
        return cur != null;
      }

      @Override
      public E next() {
        E toReturn = cur.getData();
        cur = cur.getNext(SLOWEST_TRACK);
        return toReturn;
      }
    };
  }

}
