import java.util.Arrays;

// Lesson 17: prefix sums, two pointers, and sliding windows.
public class seventeenth {
    public static void main(String[] args) {
        int[] values = {2, 4, 1, 7, 3, 6};
        int[] prefix = buildPrefixSums(values);
        System.out.println("Values: " + Arrays.toString(values));
        System.out.println("Prefix sums: " + Arrays.toString(prefix));
        System.out.println("Sum on indexes 1..3: " + rangeSum(prefix, 1, 3));

        int[] sorted = {1, 3, 4, 6, 8, 10};
        System.out.println("Pair sums to 14: " + hasPairWithSum(sorted, 14));
        System.out.println("Largest window sum of size 3: " + maxWindowSum(values, 3));
    }

    // prefix[i] stores the sum of values before i; any range sum is O(1) after O(n) setup.
    public static int[] buildPrefixSums(int[] values) {
        int[] prefix = new int[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            prefix[i + 1] = prefix[i] + values[i];
        }
        return prefix;
    }

    // Inclusive range [left, right]. Validate indexes before calling in production code.
    public static int rangeSum(int[] prefix, int left, int right) {
        return prefix[right + 1] - prefix[left];
    }

    // Two pointers work here because the array is sorted.
    public static boolean hasPairWithSum(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;
        while (left < right) {
            int sum = sorted[left] + sorted[right];
            if (sum == target) return true;
            if (sum < target) left++;
            else right--;
        }
        return false;
    }

    // Fixed-size sliding window: update the sum by removing one value and adding one.
    public static int maxWindowSum(int[] values, int windowSize) {
        if (windowSize <= 0 || windowSize > values.length) {
            throw new IllegalArgumentException("window size must be between 1 and array length");
        }
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += values[i];
        }
        int largest = windowSum;
        for (int right = windowSize; right < values.length; right++) {
            windowSum += values[right] - values[right - windowSize];
            largest = Math.max(largest, windowSum);
        }
        return largest;
    }
}