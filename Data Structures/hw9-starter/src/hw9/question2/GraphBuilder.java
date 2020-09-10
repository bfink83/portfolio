package hw9.question2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GraphBuilder {

  /**
   * Generate a Graph from data file.
   *
   * @param data contain information about vertices and edges of a graph.
   *             Each file contains data about one Graph.
   *             The first row contains number of vertices.
   *             Each row represents a directed edge by indicating
   *             its endpoints and weight.
   * @return Graph generated from data file.
   */
  public static Graph build(File data) throws FileNotFoundException {
    Scanner scanner = new Scanner(data);
    int numVertices = scanner.nextInt();
    Graph graph = new Graph(numVertices);
    while (scanner.hasNext()) {
      int v = scanner.nextInt();
      int u = scanner.nextInt();
      int w = scanner.nextInt();
      graph.addEdge(v, u, w);
    }
    return graph;
  }
}
