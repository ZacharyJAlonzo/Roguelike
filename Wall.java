/**
 * @(#)Wall.java
 *
 *
 * @author 
 * @version 1.00 2020/2/23
 */


public class Wall extends RObject
{

    public Wall() 
    {
    	super(false);
    	isBarrier = true;
    	
    	display = '#';  	
    }
    
    public char getDisplay()
    {
    	return display;
    }
    
    
}