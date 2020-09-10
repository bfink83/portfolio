package hw9.question2;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sample {

  private static final int START = 1;
  private static final int END = 3;

  // Update this to any other data file for benchmarking experiments.
  private static String getDataFile() {
    return "sample01.txt";
  }

  private static ShortestPath getShortestPathSolver() {
    return new Dijkstra();
  }

  /**
   * Sample program to show case how modified Dijkstra works.
   * @param args command-line arguments - not used here.
   * @throws FileNotFoundException is thrown if data file does not exist.
   */
  public static void main(String[] args) throws FileNotFoundException {
    Path data = Paths.get("res", "src", getDataFile());
    Graph graph = GraphBuilder.build(data.toFile());
    getShortestPathSolver().solve(graph, START, END);
  }
}
