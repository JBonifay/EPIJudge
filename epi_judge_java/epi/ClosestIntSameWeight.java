package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long input) {

    for (int index = 0; index < Long.SIZE; index++) {
      if (bitsMismatch(input, index, index + 1)) {
        input = reverseBits(input, index, index + 1);
        break;
      }
    }

    return input;
  }

  private static boolean bitsMismatch(final long x, final int actualBit, final int nextBit) {
    return ((x >>> actualBit) & 1) != ((x >>> nextBit) & 1);
  }

  private static long reverseBits(long x, final int firstIndex, final int secondIndex) {
    final long mask = (1L << firstIndex) | (1L << secondIndex);
    return x ^ mask;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
