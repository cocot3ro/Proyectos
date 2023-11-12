import java.util.*;

public class Graph {

    class Node {
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

        void addNext(int n, int time) {
            next.put(singleton.getInstance(n), time);
        }

        void reset() {
            explored = false;
            minorTime = Integer.MAX_VALUE;
        }
    }

    class Singleton {
        Map<Integer, Node> singleton;

        Singleton() {
            this.singleton = new HashMap<>();
        }

        Node getInstance(int n) {
            if (!singleton.containsKey(n)) {
                singleton.put(n, new Node(n));
            }
            return singleton.get(n);
        }

    }

    private final Singleton singleton;
    boolean unreachable;
    private final List<Node> list;

    public Graph(int n, int[][] edges) {
        this.singleton = new Singleton();
        list = new ArrayList<>(n);
        for (int[] edge : edges) {
            addEdge(edge);
        }
    }

    public void addEdge(int[] edge) {
        Node node = singleton.getInstance(edge[0]);
        node.addNext(edge[1], edge[2]);
        list.add(node);
    }

    public int shortestPath(int node1, int node2) {
        this.unreachable = false;
        list.forEach(Node::reset);
        singleton.getInstance(node1).minorTime = 0;

        int ans = recursive(node1, node2);
        return (unreachable ? -1 : ans);
    }

    private int recursive(int node1, int node2) {
        if (node1 == node2) {
            return singleton.getInstance(node2).minorTime;
        }

        if (unreachable) {
            return -1;
        }

        Node thisNode = singleton.getInstance(node1);
        thisNode.explored = true;
        int minorTime = Integer.MAX_VALUE;
        Node minorNode = null;
        for (Map.Entry<Node, Integer> entry : thisNode.next.entrySet()) {
            Node aNextNode = entry.getKey();
            if (!aNextNode.explored) {
                aNextNode.minorTime = Math.min(aNextNode.minorTime, thisNode.minorTime + entry.getValue());
                if (aNextNode.minorTime < minorTime) {
                    minorTime = aNextNode.minorTime;
                    minorNode = aNextNode;
                }
            }
        }

        if (minorNode == null) {
            this.unreachable = true;
            return -1;
        }

        return recursive(minorNode.val, node2);
    }
}
