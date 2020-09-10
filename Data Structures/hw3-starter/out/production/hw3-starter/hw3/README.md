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
    * No, it is not possible to inherit the ArrayIterator from SimpleArray because it is a private inner class, 
    which cannot be extended by classes outside of its outer class. A separate MeasureArrayIterator inner class 
    would have to be declared in MeasureArray, which could also implement Iterator, or ArrayIterator would need
    to be made protected.

## Part B
Include the results of experiments (must have concrete time measurements and size of data set used).
1. There is an intentional mistake in one of the provided data files. The goal of this assignment is to use the measurements to catch that mistake. 
    * In the ascending dataset, the values are in incorrect order. Numerical order is used instead
    of lexicographical order. This error is apparent in the metrics because if a dataset was in the correct
    ascending order, we would expect no mutations from sorting it to ascending order. The sorting algorithms use
    lexicographical order because SortingAlgorithmDriver passes them as strings and the comparison behavior is
    automatically selected by the .compareTo() method as lexicographical for the string input.
2. Does the actual running time correspond to the asymptotic complexity as you would expect?
    * Yes, the runtime of these operations is O(N^2). For a dataset where N = 4000, We expect about 16 million
    operations. Looking at the mutations and access counts, we see that most are on the order of millions or tens of
    millions. The time to run is less reliable but we will consider this as well. We ran the driver on a 2.7 GHz CPU
    with estimated 15 instructions per clock. We expect roughly 16 million executions as before. 
    (1 sec / (2.7×10^9)) * 16000000 clocks * 15 IPC = .089 seconds. Many of the runtimes were on this magnitude. 
3. What explains the practical differences between these algorithms? (Theoretically, the algorithm runs in O(X) time, where X is a function of the input size, but in practice (i.e running it on datasets), you may observe that it is slower/faster compared to the other algorithms)
    * Insertion sort seems to be faster than the other algorithms. This is due to its implementation of binary search, 
    which allows it efficiently find the spot to insert the data without looping over all data. Also, the inner loop
    of the insertion sort loops over the sorted partition, which is much smaller than the dataset as a whole for much
    of the runtime. The slowest is between bubble sort and gnome sort. Bubble sort is slow because its inner loop
    traverses the whole dataset. It also makes extensive use of swaps, which is a many step operation. 
    Selection sort avoids the extensive swap use of bubble sort, so is faster in general. However, its lack of an early
    break optimization means that it requires similar access counts regardless of the dataset order.
4. Does it matter what kind of data (random, already sorted in ascending
 order, sorted in descending order) you are sorting? How should each algorithm behave (in terms of performance) based on
  the
  type of data it
  receives?
    * Yes it does. Ascending data is already sorted so these require no mutations and less access. Bubble sort is able to quickly
    recognize that a dataset is already sorted, as it ends after looping through the data once and not swapping. 
    This explains why bubble sort is the most efficient on ascending datasets.
    Descending datasets are the most demanding when faced with these algorithms because none of the data are in the 
    correct spot except possibly the middle. In addition, descending datasets are difficult for the insertion sort's
    binary search because many of the search targets are towards the ends of the array. This explains the insertion sort
    nearly doubling in operations versus the random set. One solution to this problem
    is that specialized algorithms exist to reverse the order of a list. Reversing a descending list yields an
    ascending list.
    The random data set favors insertion sort, then selection sort, then bubble sort last. This is the expected overall
    efficiency of the algorithms. As discussed earlier, insertion sort is fastest because of binary search and 
    its efficient inner loop. Bubble sort is slowest because of its poorly optimized inner loop and frequent use of 
    swaps. 
    
    Collected Data:
    
    Run #1:
    ascending.data    Null Sort        false    0            0            0.000006    
    ascending.data    Gnome Sort       true     15,230,058   5,074,020    0.107251    
    ascending.data    Selection Sort   true     16,003,980   7,980        0.066770    
    ascending.data    Bubble Sort      true     3,988,000    5,074,020    0.078726    
    ascending.data    Insertion Sort   true     2,769,851    2,540,980    0.048135    
    
    descending.data   Null Sort        false    0            0            0.000001    
    descending.data   Gnome Sort       true     47,988,000   15,996,000   0.208908    
    descending.data   Selection Sort   true     16,000,000   4,000        0.107852    
    descending.data   Bubble Sort      true     16,000,000   15,996,000   0.228756    
    descending.data   Insertion Sort   true     8,305,339    8,001,999    0.056809    
    
    random.data       Null Sort        false    0            0            0.000002    
    random.data       Gnome Sort       true     24,145,478   8,045,828    0.176254    
    random.data       Selection Sort   true     16,003,992   7,992        0.077461    
    random.data       Bubble Sort      true     15,404,000   8,045,828    0.322120    
    random.data       Insertion Sort   true     4,259,191    4,026,905    0.035488 
    
    Run #2:
    ascending.data    Null Sort        false    0            0            0.000006    
    ascending.data    Gnome Sort       true     15,230,058   5,074,020    0.169366    
    ascending.data    Selection Sort   true     16,003,980   7,980        0.096122    
    ascending.data    Bubble Sort      true     3,988,000    5,074,020    0.123888    
    ascending.data    Insertion Sort   true     2,769,851    2,540,980    0.041830    
    
    descending.data   Null Sort        false    0            0            0.000001    
    descending.data   Gnome Sort       true     47,988,000   15,996,000   0.187036    
    descending.data   Selection Sort   true     16,000,000   4,000        0.077326    
    descending.data   Bubble Sort      true     16,000,000   15,996,000   0.194779    
    descending.data   Insertion Sort   true     8,305,339    8,001,999    0.051610    
    
    random.data       Null Sort        false    0            0            0.000001    
    random.data       Gnome Sort       true     24,145,478   8,045,828    0.144075    
    random.data       Selection Sort   true     16,003,992   7,992        0.080468    
    random.data       Bubble Sort      true     15,404,000   8,045,828    0.350077    
    random.data       Insertion Sort   true     4,259,191    4,026,905    0.044211  
    
    Run #3:
    ascending.data    Null Sort        false    0            0            0.000006    
    ascending.data    Gnome Sort       true     15,230,058   5,074,020    0.131853    
    ascending.data    Selection Sort   true     16,003,980   7,980        0.122116    
    ascending.data    Bubble Sort      true     3,988,000    5,074,020    0.143607    
    ascending.data    Insertion Sort   true     2,769,851    2,540,980    0.062860    
    
    descending.data   Null Sort        false    0            0            0.000001    
    descending.data   Gnome Sort       true     47,988,000   15,996,000   0.239765    
    descending.data   Selection Sort   true     16,000,000   4,000        0.088024    
    descending.data   Bubble Sort      true     16,000,000   15,996,000   0.218963    
    descending.data   Insertion Sort   true     8,305,339    8,001,999    0.062822    
    
    random.data       Null Sort        false    0            0            0.000001    
    random.data       Gnome Sort       true     24,145,478   8,045,828    0.203885    
    random.data       Selection Sort   true     16,003,992   7,992        0.121103    
    random.data       Bubble Sort      true     15,404,000   8,045,828    0.302321    
    random.data       Insertion Sort   true     4,259,191    4,026,905    0.030162 
    
    Run with linear search implemented for insertion sort instead of binary search:
    ascending.data    Null Sort        false    0            0            0.000006    
    ascending.data    Gnome Sort       true     15,230,058   5,074,020    0.152150    
    ascending.data    Selection Sort   true     16,003,980   7,980        0.112302    
    ascending.data    Bubble Sort      true     3,988,000    5,074,020    0.105058    
    ascending.data    Insertion Sort   true     7,623,027    2,540,980    0.047347    
    
    descending.data   Null Sort        false    0            0            0.002854    
    descending.data   Gnome Sort       true     47,988,000   15,996,000   0.165935    
    descending.data   Selection Sort   true     16,000,000   4,000        0.067122    
    descending.data   Bubble Sort      true     16,000,000   15,996,000   0.155946    
    descending.data   Insertion Sort   true     23,997,999   8,001,999    0.067290    
    
    random.data       Null Sort        false    0            0            0.000001    
    random.data       Gnome Sort       true     24,145,478   8,045,828    0.147225    
    random.data       Selection Sort   true     16,003,992   7,992        0.088609    
    random.data       Bubble Sort      true     15,404,000   8,045,828    0.461673    
    random.data       Insertion Sort   true     12,080,735   4,026,905    0.066528  
    
    Run with fixed ascending.data file:
    
5. (IGNORE) How do your implementations compare to the sorting done by Java
 Collections? Do you think that the standardized code is just better written than yours or is more asymptoticly efficient? How would you go about determining which is the case?

## Part C
1. Determine exactly how many comparisons C(n) and assignments A(n) are performed by this implementation of selection sort in the worst case. Both of those should be polynomials of degree 2 since you know that the asymptotic complexity of selection sort is O(n^2).

