/**
 * @(#)MovingObject.java
 *
 *
 * @author 
 * @version 1.00 2020/2/25
 */


abstract class MovingObject extends RObject
{
	protected Tile playMap;
	protected int currentTile;
	protected int ax, ay;
		   
    public MovingObject(Tile t) 
    {
    	super(true);
    	isMoving = true;
    	playMap = t;
    	currentTile = 0;
    	
    	ax = playMap.getSize()/2-1;
    	ay = playMap.getSize()/2-1;
    	
    	playMap.spawn(this, currentTile, ax, ay);
    }
    
    public void tick()
    {
    		
    }  
    
    public boolean moveRight()
    {
    	//the moving entity has attempted to step into a new tile
    	if(ax+1 == playMap.getCells(currentTile).length)
    	{
    		//is there a tile to the right?
    		/* 0 1 2  no right for 2
    		 * 3 4 5  no right for 5
    		 * 6 7 8  no right for 8
    		 */
    		switch(currentTile)
    		{
    			case 2:
    			{
    				return false;
    			}
    			
    			case 5:
    			{
    				return false;   				
    			}
    			case 8:
    			{
    				return false;    			
    			}
    			
    			default:
    			{
    				break;
    			}
    		}
    		
    		//if there is a tile, is it blocked by a wall?
    		//ax is 0 because we are checking the first row
    		if(playMap.getCells(currentTile+1)[ay][0].getObject().getDisplay() == '#')
    		{
    			//the object we tried to reach was a wall
    			return false;
    		}
    		else
    		{
    			playMap.getCells(currentTile)[ay][ax].removeObject();			
    		 			
    			//since we ae moving to a new tile, update the current tile
    			currentTile += 1;
    			ax = 0;
    			//ay remains the same
    			
    			 playMap.getCells(currentTile)[ay][ax].addObject(this);
    			return true;
    		}
    		
    	}
    	//we are only moving within the tile's bounds
    	else
    	{
    		if(playMap.getCells(currentTile)[ay][ax+1].getObject().getDisplay() == '#')
    		{
    			//the object we tried to reach was a wall
    			return false;
    		}
    		else
    		{
				//update ax 
				 playMap.getCells(currentTile)[ay][ax].removeObject();			
    			ax += 1;
    			 playMap.getCells(currentTile)[ay][ax].addObject(this);
    			//ay remains the same
    			return true;
    		}
    		
    	}

    }
    
    
    
    
    
    
    
    
    
    public boolean moveLeft()
    {
    	//the moving entity has attempted to step into a new tile
    	if(ax-1 == -1)
    	{
    		//is there a tile to the left?
    		/* 0 1 2  no right for 0
    		 * 3 4 5  no right for 3
    		 * 6 7 8  no right for 6
    		 */
    		switch(currentTile)
    		{
    			case 0:
    			{
    				return false;
    			}
    			
    			case 3:
    			{
    				return false;   				
    			}
    			case 6:
    			{
    				return false;    			
    			}
    			
    			default:
    			{
    				break;
    			}
    		}
    		
    		//if there is a tile, is it blocked by a wall?
    		//ax is 0 because we are checking the first row
    		if(playMap.getCells(currentTile-1)[ay][playMap.getCells(currentTile).length-1].getObject().getDisplay() == '#')
    		{
    			//the object we tried to reach was a wall
    			return false;
    		}
    		else
    		{
    			playMap.getCells(currentTile)[ay][ax].removeObject();			

    			//since we ae moving to a new tile, update the current tile
    			currentTile -= 1;
    			ax = playMap.getCells(currentTile).length-1;
    			//ay remains the same
    			
    			 playMap.getCells(currentTile)[ay][ax].addObject(this);
    			return true;
    		}
    		
    	}
    	//we are only moving within the tile's bounds
    	else
    	{
    		if(playMap.getCells(currentTile)[ay][ax-1].getObject().getDisplay() == '#')
    		{
    			//the object we tried to reach was a wall
    			return false;
    		}
    		else
    		{
				//update ax 
				 playMap.getCells(currentTile)[ay][ax].removeObject();			
    			ax -= 1;
    			 playMap.getCells(currentTile)[ay][ax].addObject(this);
    			//ay remains the same
    			return true;
    		}
    		
    	}

    }
    
    





    public boolean moveUp()
    {
    	//the moving entity has attempted to step into a new tile
    	if(ay-1 == -1)
    	{
    		//is there a tile to the north?
    		/* 0 1 2  no right for 0
    		 * 3 4 5  no right for 1
    		 * 6 7 8  no right for 2
    		 */
    		switch(currentTile)
    		{
    			case 0:
    			{
    				return false;
    			}
    			
    			case 1:
    			{
    				return false;   				
    			}
    			case 2:
    			{
    				return false;    			
    			}
    			
    			default:
    			{
    				break;
    			}
    		}
    		
    		//if there is a tile, is it blocked by a wall?
    		if(playMap.getCells(currentTile-3)[playMap.getCells(currentTile).length-1][ax].getObject().getDisplay() == '#')
    		{
    			//the object we tried to reach was a wall
    			return false;
    		}
    		else
    		{
    			//since we ae moving to a new tile, update the current tile
    			playMap.getCells(currentTile)[ay][ax].removeObject();
    			
    			currentTile -= 3;
    			ay = playMap.getCells(currentTile).length-1;
    			//ax remains the same
    			
    			playMap.getCells(currentTile)[ay][ax].addObject(this);
    								
    			return true;
    		}
    		
    	}
    	//we are only moving within the tile's bounds
    	else
    	{
    		if(playMap.getCells(currentTile)[ay-1][ax].getObject().getDisplay() == '#')
    		{
    			//the object we tried to reach was a wall
    			return false;
    		}
    		else
    		{
				//update ax 
				 playMap.getCells(currentTile)[ay][ax].removeObject();			
    			ay -= 1;
    			 playMap.getCells(currentTile)[ay][ax].addObject(this);
    			//ay remains the same
    			return true;
    		}
    		
    	}

    }
    
    
    
    
    
    
     public boolean moveDown()
    {
    	//the moving entity has attempted to step into a new tile
    	if(ay+1 == playMap.getCells(currentTile).length)
    	{
    		//is there a tile to the south?
    		/* 0 1 2  no right for 6
    		 * 3 4 5  no right for 7
    		 * 6 7 8  no right for 8
    		 */
    		switch(currentTile)
    		{
    			case 6:
    			{
    				return false;
    			}
    			
    			case 7:
    			{
    				return false;   				
    			}
    			case 8:
    			{
    				return false;    			
    			}
    			
    			default:
    			{
    				break;
    			}
    		}
    		
    		//if there is a tile, is it blocked by a wall?
    		if(playMap.getCells(currentTile+3)[0][ax].getObject().getDisplay() == '#')
    		{
    			System.out.println("wall");
    			//the object we tried to reach was a wall
    			return false;
    		}
    		else
    		{
    			System.out.println("here");
    			//since we ae moving to a new tile, update the current tile
    			playMap.getCells(currentTile)[ay][ax].removeObject();			
    			
    			currentTile += 3;
    			ay = 0;
    			  			
    			playMap.getCells(currentTile)[ay][ax].addObject(this);
    			//ax remains the same
    			return true;
    		}
    		
    	}
    	//we are only moving within the tile's bounds
    	else
    	{
    		if(playMap.getCells(currentTile)[ay+1][ax].getObject().getDisplay() == '#')
    		{
    			//the object we tried to reach was a wall
    			return false;
    		}
    		else
    		{
				//update ax 
				 playMap.getCells(currentTile)[ay][ax].removeObject();			
    			ay += 1;
    			 playMap.getCells(currentTile)[ay][ax].addObject(this);
    			//ay remains the same
    			return true;
    		}
    		
    	}

    }
    	
}
/*
 *public boolean moveRight()
    {
    		
    	if((getCurrentTile().getAY()+1) == getCurrentTile().getMapTile().getTiles()[0].length) //moved over a mapTile
    	{
    		   		
    		switch(getCurrentTile().getMapTile().getArrayNumber())
    		{
    			case 2:
    			{
    				return false;
    			}
    			
    			case 5:
    			{
    				return false;   				
    			}
    			case 8:
    			{
    				return false;    			
    			}
    			
    			default:
    			{
    				break;
    			}
    		}
    		
    		
    		if(getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()+1].getTiles()[getCurrentTile().getAX()][0].arrive(this))
    		{
    			getCurrentTile().leave();   		
    			setCurrentTile(getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()+1].getTiles()[getCurrentTile().getAX()][0]);
    		}
    		else //moving to new mapTile, but something blocks the way
    		{
    			EF_MovingEntity temp = getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()+1].getTiles()[getCurrentTile().getAX()][0].getMovingContained();
    			
    			if(temp == null) //non-movable object blocks the path
        		{
        			return false;
        		}        		
        		
        		else //NPC blocks the path
        		{
        			if(!(temp.isFriendly()))
        			{        				
        				return false;
        			}
        			else
        			{        				
        				return false;
        			}
        		}
    		}
    		    			
    	}      	
        else 
        {
        	if(getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()][getCurrentTile().getAY()+1].arrive(this))
        	{
        		getCurrentTile().leave();
        		setCurrentTile(getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()][getCurrentTile().getAY()+1]);
        	}
        	else //something blocks the way
        	{
        		EF_MovingEntity temp = getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()][getCurrentTile().getAY()+1].getMovingContained();
        		
        		if(temp == null) //non-movable object blocks the path
        		{
        			return false;
        		}        		
        		else //NPC blocks the path
        		{
        			if(!(temp.isFriendly()))
        			{
        				return false;
        			}
        			else
        			{
        				return false;
        			}
        		}
        	}
    			
        }		
    
    	return true;
    }   
    public boolean moveLeft()
    {
    
    	if((getCurrentTile().getAY()-1) == -1) //moved over a mapTile
    	{   	
    		switch(getCurrentTile().getMapTile().getArrayNumber())
    		{
    			case 0:
    			{
    				return false;
    			}
    			
    			case 3:
    			{
    				return false;   				
    			}
    			case 6:
    			{
    				return false;    			
    			}
    			    			
    			default:
    			{
    				break;
    			}
    		}
    	  		
    		
    		if(getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()-1].getTiles()[getCurrentTile().getAX()][getCurrentTile().getMapTile().getTiles()[0].length-1].arrive(getCurrentTile().leave()))
    		{
    			getCurrentTile().leave();
    			setCurrentTile(getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()-1].getTiles()[getCurrentTile().getAX()][getCurrentTile().getMapTile().getTiles()[0].length-1]);
    		}
    		else //moving to new mapTile, but something blocks the way
    		{
    			EF_MovingEntity temp = getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()-1].getTiles()[getCurrentTile().getAX()][getCurrentTile().getMapTile().getTiles()[0].length-1].getMovingContained();
    			
    			if(temp == null) //non-movable object blocks the path
        		{
        			return false;
        		}        		
        		
        		else //NPC blocks the path
        		{
        			if(!(temp.isFriendly()))
        			{        				
        				return false;
        			}
        			else
        			{        				
        				return false;
        			}
        		}
    		}
    			
    	}      	
    	else
    	{   	
    		if(getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()][getCurrentTile().getAY()-1].arrive(this))
    		{
    			getCurrentTile().leave();
    			setCurrentTile(getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()][getCurrentTile().getAY()-1]);   
    		}
    		else //something blocks the way
    		{
    			EF_MovingEntity temp = getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()][getCurrentTile().getAY()-1].getMovingContained();
    			
    			if(temp == null) //non-movable object blocks the path
        		{
        			return false;
        		}        		
        		
        		else //NPC blocks the path
        		{
        			if(!(temp.isFriendly()))
        			{       				
        				return false;
        			}
        			else
        			{        				
        				return false;
        			}
        		}
    		}
    		
    	}
    		return true;
    }
    
    public boolean moveUp()
    {
    	if((getCurrentTile().getAX()-1) == -1) //moved over a mapTile
    	{    	
    		switch(getCurrentTile().getMapTile().getArrayNumber())
    		{
    			case 0:
    			{
    				return false;
    			}
    			
    			case 1:
    			{
    				return false;   				
    			}
    			case 2:
    			{
    				return false;    			
    			}
    			
    			default:
    			{
    				break;
    			}
    		}
    		
    		if(getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()-3].getTiles()[getCurrentTile().getMapTile().getTiles().length-1][getCurrentTile().getAY()].arrive(this))
    		{
    			getCurrentTile().leave();
    			setCurrentTile(getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()-3].getTiles()[getCurrentTile().getMapTile().getTiles().length-1][getCurrentTile().getAY()]);
    			return true;
    		}
    		else //moving to new mapTile, but something blocks the way
    		{
    			EF_MovingEntity temp = getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()-3].getTiles()[getCurrentTile().getMapTile().getTiles().length-1][getCurrentTile().getAY()].getMovingContained();
    			
    			if(temp == null) //non-movable object blocks the path
        		{
        			return false;
        		}        		
        		
        		else //NPC blocks the path
        		{
        			if(!(temp.isFriendly()))
        			{        				
        				return false;
        			}
        			else
        			{        				
        				return false;
        			}
        		}
    		}
    			
    	}  
    	else
    	{  	
    		if(getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()-1][getCurrentTile().getAY()].arrive(this))
    		{
    			getCurrentTile().leave();
    			setCurrentTile(getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()-1][getCurrentTile().getAY()]);
    			return true;
    		}
    		else //something blocks the way
    		{
    			EF_MovingEntity temp = getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()-1][getCurrentTile().getAY()].getMovingContained();
    			
    			if(temp == null) //non-movable object blocks the path
        		{
        			return false;
        		}        		
        		
        		else //NPC blocks the path
        		{
        			if(!(temp.isFriendly()))
        			{       				
        				return false;
        			}
        			else
        			{        				
        				return false;
        			}
        		}
    		}
    		
    	}
    }
    
    public boolean moveDown()
    {
   		if((getCurrentTile().getAX()+1) == getCurrentTile().getMapTile().getTiles().length) //moved over a mapTile
    	{
    		switch(getCurrentTile().getMapTile().getArrayNumber())
    		{
    			case 6:
    			{
    				return false;
    			}
    			
    			case 7:
    			{
    				return false;   				
    			}
    			case 8:
    			{
    				return false;    			
    			}
    			
    			default:
    			{
    				break;
    			}
    		}
    		
    		if(getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()+3].getTiles()[0][getCurrentTile().getAY()].arrive(this))
    		{
    			getCurrentTile().leave();
    			setCurrentTile(getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()+3].getTiles()[0][getCurrentTile().getAY()]);	
    		}
    		else
    		{
    			EF_MovingEntity temp = getCurrentTile().getMapTile().getRoamingTile().getTiles()[getCurrentTile().getMapTile().getArrayNumber()+3].getTiles()[getCurrentTile().getMapTile().getTiles().length-1][getCurrentTile().getAY()].getMovingContained();
    			
    			if(temp == null) //non-movable object blocks the path
        		{
        			return false;
        		}        		
        		
        		else //NPC blocks the path
        		{
        			if(!(temp.isFriendly()))
        			{        				
        				return false;
        			}
        			else
        			{        				
        				return false;
        			}
        		}
    		}
    			
    	}  
    	else
    	{	
    		if(getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()+1][getCurrentTile().getAY()].arrive(this))
    		{
    			getCurrentTile().leave();
    			setCurrentTile(getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()+1][getCurrentTile().getAY()]);
    		}
    		else
    		{
    			EF_MovingEntity temp = getCurrentTile().getMapTile().getTiles()[getCurrentTile().getAX()+1][getCurrentTile().getAY()].getMovingContained();
    			
    			if(temp == null) //non-movable object blocks the path
        		{
        			return false;
        		}        		
        		
        		else //NPC blocks the path
        		{
        			if(!(temp.isFriendly()))
        			{       				
        				return false;
        			}
        			else
        			{        				
        				return false;
        			}
        		}
    		}
    		
    	}
    	return true;
    }
 **/