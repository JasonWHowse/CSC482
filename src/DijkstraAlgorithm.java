import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithm {
    private int[] distances;
    private boolean[] visited;
    private MinHeap minHeap;
    private List<List<NodeDistance>> adjacencyList;

    public DijkstraAlgorithm(int numNodes) {
        distances = new int[numNodes];
        visited = new boolean[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        minHeap = new MinHeap(numNodes);
        adjacencyList = new ArrayList<>(numNodes);

        for (int i = 0; i < numNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjacencyList.get(from).add(new NodeDistance(to, weight));
    }

    public void shortestPath(int startNode) {
        distances[startNode] = 0;
        minHeap.insert(new NodeDistance(startNode, 0));

        while (!minHeap.isEmpty()) {
            NodeDistance current = minHeap.extractMin();
            int currentNode = current.node;
            visited[currentNode] = true;

            for (NodeDistance neighbor : adjacencyList.get(currentNode)) {
                int neighborNode = neighbor.node;
                int neighborDistance = neighbor.distance;

                if (
    !visited[neighborNode] &&
    distances[currentNode] != Integer.MAX_VALUE &&
    distances[currentNode] + neighborDistance < distances[neighborNode]
                ) {
                    distances[neighborNode] = distances[currentNode] + neighborDistance;
                    minHeap.insert(new NodeDistance(neighborNode, distances[neighborNode]));
                }
            }
        }
    }

    public int[] getDistances() {
        return distances;
    }

    public static void main(String[] args) {
        int numNodes = 5;
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(numNodes);

        // Add edges and weights
        dijkstra.addEdge(0, 1, 10);
        dijkstra.addEdge(0, 3, 5);
        dijkstra.addEdge(1, 2, 1);
        dijkstra.addEdge(1, 3, 2);
        dijkstra.addEdge(2, 4, 4);
        dijkstra.addEdge(3, 1, 3);
        dijkstra.addEdge(3, 2, 9);
        dijkstra.addEdge(3, 4, 2);

        int startNode = 0;
        dijkstra.shortestPath(startNode);

        int[] distances = dijkstra.getDistances();
        System.out.println("Shortest distances from node " + startNode + ":");
        for (int i = 0; i < numNodes; i++) {
            System.out.println("To node " + i + ": " + distances[i]);
        }

        numNodes =5;
        DijkstraAlgorithm dijkstra2 = new DijkstraAlgorithm(numNodes);

        // Add edges and weights

        dijkstra2.addEdge(0, 1, 10);
        dijkstra2.addEdge(0, 3, 5);
        dijkstra2.addEdge(1, 2, 1);
        dijkstra2.addEdge(1, 3, 2);
        dijkstra2.addEdge(2, 4, 4);
        dijkstra2.addEdge(3, 1, 3);
        dijkstra2.addEdge(3, 2, 9);
        dijkstra2.addEdge(3, 4, 2);

        startNode = 0;
        dijkstra2.shortestPath(startNode);

        int[] distances2 = dijkstra2.getDistances();
        System.out.println("Shortest distances from node " + startNode + ":");
        for (int i = 0; i < numNodes; i++) {
            System.out.println("To node " + i + ": " + distances2[i]);
        }
    }
}
