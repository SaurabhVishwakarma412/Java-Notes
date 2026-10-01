// Lesson 6: algorithm analysis and Big O notation. Practice: practice-questions.md#6-complexity-and-big-o.
public class sixth {
    public static void main(String[] args) {
        int[] values = {2, 4, 6, 8, 10};
        System.out.println("First value: " + first(values));
        System.out.println("Contains 8: " + contains(values, 8));
        printAllPairs(values);
        System.out.println("Sum: " + sum(values));
    }

    // O(1): the work does not grow with array length.
    public static int first(int[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        return values[0];
    }

    // O(n): in the worst case, inspect every item. Extra space is O(1).
    public static boolean contains(int[] values, int target) {
        for (int value : values) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    // O(n^2): two nested loops each scale with n.
    public static void printAllPairs(int[] values) {
        for (int left : values) {
            for (int right : values) {
                System.out.print("(" + left + "," + right + ") ");
            }
        }
        System.out.println();
    }

    // O(n) time and O(1) extra space. Track time and extra memory separately.
    public static int sum(int[] values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }
}