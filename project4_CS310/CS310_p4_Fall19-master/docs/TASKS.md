## Tasks
[back](README.md)

There are **5** tasks in this assignment. It is suggested that you implement these tasks in the given order. 

### Task 1: Examine the JCF Classes (0%)

Read and familiarize yourself with the following JCF classes. Some of these are allowed (or required) in certain parts of the code. Below is an overview:

1. [ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) and [LinkedList](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html) - Java's list classes supported by a dynamic array or a linked structure respectively
2. [PriorityQueue](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html) - Java's priority queue (supported by a heap)
3. [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) - All JCF classes implement this generic interface.

Where should you start? The Java Tutorials of course! (If you didn't know, Oracle's official Java documentation includes a set of tutorials.) The [Trail: Collections](https://docs.oracle.com/javase/tutorial/collections/) tutorial will provide you with more than enough information on how to use these classes.

### Task 2: Read the Provided Code Base (0%)

Read and familiarize yourself with the code. This will save you a lot of time later. An overview of the provided code in is given below, but you need to read the code base yourself.

It is HIGHLY RECOMMENDED that you write your JavaDocs for this project _first_ and during this stage. That way you will have a full understanding of the code base as you work.

```java

//This is keeps a key and value together as a single unit. It is provided.
class KeyValuePair<K,V> {...}

//This is an array where each entry is the head of its own linked list.
//Each node in each linked list contains a key-value pair.
//You will write 99% of this class, but a template is provided.
class ArrayOfListsOfPairs<K,V> {...}

//Represents a host in a network (i.e. a computer in a network).
//It is provided.
class Host {...}

//Represents a connection in the network (i.e. a wired or wireless
//connection). It has a weight, which indicates how much time in 
//takes to send on that connection in ms. It is provided.
class Connection {...}

//This class represents a directed graph formed by connecting
//hosts in the network with connections (e.g. wires).
//You will write 99% of this class, but a template is provided.
class Network {...}

//This is a hash table which forms a map of key-value pairs.
//You will write 99% of this class, but a template is provided.
class HashTable<K,V> {...}

//This extends HashTable<Host,Host> and is used to store routes
//for network hosts. The only change from HashTable is a toString()
//method, which is provided.
class RoutingTable {...}

//You will complete a small amount of code in this class to kick-off
//Dijkstra's shortest path algorithm and setup the routing tables
//for the network.
class ThreeTenNetwork {...}

//This is the simulator and handles all the graphical stuff, it is provided.
class SimGUI {...}

```

### Task 3: Implement a Directed Graph Class to Support the Network Structure (60%)

In order for the simulator to work, you need an internal representation of a graph. The JUNG library provides an interfaces for this: `Graph<V,E>`. You need to implement the directed graph `Network<Host,Connection>` (in `Network.java`) which implements the `Graph<Host,Connection>` interface. The Network class has some internal storage setup for you (and `ArrayOfListsOfPairs`), you have a lot of freedom in how you'd like to implement this underlying support class, so make sure to read the instructions there.

Below is a quick overview of the methods you need to support. Note that in the template, actual JavaDoc comments are provided. That said, the JavaDocs are those from the `Graph<>` interface and the `HyperGraph<>` interface in JUNG. They have been copied from that library for your reference, but are otherwise unaltered. Part of this assignment is to practice reading "real" documentation and understanding what to implement based on the library's requirements.

```java

//********************************
// Graph Editing (20%)
//********************************

boolean addEdge(E e, V v1, V v2) {...}
boolean addVertex(V vertex) {...}

boolean removeEdge(E edge) {...}
boolean removeVertex(V vertex) {...}


//********************************
// Graph Information (40%)
//********************************

//For a given graph...

Collection<E> getEdges() {...}
Collection<V> getVertices()  {...}

boolean containsVertex(V vertex) {...}
boolean containsEdge(E edge) {...}

int getEdgeCount() {...}
int getVertexCount() {...}


//For a given vertex in a graph...

Collection<E> getInEdges(V vertex) {...}
Collection<E> getOutEdges(V vertex) {...}

Collection<V> getPredecessors(V vertex) {...}
Collection<V> getSuccessors(V vertex) {...}


//Given two verticies in a graph...

E findEdge(V v1, V v2) {...}


//Given an edge in a graph...

V getSource(E directed_edge) {...}
V getDest(E directed_edge) {...}
 
```

When you are done with this step, you can generate and play with some graphs in the simulator (see the [Examples Page](EXAMPLES.md "")).

_Hints and Notes_
- Read ALL the methods before you decide how to implement any methods, you may need track a lot more things than the simple graphs we covered in class.
- Note that we cannot test editing a graph or getting information about a graph independently of each other. So you cannot get points for completing only the graph editing or only the graph information parts of this interface, you need everything...

### Task 4: Implement a Hash Table Class to Support the Network Routing (40%)

The hosts must have routing tables in order to send messages to each other. This is typically done with a map (host destination -> host next hop). The "next hop" when routing is the immediate neighbor to send messages to when they are addressed to a certain destination. For example, if we have the following network:

```
(A) <--> (B) <--> (C) <--> (D)
```

And host A wants to send to host D, then the "next hop" is host B. Similarly, host B's next hop for sending to host D is host C.

The typical routing mechanism used in small networks (such as a LAN) to setup these routing tables is to use algorithms like [OSPF](https://en.wikipedia.org/wiki/Open_Shortest_Path_First) which simply runs Dijkstra's shortest path algorithm from each node in the network and builds the routing tables based on the parents discovered. In the above example, one would run Dijkstra's shortest path starting at host A. This would setup the parent of host D as host C, the parent of host C as host B, and the parent of host B as host A. Reversing this we get the next hops.

You need to construct a hash table with separate chaining to support the routing table. Some internal storage is setup for you (and `ArrayOfListsOfPairs`). Below is a quick overview of the methods you need to support. Note that in the template, actual comments are provided.

```java

//********************************
// Hash Table (20%)
//********************************

//constructor
HashTable(int numSlots) {...}

//accessors
int size() {...}
int getNumSlots() {...}
double getLoad() {...}

//manipulation
boolean add(K key, V value) {...}
boolean remove(K key) {...}
V get(K key) {...}
void rehash(int newSize) {...}

```

### Task 5: Finish Dijkstra's Shortest Path Algorithm in the Simulator (20%)

Now for the fun part! The simulator need just a little bit more code to do the routing. There are two "YOUR CODE HERE" parts in `ThreeTenNetwork`. The first initializes some basic things for Dijkstra's algorithm to run. The second takes the results of Dijkstra's algorithm and creates the routing tables. These are both used by the `runDijkstra()` method in the "do not edit" section of `ThreeTenNetwork`. Please remember that one of the most common tasks in industry is reading other people's code and making very small contributions to integrate your own, and this is what you are practicing here. It's a time consuming task, but the skill is very important.

```java

//********************************
// Routing (20%)
//********************************

void setupDijkstras(Network graph,
		Host startNode,
		PriorityQueue<DijkstraNode> queue,
		HashTable<Host,DijkstraNode> hostToDijkstraNode)
		{...}

void setupTables(Network graph,
		Host startNode,
		HashTable<Host,DijkstraNode> hostToDijkstraNode)
		{...}

```

When you are done with this step, you can play the algorithm in the simulator (see the [Examples Page](EXAMPLES.md "")).

_Hints and Notes_
- You are not responsible for making the algorithm work if the user edits the graph after Dijkstra's has been run. Just assume all editing will take place before hitting "route".


### Misc Items: JavaDocs, Style, etc.

You are required to complete the JavaDocs and adhere to the style checker as you have been for all previous projects Please make sure to use the `.xml` files provided with this project specifically as they have been very slightly altered. The checkstyle.jar and associated .xml files are the same as on previous projects. You may need to correct/edit the provided style and/or JavaDocs. The JUNG library comments don't quite adhere to the style requirements for this class, so you must fix them (this is a normal process when integrating code from other libraries -- again, boring but necessary).
