/* Problem2
 * Ji Ho Hyun
 * jh3888
 * A class that runs a generic Binary Search
 * using Recursion. */

import java.util.Arrays;

public class Problem2
{
    // constructor given
    public static <AnyType extends Comparable<AnyType>> 
        int binarySearch(AnyType[] a, AnyType x)
    
    {
        // instance variables:
        AnyType[] array = a;
        AnyType target = x;      
        int start = 0;
        int end = a.length-1;
        
        // runs the helper method
        return helper(a, target, start, end);
    }
    
    /* my helper method that runs the search using recursion
     * takes in two more parameters than binarySearch */
        
    public static <AnyType extends Comparable<AnyType>> 
        int helper (AnyType[] b, AnyType y, int start, int end)    
    {
        int mid = (start+end)/2;       
        if (start<=end)
        {
               
            if (y.compareTo(b[mid])==0)
                return mid;
              
            if (b[mid].compareTo(y)<0)
            {
                // runs helper again till y is found
                return helper(b, y, mid+1, end);
                
            }
            
            if (b[mid].compareTo(y)>0)        
            {
                // runs helper again till y is found
                return helper(b, y, start, mid-1);
                
            }
            // covers the scenario in which mid is 0
            if (mid==0)
            {
                if (y.compareTo(b[mid])==0)   
                    return mid;
                if (y.compareTo(b[mid+1])==0)
                    return mid+1;
                
            }
           
        }
        // in case the desired element is not found:        
        return -1;          
        
    }
        
    // a main method for testing the search: 
    public static final void main(String[] args) 
      
    {
         // same array of Rectangles as Problem1
         
        Rectangle [] r = new Rectangle [6];
       
        r[0]= new Rectangle(1, 5);
        
        r[1]= new Rectangle(1, 3);
        
        r[2]= new Rectangle(4, 5);
        
        r[3]= new Rectangle(15, 6);
        
        r[4]= new Rectangle(5, 7);
             
        r[5]= new Rectangle(7, 8);
              
        Arrays.sort(r);
        
        // The object we are searching for:
        
        Rectangle a =  new Rectangle (5,7);
   
        System.out.println("The Rectangle you are searching for"+
                         " is at index: " +binarySearch(r, a)+", has "
                          +"length: "+r[binarySearch(r, a)].getLength()
                          +", width: "+r[binarySearch(r, a)].getWidth());
         
    }
         
}