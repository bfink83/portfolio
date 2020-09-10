package hw9.question1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * An implementation of topological sort based on the demo and
 * pseudocode you've seen in class (on slides).
 *
 * <p>There are a <b>few</b> errors (bugs) in the code.
 * Your task is to identify and fix them.</p>
 */
@SuppressWarnings("all")
public class Topological {

  /**
   * Topological sort.
   *
   * @param vertices an array of characters representing each vertex.
   * @param edges    a M-by-2 array of characters.
   *                 Each row represents a directed edge by indicating its
   *                 endpoints (in the first and second columns, respectively).
   * @return a list of vertices in (correct topological) order.
   */
  private static List<Character> sort(char[] vertices, char[][] edges) {
    Map<Character, Set<Character>> outgoing = genAdjacencyMap(vertices, edges);
    Map<Character, Integer> indegree = genInDegreeMap(vertices, edges);
    Stack<Character> visitNext = genVisitNextStack(vertices, indegree);

    // This list will hold the vertices in (correct topological) order
    List<Character> sortedOrder = new ArrayList<>(); // output

    while (!visitNext.isEmpty()) {
      char v = visitNext.pop();
      sortedOrder.add(v);
      for (char u : outgoing.get(v)) {
        indegree.put(u, indegree.get(u) - 1);
        if (!visitNext.contains(u) && indegree.get(u) == 0) {
          visitNext.push(u);
        }
      }
    }

    return sortedOrder;
  }

  // Adjacency list representation of Graph
  private static Map<Character, Set<Character>> genAdjacencyMap(
      char[] vertices, char[][] edges) {
    Map<Character, Set<Character>> outgoing = new HashMap<>();
    for (int i = 0; i < vertices.length; i++) {
      outgoing.put(vertices[i], new HashSet<>());
    }

    for (int i = 0; i < edges.length; i++) {
      char v = edges[i][0];
      char u = edges[i][1];
      outgoing.get(v).add(u);
    }
    return outgoing;
  }

  // Compute and store each vertex's in-degree
  private static Map<Character, Integer> genInDegreeMap(
      char[] vertices, char[][] edges) {
    Map<Character, Integer> indegree = new HashMap<>();
    for (int i = 0; i < vertices.length; i++) {
      indegree.put(vertices[i], 0);
    }
    for (int i = 0; i < edges.length; i++) {
      char v = edges[i][1];
      indegree.put(v, indegree.get(v) + 1);
    }
    return indegree;
  }

  // Initialize a stack with all in-degree zero vertices
  private static Stack<Character> genVisitNextStack(
      char[] vertices, Map<Character, Integer> indegree) {
    Stack<Character> visitNext = new Stack<>();
    for (int i = 0; i < vertices.length; i++) {
      if (indegree.get(vertices[i]) == 0) {
        visitNext.push(vertices[i]);
      }
    }
    return visitNext;
  }

  /**
   * A demo program that replicates a worksheet exercise.
   *
   * @param args command-line arguments - not used here.
   */
  public static void main(String[] args) {
    char[] vertices = new char[]
        {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'};
    char[][] edges = new char[][]{
        {'A', 'B'}, {'B', 'C'}, {'B', 'G'}, {'C', 'E'}, {'C', 'H'}, {'C', 'L'},
        {'D', 'C'}, {'D', 'F'}, {'E', 'I'}, {'F', 'E'}, {'F', 'J'}, {'F', 'M'},
        {'G', 'D'}, {'H', 'F'}, {'I', 'J'}, {'I', 'M'}, {'K', 'H'}, {'L', 'K'},
        {'L', 'M'}, {'M', 'J'}
    };
    List<Character> sorted = Topological.sort(vertices, edges);
    System.out.println(sorted);
  }
}