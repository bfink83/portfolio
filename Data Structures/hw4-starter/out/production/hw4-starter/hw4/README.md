## Part A: RPN Calculator

1. Document all error conditions you determined and why they are error conditions. Do this by including the inputs that you used to test your program and what error conditions they exposed:
Besides the three that were given to us, I found two other error conditions. I discovered error conditions for division by zero and modulo by zero. I first discovered the division by zero error
when adding inputs "4 0 ? / ?" in order to test the special case, which I knew would output something unexpected. Once I discovered the error message, I created a condition within my program to 
deal with it. After realizing that division by zero violated an error condition, I decided to test with the remainder operation, since they are very closely related. I inputted, "4 0 ? % ?" and
sure enough, remainder by zero caused an error, so I added it to the error condition I created for the division by zero error. They are error conditions because dividing by zero is undefined, so it
is illegal in java.


## Part B: Testing Deques

1. Please give a list of the flaws you uncovered in our implementation and list the JUnit 4 tests (using the names you gave them) that uncovered each flaw. For each flaw you identify, please share an educated guess as to what is the mistake that led to the flaw.

There were three flaws in regards to functions that should have thrown empty exceptions:
    back();
    The back() function threw a length exception instead of an empty exception. The mistake made was most likely just the author throwing the wrong exception when implementing the function.
    Test used to determine error: public void backThrowsEmptyException();
    
    removeBack();
    It seems that the removeBack() did not throw an exception at all. Therefore, the mistake made was simply forgetting to throw the exception when the input doesn't satisfy the pre-condition.
    Test used to determine error: public void removeFrontThrowsEmptyException();
    
    removeFront();
    The flaw in removeFront() is the same as the flaw in removeBack(), as no exception was thrown. The author's mistake was not throwing an exception when the input does not satisfy the pre-condition.
    Test used to determine error: public void removeBackThrowsEmptyException();
    
There was one flaw that dealt with the actual implementation of a function and not throwing exceptions:
    insertBack();
    I first noticed the insertBack() function was not working correctly when I tried inserting two values(one at a time), and back() would return the first value both times. After noticing this, I tried
        adding more values to the deque, and noticed that back() would run properly for every two values that were added. In order to test my hypothesis, I made a more extensive test for insertBack() and
        discovered that it was not flawed for every other value added, but for an increasing number of values added since the last error. This led me to believe that there is an flaw within the grow part
        of the function. The author grows the deque when there is no space to insert in the back, but they fail to go back and add the value that was supposed to be inserted initially. This leads to the value 
        never being added, and the back() function returning the wrong back value whenever the deque is full when insertBack() is called.
    Tests used to determine error: public void insertBackInsertsAtBack();
                                   public void confirmInsertBackIsFlawed();
    
    
2. Discuss the process with which you determined what tests to write. What made this process more difficult?
    I started by making tests for the more basic functions such as empty() and length(). Once these were done, I created exception tests for all the functions that were supposed to throw exceptions. After that, 
    I created tests for the functions that I would need to prove that other functions worked, such as back() and front(). If those were flawed, then it would be almost impossible to determine whether the insert 
    and remove functions worked properly. Once those were deemed correct, I was able to create tests for all of the other functions. Within functions, there were two things that I tested for. Whether the deque was
    incremented or decremented properly, and whether the proper value was added or removed to/from the proper place.
    
    The most difficult part of the testing process was attempting to determine what the flaws of Deque were precisely. Its easy to see when there is a flaw in code, but it is much harder to determine exactly what
    that flaw is, especially when you do not have access to the actual implementation of the interface.
