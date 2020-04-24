// AvlTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
import java.util.*;
import java.util.Scanner;
import java.io.*;
public class AvlTree
{
    /**
     * Construct the tree.
     */
    public AvlTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( String x, int line )
    {
        root = insert( x, line, root );
    }

       
    
    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains( String x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    

    private static final int ALLOWED_IMBALANCE = 1;
    
    // Assume t is either balanced or within one of being balanced
    private AvlNode balance( AvlNode t )
    {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public void checkBalance( )
    {
        checkBalance( root );
    }
    
    private int checkBalance( AvlNode t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return height( t );
    }
    
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<String> insert( String x, int line, AvlNode<String> t )
    {
        if( t == null )
            {
                LinkedList newList = new LinkedList(); 
                newList.add(line);
                return new AvlNode<String>( x, newList, null, null );
            }
        int compareResult = x.compareTo( t.element );
        
        if( compareResult < 0 )
            t.left = insert( x, line, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, line, t.right );
        else
        {
            if(!t.indexList.contains(line))
              
            t.indexList.add(line); 
        }
         return balance( t );
    }
        
    private boolean contains( String x, AvlNode t )
    {
        while( t != null )
        {
            int compareResult = x.compareTo((String) t.element );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    public void printIndex()
    {
        printIndex(root);
    }
    private void printIndex( AvlNode t )
    {
        if( t != null )
        {
            
            printIndex( t.left );
            
            String line="";
            for(int i=0; i<t.indexList.size();i++)
            {
                if(i==t.indexList.size()-1)
                {
                    line = line + t.indexList.get(i);
                }
                else
                    line = line + t.indexList.get(i)+","+" ";
                
            }
            
            System.out.println( t.element + ":" +" "+ line );
            
            printIndex( t.right );
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode t )
    {
        return t == null ? -1 : t.height;
    }

    
    private AvlNode rotateWithLeftChild( AvlNode k2 )
    {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

   
    private AvlNode rotateWithRightChild( AvlNode k1 )
    {
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    private AvlNode doubleWithLeftChild( AvlNode k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    
    private AvlNode doubleWithRightChild( AvlNode k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
    
    public List linesForWord(String word)
    {
        return linesForWord(word, root);
    }
    
    private List linesForWord(String word, AvlNode x)
    {
        LinkedList empty = new LinkedList();
        if(x!=null)
        {
            if(contains(word))
            {
            for(int i=0; i<x.indexList.size(); i++)
                {
                    
                    LinkedList list = (LinkedList) x.indexList;
                    
                    return list;
                }
            }
            linesForWord(word, x.left);
            linesForWord(word, x.right);
        }
        return empty;
                  
        
    }

     private static class AvlNode<String>
    {
            // Constructors
        AvlNode( String theElement, LinkedList n )
        {
            this( theElement, n, null, null );
        }

        AvlNode( String theElement, LinkedList n, AvlNode<String> lt, AvlNode<String> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
            indexList = n;
        }

        String element;      // The data in the node
        AvlNode<String> left;         // Left child
        AvlNode<String> right;        // Right child
        int height;       // Height
        LinkedList indexList;
    }
      /** The tree root. */
    private AvlNode root;

}
