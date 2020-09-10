# Discussion

## Part A
1. Discuss from a design perspective whether or not iterating over a MeasuredArray should affect the accesses and mutation counts.
    * Calling the hasNext() method should not affect the access or mutate counts. This is because this just
    compares the internal current index with the length metadata. No values in the array are looked up.
    Calling the next() method should update the access count. Each call of the next() method returns the value
    at the current index and then increments the current index forward. This constitutes a lookup of an array 
    value. This could be problematic if the user wanted to iterate over values to advance the cursor and was
    not interested in the values themselves. However, the next() method is defined to return an element of
    the array, so this might not be practical to modify. The user might not find iterators practical
    for their approach in this case.
2. Can you inherit ArrayIterator from SimpleArray and override the relevant methods, or not? Explain.
    * Yes, it should be possible to extend the ArrayIterator class to a MeasuredArrayIterator subclass nested
    in the MeasuredArray class. Then, the next() method could be overridden to adjust the access counts.
    * No, it is not possible to inherit the ArrayIterator from SimpleArray because it is an inner class, which cannot
    be extended by classes outside of its outer classes. A separate MeasureArrayIterator inner class would have to be
    declared in MeasureArray, which could also implement Iterator.

## Part B
Include the results of experiments (must have concrete time measurements and size of data set used).
1. There is an intentional mistake in one of the provided data files. The goal of this assignment is to use the measurements to catch that mistake. 
2. Does the actual running time correspond to the asymptotic complexity as you would expect?
3. What explains the practical differences between these algorithms? (Theoretically, the algorithm runs in O(X) time, where X is a function of the input size, but in practice (i.e running it on datasets), you may observe that it is slower/faster compared to the other algorithms)
4. Does it matter what kind of data (random, already sorted in ascending
 order, sorted in descending order) you are sorting? How should each algorithm behave (in terms of performance) based on
  the
  type of data it
  receives?
5. (IGNORE) How do your implementations compare to the sorting done by Java
 Collections? Do you think that the standardized code is just better written than yours or is more asymptoticly efficient? How would you go about determining which is the case?

## Part C
1. Determine exactly how many comparisons C(n) and assignments A(n) are performed by this implementation of selection sort in the worst case. Both of those should be polynomials of degree 2 since you know that the asymptotic complexity of selection sort is O(n^2).

