package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // 0101 1010 => 1
    // 0101 1110 => 0
    short parity = 0;
    while (x != 0) {
      parity ^= 1; // set bit to 0 or 1 
      x &= (x - 1); // remove the lowest set bit
    }

    return parity;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
