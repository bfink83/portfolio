package hw9.question2;

/**
 * An interface for solving the shortest path problems.
 */
public interface ShortestPath {
  /**
   * Prints the shortest path between the source and the
   * destination vertex, if one exist.
   *
   * @param graph       representation of a directed Graph.
   * @param source      source vertex.
   * @param destination destination vertex.
   */
  void solve(Graph graph, int source, int destination);
}
