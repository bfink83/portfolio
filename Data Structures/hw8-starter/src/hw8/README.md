# Homework 8

## Discussion 

```
  JHU to Druid Lake
  Starting location: -76.6175,39.3296
  Ending location: -76.6383,39.3206

  7-11 to Druid Lake
  Starting location: -76.6214,39.3212
  Ending location: -76.6383,39.3206

  Inner Harbor to JHU
  Starting location: -76.6107,39.2866
  Ending location: -76.6175,39.3296

```

**Comment on how the memory and times vary
for the different queries** -- the main question is *why* does it change
when searching the exact same graph.  Make sure to also report the
total length (in meters) between the locations, and the names of the
road segments used. If your program fails to find a path, please
report this as well, and explain why you think it did not find a path.

I could not figure out how to implement Djikstra's algorithm. I believe that it is very close to
complete, but I ran out of time before I could finish it off, so I commented it out.

I am, however, going to take a guess as to how the memory and times vary for the different 
queries. I believe that they vary based on the number of edges, or roads, are being searched. 
For some, if the shortest path is a direct path, it will greatly reduce the memory and times used
as opposed to a query that must check multiple different edges every block to see which combination
of streets is the shortest.