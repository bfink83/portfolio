# Discussion

### When would a `SparseArray` be useful and why?

A sparse array would be useful when the client has a large data set that is almost entirely made up of the same element.
It is useful because it would save storage by only saving elements and indexes that are dissimilar to the majority element.
Since the data set is made up mostly of the same value and that value isn't stored, very little storage would be used when using a sparse array.

### Why is it that `SparseArray` must implement `iterator()`? Also, why is it
  beneficial for `SparseArray` to have an iterator?
  
It must implement iterator() because it is a method in an interface that is implemented within SparseArray.
It is beneficial for SparseArray to have an iterator because it allows the client to return the elements at each index in the array,
 not just the elements that are saved as Nodes.