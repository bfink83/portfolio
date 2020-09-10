package hw9.question3;

import static hw9.question3.TrainTrackListSet.MAX_TRACKS;
import static hw9.question3.TrainTrackListSet.SLOWEST_TRACK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("All")
public class TrainTrackListSetPrinter {
  /**
   * Prepare a prettified string representation of the TrainTrackListSet.
   * @param ttls ordered set implemented using TrainTrackListSet.
   * @param baltimorePenn the baltimore/Penn station
   *                      ("head" of the singly linked list).
   * @param <E> base type.
   * @return prettified string representation of the TrainTrackListSet.
   */
  public static <E extends Comparable<E>> String toString(
      TrainTrackListSet<E> ttls, Station<E> baltimorePenn) {
    int maxLen = getMaxLen(ttls);
    Map<Integer, List<E>> tracks = getTrackMap();
    addStationsToTracks(baltimorePenn, tracks);
    StringBuilder sb = displayStopsOnTracks(ttls, maxLen, tracks);
    displayData(ttls, maxLen, tracks, sb);
    return sb.toString();
  }

  private static <E extends Comparable<E>> void displayData(
      TrainTrackListSet<E> ttls, int maxLen,
      Map<Integer, List<E>> tracks, StringBuilder sb) {
    sb.append("    data :      ");
    for (int station = 1; station <= ttls.size(); station++) {
      E data = tracks.get(SLOWEST_TRACK).get(station);
      String dataStr = data == null ? "-" : data.toString();
      sb.append(pad(maxLen, dataStr)).append(" ");
    }
    sb.append("     \n");
  }

  private static <E extends Comparable<E>> StringBuilder displayStopsOnTracks(
      TrainTrackListSet<E> ttls, int maxLen, Map<Integer, List<E>> tracks) {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    for (int track = MAX_TRACKS; track >= SLOWEST_TRACK; track--) {
      sb.append("Track(").append(track).append(") : PENN ");
      for (int station = 1; station <= ttls.size(); station++) {
        E data = tracks.get(track).get(station);
        String dataStr = data == null ? "-" : "▣";
        sb.append(pad(maxLen, dataStr)).append(" ");
      }
      sb.append(" UNION\n");
    }
    return sb;
  }

  private static <E extends Comparable<E>> void addStationsToTracks(
      Station<E> baltimorePenn, Map<Integer, List<E>> tracks) {
    Station<E> curr = baltimorePenn;
    while (curr != null) {
      for (int track = SLOWEST_TRACK; track <= curr.getNumTracks(); track++) {
        tracks.get(track).add(curr.getData());
      }
      for (int track = curr.getNumTracks() + 1; track <= MAX_TRACKS; track++) {
        tracks.get(track).add(null);
      }
      curr = curr.getNext(SLOWEST_TRACK);
    }
  }

  // Create an empty Map fron track number to stations on that tracks
  private static <E extends Comparable<E>> Map<Integer, List<E>> getTrackMap() {
    Map<Integer, List<E>> tracks = new HashMap<>();
    for (int track = SLOWEST_TRACK; track <= MAX_TRACKS; track++) {
      tracks.put(track, new ArrayList<>());
    }
    return tracks;
  }

  // Max length needed to print data elements in ttls
  private static <E extends Comparable<E>> int getMaxLen(
      TrainTrackListSet<E> ttls) {
    int maxLen = 0;
    for (E el : ttls) {
      int l = el.toString().length();
      if (l > maxLen) {
        maxLen = l;
      }
    }
    return maxLen;
  }

  // Add padding to the given String value to have a length of n.
  private static String pad(int n, String s) {
    StringBuilder sb = new StringBuilder(s);
    while (sb.length() < n) {
      sb.insert(0, " ");
    }
    s = sb.toString();
    return s;
  }
}
