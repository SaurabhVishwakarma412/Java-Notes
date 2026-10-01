// Lesson 11: linked lists and node references. Practice: practice-questions.md#11-linked-lists.
public class eleventh {
    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        System.out.print("List: ");
        printList(head);
        head = prepend(head, 5);
        System.out.print("After prepend: ");
        printList(head);
        System.out.println("Find 20: " + contains(head, 20));
        System.out.println("Length: " + length(head));
        head = removeFirst(head, 20);
        System.out.print("After removing 20: ");
        printList(head);
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    // Each node stores a value and a reference to the next node.
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static Node prepend(Node head, int value) {
        Node newHead = new Node(value);
        newHead.next = head;
        return newHead;
    }

    public static boolean contains(Node head, int target) {
        for (Node current = head; current != null; current = current.next) {
            if (current.value == target) {
                return true;
            }
        }
        return false;
    }

    public static int length(Node head) {
        int size = 0;
        for (Node current = head; current != null; current = current.next) {
            size++;
        }
        return size;
    }

    public static Node removeFirst(Node head, int target) {
        if (head == null) {
            return null;
        }
        if (head.value == target) {
            return head.next;
        }
        Node current = head;
        while (current.next != null && current.next.value != target) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
        return head;
    }
}