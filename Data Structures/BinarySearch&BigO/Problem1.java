/* Problem1.java
 * Ji Ho Hyun
 * jh3888
 * A program for creating an Array of Rectangles
 * and returning the largest one. */

import java.util.Arrays;

public class Problem1 {
  
  // the code fragment given:
  
  public static <AnyType extends Comparable<AnyType>>  AnyType findMax(AnyType[] arr) {
    int maxIndex = 0;
    for (int i = 1; i < arr.length; i++)
      if ( arr[i].compareTo(arr[maxIndex]) > 0 )
         maxIndex = i;
      return arr[maxIndex];
  }
  
  
  public static final void main(String[] args) 
  {
      // creating a new array of Rectangles
      Rectangle [] r = new Rectangle [6];
      
      // I filled the array with Rectangles of random width and height:
      
      r[0]= new Rectangle(1, 5);
       
      r[1]= new Rectangle(1, 3);
      
      r[2]= new Rectangle(4, 5);
      
      r[3]= new Rectangle(15, 6);
      
      r[4]= new Rectangle(5, 7);
       
      r[5]= new Rectangle(7, 8);
      
      // returns the results to the user
      System.out.println("Largest Rectangle: "+findMax(r).toString());  
    
    
  }
  
 
}


