/* Rectangle
 * Ji Ho Hyun
 * jh3888
 * A class that simulates a Rectangle that is
 * Comparable by perimeter. */

public class Rectangle implements Comparable<Rectangle>
{
    // instance variables:
    private double length;
    private double width;
    private double perimeter;
    
    public Rectangle (double l, double w)
    {
        length = l;
        width = w;
        perimeter = 2*length + 2*width;
        
    }
    
    // simple method that returns the length:
    public double getLength()
    {
        return length;
    
    }
    
    // simple method that returns the width:
    public double getWidth()
    {
        return width;       
    }
    
    // A method that returns the features of a Rectangle in a String:
    public String toString()
    {
        return ("perimeter: "+ perimeter+ ", length: " 
        + length + ", width: "+width);
    }
    
    // Establishing the parameters of compareTo for Rectangles
    public int compareTo(Rectangle other)
    {
        
        int perimeterComp = 0;
        
        if(this.perimeter>other.perimeter)
        {
        perimeterComp = 1;
        }
        else if (this.perimeter<other.perimeter)
        {
        perimeterComp = -1;
        }
        if(this.perimeter==other.perimeter)
        {
        perimeterComp = 0;
        }
        return perimeterComp;
        
    }
        


}