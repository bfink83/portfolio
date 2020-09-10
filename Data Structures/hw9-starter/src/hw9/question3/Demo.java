package hw9.question3;

/**
 * Demo program for TrainTrackListSet data structure.
 */
public class Demo {

  /**
   * Demo program for testing TrainTrackListSet data structure.
   * @param args command-line arguments - nit used here.
   */
  public static void main(String[] args) {
    TrainTrackListSet<Integer> ttls = new TrainTrackListSet<>();
    int[] data = new int[]{4, 9, 3, 1, 8, 7, 5, 6, 2, 3, 9};
    for (int num: data) {
      ttls.insert(num);
    }

    System.out.println(ttls);
    System.out.println(ttls.has(8));
    System.out.println(ttls.has(-1));

    ttls.remove(8);
    ttls.insert(0);
    System.out.println(ttls);
    System.out.println(ttls.has(8));
    System.out.println(ttls.has(0));

    System.out.print("\nSorted set: ");
    for (int num: ttls) {
      System.out.print(num + " ");
    }
    System.out.println();
  }
}
