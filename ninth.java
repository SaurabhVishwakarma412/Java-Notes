import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Lesson 9: HashMap and HashSet for fast lookup and counting.
public class ninth {
    public static void main(String[] args) {
        String[] words = {"cat", "dog", "cat", "bird", "dog", "cat"};
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        System.out.println("Frequencies: " + counts);
        System.out.println("Cat count: " + counts.get("cat"));
        System.out.println("Missing key: " + counts.get("fox"));

        Set<Integer> seen = new HashSet<Integer>();
        int[] values = {4, 1, 4, 9, 1};
        for (int value : values) {
            seen.add(value);
        }
        System.out.println("Unique values: " + seen);
        System.out.println("Contains 9: " + seen.contains(9));

        // Expected add/get/contains time is O(1); worst cases can be slower.
        // Hash-based keys should not be changed in a way that alters equals/hashCode while stored.
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " appears " + entry.getValue() + " times");
        }
    }
}