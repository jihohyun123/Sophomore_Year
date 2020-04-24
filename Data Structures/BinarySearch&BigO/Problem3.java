/* Problem3
 * Ji Ho Hyun
 * jh3888
 * A class that runs three code fragments */


public class Problem3
{
    // code fragment 1 inserted into a method:
    public static int frag1(int n)
    {
    int sum = 0;
    for ( int i = 0; i < 23; i ++)
    {
        for ( int j = 0; j < n ; j ++)
        sum = sum + 1;
    }  
        return sum;
    }
    
    // a method for running fragment 1:
    public static long run1(int k)
    {
        long startTime = System.nanoTime();
        frag1(k);
        long endTime = System.nanoTime();
        long calc = (endTime-startTime);
        return calc;
  
    }
    
    // code fragment 2 inserted into a method:
    public static int frag2(int n)
    {
        int sum = 0;
        for ( int i = 0; i < n ; i ++)
            {
                for ( int k = i ; k < n ; k ++)
                sum = sum + 1;
        
            }
            return sum;
    }
    
    // a method for running fragment 2:
    public static long run2(int l)
    {
        long startTime = System.nanoTime();
        frag2(l);
        long endTime = System.nanoTime();
        long calc = (endTime-startTime);
        return calc;
    }
    
    // code fragment 3 inserted into a method
    public static int foo(int n, int k) 
    {
 
    if(n<=k)
        return 1;
    else
        return foo(n/k,k) + 1;
    }
    
    // a method for running fragment 3:
    public static long runFoo(int m, int l)
    {
        long startTime = System.nanoTime();
        foo(m, 2);
        long endTime = System.nanoTime();
        long calc = (endTime-startTime);
        return calc;
  
    }


    public static final void main(String[] args)
    {
        // code fragment 1
        run1(10);
        System.out.println("Timing for frag1(10): "+ run1(10));
        run1(100);
        System.out.println("Timing for frag1(100): "+ run1(100));
        run1(1000);
        System.out.println("Timing for frag1(1000): "+ run1(1000));
        run1(10000);
        System.out.println("Timing for frag1(10000): "+ run1(10000));
        
        // code fragment 2
        run2(10);
        System.out.println("Timing for frag2(10): "+ run2(10));
        run2(100);
        System.out.println("Timing for frag2(100): "+ run2(100));
        run2(1000);
        System.out.println("Timing for frag2(1000): "+ run2(1000)); 
        run2(10000);
        System.out.println("Timing for frag2(10000): "+ run2(10000));
        
        
        // code fragment 3 
        runFoo(10,2);
        System.out.println("Timing for foo(10): "+ runFoo(10,2));
        runFoo(100,2);
        System.out.println("Timing for foo(100): "+ runFoo(100,2));
        runFoo(1000,2);
        System.out.println("Timing for foo(1000): "+ runFoo(1000,2));
        runFoo(10000,2);
        System.out.println("Timing for foo(10000): "+ runFoo(10000,2));
      

    }
                     
}
