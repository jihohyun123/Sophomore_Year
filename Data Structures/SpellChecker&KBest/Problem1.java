import java.util.*;
import java.io.*;


public class Problem1 
{
    public static void main(String[] args) throws IOException
    {
        Scanner scan = new Scanner(args[0]);
        SpellChecker test = new SpellChecker("words.txt");
        String fileName=scan.next();        
        List<String> wrong = test.getIncorrectWords(fileName);
        
        for(String word : wrong) 
        {
			List<String> suggestions = test.getSuggestions(word);
			System.out.print(word + " - ");
			for (String suggestion : suggestions) 
            {
				System.out.print(suggestion + " ");
		
            }
			System.out.println();
        
        }
        
        
        
        
    }
}