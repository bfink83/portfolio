package hw9.question2;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Modified Dijkstra Algorithm.
 */
public class Dijkstra implements ShortestPath {

  @Override
  public void solve(Graph graph, int source, int destination) {
    Vertex start = new Vertex(source);
    Vertex target = new Vertex(destination);
    SortedSet<Vertex> toExplore = new TreeSet<>();
    start.update(0, null, 0);
    toExplore.add(start);
    while (!toExplore.isEmpty()) {
      Vertex v = toExplore.first();
      if (v.id == destination) {
        target = v;
        break;
      }
      updateNeighbors(graph, toExplore, v);
      toExplore.remove(v);
    }

    outputShortestPath(target);
  }

  private void updateNeighbors(Graph g, SortedSet<Vertex> toExplore, Vertex v) {
    for (int id : g.neighbors(v.id)) {
      int distToU = v.distance + g.weight(v.id, id);
      Vertex u = new Vertex(id);
      u.update(distToU, v, v.minNumEdges + 1);
      toExplore.add(u);
    }
  }

  private void outputShortestPath(Vertex v) {
    if (v != null) {
      outputShortestPath(v.previous);
      System.out.print(v.id + " ");
    }
  }

  private static class Vertex implements Comparable<Vertex> {
    int id;
    int distance;
    int minNumEdges;
    Vertex previous;

    Vertex(int id) {
      this.id = id;
      this.distance = Integer.MAX_VALUE;
      previous = null;
      this.minNumEdges = 0;
    }

    public void update(int dist, Vertex prev, int numEdge) {
      if (distance > dist || (distance == dist && minNumEdges > numEdge)) {
        distance = dist;
        previous = prev;
        minNumEdges = numEdge;
      }
    }

    @Override
    public int compareTo(Vertex other) {
      if (this.distance != other.distance) {
        return Integer.compare(this.distance, other.distance);
      }
      if (this.minNumEdges != other.minNumEdges) {
        return Integer.compare(this.minNumEdges, other.minNumEdges);
      }
      return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Vertex vertex = (Vertex) o;
      return id == vertex.id
          && distance == vertex.distance
          && minNumEdges == vertex.minNumEdges;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, distance, minNumEdges);
    }

    @Override
    public String toString() {
      return "Vertex{"
          + "id=" + id
          + ", distance=" + distance
          + ", minNumEdges=" + minNumEdges
          + ", previous=" + previous
          + '}';
    }
  }
}
