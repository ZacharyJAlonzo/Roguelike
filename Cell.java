/**
 * @(#)Cel.java
 *
 *
 * @author 
 * @version 1.00 2020/2/23
 */


public class Cell 
{	
	CellQueue contained;	
    public Cell(RObject obj) 
    {  		 	
    	contained = new CellQueue(obj);  
    }
    
    //get the displayed object
    public RObject getObject()
    {
    	return contained.peek();
    }
    
    //remove the displayed object
    public void removeObject()
    {
    	contained.pop();
    }
    
    public boolean addObject(RObject obj)
    {
    	return contained.push(obj);
    }
}