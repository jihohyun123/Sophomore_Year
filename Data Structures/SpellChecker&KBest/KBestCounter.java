import java.util.PriorityQueue;
import java.util.*;
public class KBestCounter<T extends Comparable<? super T>> implements KBest<T> {

    PriorityQueue<T> heap;
    int k;
    int kBest;
    

    public KBestCounter(int k) 
    {
        
        heap = new PriorityQueue(k);        
        kBest = k;
        
    }

    public void count(T x) 
    {
        
        
        if(heap.size()<kBest)    
        {
            heap.add(x);
        }
        
        if((heap.size()>=kBest))
        {
        	if(x.compareTo(heap.peek())>0)
            {
                heap.poll();
                heap.add(x);
            }
            if(x.compareTo(heap.peek())==0)
            {
                System.out.println("equal to k good");
                heap.add(x);
            }
        }
        
        
        
    }

    public List<T> kbest() 
    {
        
        List print = new ArrayList<T>();
        Object[] array = heap.toArray();
        Arrays.sort(array);
                
        for(int i=array.length-1;i>=0;i--)
        {
            print.add(array[i]);
        }
        return print;
    }
    
}
