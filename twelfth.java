import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

// Lesson 12: stacks, queues, and deque operations. Practice: practice-questions.md#12-stacks-and-queues.
public class twelfth {
    public static void main(String[] args) {
        // Stack: last in, first out. Deque is preferred over the legacy Stack class.
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack top: " + stack.peek());
        while (!stack.isEmpty()) {
            System.out.println("Pop: " + stack.pop());
        }

        // Queue: first in, first out. offer/poll/peek avoid exceptions for normal empty cases.
        Queue<String> queue = new ArrayDeque<String>();
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");
        System.out.println("Queue front: " + queue.peek());
        while (!queue.isEmpty()) {
            System.out.println("Poll: " + queue.poll());
        }

        // A deque supports both ends and can serve as a stack or a queue.
        Deque<Integer> deque = new ArrayDeque<Integer>();
        deque.addFirst(2);
        deque.addLast(3);
        deque.addFirst(1);
        System.out.println("Deque: " + deque);
        System.out.println("Remove last: " + deque.removeLast());

        // ArrayDeque is generally a good default; it does not allow null elements.
    }
}