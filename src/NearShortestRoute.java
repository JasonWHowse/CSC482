import java.util.*;

public class NearShortestRoute {
    private ArrayList<Node> nodes;
    private int[] distanceList;
    private boolean [] haveVisited;
    Heap<Edge> hn;

    @Override
    public String toString() {
        String output = "";
        for (Node node: nodes){
            for(Edge edge : node.getEdges()){
                output += "Node: " + node.getName() + " to: " + edge.getDestination().getName() + " at " + edge.getDistance() + "\r\n";
            }
            output+="\r\n";
        }
        return output;
    }

    private class Node{
        private ArrayList<Edge> edges;
        private int name;

        private Node previousNode = null;

        public Node(int name, ArrayList<Edge> edges){
            this.edges = edges;
            this.name = name;
        }

        public Node(int name){
            this(name, new ArrayList<Edge>(0));
        }

        public Node addEdge(Edge edge){
            this.edges.add(edge);
            return this;
        }

        public Node addEdge(Node destination, int distance){
            this.addEdge(new Edge(destination, distance));
            return this;
        }

        public Edge getEdge(int destination){
            for (Edge edge : edges){
                if(destination==edge.destination.name){
                    return edge;
                }
            }
            return null;
        }

        public int getEdgeCount(){
            return this.edges.size();
        }

        public int getName(){
            return this.name;
        }

        public ArrayList<Edge> getEdges(){
            return this.edges;
        }

        public boolean deleteEdge(Edge edge){
            return this.edges.remove(edge);
        }

        public boolean deleteEdge(int destination){
            return this.deleteEdge(this.getEdge(destination));
        }

        public Node getPreviousNode() {
            return previousNode;
        }

        public Node setPreviousNode(Node previousNode) {
            this.previousNode = previousNode;
            return this;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "edges=" + edges.size() +
                    ", name=" + name +
                    ", previousNode=" + previousNode +
                    '}';
        }
    }

    private class Edge{
        private int distance;
        private Node destination;

        public Edge(Node destination, int distance){
            this.destination = destination;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        public Node getDestination() {
            return destination;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "distance=" + distance +
                    ", destination=" + destination +
                    '}';
        }
    }

    public NearShortestRoute(int n) {
        this.nodes = new ArrayList<Node>(n);
        for(int i = 0; i< n; i++){
            this.nodes.add(new Node(i));
        }
        this.distanceList = new int[n];
        this.haveVisited = new boolean[n];
        this.hn = new Heap<Edge>(n);
        Arrays.fill(this.distanceList, -1);
    }

    public NearShortestRoute addEdge (int node, int destination, int distance){
        nodes.get(node).addEdge(new Edge(nodes.get(destination), distance));
        return this;
    }

    public int[] getDistanceList() {
        return distanceList;
    }

    public boolean removeEdge (int node, int destination){
        return nodes.get(node).deleteEdge(destination);
    }

    public NearShortestRoute removeShortestPath(Node previousNode){
        while(previousNode!=null && previousNode.getPreviousNode()!=null){
            this.removeEdge(previousNode.getPreviousNode().getName(), previousNode.getName());
            previousNode = previousNode.getPreviousNode();
        }
        return this;
    }

    public Node setShortestPath(int start, int finish){
        ArrayList<Edge> output = new ArrayList<Edge>(0);
        this.distanceList[start] = 0;
        this.hn.insert(new Edge(nodes.get(start), 0), 0);
        while(hn.length() != 0){
            Edge current = hn.extractMin();
            Node currentNode = current.getDestination();
            this.haveVisited[currentNode.getName()] = true;

            ArrayList<Edge> edges = this.nodes.get(currentNode.getName()).getEdges();

            for(int i = 0; i < this.nodes.get(currentNode.getName()).getEdgeCount(); i++){
                int neighborName = edges.get(i).getDestination().getName();
                int neighborDistance = edges.get(i).getDistance();
                if(!haveVisited[neighborName] && distanceList[currentNode.getName()] != -1 && distanceList[currentNode.getName()] + neighborDistance < (distanceList[neighborName]<0?Integer.MAX_VALUE:distanceList[neighborName])){
                    distanceList[neighborName] = distanceList[currentNode.getName()] + neighborDistance;
                    edges.get(i).getDestination().setPreviousNode(currentNode);
                    this.hn.insert(edges.get(i), distanceList[neighborName]);
                }
            }
        }

        return nodes.get(finish);
    }



    public static void main(String[] args) {
        int numNodes = 9;
        NearShortestRoute NSR = new NearShortestRoute(numNodes);

        // Add edges and weights
        NSR.addEdge(0, 1, 1).addEdge(0, 3, 5);

        NSR.addEdge(1, 2, 1).addEdge(1, 3, 8);

        NSR.addEdge(2, 4, 6);

        NSR.addEdge(3, 2, 1).addEdge(3, 4, 1);

        NSR.addEdge(4, 5, 10).addEdge(4, 7, 5);

        NSR.addEdge(5, 6, 1).addEdge(5, 7, 2);

        NSR.addEdge(6, 8, 4);

        NSR.addEdge(7, 5, 3).addEdge(7, 6, 9).addEdge(7, 8, 2);


        System.out.println(NSR);
        int startNode = 0;
        Node temp = NSR.setShortestPath(startNode, 8);
        System.out.println(temp);
        System.out.println(NSR.getDistanceList()[8]);
        System.out.println(NSR.removeShortestPath(temp));
        temp = NSR.setShortestPath(startNode, 8);
        System.out.println(temp);
        System.out.println(NSR.getDistanceList()[8]);
    }
}
