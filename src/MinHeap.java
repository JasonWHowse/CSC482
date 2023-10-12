import java.util.Arrays;

public class MinHeap {
    private NodeDistance[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new NodeDistance[capacity];
    }

    // Other methods remain the same...

    public void insert(NodeDistance nodeDistance) {
        if (size >= capacity) {
            System.out.println("Heap is full. Cannot insert.");
            return;
        }

        heap[size] = nodeDistance;
        size++;
        heapifyUp(size - 1);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private void swap(int index1, int index2) {
        NodeDistance temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void heapifyUp(int index) {
        int parentIndex = getParentIndex(index);
        while (index > 0 && heap[index].distance < heap[parentIndex].distance) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private void heapifyDown(int index) {
        int minChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        if (rightChildIndex < size && heap[rightChildIndex].distance < heap[minChildIndex].distance)
            minChildIndex = rightChildIndex;

        if (minChildIndex < size && heap[index].distance > heap[minChildIndex].distance) {
            swap(index, minChildIndex);
            heapifyDown(minChildIndex);
        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    public NodeDistance extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty. Cannot extract minimum.");
            return null;
        }

        NodeDistance min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    public void printHeap() {
        System.out.println(Arrays.toString(heap));
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(new NodeDistance(0,-1));
        minHeap.insert(new NodeDistance(0,-1));
        minHeap.insert(new NodeDistance(0,-1));
        minHeap.insert(new NodeDistance(0,-1));
        minHeap.insert(new NodeDistance(0,-1));
        minHeap.insert(new NodeDistance(0,-1));

        System.out.println("Min heap:");
        minHeap.printHeap();

        System.out.println("Extracted min: " + minHeap.extractMin());
        System.out.println("Min heap after extraction:");
        minHeap.printHeap();
    }
}
