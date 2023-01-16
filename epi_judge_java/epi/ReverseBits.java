package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseBits {

  private static final long[] lookupTable = new long[(1 << 16)];

  static {
    for (int i = 0; i < (1 << 16); i++) {
      lookupTable[i] = reverse(i);
    }
  }

  private static int reverse(int numberToReverse) {
    final int integerLength = 15;
    for (int left = 0, right = integerLength; left < right; left++, right--) {
      if (needToReverse(numberToReverse, left, right)) {
        numberToReverse = reverseBits(numberToReverse, left, right);
      }
    }
    return numberToReverse;
  }

  private static boolean needToReverse(final int x, final int i, final int j) {
    return ((x >>> i) & 1) != ((x >>> j) & 1);
  }

  private static int reverseBits(int x, final int i, final int j) {
    int mask = (1 << i) | (1 << j);
    x ^= mask;
    return x;
  }

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    int bitMask = 0xFFFF;
    int maskLength = 16;

    return lookupTable[(int) (x >>> (3 * maskLength) & bitMask)] << (0 * maskLength) |
           lookupTable[(int) (x >>> (2 * maskLength) & bitMask)] << (1 * maskLength) |
           lookupTable[(int) (x >>> (1 * maskLength) & bitMask)] << (2 * maskLength) |
           lookupTable[(int) (x >>> (0 * maskLength) & bitMask)] << (3 * maskLength);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
