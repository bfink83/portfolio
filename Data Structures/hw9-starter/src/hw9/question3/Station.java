package hw9.question3;

import java.lang.reflect.Array;

/**
 * Represent a Station which is analogous to a Node in a singly linked list.
 *
 * @param <E> base type.
 */
public class Station<E extends Comparable<E>> {
  private E data;
  private Station<E>[] next;

  /**
   * Creates a new station.
   *
   * @param data value to be stored at this Station.
   * @param tracks number of tracks going through this station.
   */
  public Station(E data, int tracks) {
    this.data = data;
    next = (Station<E>[]) Array.newInstance(Station.class, tracks);
  }

  /**
   * Get the number of tracks at this station.
   *
   * @return number of tracks at this station.
   */
  public int getNumTracks() {
    return next.length;
  }

  /**
   * Get the data stored in this station.
   *
   * @return the data item stored in this station.
   */
  public E getData() {
    return data;
  }

  /**
   * Get the next station on the given track.
   *
   * @param track specifies the track number.
   *              track = 1 is the slowest track.
   * @return the next station on the given track,
   *         or null if this station does not have the specified track.
   */
  public Station<E> getNext(int track) {
    if (track <= getNumTracks()) {
      return next[track - 1]; // 0-based
    }
    return null;
  }

  /**
   * Sets the next station at the given track to the given station.
   *
   * <p>Does nothing if the station does not have the given track (e.g.
   * if a station has only 3 tracks then calling setNext(5, ...)
   * should do nothing, because the station does not contain a
   * reference (to next station) at track 5).</p>
   *
   * @param track   a track's number.
   * @param station a reference to a Station.
   */
  public void setNext(int track, Station<E> station) {
    if (track <= getNumTracks()) {
      next[track - 1] = station; // 0-based
    }
  }

  @Override
  public String toString() {
    return data + "";
  }
}