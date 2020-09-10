# Data Structure { Spring 2020 } Take Home Final Exam

## Question 1
For each error (bug) in the implementation of topological sort, please indicate 
* what the error was
* how you fix it
* and (very briefly) how you found it.

1.  a) The for each loop for each outgoing edge of each vertex was updating the incoming degree of the outgoing vertex, 
    instead of the incoming vertex.
    b) To fix it, I changed the 'v' parameter in indegree.put(v, indegree.get(u) - 1) to a 'u' so it becomes indegree.put(u, indegree.get(u) - 1).
    c) I found it through the Java Visualizer when I noticed that the code was attempting to update the incoming
    degree of 'A' when I know that the incoming degree of a vertex is only updated if it is found in an outgoing vertex list. 
    So, since 'A' has an incoming degree of zero, it should never be in an outgoing list. Therefore, the fact that the program was trying to update it is wrong,
    and it should instead be updating the incoming degree of the vertex that was found in the outgoing list according to the algorithm.
    
2.  a) The for each loop added each outgoing vertex to the visitNext stack regardless of if it was already in the stack and if it
    should even be in the stack (vertices should only be added to the visitNext stack if they have zero incoming edges).
    b) To fix it, I added an if statement to ensure that the stack did not already contain the vertex we are looking at and to make sure
    the vertex has zero incoming edges.
    c) The second error was pretty obvious when looking at the initial output of the main function. Many of the vertices were
    repeated, so it was clear that there was no check to see if the vertex had already been added to the stack that determined which vertices
    were added to sortedOrder during the next loop. On top of that, I realized that there should be another condition as well, to check
    to ensure that the vertex being pushed has no incoming edges and should indeed be pushed onto the visitNext stack.

## Question 2

### How does the modified Dijkstra algorithm work?
First, the algorithm must determine whether number of connections, or flight time is weighted heavier when compared to each other. 
The compareTo() overridden method determines that total flight time is weighted slightly heavier when breaking ties. So, the algorithm
looks at flight time to see if it should update the shortest distance to each vertex, and if the flight time is even, it will check
the number of edges (stops). The vertex with updated distance and stops cost is then added to the toExplore set to ensure that every
vertex is explored to find the shortest path. OutputShortestPath outputs each vertex's previous field, which is only updated when 
updated total time is less than the time before, or they are even and updated total stops is less than the total stops before. Therefore,
it finds the shortest path based on the specifications of both time and stops.

### What is the time/space complexity of the modified Dijkstra algorithm as it is implemented in Dijkstra.java?
The typical Dijkstra's algorithm implementation runs in O(N^2). When using a priority queue, it can be O(M + NlogN).
In this case, the algorithm uses a treeSet to hold the vertices to be explored, and they are compared using time first, 
and then number of stops, so to explore and update that would be O(logM) where M is total unexplored neighbors. All 
neighbors must be explored for each vertex, so it is multiplied by number of vertices N, and then since each vertex is 
unexplored at some point, it is multiplied by N again, so the total time/space complexity ends up being O(N^2 + logM).