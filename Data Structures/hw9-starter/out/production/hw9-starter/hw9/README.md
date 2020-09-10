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
The compareTo() overridden method determines that total flight time is weighted slightly heavier when breaking ties, but for the sake
of the algorithm, the two are summed to create a total cost variable. That is the main distance when considering both variables, and it
is implemented in the updateNeighbors function, when

### What is the time/space complexity of the modified Dijkstra algorithm as it is implemented in Dijkstra.java?