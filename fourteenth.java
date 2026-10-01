import java.util.PriorityQueue;

// Lesson 14: heaps and priority queues.
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

        // Reverse the comparator to make a max-heap.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((left, right) -> right - left);
        maxHeap.add(8);
        maxHeap.add(3);
        maxHeap.add(10);
        System.out.println("Largest item: " + maxHeap.peek());

        // Avoid subtraction comparators for unrestricted integers because overflow is possible.
        PriorityQueue<Integer> safeMaxHeap = new PriorityQueue<Integer>((left, right) -> Integer.compare(right, left));
        safeMaxHeap.add(8);
        safeMaxHeap.add(3);
        safeMaxHeap.add(10);
        System.out.println("Safe max-heap order: ");
        while (!safeMaxHeap.isEmpty()) {
            System.out.print(safeMaxHeap.poll() + " ");
        }
        System.out.println();

        // Offer and poll take O(log n); peek takes O(1). Iterating a heap is not sorted.
    }
}