package heaps;

//max heap implementation - parents are always greater than the children and it is a complete binary tree
public class Heaps 
{
    private int heap[];
    private int size;
    
    Heaps(int capacity)
    {
        heap = new int[capacity];
    }
    
    public boolean isFull()
    {
        return size == heap.length;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public void insert(int value)
    {
        if(isFull())throw new IndexOutOfBoundsException("Heap is full");
        else
        {
            heap[size] = value;
            fixHeapAbove(size);
            size++;
        }
    }
    
    public int delete(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        int parent = getParent(index);
        int deletedValue = heap[index];
        
        //replace the deleted node value with the rightmost value
        heap[index] = heap[size - 1];
        //0 indicating no value at last position
        heap[size-1] = 0;

        //if the deleted postion was root or the replaced value is less then it's parent, then children need to be fixed.
        if (index == 0 || heap[index] < heap[parent]) {
            fixHeapBelow(index, size - 1);
        }
        else {
            fixHeapAbove(index);
        }

        size--;

        return deletedValue;
    }
   
    
    private void fixHeapAbove(int index)
    {
        int value = heap[index];
        while(index > 0 && value > heap[getParent(index)])
        {
            //moving the parent's value down the array if it is less than the inserted value.
            heap[index] = heap[getParent(index)];
            //incrementing the index to the parent's index
            index = getParent(index);
        }
        //putting the value in its correct position.
        heap[index] = value;
    }
    

    
    private void fixHeapBelow(int index, int lastHeapIndex) {
        int childToSwap;

        while (index <= lastHeapIndex) {
            int leftChild = getChild(index, true);
            int rightChild = getChild(index, false);
            
            //if leftchild index is less than the last index i.e. size-1
            if (leftChild <= lastHeapIndex) {
                //if the rightchild index is greater than size-1 i.e. out of bounds
                if (rightChild > lastHeapIndex) {
                    childToSwap = leftChild;
                }
                else {
                    //if left child is greater than right child then it has to swapped with the parent else right child
                    childToSwap = (heap[leftChild] > heap[rightChild] ? leftChild : rightChild);
                }
                
                //if the new parent is less than its childern
                if (heap[index] < heap[childToSwap]) {
                    //swap the values
                    int tmp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = tmp;
                }
                else {
                    break;
                }

                index = childToSwap;
            }
            else {
                break;
            }
        }
    }
    
    
    private int getChild(int index,boolean con)
    {
        return 2 * index + (con ? 1 : 2);
    }
    private int getParent(int index)
    {
        return (index - 1) / 2;
    }
    
    public void printheap()
    {
        for(int i = 0; i < size; i++){
            System.out.print(heap[i]);
            System.out.print(" , ");
        }
        System.out.println();
    }
    
    public int peek()
    {
        return heap[0];
    }
    
    public void sort(){
        int lastHeapIndex = size-1; 
        for(int i = 0; i < lastHeapIndex; i++)
        {
            int temp = heap[0];
            heap[0] = heap[lastHeapIndex - i];
            heap[lastHeapIndex - i] = temp;
            
            //sorting leaving the last position
            //the sorted partition increases from right to left
            fixHeapBelow(0 , lastHeapIndex - i - 1);
        }
    }
   
    public static void main(String[] args) 
    {
        Heaps h = new Heaps(9);
        h.insert(85);
        h.insert(62);
        h.insert(77);
        h.insert(54);
        h.insert(42);
        h.insert(72);
        h.insert(68);
        h.insert(21);
        h.insert(16);
        h.printheap();
        
        //delete element by index
        System.out.println("Deleted value: " + h.delete(0));
        h.printheap();
        
        System.out.println("Peeked element: " + h.peek());
        
        h.sort();
        h.printheap();
    }
    
}
