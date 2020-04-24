/* Following the specification in the README.md file, provide your 
 * SpellChecker class.
 */

import java.util.*;
import java.io.*;
import java.util.Scanner;


public class SpellChecker
{
    HashSet dictionary = new HashSet();
    public SpellChecker(String filename)
    {
        try
        {
            String name = filename;
            File newFile = new File(name);
            Scanner scan = new Scanner(newFile);
            while (scan.hasNextLine())
            {
                String currentLine = scan.nextLine();
                String[] temp = currentLine.split(" ", -1);
                for(int i = 0; i< temp.length; i++)
                {
                    if(!temp[i].isEmpty())
                    {
                        dictionary.add(temp[i].toLowerCase());
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("invalid file");
        }
        
        
        
    }
    
    public List <String> getIncorrectWords(String filename)
    {
        List<String> wrongWord = new ArrayList<String>();
        
        try
        {
            File newFile2 = new File(filename);
            Scanner scan2 = new Scanner(newFile2);
            
            while (scan2.hasNextLine())
            {
                String currentLine2 = scan2.next();
                if(currentLine2.isEmpty())
                {
                    wrongWord.add(currentLine2);
                }
                String[] temp2 = currentLine2.split(" ", -1);
                for(int i = 0; i< temp2.length; i++)
                {
                    if(!temp2[i].isEmpty())
                    {
                        String currentWord = temp2[i].toLowerCase();
                        StringBuilder punctuation = new StringBuilder(currentWord);
                        if (!Character.isLetterOrDigit(punctuation.charAt(0)))
                        {
                            punctuation.deleteCharAt(0);
                        }
                        if (!Character.isLetterOrDigit(punctuation.charAt(punctuation.length()-1)))
                        {
                            punctuation.deleteCharAt(punctuation.length()-1);
                        }
                        String editedWord = punctuation.toString();
                        if (!dictionary.contains(editedWord))
                        {
                            wrongWord.add(currentWord);
                        }
                    }
                }
            }
            
        }
        
        catch(FileNotFoundException e)
        {
            System.out.println("invalid file");
        }
        return wrongWord;
    }
    
    public List<String> getSuggestions(String word)
    {
        
        List<String> suggestionsList = new ArrayList<String>();
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        if (word.equals("\"\""))
        {
            suggestionsList.add("a b c d e f g h i j k l m n o p q r s t u v w x y z");
        }
        
        
        
        
        
        for(int i = 0; i <= word.length(); i++)
        {
            for(int j=0; j<alphabet.length();j++)
            {
                StringBuilder test1=new StringBuilder(word);
                test1.insert(i, alphabet.charAt(j));
                if (valid(test1.toString()))
                {
                    suggestionsList.add(test1.toString());
                }
            }
                
        }
        
        for (int i = 0; i< word.length(); i++)
        {
            StringBuilder test2=new StringBuilder(word);
            test2.deleteCharAt(i);
                        
            if (valid(test2.toString()))
            {
                suggestionsList.add(test2.toString());
            }
        }
        
        for (int i = 0; i< word.length()-1; i++)
        {
            StringBuilder test3=new StringBuilder(word);
            char oldC = word.charAt(i);
            char newC = word.charAt(i+1);
            test3.setCharAt(i, newC);
            test3.setCharAt(i+1, oldC);
                        
            if (valid(test3.toString()))
            {
                suggestionsList.add(test3.toString());
            }
        }
        return suggestionsList;
 
        
    }
    
    public boolean valid(String word)
    {
        String check = word;
        if (dictionary.contains(word))
        {
            return true;
        }
        else
            return false;
              
    }
}