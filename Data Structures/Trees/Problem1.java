/* Following the specification in the README.md file, provide your 
 * Problem1 class.
 * */

public class Problem1
{
    public static void main (String args[])
    {
        String input = "1 2 + 3 * 4 -";
        ExpressionTree testTree = new ExpressionTree(input);
        System.out.println(testTree.postfix());
        System.out.println(testTree.prefix());
        System.out.println(testTree.infix());
        System.out.println(testTree.eval());
        
    
    }












}
