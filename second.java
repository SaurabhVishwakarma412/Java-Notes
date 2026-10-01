import java.util.Arrays;

// Lesson 2: arrays in Java. Practice: practice-questions.md#2-arrays.
public class second {
    public static void main(String[] args) {
        // 1. Create an array. Its size is fixed after it is created.
        int[] numbers = {7, 2, 9, 4, 2};
        int[] emptyNumbers = new int[3];
        String[] emptyNames = new String[2];

        System.out.println("Length: " + numbers.length);
        System.out.println("First value: " + numbers[0]);
        System.out.println("Last value: " + numbers[numbers.length - 1]);
        System.out.println("New int array defaults: " + Arrays.toString(emptyNumbers));
        System.out.println("New String array defaults: " + Arrays.toString(emptyNames));

        // Indexes start at 0. Assigning to an index changes that element.
        numbers[1] = 5;
        System.out.println("After changing index 1: " + Arrays.toString(numbers));

        // 2. Visit every element with an index when the position is useful.
        System.out.print("Index loop: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // Use an enhanced for loop when you only need each value.
        System.out.print("Enhanced loop: ");
        for (int value : numbers) {
            System.out.print(value + " ");
        }
        System.out.println();

        // 3. Common operations: total, smallest/largest, and linear search.
        int total = 0;
        int smallest = numbers[0];
        int largest = numbers[0];
        int target = 9;
        int targetIndex = -1;

        for (int i = 0; i < numbers.length; i++) {
            int value = numbers[i];
            total += value;
            if (value < smallest) {
                smallest = value;
            }
            if (value > largest) {
                largest = value;
            }
            if (value == target && targetIndex == -1) {
                targetIndex = i;
            }
        }

        System.out.println("Total: " + total);
        System.out.println("Smallest: " + smallest);
        System.out.println("Largest: " + largest);
        System.out.println("First index of " + target + ": " + targetIndex);

        // 4. Copying creates a separate array; changing the copy won't change the original.
        int[] copiedNumbers = Arrays.copyOf(numbers, numbers.length);
        copiedNumbers[0] = 100;
        System.out.println("Original: " + Arrays.toString(numbers));
        System.out.println("Copy: " + Arrays.toString(copiedNumbers));

        // Assignment copies the reference, not the elements; both variables point to one array.
        int[] sameArray = numbers;
        sameArray[0] = 8;
        System.out.println("Original after changing its alias: " + Arrays.toString(numbers));
        numbers[0] = 7;

        // A new length can be requested when copying; added int elements default to zero.
        int[] longerCopy = Arrays.copyOf(numbers, numbers.length + 2);
        System.out.println("Longer copy: " + Arrays.toString(longerCopy));

        // Arrays.toString is useful for displaying a one-dimensional array.
        // Arrays.fill assigns the same value to every element.
        int[] filledNumbers = new int[4];
        Arrays.fill(filledNumbers, 6);
        System.out.println("Filled: " + Arrays.toString(filledNumbers));

        // 5. Sort before binarySearch. Binary search requires sorted data.
        int[] sortedNumbers = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sortedNumbers);
        System.out.println("Sorted: " + Arrays.toString(sortedNumbers));
        int foundIndex = Arrays.binarySearch(sortedNumbers, 9);
        System.out.println("Index of 9 in sorted array: " + foundIndex);
        System.out.println("Arrays.equals: " + Arrays.equals(numbers, copiedNumbers));

        // 6. Pass arrays to methods. Arrays are objects, so a method can change their elements.
        int[] valuesForMethod = {3, 8, 1};
        System.out.println("Method sum: " + sum(valuesForMethod));
        changeFirstElement(valuesForMethod, 50);
        System.out.println("After method changed it: " + Arrays.toString(valuesForMethod));

        // 7. A two-dimensional array is an array whose elements are arrays.
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println("Rows: " + grid.length);
        System.out.println("Columns in first row: " + grid[0].length);
        System.out.println("Value at row 1, column 2: " + grid[1][2]);

        System.out.println("Grid:");
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                System.out.print(grid[row][column] + " ");
            }
            System.out.println();
        }

        // Rows can have different lengths; this is called a jagged array.
        int[][] jagged = {
            {1, 2},
            {3, 4, 5},
            {6}
        };
        System.out.println("Jagged array: " + Arrays.deepToString(jagged));
    }

    public static int sum(int[] values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    public static void changeFirstElement(int[] values, int newValue) {
        if (values.length > 0) {
            values[0] = newValue;
        }
    }
}