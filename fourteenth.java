import java.util.PriorityQueue;

// Lesson 14: heaps and priority queues. Practice: practice-questions.md#14-heaps-and-priority-queues.
public class fourteenth {
    public static void main(String[] args) {
        // Java's PriorityQueue is a min-heap by default.
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        minHeap.offer(8);
        minHeap.offer(3);
        minHeap.offer(10);
        minHeap.offer(1);
        System.out.println("Smallest item: " + minHeap.peek());
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();

        // Compare directly instead of subtracting, which can overflow for unrestricted integers.
        PriorityQueue<Integer> safeMaxHeap = new PriorityQueue<Integer>((left, right) -> Integer.compare(right, left));
        safeMaxHeap.add(8);
        safeMaxHeap.add(3);
        safeMaxHeap.add(10);
        System.out.println("Largest item: " + safeMaxHeap.peek());
        System.out.print("Max-heap order: ");
        while (!safeMaxHeap.isEmpty()) {
            System.out.print(safeMaxHeap.poll() + " ");
        }
        System.out.println();

        // Offer and poll take O(log n); peek takes O(1). Iterating a heap is not sorted.
    }
}