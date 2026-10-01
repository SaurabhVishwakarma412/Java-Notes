import java.util.ArrayDeque;
import java.util.Queue;

// Lesson 13: binary trees, recursion, and breadth-first traversal.
public class thirteenth {
    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);

        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();
        System.out.print("Postorder: ");
        postorder(root);
        System.out.println();
        System.out.println("Breadth-first: " + breadthFirst(root));
        System.out.println("Contains 6: " + containsBst(root, 6));
        System.out.println("Height in nodes: " + height(root));
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    public static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public static Queue<Integer> breadthFirst(Node root) {
        Queue<Integer> result = new ArrayDeque<Integer>();
        if (root == null) return result;
        Queue<Node> pending = new ArrayDeque<Node>();
        pending.offer(root);
        while (!pending.isEmpty()) {
            Node node = pending.poll();
            result.offer(node.value);
            if (node.left != null) pending.offer(node.left);
            if (node.right != null) pending.offer(node.right);
        }
        return result;
    }

    // This search relies on the binary-search-tree rule: left < node < right.
    public static boolean containsBst(Node node, int target) {
        while (node != null) {
            if (node.value == target) return true;
            node = target < node.value ? node.left : node.right;
        }
        return false;
    }

    public static int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
}