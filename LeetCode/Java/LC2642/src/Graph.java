import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
    private static class Node {
        int val;
        boolean explored;
        Map<Node, Integer> next;
        int minorTime;

        Node(int val) {
            this.val = val;
            this.explored = false;
            this.next = new HashMap<>();
            this.minorTime = Integer.MAX_VALUE;
        }

        void addNext(int n, int length) {
            next.put(Singleton.getInstance(n), length);
        }
    }

    private static class Singleton {
        static Map<Integer, Node> singleton = new HashMap<>();

        static Node getInstance(int n) {
            if (!singleton.containsKey(n)) {
                singleton.put(n, new Node(n));
            }
            return singleton.get(n);
        }

    }

    private List<Node> graph;

    public Graph(int n, int[][] edges) {
        this.graph = new LinkedList<>();
        for (int[] edge : edges) {
            addEdge(edge);
        }
    }

    public void addEdge(int[] edge) {
        Node node = Singleton.getInstance(edge[0]);
        node.addNext(edge[1], edge[2]);
        graph.add(node);
    }

    public int shortestPath(int node1, int node2) {
        return -1;
    }
}
