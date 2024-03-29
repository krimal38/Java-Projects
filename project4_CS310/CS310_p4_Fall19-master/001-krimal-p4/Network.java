
//You may use the Java Collections Framework in this part of your
//project, but note that your primary internal structure is an
//ArrayOfListsOfPairs, you may still find some of these useful
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Hypergraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * This method is network which represents the graph as an adjacency list which
 * is storage.
 * 
 * @author Kshitiz Rimal.
 *
 */
public class Network implements Graph<Host, Connection>, DirectedGraph<Host, Connection> {
	// We promise the network will never have more than this
	// many nodes... use that knowledge to simplify your code!
	/**
	 * This is the constant Max network size.
	 */
	private static final int MAX_NETWORK_SIZE = 255;

	// Adjacency List: Host 0's outgoing connections will be in list 0
	// and each key-value pair in list 0 is a host-connection pair from
	// host 0, to the host in the pair, using the connection in the pair.
	// You must use this as your internal storage, you may not change
	// the type, name, privacy, or anything else about this variable.
	/**
	 * This is the storage Array of list of pairs.
	 */
	private ArrayOfListsOfPairs<Host, Connection> storage;

	// Any other variables you want here! (Must be private.)
	// You may also add private methods if you'd like, but nothing
	// public.

	/**
	 * Default constructor that sets the max size.
	 */
	public Network() {
		// constructor to initialize what you want.
		this(MAX_NETWORK_SIZE);

	}

	/**
	 * Constructor which sets storage to ArrayOfListsOfPairs.
	 * 
	 * @param numLists
	 *            - Takes in integer of list.
	 */
	private Network(int numLists) {
		storage = new ArrayOfListsOfPairs<>(numLists);
	}

	/**
	 * Returns a view of all edges in this graph. In general, this obeys the
	 * Collection contract, and therefore makes no guarantees about the ordering of
	 * the vertices within the set.
	 * 
	 * @return a Collection view of all edges in this graph
	 */
	public Collection<Connection> getEdges() {
		ArrayList<Connection> list = new ArrayList<>();

		Iterator<KeyValuePair<Host, Connection>> iterator = storage.getAllPairs().iterator();

		while (iterator.hasNext()) {
			list.add(iterator.next().getValue());
		}
		return list;
	}

	/**
	 * Returns a view of all vertices in this graph. In general, this obeys the
	 * Collection contract, and therefore makes no guarantees about the ordering of
	 * the vertices within the set.
	 * 
	 * @return a Collection view of all vertices in this graph
	 */
	public Collection<Host> getVertices() {
		ArrayList<Host> list = new ArrayList<>();

		Collection<KeyValuePair<Host, Connection>> collection = storage.getAllPairs();

		int size = collection.size(); // initial size of the collection

		for (int i = 0; i < size; i++) {
			KeyValuePair<Host, Connection> valuepair = collection.iterator().next();
			collection.remove(valuepair);
			list.add(valuepair.getKey());
		}
		return list;
	}

	/**
	 * Returns true if this graph's vertex collection contains vertex. Equivalent to
	 * getVertices().contains(vertex).
	 * 
	 * @param vertex
	 *            the vertex whose presence is being queried
	 * @return true if this graph contains a vertex vertex
	 */
	public boolean containsVertex(Host vertex) {
		return getVertices().contains(vertex);
	}

	/**
	 * Returns true if this graph's edge collection contains edge. Equivalent to
	 * getEdges().contains(edge).
	 * 
	 * @param edge
	 *            the edge whose presence is being queried
	 * @return true iff this graph contains an edge edge
	 */
	public boolean containsEdge(Connection edge) {
		return getEdges().contains(edge);
	}

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return the number of edges in this graph
	 */
	public int getEdgeCount() {
		return getEdges().size();
	}

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return the number of vertices in this graph
	 */
	public int getVertexCount() {
		return getVertices().size();
	}

	/**
	 * Returns a Collection view of the outgoing edges incident to vertex in this
	 * graph.
	 * 
	 * @param vertex
	 *            the vertex whose outgoing edges are to be returned
	 * @return a Collection view of the outgoing edges incident to vertex in this
	 *         graph
	 */
	public Collection<Connection> getOutEdges(Host vertex) {
		return null;
	}

	/**
	 * Returns a Collection view of the incoming edges incident to vertex in this
	 * graph.
	 * 
	 * @param vertex
	 *            the vertex whose incoming edges are to be returned
	 * @return a Collection view of the incoming edges incident to vertex in this
	 *         graph
	 */
	public Collection<Connection> getInEdges(Host vertex) {
		return null;
	}

	/**
	 * Returns a Collection view of the predecessors of vertex in this graph. A
	 * predecessor of vertex is defined as a vertex v which is connected to vertex
	 * by an edge e, where e is an outgoing edge of v and an incoming edge of
	 * vertex.
	 * 
	 * @param vertex
	 *            the vertex whose predecessors are to be returned
	 * @return a Collection view of the predecessors of vertex in this graph
	 */
	public Collection<Host> getPredecessors(Host vertex) {
		Collection<Host> collections = new ArrayList<>();

		for (int i = 0; i < storage.getNumLists(); i++) {
			Collection<KeyValuePair<Host, Connection>> connectionIndex = storage.getAllPairs(i);

			for (int j = 0; j < connectionIndex.size(); j++) {
				KeyValuePair<Host, Connection> valuePair = connectionIndex.iterator().next();
				connectionIndex.remove(valuePair);

				if (valuePair.getKey() == vertex) {
					ArrayList<Host> temp = (ArrayList<Host>) getVertices();
					collections.add(temp.get(i));
				}
			}
		}
		return collections;

	}

	/**
	 * Returns a Collection view of the successors of vertex in this graph. A
	 * successor of vertex is defined as a vertex v which is connected to vertex by
	 * an edge e, where e is an incoming edge of v and an outgoing edge of vertex.
	 * 
	 * @param vertex
	 *            the vertex whose predecessors are to be returned
	 * @return a Collection view of the successors of vertex in this graph
	 */
	public Collection<Host> getSuccessors(Host vertex) {
		return null;
	}

	/**
	 * If directedEdge is a directed edge in this graph, returns the source;
	 * otherwise returns null. The source of a directed edge d is defined to be the
	 * vertex for which d is an outgoing edge. directedEdge is guaranteed to be a
	 * directed edge if its EdgeType is DIRECTED.
	 * 
	 * @param directedEdge
	 *            the edge to get the source of
	 * @return the source of directedEdge if it is a directed edge in this graph, or
	 *         null otherwise
	 */
	public Host getSource(Connection directedEdge) {
		return null;
	}

	/**
	 * If directedEdge is a directed edge in this graph, returns the destination;
	 * otherwise returns null. The destination of a directed edge d is defined to be
	 * the vertex incident to d for which d is an incoming edge. directedEdge is
	 * guaranteed to be a directed edge if its EdgeType is DIRECTED.
	 * 
	 * @param directedEdge
	 *            the edge to get the destination of
	 * @return the destination of directedEdge if it is a directed edge in this
	 *         graph, or null otherwise
	 */
	public Host getDest(Connection directedEdge) {
		return null;
	}

	/**
	 * Returns an edge that connects v1 to v2. If this edge is not uniquely defined
	 * (that is, if the graph contains more than one edge connecting v1 to v2), any
	 * of these edges may be returned. findEdgeSet(v1, v2) may be used to return all
	 * such edges. Returns null if either of the following is true:
	 * <ul>
	 * <li/>v1 is not connected to v2
	 * <li/>either v1 or v2 are not present in this graph
	 * </ul>
	 * <p>
	 * <b>Note</b>: for purposes of this method, v1 is only considered to be
	 * connected to v2 via a given <i>directed</i> edge e if v1 == e.getSource() &&
	 * v2 == e.getDest() evaluates to true. (v1 and v2 are connected by an
	 * undirected edge u if u is incident to both v1 and v2.)
	 * 
	 * @return an edge that connects v1 to v2, or null if no such edge exists (or
	 *         either vertex is not present)
	 * @see Hypergraph#findEdgeSet(Object, Object)
	 */
	public Connection findEdge(Host v1, Host v2) {
		return null;
	}

	/**
	 * Adds edge e to this graph such that it connects vertex v1 to v2. Equivalent
	 * to addEdge(e, new Pair<Host>(v1, v2)). If this graph does not contain v1, v2,
	 * or both, implementations may choose to either silently add the vertices to
	 * the graph or throw an IllegalArgumentException. If this graph assigns edge
	 * types to its edges, the edge type of e will be the default for this graph.
	 * See Hypergraph.addEdge() for a listing of possible reasons for failure.
	 * 
	 * @param e
	 *            the edge to be added
	 * @param v1
	 *            the first vertex to be connected
	 * @param v2
	 *            the second vertex to be connected
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object, EdgeType)
	 */
	public boolean addEdge(Connection e, Host v1, Host v2) {
		return false;
	}

	/**
	 * Adds vertex to this graph. Fails if vertex is null or already in the graph.
	 * 
	 * @param vertex
	 *            the vertex to add
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException
	 *             if vertex is null
	 */
	public boolean addVertex(Host vertex) {
		return false;
	}

	/**
	 * Removes edge from this graph. Fails if edge is null, or is otherwise not an
	 * element of this graph.
	 * 
	 * @param edge
	 *            the edge to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeEdge(Connection edge) {
		return false;
	}

	/**
	 * Removes vertex from this graph. As a side effect, removes any edges e
	 * incident to vertex if the removal of vertex would cause e to be incident to
	 * an illegal number of vertices. (Thus, for example, incident hyperedges are
	 * not removed, but incident edges--which must be connected to a vertex at both
	 * endpoints--are removed.)
	 * 
	 * <p>
	 * Fails under the following circumstances:
	 * <ul>
	 * <li/>vertex is not an element of this graph
	 * <li/>vertex is null
	 * </ul>
	 * 
	 * @param vertex
	 *            the vertex to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeVertex(Host vertex) {
		return false;
	}

	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------

	public String toString() {
		// you may edit this to make string representations of your
		// graph for testing, making a string representation for
		// ArrayOfListsOfPairs might be helpful here too.
		return super.toString();
	}

	public static void main(String[] args) {
		// Some example testing code...

		// create a set of 10 nodes and 10 edges to test with
		Host[] hosts = new Host[10];
		Factory<Host> hostFactory = Host.getFactory();
		for (int i = 0; i < hosts.length; i++) {
			hosts[i] = hostFactory.create();
		}

		Connection[] connections = new Connection[10];
		Factory<Connection> connFactory = Connection.getFactory();
		for (int i = 0; i < connections.length; i++) {
			connections[i] = connFactory.create();
		}

		// constructs a graph
		Network graph = new Network();
		graph.addVertex(hosts[0]);
		graph.addVertex(hosts[1]);
		graph.addEdge(connections[0], hosts[0], hosts[1]);

		// get the internal structure
		ArrayOfListsOfPairs<Host, Connection> intTable = graph.getInternalTable();

		// get the entries for host0
		ArrayList<KeyValuePair<Host, Connection>> pairs = intTable.getAllPairs(0);

		// there should be only one pair
		KeyValuePair<Host, Connection> pair = pairs.get(0);

		// make sure it's an entry connecting to host[1] using connection[0]
		if (pair.getKey().equals(hosts[1]) && pair.getValue().equals(connections[0])) {
			System.out.println("Yay");
		}
	}

	// ********************************************************************************
	// YOU MAY, BUT DON'T NEED TO, EDIT THINGS IN THIS SECTION, BUT DON'T BREAK
	// IT...
	// THERE ARE MUCH MORE OPTIMAL WAYS TO DO MANY OF THESE METHODS, SO IT MIGHT BE
	// GOOD TO LOOK HERE IF YOUR CODE IS SLOWER THAN IT NEEDS TO BE.
	// ********************************************************************************

	/**
	 * Returns true if v1 is a predecessor of v2 in this graph. Equivalent to
	 * v1.getPredecessors().contains(v2).
	 * 
	 * @param v1
	 *            the first vertex to be queried
	 * @param v2
	 *            the second vertex to be queried
	 * @return true if v1 is a predecessor of v2, and false otherwise.
	 */
	public boolean isPredecessor(Host v1, Host v2) {
		return getPredecessors(v2).contains(v1);
	}

	/**
	 * Returns true if v1 is a successor of v2 in this graph. Equivalent to
	 * v1.getSuccessors().contains(v2).
	 * 
	 * @param v1
	 *            the first vertex to be queried
	 * @param v2
	 *            the second vertex to be queried
	 * @return true if v1 is a successor of v2, and false otherwise.
	 */
	public boolean isSuccessor(Host v1, Host v2) {
		return getSuccessors(v2).contains(v1);
	}

	/**
	 * Returns the endpoints of edge as a Pair<Host>.
	 * 
	 * @param edge
	 *            the edge whose endpoints are to be returned
	 * @return the endpoints (incident vertices) of edge
	 */
	public Pair<Host> getEndpoints(Connection edge) {
		// System.out.println(getSource(edge).getId() + "---" + edge + "---" +
		// getDest(edge).getId());
		return new Pair<Host>(getSource(edge), getDest(edge));
	}

	/**
	 * Returns true if vertex and edge are incident to each other. Equivalent to
	 * getIncidentEdges(vertex).contains(edge) and to
	 * getIncidentVertices(edge).contains(vertex).
	 * 
	 * @param vertex
	 * @param edge
	 * @return true if vertex and edge are incident to each other
	 */
	public boolean isIncident(Host vertex, Connection edge) {
		return getIncidentEdges(vertex).contains(edge);
	}

	/**
	 * Returns true if v1 and v2 share an incident edge. Equivalent to
	 * getNeighbors(v1).contains(v2).
	 * 
	 * @param v1
	 *            the first vertex to test
	 * @param v2
	 *            the second vertex to test
	 * @return true if v1 and v2 share an incident edge
	 */
	public boolean isNeighbor(Host v1, Host v2) {
		return getNeighbors(v1).contains(v2);
	}

	/**
	 * Returns the collection of vertices which are connected to vertex via any
	 * edges in this graph. If vertex is connected to itself with a self-loop, then
	 * it will be included in the collection returned.
	 * 
	 * @param vertex
	 *            the vertex whose neighbors are to be returned
	 * @return the collection of vertices which are connected to vertex, or null if
	 *         vertex is not present
	 */
	public Collection<Host> getNeighbors(Host vertex) {
		if (!containsVertex(vertex))
			return null;
		ArrayList<Host> neighbors = new ArrayList<>();
		neighbors.addAll(getSuccessors(vertex));
		neighbors.addAll(getPredecessors(vertex));

		Connection c = findEdge(vertex, vertex);
		if (c != null)
			neighbors.remove(vertex);

		return neighbors;
	}

	/**
	 * Returns the collection of edges in this graph which are connected to vertex.
	 * 
	 * @param vertex
	 *            the vertex whose incident edges are to be returned
	 * @return the collection of edges which are connected to vertex, or null if
	 *         vertex is not present
	 */
	public Collection<Connection> getIncidentEdges(Host vertex) {
		if (!containsVertex(vertex))
			return null;
		ArrayList<Connection> edges = new ArrayList<>();
		edges.addAll(getOutEdges(vertex));
		edges.addAll(getInEdges(vertex));

		Connection c = findEdge(vertex, vertex);
		if (c != null)
			edges.remove(c);

		return edges;
	}

	/**
	 * Returns the number of incoming edges incident to vertex. Equivalent to
	 * getInEdges(vertex).size().
	 * 
	 * @param vertex
	 *            the vertex whose indegree is to be calculated
	 * @return the number of incoming edges incident to vertex
	 */
	public int inDegree(Host vertex) {
		return getInEdges(vertex).size();
	}

	/**
	 * Returns the number of vertices that are adjacent to vertex (that is, the
	 * number of vertices that are incident to edges in vertex's incident edge set).
	 * 
	 * <p>
	 * Equivalent to getNeighbors(vertex).size().
	 * 
	 * @param vertex
	 *            the vertex whose neighbor count is to be returned
	 * @return the number of neighboring vertices
	 */
	public int getNeighborCount(Host vertex) {
		return getNeighbors(vertex).size();
	}

	/**
	 * Returns the number of edges incident to vertex. Special cases of interest:
	 * <ul>
	 * <li/>Incident self-loops are counted once.
	 * <li>If there is only one edge that connects this vertex to each of its
	 * neighbors (and vice versa), then the value returned will also be equal to the
	 * number of neighbors that this vertex has (that is, the output of
	 * getNeighborCount).
	 * <li>If the graph is directed, then the value returned will be the sum of this
	 * vertex's indegree (the number of edges whose destination is this vertex) and
	 * its outdegree (the number of edges whose source is this vertex), minus the
	 * number of incident self-loops (to avoid double-counting).
	 * </ul>
	 * <p>
	 * Equivalent to getIncidentEdges(vertex).size().
	 * 
	 * @param vertex
	 *            the vertex whose degree is to be returned
	 * @return the degree of this node
	 * @see Hypergraph#getNeighborCount(Object)
	 */
	public int degree(Host vertex) {
		return getIncidentEdges(vertex).size();
	}

	/**
	 * Returns the number of outgoing edges incident to vertex. Equivalent to
	 * getOutEdges(vertex).size().
	 * 
	 * @param vertex
	 *            the vertex whose outdegree is to be calculated
	 * @return the number of outgoing edges incident to vertex
	 */
	public int outDegree(Host vertex) {
		return getOutEdges(vertex).size();
	}

	/**
	 * Returns the number of predecessors that vertex has in this graph. Equivalent
	 * to vertex.getPredecessors().size().
	 * 
	 * @param vertex
	 *            the vertex whose predecessor count is to be returned
	 * @return the number of predecessors that vertex has in this graph
	 */
	public int getPredecessorCount(Host vertex) {
		return getPredecessors(vertex).size();
	}

	/**
	 * Returns the number of successors that vertex has in this graph. Equivalent to
	 * vertex.getSuccessors().size().
	 * 
	 * @param vertex
	 *            the vertex whose successor count is to be returned
	 * @return the number of successors that vertex has in this graph
	 */
	public int getSuccessorCount(Host vertex) {
		return getSuccessors(vertex).size();
	}

	/**
	 * Returns the vertex at the other end of edge from vertex. (That is, returns
	 * the vertex incident to edge which is not vertex.)
	 * 
	 * @param vertex
	 *            the vertex to be queried
	 * @param edge
	 *            the edge to be queried
	 * @return the vertex at the other end of edge from vertex
	 */
	public Host getOpposite(Host vertex, Connection edge) {
		Pair<Host> p = getEndpoints(edge);
		if (p.getFirst().equals(vertex)) {
			return p.getSecond();
		} else {
			return p.getFirst();
		}
	}

	/**
	 * Returns all edges that connects v1 to v2. If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting v1 to
	 * v2), any of these edges may be returned. findEdgeSet(v1, v2) may be used to
	 * return all such edges. Returns null if v1 is not connected to v2. <br/>
	 * Returns an empty collection if either v1 or v2 are not present in this graph.
	 * 
	 * <p>
	 * <b>Note</b>: for purposes of this method, v1 is only considered to be
	 * connected to v2 via a given <i>directed</i> edge d if v1 == d.getSource() &&
	 * v2 == d.getDest() evaluates to true. (v1 and v2 are connected by an
	 * undirected edge u if u is incident to both v1 and v2.)
	 * 
	 * @return a collection containing all edges that connect v1 to v2, or null if
	 *         either vertex is not present
	 * @see Hypergraph#findEdge(Object, Object)
	 */
	public Collection<Connection> findEdgeSet(Host v1, Host v2) {
		Connection edge = findEdge(v1, v2);
		if (edge == null) {
			return null;
		}

		ArrayList<Connection> ret = new ArrayList<>();
		ret.add(edge);
		return ret;

	}

	/**
	 * Returns true if vertex is the source of edge. Equivalent to
	 * getSource(edge).equals(vertex).
	 * 
	 * @param vertex
	 *            the vertex to be queried
	 * @param edge
	 *            the edge to be queried
	 * @return true iff vertex is the source of edge
	 */
	public boolean isSource(Host vertex, Connection edge) {
		return getSource(edge).equals(vertex);
	}

	/**
	 * Returns true if vertex is the destination of edge. Equivalent to
	 * getDest(edge).equals(vertex).
	 * 
	 * @param vertex
	 *            the vertex to be queried
	 * @param edge
	 *            the edge to be queried
	 * @return true iff vertex is the destination of edge
	 */
	public boolean isDest(Host vertex, Connection edge) {
		return getDest(edge).equals(vertex);
	}

	/**
	 * Returns the collection of vertices in this graph which are connected to edge.
	 * Note that for some graph types there are guarantees about the size of this
	 * collection (i.e., some graphs contain edges that have exactly two endpoints,
	 * which may or may not be distinct). Implementations for those graph types may
	 * provide alternate methods that provide more convenient access to the
	 * vertices.
	 * 
	 * @param edge
	 *            the edge whose incident vertices are to be returned
	 * @return the collection of vertices which are connected to edge, or null if
	 *         edge is not present
	 */
	public Collection<Host> getIncidentVertices(Connection edge) {
		if (!containsEdge(edge))
			return null;

		ArrayList<Host> vert = new ArrayList<>();

		Host source = getSource(edge);
		Host dest = getDest(edge);

		vert.add(source);
		if (!source.equals(dest))
			vert.add(dest);

		return vert;
	}

	/**
	 * Returns the number of edges of type edgeType in this graph.
	 * 
	 * @param edgeType
	 *            the type of edge for which the count is to be returned
	 * @return the number of edges of type edgeType in this graph
	 */
	public int getEdgeCount(EdgeType edgeType) {
		if (edgeType == EdgeType.DIRECTED) {
			return getEdgeCount();
		}
		return 0;
	}

	/**
	 * Returns the collection of edges in this graph which are of type edgeType.
	 * 
	 * @param edgeType
	 *            the type of edges to be returned
	 * @return the collection of edges which are of type edgeType, or null if the
	 *         graph does not accept edges of this type
	 * @see EdgeType
	 */
	public Collection<Connection> getEdges(EdgeType edgeType) {
		if (edgeType == EdgeType.DIRECTED) {
			return getEdges();
		}
		return null;
	}

	/**
	 * Adds edge e to this graph such that it connects vertex v1 to v2. Equivalent
	 * to addEdge(e, new Pair<Host>(v1, v2)). If this graph does not contain v1, v2,
	 * or both, implementations may choose to either silently add the vertices to
	 * the graph or throw an IllegalArgumentException. If edgeType is not legal for
	 * this graph, this method will throw IllegalArgumentException. See
	 * Hypergraph.addEdge() for a listing of possible reasons for failure.
	 * 
	 * @param e
	 *            the edge to be added
	 * @param v1
	 *            the first vertex to be connected
	 * @param v2
	 *            the second vertex to be connected
	 * @param edgeType
	 *            the type to be assigned to the edge
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object)
	 */
	public boolean addEdge(Connection e, Host v1, Host v2, EdgeType edgeType) {
		// NOTE: Only directed edges allowed

		if (edgeType == EdgeType.UNDIRECTED) {
			throw new IllegalArgumentException();
		}

		return addEdge(e, v1, v2);
	}

	/**
	 * Adds edge to this graph. Fails under the following circumstances:
	 * <ul>
	 * <li/>edge is already an element of the graph
	 * <li/>either edge or vertices is null
	 * <li/>vertices has the wrong number of vertices for the graph type
	 * <li/>vertices are already connected by another edge in this graph, and this
	 * graph does not accept parallel edges
	 * </ul>
	 * 
	 * @param edge
	 * @param vertices
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException
	 *             if edge or vertices is null, or if a different vertex set in this
	 *             graph is already connected by edge, or if vertices are not a
	 *             legal vertex set for edge
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(Connection edge, Collection<? extends Host> vertices) {
		if (edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}

		Host[] vs = (Host[]) vertices.toArray();
		return addEdge(edge, vs[0], vs[1]);
	}

	/**
	 * Adds edge to this graph with type edgeType. Fails under the following
	 * circumstances:
	 * <ul>
	 * <li/>edge is already an element of the graph
	 * <li/>either edge or vertices is null
	 * <li/>vertices has the wrong number of vertices for the graph type
	 * <li/>vertices are already connected by another edge in this graph, and this
	 * graph does not accept parallel edges
	 * <li/>edgeType is not legal for this graph
	 * </ul>
	 * 
	 * @param edge
	 * @param vertices
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException
	 *             if edge or vertices is null, or if a different vertex set in this
	 *             graph is already connected by edge, or if vertices are not a
	 *             legal vertex set for edge
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(Connection edge, Collection<? extends Host> vertices, EdgeType edgeType) {
		if (edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}

		Host[] vs = (Host[]) vertices.toArray();
		return addEdge(edge, vs[0], vs[1], edgeType);
	}

	// ********************************************************************************
	// DO NOT EDIT ANYTHING BELOW THIS LINE EXCEPT TO ADD/CORRECT JAVADOCS
	// ********************************************************************************

	// This will be used to check that you are setting
	// the storage up correctly... it's very important
	// that you (1) are using the ArrayOfListsOfPairs
	// provided and (2) don't edit this at all
	public ArrayOfListsOfPairs<Host, Connection> getInternalTable() {
		return storage;
	}

	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * 
	 * @param <Host>
	 *            the vertex type for the graph factory
	 * @param <Connection>
	 *            the edge type for the graph factory
	 */
	@SuppressWarnings("unchecked")
	public static Factory<Graph<Host, Connection>> getFactory() {
		return new Factory<Graph<Host, Connection>>() {
			public Graph<Host, Connection> create() {
				return (Graph<Host, Connection>) new Network();
			}
		};
	}

	/**
	 * Returns the edge type of edge in this graph.
	 * 
	 * @param edge
	 * @return the EdgeType of edge, or null if edge has no defined type
	 */
	public EdgeType getEdgeType(Connection edge) {
		return EdgeType.DIRECTED;
	}

	/**
	 * Returns the default edge type for this graph.
	 * 
	 * @return the default edge type for this graph
	 */
	public EdgeType getDefaultEdgeType() {
		return EdgeType.DIRECTED;
	}

	/**
	 * Returns the number of vertices that are incident to edge. For hyperedges,
	 * this can be any nonnegative integer; for edges this must be 2 (or 1 if
	 * self-loops are permitted).
	 * 
	 * <p>
	 * Equivalent to getIncidentVertices(edge).size().
	 * 
	 * @param edge
	 *            the edge whose incident vertex count is to be returned
	 * @return the number of vertices that are incident to edge.
	 */
	public int getIncidentCount(Connection edge) {
		return 2;
	}
}
