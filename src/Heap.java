import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public class Heap<T> {
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

    public static void main(String[] args) {
        StringBuilder[] treeTest = {
                new StringBuilder("hwllo"),
                new StringBuilder("world"),
                new StringBuilder("345"),
                new StringBuilder("unique"),
                new StringBuilder("items")
        };
        Heap<StringBuilder> Test = new Heap<StringBuilder>(treeTest);
        System.out.println("Origin:");
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
        Test.changePriority(treeTest[2], 0);
        System.out.println("Change Priority Test 1:");
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
        Test.changePriority(treeTest[2], 6);
        System.out.println("Change Priority Test 2:");
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
        Test.changePriority(treeTest[4], 0);
        System.out.println("Change Priority Test 3:");
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
        Test.changePriority(treeTest[4], 8);
        System.out.println("Change Priority Test 4:");
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
        Test.delete(4);
        System.out.println("Delete Test 1:");
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
        System.out.println("Insert Test:");
        Test.insert(new StringBuilder("bob"),0);
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
        Test.delete(treeTest[0]);
        System.out.println("Delete Test 2:");
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
        System.out.println("Extract Min Test:");
        System.out.println("Extracted Value: " + Test.extractMin());
        System.out.println(Test);
        System.out.println("Length: " + Test.length() + "\r\n");
    }
}
