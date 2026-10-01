import java.util.Arrays;
import java.util.Comparator;

// Lesson 10: linear search, binary search, and sorting. Practice: practice-questions.md#10-searching-and-sorting.
public class tenth {
    public static void main(String[] args) {
        int[] unsorted = {8, 3, 10, 1, 6};
        System.out.println("Linear search index: " + linearSearch(unsorted, 10));

        int[] sorted = Arrays.copyOf(unsorted, unsorted.length);
        Arrays.sort(sorted);
        System.out.println("Sorted: " + Arrays.toString(sorted));
        System.out.println("Binary search index: " + Arrays.binarySearch(sorted, 6));
        System.out.println("Custom binary search index: " + binarySearch(sorted, 6));
        System.out.println("Missing value result: " + Arrays.binarySearch(sorted, 7));

        int[] insertionExample = {5, 2, 4, 1, 3};
        insertionSort(insertionExample);
        System.out.println("Insertion-sorted: " + Arrays.toString(insertionExample));

        String[] names = {"Zoe", "Ari", "Mina"};
        Arrays.sort(names, Comparator.reverseOrder());
        System.out.println("Reverse alphabetical: " + Arrays.toString(names));
    }

    // O(n) time, O(1) extra space.
    public static int linearSearch(int[] values, int target) {
        for (int index = 0; index < values.length; index++) {
            if (values[index] == target) {
                return index;
            }
        }
        return -1;
    }

    // O(log n) time, O(1) extra space. The input must already be sorted.
    public static int binarySearch(int[] sortedValues, int target) {
        int low = 0;
        int high = sortedValues.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (sortedValues[middle] == target) {
                return middle;
            } else if (sortedValues[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    // Insertion sort is O(n^2) average/worst case and O(1) extra space.
    public static void insertionSort(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int current = values[index];
            int position = index - 1;
            while (position >= 0 && values[position] > current) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = current;
        }
    }
}