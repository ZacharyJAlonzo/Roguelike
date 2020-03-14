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
	
	//can you walk through it?
	protected boolean isBarrier;
		
    public RObject(boolean tick)
    {
    	canTick = tick;
    }
    
    public void tick(){}
  	abstract char getDisplay();
  	
}