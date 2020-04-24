/* Following the specification in the README.md file, provide your 
 * Problem2 class.
 */
import java.util.Scanner;
import java.io.*;

public class Problem2
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(args[0]);
        
        String fileName=scan.next();
        
        FileAvl tree = new FileAvl(fileName);
        
        
        tree.printIndex();
               
        
        
    }
    
    
    
    
    
    
}