import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public class Heap<T> {
    private HeapNode<T>[] arr;
    private int length = 0;
    final private HashMap<T, Integer> position = new HashMap<>();

    public Heap(int N){
        this.startHeap(N);
    }

    public void heapify_Up(int index){
        if(arr[index].getParent()!=null && arr[index].getPriority()<arr[index].getParent().getPriority()){
            heapify_Up(swapHeapNodes(arr[index], arr[index].getParent())[0]);
            checker();
        }
    }

    public void heapify_Down(int index){
        var node = arr[index];
        if(     node.getChildOne() != null &&
                (node.getChildTwo()== null ||
                        node.getChildTwo().getPriority()>node.getChildOne().getPriority()) &&
                node.getChildOne().getPriority()<node.getPriority()) {
            heapify_Down(swapHeapNodes(node.getChildOne(),node)[1]);
            checker();
        }else if (node.getChildTwo() != null &&
                node.getChildTwo().getPriority()<node.getPriority()){
            heapify_Down(swapHeapNodes(node.getChildTwo(),node)[1]);
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

    private int[] swapHeapNodes(HeapNode<T> Swap1, HeapNode<T> Swap2){
        HeapNode<T> currentParent = Swap2.getParent()==null || (Swap1.getParent() != null && Swap1.getParent()==Swap2) ? Swap2 : Swap1;
        HeapNode<T> currentChild = Swap2.getParent()==null || (Swap1.getParent() != null && Swap1.getParent()==Swap2) ? Swap1 : Swap2;
        int parentIndex = position.get(currentParent.value());
        int childIndex = position.get(currentChild.value());

        T ParentObject = currentParent.value();
        T ChildObject = currentChild.value();

        int ParentPriority = currentParent.getPriority();
        int ChildPriority = currentChild.getPriority();

        currentChild.update(ParentObject);
        currentParent.update(ChildObject);
        currentChild.setPriority(ParentPriority);
        currentParent.setPriority(ChildPriority);
        position.replace(ParentObject, childIndex);
        position.replace(ChildObject, parentIndex);

        return new int[]{parentIndex, childIndex};
    }

    @SuppressWarnings("unchecked")
    public void startHeap(int N){
        this.arr = (HeapNode<T>[]) new HeapNode[N];
        for (int i = 0; i < N; i++) {
            this.arr[i] = null;
        }
    }

    public void insert(T value, int priority){
        if(position.containsKey(value)){
            return;
        }
        int index = 0;
        HeapNode<T> parent = null;
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
        arr[index] = new HeapNode<>(value, priority);
        if(parent != null && parent.getChildOne()==null){
            parent.setChildOne(arr[index]);
        }else if(parent != null && parent.getChildTwo()==null){
            parent.setChildTwo(arr[index]);
        }
        heapify_Up(index);
        length++;
        this.checker();
    }

    public T findMin(){
        return arr[0].value();
    }

    public void delete(int index){
        T itemToDelete = arr[index].value();
        arr[index].setPriority(Integer.MAX_VALUE);
        heapify_Down(index);

        if(null!=arr[position.get(itemToDelete)].getParent()) {
            if (arr[position.get(itemToDelete)] == arr[position.get(itemToDelete)].getParent().getChildOne()) {
                arr[position.get(itemToDelete)].getParent().setChildOne(null);
            } else {
                arr[position.get(itemToDelete)].getParent().setChildTwo(null);
            }
        }
        arr[position.get(itemToDelete)] = null;
        position.remove(itemToDelete);
        length--;
    }

    public T extractMin(){
        T output = findMin();
        delete(0);
        return output;
    }

    public int length(){
        return length;
    }

    @Override
    public String toString() {
        return toStringRecursive(arr[0], "", true);
    }

    private String toStringRecursive(HeapNode<T> node, String prefix, boolean isLeft) {
        StringBuilder output = new StringBuilder();
        if (node != null) {
            output.append(prefix).append(isLeft ? "├── " : "└── ").append(node).append("\n").append(toStringRecursive(node.getChildOne(), prefix + (isLeft ? "│   " : "    "), true)).append(toStringRecursive(node.getChildTwo(), prefix + (isLeft ? "│   " : "    "), false));
        }
        return output.toString();
    }
}
