package hw8;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


/**
 * An implementation of a directed graph using incidence lists
 * for sparse graphs where most things aren't connected.
 *
 * @param <V> Vertex element type.
 * @param <E> Edge element type.
 */
public class SparseGraph<V, E> implements Graph<V, E> {

  private Set<Vertex<V>> vertices;
  private Set<Edge<E>> edges;

  /**
   * Constructor for instantiating a graph.
   */
  public SparseGraph() {
    this.vertices = new LinkedHashSet<>();
    this.edges = new LinkedHashSet<>();
  }

  // Checks vertex belongs to this graph
  private void checkOwner(VertexNode<V> toTest) {
    if (toTest.owner != this) {
      throw new PositionException();
    }
  }

  // Checks edge belongs to this graph
  private void checkOwner(EdgeNode<E> toTest) {
    if (toTest.owner != this) {
      throw new PositionException();
    }
  }

  // Converts the vertex back to a VertexNode to use internally
  private VertexNode<V> convert(Vertex<V> v) throws PositionException {
    try {
      VertexNode<V> gv = (VertexNode<V>) v;
      this.checkOwner(gv);
      return gv;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  // Converts and edge back to a EdgeNode to use internally
  private EdgeNode<E> convert(Edge<E> e) throws PositionException {
    try {
      EdgeNode<E> ge = (EdgeNode<E>) e;
      this.checkOwner(ge);
      return ge;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  @Override
  public Vertex<V> insert(V v) {
    if (v == null) {
      return null;
    }
    VertexNode<V> node = new VertexNode<>(v);
    node.owner = this;
    node.data = v;
    vertices.add(node);
    return node;
  }

  @Override
  public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
          throws PositionException, InsertionException {
    if (e == null) {
      return null;
    } else if (from == null || to == null) {
      throw new PositionException();
    }
    VertexNode<V> f = convert(from);
    VertexNode<V> t = convert(to);
    EdgeNode<E> edge = new EdgeNode<>(f, t, e);
    insertCheck(f,t);
    edge.owner = this;
    edges.add(edge);
    f.outgoing.add(edge);
    t.incoming.add(edge);
    return edge;
  }

  private void insertCheck(VertexNode<V> f, VertexNode<V> t)
      throws PositionException, InsertionException {
    if (f.equals(t)) {
      throw new InsertionException();
    }
    for (Edge<E> i: t.incoming) {
      EdgeNode<E> curr = convert(i);
      if (curr.from.equals(f)) {
        throw new InsertionException();
      }
    }
  }

  @Override
  public V remove(Vertex<V> v) throws PositionException,
      RemovalException {
    if (v == null) {
      return null;
    }
    VertexNode<V> node = convert(v);
    if (!vertices.contains(node)) {
      throw new PositionException();
    }
    if (node.incoming.size() == 0 && node.outgoing.size() == 0) {
      vertices.remove(node);
      node.owner = null;
      return node.get();
    } else {
      throw new RemovalException();
    }
  }

  @Override
  public E remove(Edge<E> e) throws PositionException {
    if (e == null) {
      return null;
    }
    EdgeNode<E> edge = convert(e);
    if (!edges.contains(edge)) {
      throw new PositionException();
    }
    for (Vertex<V> v: vertices) {
      VertexNode<V> node = convert(v);
      node.incoming.remove(edge);
      node.outgoing.remove(edge);
    }
    edges.remove(edge);
    edge.owner = null;
    return edge.get();
  }

  @Override
  public Iterable<Vertex<V>> vertices() {
    return Collections.unmodifiableSet(vertices);
  }

  @Override
  public Iterable<Edge<E>> edges() {
    return Collections.unmodifiableSet(edges);
  }

  @Override
  public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
    return Collections.unmodifiableSet(convert(v).outgoing);
  }

  @Override
  public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
    return Collections.unmodifiableSet(convert(v).incoming);
  }

  @Override
  public Vertex<V> from(Edge<E> e) throws PositionException {
    return convert(e).from;
  }

  @Override
  public Vertex<V> to(Edge<E> e) throws PositionException {
    return convert(e).to;
  }

  @Override
  public void label(Vertex<V> v, Object l) throws PositionException {
    if (v == null) {
      throw new PositionException();
    }
    VertexNode<V> node = convert(v);
    if (!vertices.contains(node)) {
      throw new PositionException();
    }
    node.label = l;
  }

  @Override
  public void label(Edge<E> e, Object l) throws PositionException {
    if (e == null) {
      throw new PositionException();
    }
    EdgeNode<E> node = convert(e);
    if (!edges.contains(node)) {
      throw new PositionException();
    }
    node.label = l;
  }

  @Override
  public Object label(Vertex<V> v) throws PositionException {
    return convert(v).label;
  }

  @Override
  public Object label(Edge<E> e) throws PositionException {
    return convert(e).label;
  }

  @Override
  public void clearLabels() {
    for (Vertex<V> v: vertices) {
      VertexNode<V> vert = convert(v);
      vert.label = null;
      for (Edge<E> e: vert.outgoing) {
        EdgeNode<E> edge = convert(e);
        edge.label = null;
      }
    }
  }

  private String vertexString(Vertex<V> v) {
    return "\"" + v.get() + "\"";
  }

  private String verticesToString() {
    StringBuilder sb = new StringBuilder();
    for (Vertex<V> v : this.vertices) {
      sb.append("  ").append(vertexString(v)).append("\n");
    }
    return sb.toString();
  }

  private String edgeString(Edge<E> e) {
    return String.format("%s -> %s [label=\"%s\"]",
        this.vertexString(this.from(e)),
        this.vertexString(this.to(e)),
        e.get());
  }

  private String edgesToString() {
    String edgs = "";
    for (Edge<E> e : this.edges) {
      edgs += "  " + this.edgeString(e) + ";\n";
    }
    return edgs;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph {\n")
        .append(this.verticesToString())
        .append(this.edgesToString())
        .append("}");
    return sb.toString();
  }

  // Class for a vertex of type V
  private final class VertexNode<V> implements Vertex<V> {
    V data;
    Graph<V, E> owner;
    Set<Edge<E>> outgoing;
    Set<Edge<E>> incoming;
    Object label;

    VertexNode(V v) {
      this.data = v;
      this.outgoing = new LinkedHashSet<>();
      this.incoming = new LinkedHashSet<>();
      this.label = null;
    }

    @Override
    public V get() {
      return this.data;
    }

    @Override
    public void put(V v) {
      this.data = v;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      VertexNode<?> that = (VertexNode<?>) o;
      return data.equals(that.data) && owner.equals(that.owner);
    }

    @Override
    public int hashCode() {
      return Objects.hash(data, owner);
    }
  }

  //Class for an edge of type E
  private final class EdgeNode<E> implements Edge<E> {
    E data;
    Graph<V, E> owner;
    VertexNode<V> from;
    VertexNode<V> to;
    Object label;

    // Constructor for a new edge
    EdgeNode(VertexNode<V> f, VertexNode<V> t, E e) {
      this.from = f;
      this.to = t;
      this.data = e;
      this.label = null;
    }

    @Override
    public E get() {
      return this.data;
    }

    @Override
    public void put(E e) {
      this.data = e;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      EdgeNode<?> edgeNode = (EdgeNode<?>) o;
      return owner.equals(edgeNode.owner)
              && from.equals(edgeNode.from)
              && to.equals(edgeNode.to);
    }

    @Override
    public int hashCode() {
      return Objects.hash(owner, from, to);
    }
  }
}