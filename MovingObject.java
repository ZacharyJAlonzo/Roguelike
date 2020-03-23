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
    	
    	System.out.println("Spawning in constructor");
    	playMap.spawn(this, currentTile, ax, ay);
    }
    
    public MovingObject(Tile t, int x, int y)
    {
    	super(true);
    	isMoving = true;
    	playMap = t;
    	currentTile = 0;
    	
    	ax = x;
    	ay = y;
    	
    	System.out.println("Spawning in constructor");
    	playMap.spawn(this, currentTile, ax, ay);
    }
    
    
    
    abstract public void Tick();

    
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
    		switch(playMap.getCells(currentTile+1)[ay][0].getObject().getDisplay())
    		{
    			case '#':
    				return false;
    			case ' ':
    			{
    				playMap.getCells(currentTile)[ay][ax].removeObject();			
    		 			
    				//since we ae moving to a new tile, update the current tile
    				currentTile += 1;
    				ax = 0;
    				//ay remains the same
    			
    			 	playMap.getCells(currentTile)[ay][ax].addObject(this);
    				return true;
    			}
    			default:
    				return false;
    		}
    		
    	}
    	//we are only moving within the tile's bounds
    	else
    	{
    		switch(playMap.getCells(currentTile)[ay][ax+1].getObject().getDisplay())
    		{
    			case '#':
    				return false;
    			case ' ':
    			{
    				//update ax 
				 	playMap.getCells(currentTile)[ay][ax].removeObject();			
    				ax += 1;
    				 playMap.getCells(currentTile)[ay][ax].addObject(this);
    				//ay remains the same
    				return true;
    			}
    			default:
    				return false;
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
    		
    		switch(playMap.getCells(currentTile-1)[ay][playMap.getCells(currentTile).length-1].getObject().getDisplay())
    		{
    			case '#':
    				return false;
    			case ' ':
    			{
    				playMap.getCells(currentTile)[ay][ax].removeObject();			

    				//since we ae moving to a new tile, update the current tile
    				currentTile -= 1;
    				ax = playMap.getCells(currentTile).length-1;
    				//ay remains the same
    			
    			 	playMap.getCells(currentTile)[ay][ax].addObject(this);
    				return true;
    			}
    			default:
    				return false;
    		}
    		
    	}
    	//we are only moving within the tile's bounds
    	else
    	{
    		
    		switch(playMap.getCells(currentTile)[ay][ax-1].getObject().getDisplay())
    		{
    			case '#':
    				return false;
    			case ' ':
    			{
    				//update ax 
				 	playMap.getCells(currentTile)[ay][ax].removeObject();			
    				ax -= 1;
    			 	playMap.getCells(currentTile)[ay][ax].addObject(this);
    				//ay remains the same
    				return true;
    			}
    			default:
    				return false;
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
    		
    		switch(playMap.getCells(currentTile-3)[playMap.getCells(currentTile).length-1][ax].getObject().getDisplay())
    		{
    			case '#':
    				return false;
    			case ' ':
    			{
    				//since we ae moving to a new tile, update the current tile
    				playMap.getCells(currentTile)[ay][ax].removeObject();
    			
    				currentTile -= 3;
    				ay = playMap.getCells(currentTile).length-1;
    				//ax remains the same
    			
    				playMap.getCells(currentTile)[ay][ax].addObject(this);
    								
    				return true;
    			}
    			default:
    				return false;
    		}
    		
    	}
    	//we are only moving within the tile's bounds
    	else
    	{
    		switch(playMap.getCells(currentTile)[ay-1][ax].getObject().getDisplay())
    		{
    			case '#':
    				return false;
    			case ' ':
    			{
    				//update ax 
				 	playMap.getCells(currentTile)[ay][ax].removeObject();			
    				ay -= 1;
    			 	playMap.getCells(currentTile)[ay][ax].addObject(this);
    				//ay remains the same
    				return true;
    			}
    			default:
    				return false;
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
    		
    		
    		switch(playMap.getCells(currentTile+3)[0][ax].getObject().getDisplay())
    		{
    			case '#':
    				return false;
    			case ' ':
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
    			default:
    				return false;
    		}
    		
    	}
    	//we are only moving within the tile's bounds
    	else
    	{
    		switch(playMap.getCells(currentTile)[ay+1][ax].getObject().getDisplay())
    		{
    			case '#':
    				return false;
    			case ' ':
    			{
    				//update ax 
					 playMap.getCells(currentTile)[ay][ax].removeObject();			
    				ay += 1;
    			 	playMap.getCells(currentTile)[ay][ax].addObject(this);
    				//ay remains the same
    				return true;
    			}
    			default:
    				return false;
    		}
    		
    	}

    }
    
    
    public int getAX()
    {
    	return ax;
    }
    public int getAY()
    {
    	return ay;
    }
    
    
    	
}
