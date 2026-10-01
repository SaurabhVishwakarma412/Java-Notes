// Lesson 18: bitwise operators and common bit tricks. Practice: practice-questions.md#18-bit-manipulation.
public class eighteenth {
    public static void main(String[] args) {
        int left = 6;  // binary 0110
        int right = 10; // binary 1010
        System.out.println("AND: " + (left & right));
        System.out.println("OR: " + (left | right));
        System.out.println("XOR: " + (left ^ right));
        System.out.println("NOT of 6: " + (~left));
        System.out.println("6 shifted left: " + (left << 1));
        System.out.println("10 shifted right: " + (right >> 1));

        int value = 10; // binary 1010
        int bitIndex = 3;
        System.out.println("Bit 3 is set: " + isBitSet(value, bitIndex));
        System.out.println("Set bit 0: " + setBit(value, 0));
        System.out.println("Clear bit 1: " + clearBit(value, 1));
        System.out.println("Toggle bit 1: " + toggleBit(value, 1));
        System.out.println("Is 16 a power of two: " + isPowerOfTwo(16));

        // XOR cancels pairs: x ^ x is 0 and x ^ 0 is x.
        int[] pairedValues = {4, 1, 4, 7, 1};
        int unpaired = 0;
        for (int item : pairedValues) {
            unpaired ^= item;
        }
        System.out.println("Unpaired value: " + unpaired);
    }

    public static boolean isBitSet(int value, int bitIndex) {
        validateBitIndex(bitIndex);
        return (value & (1 << bitIndex)) != 0;
    }

    public static int setBit(int value, int bitIndex) {
        validateBitIndex(bitIndex);
        return value | (1 << bitIndex);
    }

    public static int clearBit(int value, int bitIndex) {
        validateBitIndex(bitIndex);
        return value & ~(1 << bitIndex);
    }

    public static int toggleBit(int value, int bitIndex) {
        validateBitIndex(bitIndex);
        return value ^ (1 << bitIndex);
    }

    private static void validateBitIndex(int bitIndex) {
        if (bitIndex < 0 || bitIndex >= Integer.SIZE) {
            throw new IllegalArgumentException("bit index must be between 0 and 31");
        }
    }

    public static boolean isPowerOfTwo(int value) {
        return value > 0 && (value & (value - 1)) == 0;
    }
}