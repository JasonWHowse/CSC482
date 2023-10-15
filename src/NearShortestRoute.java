import java.util.*;

public class NearShortestRoute {
    private final ArrayList<NearShortestRoute.Node> nodes;
    private final int[] distanceList;
    private final boolean [] haveVisited;
    private final Heap<NearShortestRoute.Edge> hn;

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (NearShortestRoute.Node node: nodes){
            for(NearShortestRoute.Edge edge : node.getEdges()){
                output.append("Node: ").append(node.getName()).append(" to: ").append(edge.getDestination().getName()).append(" at ").append(edge.getDistance()).append("\r\n");
            }
            output.append(node.getEdges().size() == 0 ? "" : "\r\n");
        }
        return output.toString();
    }

    public static class Node{
        private final ArrayList<NearShortestRoute.Edge> edges;
        private final int name;

        private ArrayList<NearShortestRoute.Node> previousNodes;

        public Node(int name){
            this.edges = new ArrayList<>(0);
            this.previousNodes = new ArrayList<>(0);
            this.name = name;
        }

        public NearShortestRoute.Node addEdge(NearShortestRoute.Edge edge){
            this.edges.add(edge);
            return this;
        }

        public NearShortestRoute.Edge getEdge(int destination){
            for (NearShortestRoute.Edge edge : edges){
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

        public ArrayList<NearShortestRoute.Edge> getEdges(){
            return this.edges;
        }

        public boolean deleteEdge(NearShortestRoute.Edge edge){
            return this.edges.remove(edge);
        }

        public boolean deleteEdge(int destination){
            return this.deleteEdge(this.getEdge(destination));
        }

        public ArrayList<NearShortestRoute.Node> getPreviousNode() {
            return previousNodes;
        }

        public NearShortestRoute.Node setPreviousNode(NearShortestRoute.Node previousNode) {
            this.previousNodes = new ArrayList<>();
            this.previousNodes.add(previousNode);
            return this;
        }

        public NearShortestRoute.Node addPreviousNode(NearShortestRoute.Node previousNode){
            this.previousNodes.add(previousNode);
            return this;
        }

        @Override
        public String toString() {
            return "From " + (previousNodes!=null && previousNodes.size()>0 ?previousNodes.get(0).getName():"None") + " -> " + name;
        }
    }

    public static class Edge{
        private final int distance;
        private final NearShortestRoute.Node destination;

        public Edge(NearShortestRoute.Node destination, int distance){
            this.destination = destination;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        public NearShortestRoute.Node getDestination() {
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

    public NearShortestRoute(int n, int e) {
        this.nodes = new ArrayList<>(n);
        for(int i = 0; i< n; i++){
            this.nodes.add(new Node(i));
        }
        this.distanceList = new int[n];
        this.haveVisited = new boolean[n];
        this.hn = new Heap<>(e);
        Arrays.fill(this.distanceList, -1);
    }

    public NearShortestRoute addEdge (int node, int destination, int distance){
        nodes.get(node).addEdge(new Edge(nodes.get(destination), distance));
        return this;
    }

    public int[] getDistanceList() {
        return distanceList;
    }

    public NearShortestRoute resetPath(){
        Arrays.fill(this.distanceList, -1);
        Arrays.fill(this.haveVisited, false);
        return this;
    }

    public NearShortestRoute removeEdge (int node, int destination){
        nodes.get(node).deleteEdge(destination);
        return this;
    }

    public NearShortestRoute removeShortestPath(NearShortestRoute.Node previousNode){
        Queue<NearShortestRoute.Node> previousNodeList = new LinkedList<>();
        previousNodeList.add(previousNode);
        while(previousNodeList.size()>0){
            for(int i = 0; i< (previousNodeList.peek() != null ? previousNodeList.peek().getPreviousNode().size() : 0); i++){
                if((previousNodeList.peek() != null ? previousNodeList.peek().getPreviousNode() : null) != null || (previousNodeList.peek() != null ? previousNodeList.peek().getPreviousNode().size() : 0) >0) {
                    this.removeEdge(previousNodeList.peek() != null ? previousNodeList.peek().getPreviousNode().get(i).getName() : 0, previousNodeList.peek() != null ? previousNodeList.peek().getName() : 0);
                    previousNodeList.add(previousNodeList.peek() != null ? previousNodeList.peek().getPreviousNode().get(i) : null);
                }
            }
            previousNodeList.poll();
        }
        return this;
    }

    public NearShortestRoute.Node setShortestPath(int start, int finish){
        this.distanceList[start] = 0;
        this.hn.insert(new Edge(nodes.get(start), 0), 0);
        while(hn.length() != 0){
            NearShortestRoute.Edge current = hn.extractMin();
            NearShortestRoute.Node currentNode = current.getDestination();
            this.haveVisited[currentNode.getName()] = true;

            ArrayList<NearShortestRoute.Edge> edges = this.nodes.get(currentNode.getName()).getEdges();
            for(int i = 0; i < this.nodes.get(currentNode.getName()).getEdgeCount(); i++){
                int neighborName = edges.get(i).getDestination().getName();
                int neighborDistance = edges.get(i).getDistance();
                if(     !haveVisited[neighborName] &&
                        distanceList[currentNode.getName()] != -1 &&
                        distanceList[currentNode.getName()] + neighborDistance < (distanceList[neighborName]<0?Integer.MAX_VALUE:distanceList[neighborName])){
                    distanceList[neighborName] = distanceList[currentNode.getName()] + neighborDistance;
                    edges.get(i).getDestination().setPreviousNode(currentNode);
                    this.hn.insert(edges.get(i), distanceList[neighborName]);
                }else if(     !haveVisited[neighborName] &&
                        distanceList[currentNode.getName()] != -1 &&
                        distanceList[currentNode.getName()] + neighborDistance == (distanceList[neighborName]<0?Integer.MAX_VALUE:distanceList[neighborName])) {
                    distanceList[neighborName] = distanceList[currentNode.getName()] + neighborDistance;
                    edges.get(i).getDestination().addPreviousNode(currentNode);
                    this.hn.insert(edges.get(i), distanceList[neighborName]);
                }
            }
        }

        return nodes.get(finish);
    }

    public static void main(String[] args)  {
        Scanner input= new Scanner(System.in);
        for(int[] a = new int[]{input.nextInt(),input.nextInt()};!(a[0]==0 && a[1]==0);a=new int[]{input.nextInt(),input.nextInt()}){
            int edges = a[1];
            NearShortestRoute NSR = new NearShortestRoute(a[0], edges);
            int[] SF = new int[]{input.nextInt(),input.nextInt()};
            for(int i = 0; i< edges;i++){
                NSR.addEdge(input.nextInt(), input.nextInt(), input.nextInt());
            }
            NearShortestRoute.Node shortNode  = NSR.setShortestPath(SF[0],SF[1]);
            NSR.removeShortestPath(shortNode).resetPath().setShortestPath(SF[0],SF[1]);
            int newLength=NSR.getDistanceList()[SF[1]];
            System.out.println(newLength);
        }
    }
}
