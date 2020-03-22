/**
 * @(#)Ground.java
 *
 *
 * @author 
 * @version 1.00 2020/2/23
 */


public class Path extends RObject
{
	
    public Path() 
    {
    	super(false);
    	isBarrier = false;
    	
    	display = ' ';
    }
    
    public char getDisplay()
    {
    	return display;
    }
    
}