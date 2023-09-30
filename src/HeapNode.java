import java.util.Objects;
public class HeapNode <T>{
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
