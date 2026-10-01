import java.util.Arrays;

// Lesson 4: methods, parameters, return values, and scope. Practice: practice-questions.md#4-methods.
public class fourth {
    public static void main(String[] args) {
        int[] values = {3, 7, 2, 9};
        System.out.println("Sum: " + sum(values));
        System.out.println("Maximum: " + maximum(values));
        System.out.println("Maximum of two: " + maximum(5, 8));
        changeFirst(values, 100);
        System.out.println("Array after method call: " + Arrays.toString(values));

        // Java passes every argument by value. For an array, the copied value is its reference.
        int number = 4;
        tryToChange(number);
        System.out.println("Primitive remains: " + number);
    }

    public static int sum(int[] values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    public static int maximum(int[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        int largest = values[0];
        for (int value : values) {
            if (value > largest) {
                largest = value;
            }
        }
        return largest;
    }

    // Overloading means same method name with different parameter lists.
    public static int maximum(int left, int right) {
        return left > right ? left : right;
    }

    public static void changeFirst(int[] values, int replacement) {
        if (values.length > 0) {
            values[0] = replacement;
        }
    }

    public static void tryToChange(int value) {
        value = 99;
    }
}