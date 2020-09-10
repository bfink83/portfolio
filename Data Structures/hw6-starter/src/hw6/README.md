## Part A: Test-first development

1. Discuss the difficulties you encountered in testing
   rotations for Avl and Treap map implementations; what tests cases you used and why you chose those particular examples. You are enouraged to draw little ASCII trees to illustrate  the test cases.
   
   The main difficulties lie in being able to predict the order of keys to be inserted in order to obtain the correct rotations to test.
   Once properly drawn out on a whiteboard step-by-step according to each insert/remove it was relatively easy to test the different map implementations.
   The treap map implementation added another layer of difficulty to testing because of its inherent random priorities. Because these priorities are much longer
   numbers and aren't typically readily visible to the reader, it was much harder to predict the rotations in the treap map implementation. However, once
   the priorities were listed out and manually sorted into a minimum priority list on a whiteboard, it became easier to predict the rotations and allowed me
   to write proper tests for it.

## Part D: Benching Word Counts

1. Note the results of running `WordFrequencyCountExperiment` over several different test data using various implementations of `Map`.  More importantly, *describe* your observations and try to *explain* (justify) them using your understanding of the code you're benchmarking. (in other words, *why* are the numbers as they are?)
      Hotel California, SimpleMap: Processed 271 words using hw6.SimpleMap in 73 ms using 669 kb memory.
      Hotel California, TreapMap: Processed 271 words using hw6.TreapMap in 38 ms using 0 kb memory.
      Hotel California, AvlTreeMap: Processed 271 words using hw6.AvlTreeMap in 290 ms using 1333 kb memory.
      
      Federalist01, SimpleMap: Processed 1510 words using hw6.SimpleMap in 340 ms using 3333 kb memory.
      Federalist01, TreapMap: Processed 1510 words using hw6.TreapMap in 303 ms using 1996 kb memory.
      Federalist01, AvlTreeMap: Processed 1510 words using hw6.AvlTreeMap in 105 ms using 2692 kb memory.
      
      Moby Dick, SimpleMap: Processed 168362 words using hw6.SimpleMap in 7432 ms using 6905 kb memory.
      Moby Dick, TreapMap: Processed 168362 words using hw6.TreapMap in 2235 ms using 35213 kb memory.
      Moby Dick, AvlTreeMap: Processed 168362 words using hw6.AvlTreeMap in 954 ms using 37644 kb memory.
      
      Pride and Prejudice, SimpleMap: Processed 99642 words using hw6.SimpleMap in 2493 ms using 9754 kb memory.
      Pride and Prejudice, TreapMap: Processed 99642 words using hw6.TreapMap in 1482 ms using 43986 kb memory.
      Pride and Prejudice, AvlTreeMap: Processed 99642 words using hw6.AvlTreeMap in 1520 ms using 9381 kb memory.
      
      From the tests, it is clear that AVL Trees and Treaps are similarly efficient for larger data sets, while AVL Trees 
      are not very efficient for smaller data sets. Meanwhile, SimpleMaps are relatively less efficient throughout each experiment.
      The difference in efficiency is because of the difference in their big O notations. Treap and AVL Trees are O(logN) while
      SimpleMap is O(N). Their differences do not show as much in the smaller data sets because they are small enough that all of their
      trees are relatively balanced in the beginning, while the differences are larger for larger data sets because SimpleMap doesn't
      balance itself at all, Treap is probabilistic (so it balances, but less than AVL), and AVL balances after every insert. So,
      AVL and Treap should have similar efficiency, while SimpleMap should be less efficient.
      
    