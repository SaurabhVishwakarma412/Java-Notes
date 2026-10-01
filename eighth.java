import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Lesson 8: ArrayList, List, generics, and dynamic arrays.
public class eighth {
    public static void main(String[] args) {
        // Program to the List interface; ArrayList is a common implementation.
        List<Integer> scores = new ArrayList<Integer>();
        scores.add(70);
        scores.add(95);
        scores.add(82);
        scores.add(1, 76);
        System.out.println("Scores: " + scores);
        System.out.println("At index 1: " + scores.get(1));
        scores.set(0, 88);
        System.out.println("Contains 95: " + scores.contains(95));
        System.out.println("Size: " + scores.size());

        scores.remove(Integer.valueOf(95)); // Remove the value, not index 95.
        System.out.println("After removal: " + scores);
        Collections.sort(scores);
        System.out.println("Sorted: " + scores);

        int total = 0;
        for (int score : scores) {
            total += score;
        }
        System.out.println("Average: " + (double) total / scores.size());

        // Generic types help catch mistakes at compile time. Primitives use wrapper classes.
        List<String> names = Arrays.asList("Ari", "Bea", "Cal");
        System.out.println("Names: " + names);

        // ArrayList get/set are O(1) on average; inserting/removing in the middle is O(n).
        // Use int[] when fixed size and low overhead are useful; use List when size changes.
    }
}