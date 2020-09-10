package hw4;

import java.util.Scanner;
import java.util.Stack;

/**
 * A program for an RPN calculator that uses a stack.
 */
public final class Calc {
  /**
   * The main function.
   *
   * @param args Not used.
   */
  public static void main(String[] args) {

    Stack<Integer> stack = new Stack<Integer>();
    Scanner in = new Scanner(System.in);
    int stackCount = 0;

    while (in.hasNext()) {
      if (in.hasNextInt()) {
        stack.push(in.nextInt());
        stackCount++;
      } else {
        String curr = in.next();

        if (isOperator(curr)) {
          if (stackCount < 2) {
            System.err.println("ERROR: Not enough arguments.");
          } else {
            int a = stack.pop();
            int b = stack.pop();
            if (a == 0 && (("/").equals(curr) || ("%").equals(curr))) {
              System.err.println("ERROR: division by zero");
              stack.push(b);
              stack.push(a);
            } else {
              int result = operate(a, b, curr);
              stack.push(result);
              stackCount--;
            }
          }
        } else if (isSpecial(curr)) {
          performSpecialCommand(curr, stack);
        } else if (("!").equals(curr)) {
          break; //ends program
        } else {
          System.err.println("ERROR: bad token");
        }
      }
    }
  }

  private static boolean isOperator(String str) {
    switch (str) {
      case "+":
      case "-":
      case "%":
      case "*":
      case "/":
        return true;
      default:
        return false;
    }
  }

  private static int operate(int a, int b, String operator) {
    switch (operator) {
      case "+":
        return b + a;
      case "-":
        return b - a;
      case "%":
        return b % a;
      case "*":
        return b * a;
      case "/":
        return b / a;
      default:
        return -1;
    }
  }

  private static boolean isSpecial(String str) {
    return (("?").equals(str) || (".").equals(str));
  }

  private static void performSpecialCommand(String spec, Stack<Integer> stack) {
    if (("?").equals(spec)) {
      System.out.println(stack.toString());
    } else if ((".").equals(spec)) {
      if (stack.empty()) {
        System.err.println("ERROR: empty stack");
      } else {
        System.out.println(stack.peek());
      }
    }
  }
}