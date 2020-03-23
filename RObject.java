/**
 * @(#)Object.java
 *
 *
 * @author 
 * @version 1.00 2020/2/23
 */


abstract class RObject 
{
	//what should be displayed in the cell?
	protected char display;
	
	//can it move around?
	protected boolean isMoving;
	
	//can it Tick?
	protected boolean canTick;
	//has it already ticked this turn?
	protected boolean didTick;
	
	//can you walk through it?
	protected boolean isBarrier;
		
    public RObject(boolean tick)
    {
    	canTick = tick;
    }
    
    public boolean CanTick()
    {
    	return canTick;
    }
    public boolean DidTick()
    {
    	return didTick;
    }
    
    public void resetTickState()
    {
    	didTick = false;
    }
   
    abstract public void Tick();
  	abstract char getDisplay();
  	
}