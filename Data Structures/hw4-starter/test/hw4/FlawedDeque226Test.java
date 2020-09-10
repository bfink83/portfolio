package hw4;

import exceptions.EmptyException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class FlawedDeque226Test {

  private Deque226<String> stringDequeue;

  @Before
  public void setupDequeue() {
    this.stringDequeue = new FlawedDeque226<String>();
  }
  // METHODS:
  //boolean empty()
  //int length()
  //T front() throws EmptyException;
  //T back() throws Empty Exception;
  //void insertFront (T t);
  //void insertBack (T t);
  //void removeFront();
  //void removeBack();

  //empty()
  @Test
  public void emptyDequeReturnsTrue() {
    assertTrue(stringDequeue.empty());
  }

  @Test
  public void emptyMethodReturnsFalse() {
    stringDequeue.insertFront("one");
    assertFalse(stringDequeue.empty());
  }

  //front()
  @Test (expected = EmptyException.class)
  public void frontThrowsEmptyException() {
    assertTrue(stringDequeue.empty());
    stringDequeue.front();
  }

  @Test
  public void frontReturnsFront() {     //front() works
    assertTrue(stringDequeue.empty());
    stringDequeue.insertFront("three");
    assertEquals("three", stringDequeue.front());
    stringDequeue.insertFront("two");
    assertEquals("two", stringDequeue.front());
    stringDequeue.insertFront("one");
    assertEquals("one", stringDequeue.front());
  }

  //back()
  @Test (expected = EmptyException.class)     //flawed deque throws length exception, not empty exception
  public void backThrowsEmptyException() {
    assertTrue(stringDequeue.empty());
    stringDequeue.back();
  }

  @Test
  public void backReturnsBack() {   //since we know insertFront() works, we can use it to see what is wrong with back()
    assertTrue(stringDequeue.empty());    //or insertBack()
    stringDequeue.insertFront("three");
    assertEquals("three", stringDequeue.back());
    stringDequeue.insertFront("two");
    assertEquals("three", stringDequeue.back());
    stringDequeue.insertFront("one");
    assertEquals("three", stringDequeue.back());
  }     //this test passes, so we know the issue is with insertBack()

  //length()
  @Test
  public void lengthReturnsCorrectLength() {
    assertTrue(stringDequeue.empty());
    assertEquals(0, stringDequeue.length());
    stringDequeue.insertFront("three");
    assertEquals(1, stringDequeue.length());
    stringDequeue.insertFront("two");
    assertEquals(2, stringDequeue.length());
    stringDequeue.insertFront("one");
    assertEquals(3, stringDequeue.length());
  }

  //insertFront()
  @Test
  public void insertFrontInsertsAtFront() {   //we know insert front works, so we can use it to test back()
    assertTrue(stringDequeue.empty());
    stringDequeue.insertFront("two");
    assertEquals("two", stringDequeue.front());
    stringDequeue.insertFront("one");
    assertEquals("one", stringDequeue.front());
  }

  //insertBack()
  @Test
  public void insertBackInsertsAtBack() {     //either back() returns front or insertBack() inserts at the front
    assertTrue(stringDequeue.empty());        // need more tests to see if back() and front() return proper values
    stringDequeue.insertBack("one");
    assertEquals("one", stringDequeue.back());
    stringDequeue.insertBack("two");
    //assertEquals("two", stringDequeue.back());
    stringDequeue.insertBack("three");
    assertEquals("three", stringDequeue.back());
    stringDequeue.insertBack("four");
    assertEquals("four", stringDequeue.back());   //three works, but four fails the test, seems like every other addition fails test
  }

  @Test
  public void confirmInsertBackIsFlawed() {
    assertTrue(stringDequeue.empty());
    stringDequeue.insertBack("one");
    assertEquals("one", stringDequeue.back());
    stringDequeue.insertBack("two");
    //assertEquals("two", stringDequeue.back());
    stringDequeue.insertBack("three");
    assertEquals("three", stringDequeue.back());
    stringDequeue.insertBack("four");
    //assertEquals("four", stringDequeue.back());
    stringDequeue.insertBack("five");
    assertEquals("five", stringDequeue.back());   //seems like insertBack() and back() are acting like a queue not a stack
    stringDequeue.insertBack("six");
    assertEquals("six", stringDequeue.back());
    stringDequeue.insertBack("seven");
    //assertEquals("seven", stringDequeue.back());
    stringDequeue.insertBack("eight");
    assertEquals("eight", stringDequeue.back());
    stringDequeue.insertBack("nine");
    assertEquals("nine", stringDequeue.back());
    stringDequeue.insertBack("ten");
    assertEquals("ten", stringDequeue.back());
  }

  //removeFront()
  @Test (expected = EmptyException.class)     //throws assertion error, but not empty exception
  public void removeFrontThrowsEmptyException() {
    assertTrue(stringDequeue.empty());
    stringDequeue.removeFront();
  }

  @Test
  public void removeFrontRemovesFront() {   //seems to work
    assertTrue(stringDequeue.empty());
    stringDequeue.insertFront("three");
    stringDequeue.insertFront("two");
    stringDequeue.insertFront("one");
    assertEquals(3, stringDequeue.length());
    assertEquals("one", stringDequeue.front());
    stringDequeue.removeFront();
    assertEquals(2, stringDequeue.length());
    assertEquals("two", stringDequeue.front());
    stringDequeue.removeFront();
    assertEquals(1, stringDequeue.length());
    assertEquals("three", stringDequeue.front());
  }


  //remove back
  @Test (expected = EmptyException.class)
  public void removeBackThrowsEmptyException() {    //throws assertion error, but does not throw empty exception
    assertTrue(stringDequeue.empty());
    stringDequeue.removeBack();
  }

  @Test
  public void removeBackRemovesBack() {
    assertTrue(stringDequeue.empty());
    stringDequeue.insertFront("three");
    stringDequeue.insertFront("two");
    stringDequeue.insertFront("one");
    assertEquals(3, stringDequeue.length());
    assertEquals("three", stringDequeue.back());
    stringDequeue.removeBack();
    assertEquals(2, stringDequeue.length());
    assertEquals("two", stringDequeue.back());
    stringDequeue.removeBack();
    assertEquals(1, stringDequeue.length());
    assertEquals("one", stringDequeue.back());
  }
}
