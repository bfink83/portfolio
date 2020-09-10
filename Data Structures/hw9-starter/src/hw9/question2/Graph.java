package hw9.question2;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A representation of Graph.
 *
 * <p>This implementation is different from the one in HW8!</p>
 */
public class Graph {
  private Map<Integer, Set<Integer>> adjacencyList;
  private int[][] adjacencyMatrix;

  /**
   * Construct an empty graph (no edges) given the number of vertices.
   *
   * @param numVertices number of vertices must be a positive integer.
   */
  public Graph(int numVertices) {
    adjacencyList = new HashMap<>(numVertices);
    for (int i = 1; i <= numVertices; i++) {
      adjacencyList.put(i, new HashSet<>());
    }
    adjacencyMatrix = new int[numVertices][numVertices];
  }

  /**
   * Get the number of vertices.
   *
   * <p>Invariant: number of vertices is a positive integer</p>
   *
   * @return the number of vertices in this graph.
   */
  public int numVertices() {
    return adjacencyList.size();
  }

  /**
   * Add an edge to this graph.
   *
   * @param v start vertex. An integer in [1, numVertices()].
   * @param u end vertex. An integer in [1, numVertices()].
   * @param w weight of the directed edge from v going to u.
   */
  public void addEdge(int v, int u, int w) {
    adjacencyList.get(v).add(u);
    adjacencyMatrix[v - 1][u - 1] = w;
  }

  /**
   * Get the weight (cost) of the directed edge v -> u.
   *
   * @param v start vertex. An integer in [1, numVertices()].
   * @param u end vertex. An integer in [1, numVertices()].
   * @return the weight of the directed edge from v going to u.
   */
  public int weight(int v, int u) {
    return adjacencyMatrix[v - 1][u - 1];
  }

  /**
   * Returns a set of outgoing neighbours of v.
   * @param v an integer in [1, numVertices()] representing
   *          a vertex in this graph.
   * @return the set of outgoing neighbours of v.
   */
  public Set<Integer> neighbors(int v) {
    return Collections.unmodifiableSet(adjacencyList.get(v));
  }
}
