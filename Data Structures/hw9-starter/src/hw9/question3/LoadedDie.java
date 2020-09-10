package hw9.question3;

/**
 * Six-sided "loaded" die where the values 1, 2, 3, 4, 5, and 6 appear with
 * uneven probabilities: face value {x} is about twice as likely as {x + 1}.
 */
public class LoadedDie {
  /**
   * Simulate rolling a loaded die.
   *
   * @return the next pseudorandom, uniformly distributed value
   *          between one (inclusive) and six (inclusive).
   */
  public static int roll() {
    // double in the range [0.0, 1.0)
    double r = Math.random();

    // integer in the range 1 to 6 with desired probabilities
    if (r < 2.0 / 64.0) {
      return 6;
    } else if (r < 4.0 / 64.0) {
      return 5;
    } else if (r < 8.0 / 64.0) {
      return 4;
    } else if (r < 16.0 / 64.0) {
      return 3;
    } else if (r < 32.0 / 64.0) {
      return 2;
    } else {
      return 1;
    }
  }
}
