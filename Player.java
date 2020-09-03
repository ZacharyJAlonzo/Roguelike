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
    
    // 'L' key action
   	public Cell look(int x, int y)
   	{
   	 	if(x <= playMap.getCells(currentTile).length-1 && x >= 0 && y <= playMap.getCells(currentTile).length-1 && y >= 0)

   	 	{
   	 		return playMap.getCells(currentTile)[y][x];
   	 	}
   		else return null;
   	}
    
    public boolean moveR()
    {
    	moveState value = super.moveRight();
    	switch(value)
    	{
    		case NO_MOVE:
    		{
    			return false;
    		}
    		case MOVE:
    		{
    			return true;
    		}
    		case ATTACK:
    		{
    			attack((MovingObject)playMap.getCells(currentTile)[ay][ax+1].getObject());
    			return true;
    		}
    		default:
    			return false;
    	}
    }
    
    public boolean moveL()
    {
    	moveState value = super.moveLeft();
    	switch(value)
    	{
    		case NO_MOVE:
    		{
    			return false;
    		}
    		case MOVE:
    		{
    			return true;
    		}
    		case ATTACK:
    		{
    			attack((MovingObject)playMap.getCells(currentTile)[ay][ax-1].getObject());
    			return true;
    		}
    		default:
    			return false;
    	}
    }
    
    public boolean moveU()
    {
    	moveState value = super.moveUp();
    	switch(value)
    	{
    		case NO_MOVE:
    		{
    			return false;
    		}
    		case MOVE:
    		{
    			return true;
    		}
    		case ATTACK:
    		{
    			attack((MovingObject)playMap.getCells(currentTile)[ay-1][ax].getObject());
    			return true;
    		}
    		default:
    			return false;
    	}
    }
    
    public boolean moveD()
    {
    	moveState value = super.moveDown();
    	switch(value)
    	{
    		case NO_MOVE:
    		{
    			return false;
    		}
    		case MOVE:
    		{
    			return true;
    		}
    		case ATTACK:
    		{
    			attack((MovingObject)playMap.getCells(currentTile)[ay+1][ax].getObject());
    			return true;
    		}
    		default:
    			return false;
    	}
    }
    
    public void attack(MovingObject target)
    {
    	target.takeDamage(10);
    }
    	
    public int getCurrentTile()
    {
    	return currentTile;
    }
    
    public char getDisplay()
    {
    	return display;
    }   
}