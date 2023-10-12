package HW1;

import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        /*
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
        System.out.println("Distance: " + NSR.getDistanceList()[8]);
        NearShortestRoute.Node temp = NSR.setShortestPath(startNode, 8);
        System.out.println(temp);
        System.out.println("Distance: " + NSR.getDistanceList()[8]);
        System.out.println(NSR.removeShortestPath(temp));
        NSR.resetPath();
        temp = NSR.setShortestPath(startNode, 8);
        System.out.println(temp);
        System.out.println("Distance: " + NSR.getDistanceList()[8]);
        for(int i = 0; i<NSR.getDistanceList().length; i++){
            System.out.println("" + i + ": " + NSR.getDistanceList()[i]);
        }
        */

    }
}

class HeapNode <T>{
    private int priorityValue = Integer.MAX_VALUE;
    private T obj;
    private HeapNode childOne;
    private HeapNode childTwo;
    private HeapNode parent;

    public HeapNode(T obj, int priorityValue){
        this.priorityValue = priorityValue;
        this.obj = obj;
    }

    public HeapNode setPriority(int priority){
        this.priorityValue = priority;
        return this;
    }

    public HeapNode setChildOne(HeapNode node){
        childOne = node;
        return this;
    }

    public HeapNode setChildTwo(HeapNode node){
        childTwo = node;
        return this;
    }

    public HeapNode setParent(HeapNode parent) {
        this.parent = parent;
        return this;
    }

    public HeapNode update(T obj){
        this.obj = obj;
        return this;
    }
    public int getPriority() {
        return priorityValue;
    }

    public HeapNode getChildOne(){
        return childOne;
    }

    public HeapNode getChildTwo(){
        return childTwo;
    }

    public HeapNode getParent() {
        return parent;
    }

    public T value(){
        return this.obj;
    }

    @Override
    public String toString() {
        return "Val: " + obj.toString() + " P: " + priorityValue;
    }
}

class Heap<T> {
    private HeapNode[] arr;
    private int length = 0;
    private HashMap<T, Integer> position = new HashMap<T, Integer>();

    public Heap(int N){
        this.startHeap(N);
    }

    public Heap(T[] arr){
        this(arr.length);
        Queue<HeapNode<T>> parentStack = new LinkedList<>();
        int cCount = 0;
        for(int p = 1; p<=this.arr.length;p++){
            this.arr[p-1] = new HeapNode<T>(arr[p-1], p);
            position.put(arr[p-1],p-1);
            parentStack.add(this.arr[p-1]);
            if(cCount == 1){
                parentStack.peek().setChildOne(this.arr[p-1].setParent(parentStack.peek()));
                parentStack.peek().getChildOne().setPriority(parentStack.peek().getPriority()*2);
            }else if (cCount==2){
                parentStack.peek().setChildTwo(this.arr[p-1].setParent(parentStack.peek()));
                parentStack.peek().getChildOne().setPriority(parentStack.peek().getPriority()*2+1);
                parentStack.poll();
                cCount=0;
            }
            length++;
            cCount++;
        }
    }

    public void heapify_Up(int index){
        if(arr[index].getParent()!=null && arr[index].getPriority()<arr[index].getParent().getPriority()){
            heapify_Up(swapHeapNodes(arr[index], arr[index].getParent()));
            checker();
        }
    }

    public void heapify_Down(int index){
        var node = arr[index];
        if(node.getChildOne() != null &&
                (node.getChildTwo()== null ||
                        node.getChildTwo().getPriority()>node.getChildOne().getPriority()) &&
                node.getChildOne().getPriority()<node.getPriority()) {
            swapHeapNodes(node.getChildOne(),node);
            heapify_Down(position.get(node.value()));
            checker();
        }else if (node.getChildTwo() != null &&
                node.getChildTwo().getPriority()<node.getPriority()){
            swapHeapNodes(node.getChildTwo(),node);
            heapify_Down(position.get(node.value()));
            checker();
        }
    }

    private void checker(){
        for(int i = 0; i<arr.length;i++){
            if(arr[i] != null && arr[i].getParent() != null && arr[i].getPriority()<arr[i].getParent().getPriority()){
                heapify_Up(i);
                break;
            }
        }
    }

    private int swapHeapNodes(HeapNode Swap1, HeapNode Swap2){
        int parIndex = position.get(Swap2.value());
        int chiIndex = position.get(Swap1.value());
        HeapNode tempChild1 = Swap1.getChildOne();
        HeapNode tempChild2 = Swap1.getChildTwo();
        if(Swap2.getChildOne()==Swap1){
            Swap1.setChildOne(Swap2).setChildTwo(Swap2.getChildTwo()).setParent(Swap2.getParent());
        }else{
            Swap1.setChildTwo(Swap2).setChildOne(Swap2.getChildOne()).setParent(Swap2.getParent());
        }
        if (Swap1.getChildOne() != null) {
            Swap1.getChildOne().setParent(Swap1);
        }
        if(Swap1.getChildTwo() != null) {
            Swap1.getChildTwo().setParent(Swap1);
        }
        Swap2.setParent(Swap1).setChildOne(tempChild1).setChildTwo(tempChild2);

        arr[parIndex]=Swap1;
        arr[chiIndex]=Swap2;

        if(Swap1.getParent()!=null){
            if(Swap1.getParent().getChildOne() == Swap2){
                Swap1.getParent().setChildOne(Swap1);
            }else  if(Swap1.getParent().getChildTwo() == Swap2){
                Swap1.getParent().setChildTwo(Swap1);

            }
        }

        position.replace((T)Swap1.value(), parIndex);
        position.replace((T)Swap2.value(), chiIndex);

        return parIndex;
    }

    public void startHeap(int N){
        this.arr = new HeapNode[N];
        for(HeapNode node : arr){
            node=null;
        }
    }

    public void insert(T value, int priority){
        int index = 0;
        HeapNode parent = null;
        while(arr[index]!=null){
            if(arr[index] != null &&
                    (arr[index].getChildOne() == null ||
                            arr[index].getChildTwo() == null) &&
                    parent == null){
                parent = arr[index];
            }
            index++;
        }
        position.put(value,index);
        arr[index] = new HeapNode<T>(value, priority);
        if(parent != null && parent.getChildOne()==null){
            parent.setChildOne(arr[index].setParent(parent));
        }else if(parent != null && parent.getChildTwo()==null){
            parent.setChildTwo(arr[index].setParent(parent));
        }
        heapify_Up(index);
        length++;
    }

    public T findMin(){
        return (T) arr[0].value();
    }

    public void delete(int index){
        HeapNode itemToDelete = arr[index];
        itemToDelete.setPriority(Integer.MAX_VALUE);
        heapify_Down(index);
        if(null!=itemToDelete.getParent()) {
            if (itemToDelete == itemToDelete.getParent().getChildOne()) {
                itemToDelete.getParent().setChildOne(null);
            } else {
                itemToDelete.getParent().setChildTwo(null);
            }
        }
        arr[position.get(itemToDelete.value())] = null;
        length--;
    }

    public T extractMin(){
        T output = findMin();
        delete(0);
        return output;
    }

    public void delete(T item){
        delete(position.get(item));
    }

    public void changePriority(T item, int newPriority){
        int index = position.getOrDefault(item, -1);
        if(index!=-1) {
            HeapNode itemToRep = arr[index];
            if (itemToRep.getPriority() > newPriority) {
                itemToRep.setPriority(newPriority);
                heapify_Up(index);
            } else {
                itemToRep.setPriority(newPriority);
                heapify_Down(index);
            }
        }
    }

    public HeapNode<T> getHead(){
        return arr[0];
    }

    public int length(){
        return length;
    }

    @Override
    public String toString() {
        return toStringRecursive(arr[0], "", true);
    }

    private String toStringRecursive(HeapNode node, String prefix, boolean isLeft) {
        StringBuilder output = new StringBuilder();
        if (node != null) {
            output.append(prefix).append(isLeft ? "├── " : "└── ").append(node.toString()).append("\n").append(toStringRecursive(node.getChildOne(), prefix + (isLeft ? "│   " : "    "), true)).append(toStringRecursive(node.getChildTwo(), prefix + (isLeft ? "│   " : "    "), false));
        }
        return output.toString();
    }
}

class NearShortestRoute {
    private final ArrayList<NearShortestRoute.Node> nodes;
    private final int[] distanceList;
    private final boolean [] haveVisited;
    private final Heap<NearShortestRoute.Edge> hn;

    @Override
    public String toString() {
        String output = "";
        for (NearShortestRoute.Node node: nodes){
            for(NearShortestRoute.Edge edge : node.getEdges()){
                output += "Node: " + node.getName() + " to: " + edge.getDestination().getName() + " at " + edge.getDistance() + "\r\n";
            }
            output+="\r\n";
        }
        return output;
    }

    public class Node{
        private ArrayList<NearShortestRoute.Edge> edges;
        private int name;

        private NearShortestRoute.Node previousNode = null;

        public Node(int name, ArrayList<NearShortestRoute.Edge> edges){
            this.edges = edges;
            this.name = name;
        }

        public Node(int name){
            this(name, new ArrayList<NearShortestRoute.Edge>(0));
        }

        public NearShortestRoute.Node addEdge(NearShortestRoute.Edge edge){
            this.edges.add(edge);
            return this;
        }

        public NearShortestRoute.Node addEdge(NearShortestRoute.Node destination, int distance){
            this.addEdge(new NearShortestRoute.Edge(destination, distance));
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

        public NearShortestRoute.Node getPreviousNode() {
            return previousNode;
        }

        public NearShortestRoute.Node setPreviousNode(NearShortestRoute.Node previousNode) {
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

    public class Edge{
        private int distance;
        private NearShortestRoute.Node destination;

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

    public NearShortestRoute(int n) {
        this.nodes = new ArrayList<NearShortestRoute.Node>(n);
        for(int i = 0; i< n; i++){
            this.nodes.add(new NearShortestRoute.Node(i));
        }
        this.distanceList = new int[n];
        this.haveVisited = new boolean[n];
        this.hn = new Heap<NearShortestRoute.Edge>(n);
        Arrays.fill(this.distanceList, -1);
    }

    public NearShortestRoute addEdge (int node, int destination, int distance){
        nodes.get(node).addEdge(new NearShortestRoute.Edge(nodes.get(destination), distance));
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

    public boolean removeEdge (int node, int destination){
        return nodes.get(node).deleteEdge(destination);
    }

    public NearShortestRoute removeShortestPath(NearShortestRoute.Node previousNode){
        while(previousNode!=null && previousNode.getPreviousNode()!=null){
            this.removeEdge(previousNode.getPreviousNode().getName(), previousNode.getName());
            previousNode = previousNode.getPreviousNode();
        }
        return this;
    }

    public NearShortestRoute.Node setShortestPath(int start, int finish){
        ArrayList<NearShortestRoute.Edge> output = new ArrayList<NearShortestRoute.Edge>(0);
        this.distanceList[start] = 0;
        this.hn.insert(new NearShortestRoute.Edge(nodes.get(start), 0), 0);
        while(hn.length() != 0){
            NearShortestRoute.Edge current = hn.extractMin();
            NearShortestRoute.Node currentNode = current.getDestination();
            this.haveVisited[currentNode.getName()] = true;

            ArrayList<NearShortestRoute.Edge> edges = this.nodes.get(currentNode.getName()).getEdges();

            for(int i = 0; i < this.nodes.get(currentNode.getName()).getEdgeCount(); i++){
                int neighborName = edges.get(i).getDestination().getName();
                int neighborDistance = edges.get(i).getDistance();
                System.out.println("neightbor distance: " + neighborDistance);
                if(     !haveVisited[neighborName] &&
                        distanceList[currentNode.getName()] != -1 &&
                        distanceList[currentNode.getName()] + neighborDistance < (distanceList[neighborName]<0?Integer.MAX_VALUE:distanceList[neighborName])){
                    distanceList[neighborName] = distanceList[currentNode.getName()] + neighborDistance;
                    edges.get(i).getDestination().setPreviousNode(currentNode);
                    this.hn.insert(edges.get(i), distanceList[neighborName]);
                }
            }
        }

        return nodes.get(finish);
    }
}