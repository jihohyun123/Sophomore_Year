/* TwoStackQueue.java
 * creates a TwoStackQueue that implements MyQueue using MyStack
 * Ji Ho Hyun
 * jh3888
 * */
public class TwoStackQueue<AnyType> implements MyQueue<AnyType>
{
    // Performs the enqueue operation
    
    private MyStack a = new MyStack<AnyType>();
    private MyStack b = new MyStack<AnyType>();
    private int size=0;
    
    // enqueue is a basic push:
    public void enqueue(AnyType x)
    {
        a.push(x);
        size++;
    
    }
    
    /* dequeue is a bit more complicated:
    /* I pop everything out of stack a and push it into stack b
    /* also checks for empty stack and returns null
     */
     
    public AnyType dequeue()
    {
        if (b.size()==0)
        {
            if (a.size()==0)
            {
                System.out.println("empty stack");
                
                return null;
        
            }
            while(a.size()!=0)
            {
                AnyType c = (AnyType) a.pop();
                b.push(c);
            }
        
        }
                      
        AnyType temp = (AnyType) b.pop();
        size--;
        
        return temp;
                  
    }
    
    
    // Checks if the Queue is empty
    public boolean isEmpty()
    {
        return size()==0;
    
    }
    // Returns the number of elements currently in the queue
    public int size()
    {
        return size;    
    
    }    

}



   
