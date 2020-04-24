# Homework 1

## Due Friday, September 21th at 4:00pm. 

Please remember that to submit the assignment you will need to the Education drop down menu and select 
assignment complete.


## Problem 1 - 18 points

Define a rectangle class that provides a *getLength* method and and a
*getWidth* method.  Rectangle should also implement the *Comparable*
interface; it should compare by **perimeter**. Create another class
called Problem1 that has the findMax routine (included below) and add
a main method that creates an array of Rectangle objects and finds the
largest Rectangle on the basis of its perimeter.

```
public static <AnyType extends Comparable<AnyType>>  AnyType findMax(AnyType[] arr) {
  int maxIndex = 0;
    for (int i = 1; i < arr.length; i++)
        if ( arr[i].compareTo(arr[maxIndex]) > 0 )
	       maxIndex = i;
	           return arr[maxIndex];
}
```		   

Note: An empty Rectangle.java file has been provided in the workspace.  You will 
need to fill that in from scratch.  The Problem1.java file has also been provided.
That file includes the pre-written findMax; do **not** change the findMax code. Fill
your test code in to the main method provided.

## Problem 2 - 18 points

In the file Problem2.java implement a static method with the signature:

```
public static <AnyType extends Comparable<AnyType>>       
     int binarySearch(AnyType[] a, AnyType x);
```
   
   
This method should then trigger another helper method that searches the array recursively for the value x.

Finally, create a main method that builds an array of rectangles (use your class from problem 1) and then sort that array with Arrays.sort.  
Demonstrate your recursive binarySearch method on this array.
 
## Problem 3 - 14 points

In Problem3.java, implement the three code fragments from written problem 3.  Have your code repeatedly run each fragment on various values of n. Time each run and see if the progression of timings as n increases matches the predicted run times from your written assignment.  

By default the java virtual machine uses a feature called the Just-In-Time (JIT) compiler that actually compiles the java bytecode down to native machine code for the computer on which you're running.  The use of the JIT generally speeds up execution of code, but in this case will cause the run times to not follow the pattern you would expect since it does some on-the-spot optimizations. Therefore, you must disable the JIT to do this problem. If you are running java from the command line, you can disable the JIT for a single run by using the command line argument: -Xint. So if you want to run your class Problem 3 with the JIT turned off:

java -Xint Problem3

Place the results of your timing and your explanation in a file called Problem3.txt.  For the third fragment, set k equal to 2 for all of your testing.  The easiest way to time your run is to execute the following code before each fragment:

long startTime = System.nanoTime();
then after each fragment place:

long endTime = System.nanoTime();

The elapsed time is the difference between these two variables.

## Problem 4 

Update a file called STUDENTREADME.md with information on how to compile and run each problem from the command line in the codio terminal. You can also use the space to let the TAs know about any other assumptions
you made while writing your solutions.