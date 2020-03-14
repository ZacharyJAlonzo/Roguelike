/**
 * @(#)CellQueue.java
 *
 *
 * @author 
 * @version 1.00 2020/2/23
 */


public class CellQueue 
{
	private RObject[] contained;

    public CellQueue(RObject obj) 
    {
    	contained = new RObject[2];
    
    	contained[1] = obj;
    	contained[0] = null;
    }
    
   
       
    /////////simulating a stack////////
    public RObject peek()
    {
    	if(contained[0] == null)
    		return contained[1];
    	return contained[0];
    }
      
    public void pop()
    {
    	contained[0] = null;
    }
    
    public boolean push(RObject obj)
    {
    	if(contained[0] == null)
    	{
    		contained[0] = obj;
    		return true;
    	}
    	else return false;    		
    }
    ///////////////////////////////////
    
}