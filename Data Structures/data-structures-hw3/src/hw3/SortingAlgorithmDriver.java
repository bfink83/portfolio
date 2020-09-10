package hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A driver program to run SortingAlgorithm(s) on various benchmark data sets.
 * <p>MeasuredArray must have been correctly implemented.</p>
 */
@SuppressWarnings({"Checkstyle", "checkstyle:ClassFanOutComplexity"})
public final class SortingAlgorithmDriver {

  private static final double NANOS = 1e9; // How many nanoseconds in a second.
  private static final int SIZE = 4000; // Number of data values for experiment.
  private static StringBuilder report;

  private SortingAlgorithmDriver() {
    // This class is not meant to be instantiated!
  }

  // List of all benchmark data.
  // Add to this list when you make a new benchmark data set.
  // PRE: data files must be stored in the "data" folder in "source resources"
  private static List<File> getDataFiles() {
    URL url = Thread.currentThread().getContextClassLoader().getResource("");
    String path = url.getPath().replace("%20", " ");
    List<File> dataFiles = new ArrayList<>();
    dataFiles.add(new File(path + "data/ascending.data"));
    dataFiles.add(new File(path + "data/descending.data"));
    dataFiles.add(new File(path + "data/random.data"));
    return dataFiles;
  }

  // List of all sorting algorithms.
  // Add to this list when you make a new sorting algorithm.
  // PRE: Your sorting algorithm must implement SortingAlgorithm.
  private static List<SortingAlgorithm<String>> getSortingAlgorithms() {
    List<SortingAlgorithm<String>> sorts = new ArrayList<>();
    sorts.add(new NullSort<>());
    sorts.add(new GnomeSort<>());
    sorts.add(new SelectionSort<>());
    sorts.add(new BubbleSort<>());
    sorts.add(new InsertionSort<>());
    return sorts;
  }

  // Read data from dataFile and store it in a List.
  // PRE: each line in dataFile contains one data value.
  // POST: each element in the returned list contains one data value.
  private static List<String> readData(File dataFile) throws IOException {
    FileReader fr = new FileReader(dataFile);
    BufferedReader br = new BufferedReader(fr);
    List<String> data = new ArrayList<>();
    String line = br.readLine();
    while (line != null) {
      data.add(line);
      line = br.readLine();
    }
    br.close();
    return data;
  }

  // Convert a List<String> to a (Measured) Array<String>.
  // toCopy is the number of elements to be copied.
  // PRE: toCopy <= list.size().
  private static MeasuredArray<String> toArray(List<String> list, int toCopy) {
    MeasuredArray<String> array = new MeasuredArray<>(toCopy, null);
    for (int i = 0; i < toCopy; i++) {
      array.put(i, list.get(i));
    }
    return array;
  }

  // Update report by adding runtime and operation statistics to it.
  private static void updateReport(String dataFile, String algo,
                                   MeasuredArray<String> array, double time) {
    if (report == null) {
      initReport();
    }
    report.append(
        String.format("%-17s %-16s %-8b %,-12d %,-12d %,-12f\n",
            dataFile, algo, Util.isSorted(array),
            array.accesses(), array.mutations(), time));
  }

  // Initialize the report by adding a header to it.
  private static void initReport() {
    report = new StringBuilder();
    report.append(
        String.format("%-17s %-16s %-8s %-12s %-12s %-12s\n\n",
            "Data file", "Algorithm", "Sorted?",
            "Accesses", "Mutations", "Seconds"));
  }

  // Run sortAlgo on data in dataFile.
  // Update report with runtime and operation statistics.
  private static void runSortAlgoOnData(SortingAlgorithm<String> sortAlgo,
                                        File dataFile) throws IOException {
    MeasuredArray<String> data = toArray(readData(dataFile), SIZE);
    data.reset();

    long before = System.nanoTime();
    sortAlgo.sort(data);
    long after = System.nanoTime();

    double seconds = (after - before) / NANOS;
    updateReport(dataFile.getName(), sortAlgo.name(), data, seconds);
  }

  /**
   * Execution starts here.
   *
   * @param args command-line arguments (not used here!).
   */
  public static void main(String[] args) {
    try {
      List<File> dataFiles = getDataFiles();
      List<SortingAlgorithm<String>> algorithms = getSortingAlgorithms();

      for (File dataFile : dataFiles) {
        for (SortingAlgorithm<String> algo : algorithms) {
          runSortAlgoOnData(algo, dataFile);
        }
        report.append("\n");
      }

      System.out.println(report.toString());

    } catch (FileNotFoundException e) {
      System.out.println("Unable to find a data file");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Unable to read from a data file");
      e.printStackTrace();
    }
  }

}