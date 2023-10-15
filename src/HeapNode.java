public class HeapNode <T>{
    private int priorityValue;
    private T obj;
    private HeapNode<T> childOne;
    private HeapNode<T> childTwo;
    private HeapNode<T> parent;

    public HeapNode(T obj, int priorityValue){
        this.priorityValue = priorityValue;
        this.obj = obj;
    }

    public HeapNode<T> setPriority(int priority){
        this.priorityValue = priority;
        return this;
    }

    public HeapNode<T> setChildOne(HeapNode<T> node){
        childOne = node;
        if(node!=null) {
            node.setParent(this);
        }
        return this;
    }

    public HeapNode<T> setChildTwo(HeapNode<T> node){
        childTwo = node;
        if(node!=null) {
            node.setParent(this);
        }
        return this;
    }

    public HeapNode<T> setParent(HeapNode<T> parent) {
        this.parent = parent;
        return this;
    }

    public HeapNode<T> update(T obj){
        this.obj = obj;
        return this;
    }
    public int getPriority() {
        return priorityValue;
    }

    public HeapNode<T> getChildOne(){
        return childOne;
    }

    public HeapNode<T> getChildTwo(){
        return childTwo;
    }

    public HeapNode<T> getParent() {
        return parent;
    }

    public T value(){
        return this.obj;
    }

    @Override
    public String toString() {
        return this.NodeInfo() + "Val: " + obj.toString();
    }


    private String NodeInfo(){
        return "P: " + this.priorityValue + " " + "PP: " + (this.parent!=null?this.parent.getPriority():"null") + " " + "C1P: " + (this.childOne!=null?this.childOne.getPriority():"null") + " " + "C2P: " + (this.childTwo!=null?this.childTwo.getPriority():"null") + " ";
    }
}