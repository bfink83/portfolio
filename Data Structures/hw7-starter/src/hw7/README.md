## HW7 Discussion

1. Which hashmap approaches did you try, in what order, and why did you
   choose them? What specific tweaks to HashMap improved or
   made things worse?  Include failed or abandoned attempts if any, and
   why. Summarize all the different ways you developed, evaluated and
   improved your hashmaps.
   
   I only attempted cuckoo hashing because of its O(1) guaranteed lookup and deletion. I researched all
   of the different hashing methods beforehand so I could use my time efficiently and only implement the
   the most efficient method instead of wasting my time implementing slower ones. I only realized after I
   completed the assignment that we are required to implement other hashing methods.
   
   With respect to my implementation of cuckoo hashing, the insert() method was indeed the most difficult to
   implement. I had to restart that method from scratch at least two or three times. Once because of an infinte
   loop, which I countered by implementing a pre-determined stopping point. Another time I restarted because I 
   realized that I implemented my method recursively by accident, which would be extremely inefficient in this context.
   My final implementation included a while loop with a pre-determined cutoff as well as a boolean that signalled if any
   values were bumped from their original positions.
   
   Other than the insertion method, the find() method was the only other method that had to be changed significantly from
   a basic HashMap implementation because it had to search two different arrays for the search key, and the find()
   method is important because it is a helper to almost every other method in the program.

2. Include all the benchmarking data, results and analysis that contributed to your final
decision on which implementation to use for the search engine.
    
    I do not have much benchmarking data because I only implemented the cuckoo hashing, but my data for that is as follows:
    
    HashMap:
    Processed newegg.txt in 874 ms using 63375 kb memory.
    Processed urls.txt in 92 ms using 665 kb memory.
    Processed apache.txt in 1548 ms using 47382 kb memory.
    Processed jhu.txt in 65 ms using 1331 kb memory.
    Processed joanne.txt in 51 ms using 665 kb memory.
    Processed random164.txt in 2248 ms using 301026 kb memory.
    
    For comparison, JdkHashMap:
    Processed newegg.txt in 1036 ms using 65781 kb memory.
    Processed urls.txt in 43 ms using 665 kb memory.
    Processed apache.txt in 1896 ms using 49528 kb memory.
    Processed jhu.txt in 63 ms using 1331 kb memory.
    Processed joanne.txt in 42 ms using 665 kb memory.
    Processed random164.txt in 2281 ms using 300059 kb memory.
    
    And TreapMap:
    Processed newegg.txt in 1094 ms using 64993 kb memory.
    Processed urls.txt in 70 ms using 665 kb memory.
    Processed apache.txt in 2230 ms using 108928 kb memory.
    Processed jhu.txt in 77 ms using 1331 kb memory.
    Processed joanne.txt in 78 ms using 665 kb memory.
    Processed random164.txt in 4525 ms using 292720 kb memory.
    
    As seen in the data, my cuckoo hashing implementation for HashMap() compares fairly reasonably to both
    JdkHashMap and TreapMap from HW6. Because of its favorable comparisons, I believe that it is both fast
    and efficient enough to use for my JHUgle search engine.

3. Provide an analysis of your benchmark data and conclusions. Why did
   you choose your final HashMap implementation as the best one? What 
   results were surprising and which were expected?

    Once I compared my HashMap implementation to TreapMap I realized that it was fast enough and efficient enough
    to choose for my final implementation. In the HW7 overview, it says beating TreapMap is difficult, but not
    impossible, so I believe that based on that, comparing reasonably well to TreapMap is a sign that my HashMap
    implementation is good enough to use for my search engine, and it is also as fast or faster and more efficient 
    than most other possible implementations. I expected my implementation to perform close to what it did, but I was
    not sure how it would compare to Java's built in JdkHashMap class (within the wrapper). I am glad that it compared
    relatively favorably to it.