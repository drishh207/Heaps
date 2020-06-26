package priority_queues;
import java.util.*;

//the class for priority queue in JDK uses min heap. If we want max heap, we can use comparator.
//lesser the number , greater the priority
public class queue 
{
    public static void main(String args[])
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(85);
        queue.add(62);
        queue.add(77);
        queue.add(54);
        queue.add(42);
        queue.add(72);
        queue.add(68);
        queue.add(21);
        queue.add(16);
        
        //remove deletes the element at the root
        System.out.println("Deleted element: " + queue.remove());
        
        //poll also removes the element from the root
        System.out.println("Polled element: " + queue.poll());
        
        //with remove object parameter can also be passed
        System.out.println("Deleted element(68): " + queue.remove(68));
        
        //printing the queue
        Object arr[] = queue.toArray();
        for(Object a : arr)
            System.out.print(a + " , ");
        System.out.println();
        
        System.out.println("Peeked element: " + queue.peek());
        
        
    }
}
