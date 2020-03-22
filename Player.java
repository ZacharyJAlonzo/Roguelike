/**
 * @(#)Player.java
 *
 *
 * @author 
 * @version 1.00 2020/2/25
 */

//import java.Event.*;
public class Player extends MovingObject
{
	
    public Player(Tile t)
    {
    	super(t);
    	display = '@';	
    }
    
    public void tick()
    {
    	//listen for input, manage turn system. 1 'tick' = 1 turn.
    	//player's tick will call tick on the current tile.
    	//tile will call tick on each cell.
    }
    
    
    // 'L' key action. COQ 
   	public void look()
   	{
   		
   		
   	}
    public int getCurrentTile()
    {
    	return currentTile;
    }
    
    public boolean moveRight()
    {
    	boolean succeed = super.moveRight();
    	
    	if(succeed)
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    
    
    public char getDisplay()
    {
    	return display;
    }
    
}