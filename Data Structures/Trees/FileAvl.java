/* FileAvl.java
 * Ji Ho Hyun
 * jh3888 
 */


import java.util.*;
import java.util.Scanner;
import java.io.*;

public class FileAvl 
    
{
    /**
     * Construct the tree.
     */
    AvlTree fileTree = new AvlTree();
    public FileAvl(String file)
    {
        
        try
        {
        
        File input = new File(file);
        int lineNumero = 1;
        Scanner scan1 = new Scanner(input);                     
        
        while (scan1.hasNextLine())
        {
            String line = scan1.nextLine();
            
            String[] arr = line.split(" ", -1);
            for(int i = 0; i<arr.length; i++)
            {
                if(!arr[i].isEmpty())
                    //String in = arr[i];
                    //in = string.toLowerCase();
                    fileTree.insert (arr[i].toLowerCase(), lineNumero);
            }
             
            lineNumero ++;
        }
            
        
        }
        catch (FileNotFoundException e)
        {
            System.out.println("invalid file");
        }
                
    }

    public void printIndex()
    {
        fileTree.printIndex();
    }
       
    public List getLinesForWord(String word)
    {
        return fileTree.linesForWord(word);
    }
    

    
}
