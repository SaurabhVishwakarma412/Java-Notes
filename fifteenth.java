import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// Lesson 15: graph representation, breadth-first search, and depth-first search. Practice: practice-questions.md#15-graphs.
public class fifteenth {
    public static void main(String[] args) {
        int vertexCount = 6;
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            graph.add(new ArrayList<Integer>());
        }

        // Add both directions because this example is an undirected graph.
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 0, 2);
        addUndirectedEdge(graph, 1, 3);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 4, 5);

        System.out.println("Adjacency list: " + graph);
        System.out.println("BFS from 0: " + breadthFirst(graph, 0));
        System.out.println("DFS from 0: " + depthFirst(graph, 0));
        System.out.println("Distances from 0: " + Arrays.toString(distances(graph, 0)));
    }

    public static void addUndirectedEdge(List<List<Integer>> graph, int left, int right) {
        validateVertex(graph, left);
        validateVertex(graph, right);
        graph.get(left).add(right);
        graph.get(right).add(left);
    }

    public static List<Integer> breadthFirst(List<List<Integer>> graph, int start) {
        validateVertex(graph, start);
        List<Integer> order = new ArrayList<Integer>();
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> pending = new ArrayDeque<Integer>();
        visited[start] = true;
        pending.offer(start);
        while (!pending.isEmpty()) {
            int vertex = pending.poll();
            order.add(vertex);
            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    pending.offer(neighbor);
                }
            }
        }
        return order;
    }

    public static List<Integer> depthFirst(List<List<Integer>> graph, int start) {
        validateVertex(graph, start);
        List<Integer> order = new ArrayList<Integer>();
        boolean[] visited = new boolean[graph.size()];
        visitDepthFirst(graph, start, visited, order);
        return order;
    }

    private static void visitDepthFirst(List<List<Integer>> graph, int vertex, boolean[] visited,
            List<Integer> order) {
        visited[vertex] = true;
        order.add(vertex);
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                visitDepthFirst(graph, neighbor, visited, order);
            }
        }
    }

    // In an unweighted graph, BFS finds shortest path lengths measured in edges.
    public static int[] distances(List<List<Integer>> graph, int start) {
        validateVertex(graph, start);
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, -1);
        Queue<Integer> pending = new ArrayDeque<Integer>();
        distance[start] = 0;
        pending.offer(start);
        while (!pending.isEmpty()) {
            int vertex = pending.poll();
            for (int neighbor : graph.get(vertex)) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[vertex] + 1;
                    pending.offer(neighbor);
                }
            }
        }
        return distance;
    }

    private static void validateVertex(List<List<Integer>> graph, int vertex) {
        if (graph == null || vertex < 0 || vertex >= graph.size()) {
            throw new IllegalArgumentException("vertex must be within the graph");
        }
    }
}