package hw8;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public abstract class GraphTest {

  protected Graph<String, String> graph;

  @Before
  public void setupGraph() {
    this.graph = createGraph();
  }


  protected abstract Graph<String, String> createGraph();

  @Test
  public void testInsertVertex() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
  }

  @Test
  public void testInsertEdge() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals(e1.get(), "v1-v2");
    assertEquals(v1.get(), graph.from(e1).get());
    assertEquals(v2.get(), graph.to(e1).get());
  }

  @Test(expected = PositionException.class)
  public void testInsertEdgeThrowsPositionExceptionWhenfirstVertexIsNull() {
    Vertex<String> v = graph.insert("v");
    Edge<String> e = graph.insert(null, v, "e");
  }

  @Test(expected = PositionException.class)
  public void testInsertEdgeThrowsPositionExceptionWhenSecondVertexIsNull() {
    Vertex<String> v = graph.insert("v");
    Edge<String> e = graph.insert(v, null, "e");
  }

  @Test(expected = PositionException.class)
  public void testInsertEdgeThrowsPositionExceptionWhenFirstVertexIsInvalid() {
    Graph<String, String> otherGraph = createGraph();
    Vertex<String> v1 = otherGraph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");
  }

  @Test(expected = PositionException.class)
  public void testInsertEdgeThrowsPositionExceptionWhenSecondVertexIsInvalid() {
    Graph<String, String> otherGraph = createGraph();
    Vertex<String> v1 = otherGraph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v2, v1, "e");
  }

  @Test(expected = PositionException.class)
  public void testInsertEdgeThrowsPositionExceptionWhenFirstVertexWasRemoved() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.remove(v1);
    graph.insert(v1, v2, "e");
  }

  @Test(expected = PositionException.class)
  public void testInsertEdgeThrowsPositionExceptionWhenSecondVertexWasRemoved() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.remove(v2);
    graph.insert(v1, v2, "e");
  }

  @Test(expected = InsertionException.class)
  public void testInsertEdgeThrowsInsertionExceptionForSelfLoopEdge() {
    Vertex<String> v = graph.insert("v");
    graph.insert(v, v, "e");
  }

  @Test(expected = InsertionException.class)
  public void testInsertEdgeThrowsInsertionExceptionForMultipleParallelEdge() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v1, v2, "v1-v2");
    graph.insert(v1, v2, "v1-v2");
  }

  @Test
  public void testInsertMultipleEdgeOppositeDirection() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    Edge<String> e2 = graph.insert(v2, v1, "v2-v1");
    assertEquals(e1.get(), "v1-v2");
    assertEquals(e2.get(), "v2-v1");
  }

  @Test
  public void insertNullVertexReturnsNull() {
    assertEquals(graph.insert(null), null);
  }

  @Test
  public void insertNullEdgeReturnsNull() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    assertEquals(graph.insert(v1, v2, null), null);
  }

  @Test
  public void removeNullVertexReturnsNull() {
    Vertex<String> v1 = null;
    assertEquals(graph.remove(v1), null);
  }

  @Test
  public void removeNullEdgeReturnsNull() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, null);
    assertEquals(graph.remove(e1), null);
  }

  @Test (expected = PositionException.class)
  public void testRemoveVertexBasic() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    assertEquals(v1.get(), "v1");
    assertEquals(v2.get(), "v2");
    graph.remove(v1);
    graph.remove(v1); //if vertex is indeed removed, removing it again should throw pos exception
  }

  @Test
  public void testInsertRemoveInsert() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    assertEquals(v1.get(), "v1");
    assertEquals(v2.get(), "v2");
    graph.remove(v1);
    graph.insert("v1");
    assertEquals(v1.get(), "v1"); //no exception should be thrown since it was removed before being added again
  } //shows remove works

  @Test (expected = RemovalException.class)
  public void removeVertexThrowsRemovalException() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    Edge<String> e2 = graph.insert(v2, v1, "v2-v1");
    assertEquals(e1.get(), "v1-v2");
    assertEquals(e2.get(), "v2-v1");
    graph.remove(v1);
  }

  @Test (expected = PositionException.class)
  public void removeVertexThrowsPositionExceptionForNullVertex() {
    Graph<String, String> otherGraph = createGraph();
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = otherGraph.insert("v2");
    graph.remove(v2);
  }

  @Test (expected = PositionException.class)
  public void removeNonExistentEdgeThrowsPositionException() {
    Graph<String, String> otherGraph = createGraph();
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = otherGraph.insert("v3");
    Vertex<String> v4 = otherGraph.insert("v4");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    Edge<String> e3 = otherGraph.insert(v3, v4, "v3-v4");
    graph.remove(e3);
  }

  @Test
  public void removeEdgeWorks() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    graph.remove(e1);
    graph.insert(v1, v2, "v1-v2");
    assertEquals(e1.get(), "v1-v2");
  } //remove edge works since insert does not throw an insertion exception the second time it is called on the same value

  @Test
  public void labelVertexWorks() {
    Vertex<String> v1 = graph.insert("v1");
    graph.label(v1, "one");
    assertEquals(graph.label(v1), "one");
  }

  @Test
  public void labelEdgeWorks() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    graph.label(e1, "one");
    assertEquals(graph.label(e1), "one");
  }

  @Test
  public void clearLabelsWorks() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    Edge<String> e2 = graph.insert(v2, v3, "v2-v3");
    graph.label(v1, "vert one");
    graph.label(e1, "edge one");
    graph.label(v2, "vert two");
    graph.label(e2, "edge two");
    graph.label(v3, "vert three");
    assertEquals(graph.label(v1), "vert one");
    assertEquals(graph.label(e1), "edge one");
    assertEquals(graph.label(v2), "vert two");
    assertEquals(graph.label(e2), "edge two");
    assertEquals(graph.label(v3), "vert three");
    graph.clearLabels();
    assertEquals(graph.label(v1), null);
    assertEquals(graph.label(e1), null);
    assertEquals(graph.label(v2), null);
    assertEquals(graph.label(e2), null);
    assertEquals(graph.label(v3), null);
  }
}
