/* MyStack.java
 * Creates a stack using LinkedList
 * Ji Ho Hyun
 * jh3888
 * */

import java.util.LinkedList;

public class MyStack<AnyType> 
{
    LinkedList a = new LinkedList<AnyType>();
    int size=0;
    
    // constructor
    public MyStack()
    {
        a.clear();
     
    }
    
    // peek method:
    public AnyType peek()
    {
        
        AnyType temp = (AnyType) a.getLast();
        return temp;
    }
    
    // push method:
    public void push(AnyType x)
    {
        a.add(x);
        size++;
        
    }
    
    // pop method:
    public AnyType pop()
    {
        if(a.size()==0)
        {
            return null;
        }
        
        // had to cast a.removeLast() into an AnyType
        size--;
        AnyType temp = (AnyType) a.removeLast();
        return temp;        
    }
    
    
    // isEmpty() method:    
    public boolean isEmpty()
    {
        return size()==0;
    }
    
    // size method:
    public int size()
    {
        return size;
    }
    
    // clear method:
    public void clear()
    {
        a.clear();
        size=0;
        
        
    }

}
