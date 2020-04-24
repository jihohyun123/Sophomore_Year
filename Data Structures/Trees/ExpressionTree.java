/* Following the specification in the README.md file, provide your 
 * ExpressionTree class.
 */

import java.util.Scanner;
import java.lang.*;
public class ExpressionTree 
{
   
        public MyStack<ExpressionTreeNode> treeStack = new MyStack<>();
        /* Your constructor should take in a string containing the postfix expression that you
         * are using to build your expression tree. */
        public ExpressionTree(String expression) 
        {
            
            /* Put your code to build the tree here. */
            String input = expression;
            Scanner scan= new Scanner(input);                     
            
            scan.useDelimiter(" ");
            
            while(scan.hasNext())
            {
                ExpressionTreeNode a;
                ExpressionTreeNode c;
                ExpressionTreeNode d;
                ExpressionTreeNode b;
                
                String stuff = scan.next();
                //System.out.println(stuff);               
                if ((stuff.equals("+"))||(stuff.equals("-"))||(stuff.equals("*"))||(stuff.equals("/")))
                {
                    a = new ExpressionTreeNode(stuff);
                    c = (ExpressionTreeNode) treeStack.pop();                    
                    d = (ExpressionTreeNode) treeStack.pop();
                    a.left = d;
                   
                    a.right = c;
                   
                    root = a;
                    treeStack.push(a);   
                   
                }
                else
                {
                    a = new ExpressionTreeNode(stuff);
                    treeStack.push(a);
                   
                }
            
                           
            
            }
            if (treeStack.size()!=1)
            {
                throw new StackOverflowError();
            }
        }
    
    
         /* This is the PUBLIC interface to the class. */
         public int eval()
         {
         
              /* Fill in code here. */
             return eval(root);
        
         }
    
        public String postfix() 
        {
            
              /* Fill in code here. */
            return postfix(treeStack.peek());
           
        }
    
    
        public String prefix() 
        {
            
              /* Fill in code here. */
            return prefix(treeStack.peek());
            
        }

        public String infix() 
        {
            
              /* Fill in code here. */
            return infix(treeStack.peek());
            
        }
    
    
         /* You will need to provide the private, recursive versions of these methods, 
          * the instance variable(s), and any static nested class that you might need below */
    
        private static class ExpressionTreeNode<String>
        {
            ExpressionTreeNode(String x)
            {
                this(x, null, null);
            }
            
            ExpressionTreeNode(String x, ExpressionTreeNode<String> l, ExpressionTreeNode<String> r)
            {
                data = x;
                left = l;
                right = r;
        
            }    
            String data;
            ExpressionTreeNode<String> left;
            ExpressionTreeNode<String> right;
           
        }
        private ExpressionTreeNode<String> root;
        
    private String postfix(ExpressionTreeNode x)
        {
            String out = "";
            if (x!=null)
            {
                
                out += postfix(x.left);
                out += postfix(x.right);
                out += x.data + " ";
            }
            
            return out;          
            
        }
     private String prefix(ExpressionTreeNode x)
        {
            String out = "";
            if (x!=null)
            {
                out += x.data + " ";
                out += prefix(x.left);
                out += prefix(x.right);
                
            }
            
            return out;          
            
        }
     private String infix(ExpressionTreeNode x)
        {
            String out = "";
            if (x!=null)
            {
                
                out += "(" + infix(x.left);
                out += x.data;
                out += infix(x.right)+")";
                
            }
            
            return out;          
            
        }
    
    
    private int eval(ExpressionTreeNode x) 
    {
        
            int test=-1;
        while(x!=null)
        {
            String current = (String) x.data;
            if(current.equals("+"))
            {
                return eval(x.left)+eval(x.right);
                               
            }
            if(current.equals("-"))
            {
                return eval(x.left)-eval(x.right);                
                
            }
            if(current.equals("*"))
            {
                return eval(x.left)*eval(x.right);
                
            }
            if(current.equals("/"))
            {
                if(x.right.data.equals("0"))
                {
                    throw new RuntimeException("divide by 0 error");
                }
                return eval(x.left)/eval(x.right);              
            }
            else
            {
                return Integer.parseInt(current);
            }
        }      
                
    return test;
    }
    
   
}
