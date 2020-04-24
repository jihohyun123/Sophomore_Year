/* SymbolBalance.java
 * checks for balanced symbols
 * Ji Ho Hyun
 * jh3888 
 */

import java.util.Scanner;
import java.io.*;

public class SymbolBalance
{
    // Writing my main:
           
    public static void main(String[] args) throws FileNotFoundException     
    {
        // listing out some variables:
        Scanner scan1 = new Scanner(args[0]);        
        String testName = scan1.nextLine();
        File test = new File(testName);
        
        MyStack a = new MyStack<Character>();
        int lineNumber = 1;        
        
        // booleans for '*' and '"', as these are special cases
        boolean star=false;
        int counter=0;
        boolean talk = false;
        // scanner for input
        Scanner scan2 = new Scanner(test);
        
        // while loop
        while (scan2.hasNext())
        {
            String line=scan2.nextLine();
            
            // incrementing by individual chars
            for(int i=0;i<line.length(); i++)
            {                
                // pushing in the opening bracket
                if((line.charAt(i)=='{')||(line.charAt(i)=='(')||(line.charAt(i)=='['))
                {    
                    /* if statement takes care of special scenario where the 
                    /* opening bracket is in a comment or quote */
                    
                    if((star==false)&&(talk==false))
                    {
                        a.push(line.charAt(i));
                    }
                    else
                    {
                        a.push(line.charAt(i));
                        a.pop();
                    }
                           
                    
                } 
                
                // chain of else-if statements for different closing brackets:
                
                else if((line.charAt(i)=='}'))
                {
                    // popping an empty stack error handling:
                    if (a.isEmpty())
                        {
                            System.out.println(lineNumber+": Empty");
                            return;
                            
                        }
                    
                    // if the char is in a quote/comment, ignore
                    else if ((star==true)||(talk==true))
                    {
                        a.push(line.charAt(i));
                        a.pop();                       
                        
                    }
                    
                    // otherwise, it's a match! pop!
                    else if ((char)a.peek()=='{')
                    {
                       a.pop();                                             
                    }
                    
                    else
                    {
                        // mismatch error handling:
                        System.out.println(lineNumber+": "+line.charAt(i)+", "+(char)a.peek());
                        return;
                    }
                }
                
                // same setup as above
                else if((line.charAt(i)==')'))
                {
                    if (a.isEmpty())
                        {
                            System.out.println(lineNumber+": Empty");
                            return;
                            
                        }
                    else if ((star==true)||(talk==true))
                    {
                        a.push(line.charAt(i));
                        a.pop();                       
                        
                    }
                    else if ((char)a.peek()=='(')
                    {    
                        a.pop();   
                    }
                   
                    else
                    {
                        System.out.println(lineNumber+": "+line.charAt(i)+", "+(char)a.peek());
                        return;                     
                    }
                }
                
                // same setup as above
                else if((line.charAt(i)==']'))
                {
                    
                    if (a.isEmpty())
                        {
                            System.out.println(lineNumber+": Empty");
                            return;
                            
                        }
                    else if ((star==true)||(talk==true))
                    {
                        a.push(line.charAt(i));
                        a.pop();                       
                        
                    }
                    else if ((char)a.peek()=='[')
                    {
                        a.pop();                                              
                    }
                  
                    else
                    {
                        System.out.println(lineNumber+": "+line.charAt(i)+", "+(char)a.peek());
                        return;                       
                    }
                }
                
                // for quotes ("), if not already in a quote:
                else if ((line.charAt(i)=='"')&&(talk==false))
                {
                    if(star==true)
                    {
                        a.push(line.charAt(i));
                        a.pop();
                    }
                    else if(star==false)
                    {
                        a.push(line.charAt(i));
                        talk=true;
                    }
                }
                
                // for comments (*), if not already in a comment:
                else if ((line.charAt(i)=='*')&&(star==false))
                {
                    if (talk==true)
                    {
                        a.push(line.charAt(i));
                        a.pop();
                    }
                    else if (talk==false)
                    {
                        a.push(line.charAt(i));            
                        star=true;
                    }                  
                }
                
                // handles closing a quote:
                else if ((line.charAt(i)=='"')&&(talk==true))
                {
                    a.pop();
                    talk=false;                   
                }
                
                // handles closing a comment:
                else if ((line.charAt(i)=='*')&&(star==true))
                {
                    if(line.charAt(i-1)==' ')
                    {
                        a.pop();
                        star=false;                  
                    }
                    
                    else
                    {
                        a.push(line.charAt(i));
                        a.pop();                       
                    }                                          
                }
            }
            
            // increments the line number for each pass through:
            lineNumber++;
           
        }
        
        // if the stack is not empty after running all the way through:
        if(!a.isEmpty())
        {
            System.out.println("Non-Empty Stack: "+(char)a.peek()+" "+(int)a.size());
        }
        
        // if the stack is empty and no errors have been caught:
        else
        {
            System.out.println("Correct");
        }
    
    }
    
}