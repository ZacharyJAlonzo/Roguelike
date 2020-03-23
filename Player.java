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
    
    public void Tick()
    {
    	
    	//listen for input, manage turn system. 1 'tick' = 1 turn.
    	//player's tick will call tick on the current tile.
    	//tile will call tick on each cell.
    	didTick = true;
    }
    
    
    // 'L' key action.
   	public Cell look(int x, int y)
   	{
   	 	if(x <= playMap.getCells(currentTile).length-1 && x >= 0 && y <= playMap.getCells(currentTile).length-1 && y >= 0)
   	 	{
   	 		return playMap.getCells(currentTile)[y][x];
   	 	}
   		else return null;
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
    
   /* public int getAX(){
    	return ax;
    }
    public int getAY(){
    	return ay;
    }*/
    
    public char getDisplay()
    {
    	return display;
    }
    
}