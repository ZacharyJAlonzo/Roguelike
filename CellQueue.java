/**
 * @(#)CellQueue.java
 *
 *
 * @author 
 * @version 1.00 2020/2/23
 */


public class CellQueue 
{
	 RObject[] contained;

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
    	if(contained[1] == null)
    		return;
    	else if(contained[0] == null) 
    		contained[1] = null;
    	else contained[0] = null;
    }
    
    public boolean push(RObject obj)
    {
    	if(contained[1] == null)
    	{
    		//System.out.println("contained[1] is null");
    		contained[1] = obj;
    		return true;
    	}
    	else if(contained[0] == null)
    	{
    		System.out.println("contained[0] is null");
    		contained[0] = obj;
    		return true;
    	}
    	//System.out.println(contained[0].getDisplay() + " " + contained[1].getDisplay());
    	return false;    		
    }
    ///////////////////////////////////
    
}