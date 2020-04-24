/* Following the specification in the README.md file, provide your 
 * Problem2 class.
 * */
public class Problem2 {



    /** If implemented correctly, this code should output: 
     * 0
     * 2
     * 1
     * 4
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9

     */
    public static final void main(String[] args) {


	TwoStackQueue<Integer> x = new TwoStackQueue<Integer>();
	System.out.println(x.size());

	x.enqueue(1);
	x.enqueue(2);
	System.out.println(x.size());
    x.enqueue(3);
    x.enqueue(4);
	System.out.println(x.dequeue());
	x.enqueue(5);
    System.out.println(x.size());
	x.enqueue(6);
	x.enqueue(7);
	System.out.println(x.dequeue());
	x.enqueue(8);
    System.out.println(x.dequeue());
    x.enqueue(9);

	while(!x.isEmpty())
	    System.out.println(x.dequeue());


    }





}