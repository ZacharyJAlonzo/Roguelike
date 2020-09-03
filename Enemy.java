/**
 * @(#)Enemy.java
 *
 *
 * @author 
 * @version 1.00 2020/3/23
 */


public class Enemy extends MovingObject
{

	private boolean isAgro;
	private int sightRange;
	private RObject agroEnemy;
	
	private int axNew;
	private int ayNew;
	
    public Enemy(Tile t, int x, int y) 
    {
    	super(t, x, y);
    	display = 'E'; 
    		
    	sightRange = 7;
    	isAgro = false;
    	agroEnemy = null;
    	
		axNew = 0;
		ayNew = 0;
    	
    	totalHP = 20;
    	currentHP = 20;
    }
    
    public void attack(MovingObject target)
    {
    	target.takeDamage(10);
    }
    
    public void Tick()
    {
    	if(!isAgro)
    	{
    		//System.out.println("enemy is not agro");
    		//check surroundings for an enemy
    		RObject enemy = isEnemyInSight();
    		//an enemy is seen
    		if(enemy != null)
    		{
    			
    			//agro to the enemy
    			agroEnemy = enemy;
    			isAgro = true;
    			//find the closest tile to that enemy
    			Cell nextMove = findRouteToHostile(agroEnemy);
    			
    			//move to that tile
    			if(nextMove != null)
    			{
    				//move to the new tile
    				moveState succeed = moveState.NO_MOVE;
    				if(axNew > ax)
    				{
    					succeed = moveRight();
    				}
    				if(axNew < ax)
    				{
    					succeed = moveLeft();
    				}
    				if(ayNew > ay)
    				{
    					succeed = moveDown();
    				}
    				if(ayNew < ay) 
    				{
    					succeed = moveUp();
    				}
    				
    				switch(succeed)
    				{
    					case NO_MOVE:
    					{
    						didTick = true;
    						break;
    					}
    					case MOVE:
    					{
    						//we are done, end the turn for the AI.
    						didTick = true;
    						return;
    					}
    					case ATTACK:
    					{
    						System.out.println("enemy attack");
    						if(((MovingObject)agroEnemy).isDead())
    						{
    							agroEnemy = null;
    							isAgro = false;
    							return;
    						}
    						if(agroEnemy != null)
    						attack((MovingObject)agroEnemy);
    						didTick = true;
    						return;
    					}
    					default:
    					{
    						System.out.println("AI failed");
    						return;
    					}
    				}
    			}
    		}
    		
    	}
    	//the enemy is already agro
    	else
    	{
    			Cell nextMove = findRouteToHostile(agroEnemy);
    		
    			didTick = true;
    			//move to that tile
    			if(nextMove != null)
    			{
    			//move to the new tile
    				System.out.println(ayNew+" "+axNew+" "+nextMove.getObject().getDisplay());
    				moveState succeed = moveState.NO_MOVE;
    				if(axNew > ax)
    				{
    					succeed = moveRight();
    				}
    				if(axNew < ax)
    				{
    					succeed = moveLeft();
    				}
    				if(ayNew > ay)
    				{
    					succeed = moveDown();
    				}
    				if(ayNew < ay) 
    				{
    					succeed = moveUp();
    				}
    				
    				switch(succeed)
    				{
    					case NO_MOVE:
    					{
    						didTick = true;
    						break;
    					}
    					case MOVE:
    					{
    						//we are done, end the turn for the AI.
    						didTick = true;
    						return;
    					}
    					case ATTACK:
    					{
    						System.out.println("enemy attack");
    						if(((MovingObject)agroEnemy).isDead())
    						{
    							isAgro = false;
    							agroEnemy = null;
    							return;
    						}
    						if(agroEnemy != null)
    						attack((MovingObject)agroEnemy);
    						didTick = true;
    						return;
    					}
    					default:
    					{
    						System.out.println("AI failed");
    						return;
    					}
    				}
    			}
    	}	
    }
    
    //scans a sightRange x sightRange area around the enemy.
    //returns true if an enemy is visible in that area.
    private RObject isEnemyInSight()
    {
    	//y limit to the north
    	int yLimitU = ay - sightRange;
    		if(yLimitU < 0)
    		{ 	
    			//bounds check, keep it on the tile.
    			yLimitU = 0;
    		}
    	//y limit to the south
    	int yLimitD = ay + sightRange;
    		if(yLimitD >= playMap.getCells(currentTile).length)
    		{
    			yLimitD = playMap.getCells(currentTile).length-1;
    		}
    	//x limit to the right
    	int xLimitR = ax + sightRange;
    		if(xLimitR >= playMap.getCells(currentTile).length)
    		{
    			xLimitR = playMap.getCells(currentTile).length-1;
    		}
    	//x limit to the left
    	int	xLimitL = ax - sightRange;
    		if(xLimitL < 0)
    		{
    			xLimitL = 0;
    		}
    	
    	
    	
    	//using the bounds we have just created, scan the area.
    	for(int r = yLimitU; r <= yLimitD; r++)
    	{
    		for(int c = xLimitL; c <= xLimitR; c++)
    		{
    			RObject temp = playMap.getCells(currentTile)[r][c].getObject();
    			
    			if(temp != null)
    			{
    				
    				switch(temp.getDisplay())
    				{
    					//player
    					case '@':
    					{
    						//an enemy is seen, return true
    						return temp;
    					}
    					//wall and ground should do nothing
    					case ' ':
    					case '#':
    					{
    						continue;
    					}
    				}
    			}
    		}
    	}
    	
    	//loop fell through, no enemy in sight
    	return null;
    }
    
    //returns the next cell that the AI should move to
    public Cell findRouteToHostile(RObject en)
    {
    	MovingObject enemy = (MovingObject)en;
    	//where is the enemy?
    	int eX = enemy.getAX();
    	int eY = enemy.getAY();
    	
    		
    	int sr = ay;
    	int sc = ax;
    	
    	int size = playMap.getCells(currentTile).length;
    	//parallel int array
    	int[][] lengthMap = new int[size][size];
    	  
    	   	
    		//initial values of -1
    		for(int r = 0; r < size; r++)  	
    			for(int c = 0; c < size; c++)	
    				lengthMap[r][c] = -1;
    		  		
    		int nextVal = 0;
    		boolean changeMade = true;
    		//initialize the start point.
    		lengthMap[sr][sc] = 0;
    		
    		while(changeMade)
    		{ 			
    			//needed to break out
    			changeMade = false;
    			for(int r = 0; r < size-1; r++)
    			{
    				for(int c = 0; c < size-1; c++)
    				{ 					
    					if(lengthMap[r][c] == nextVal)
    					{
    						//cell below
    						if(r+1 < size)
    						if(playMap.getCells(currentTile)[r+1][c].getObject().getDisplay() != '#' && lengthMap[r+1][c] == -1)
    						{
    							lengthMap[r+1][c] = nextVal+1;
    							changeMade = true;
    						}
    						//cell above
    						if(r-1 >= 0)
    						if(playMap.getCells(currentTile)[r-1][c].getObject().getDisplay() != '#'  && lengthMap[r-1][c] == -1)
    						{
    							lengthMap[r-1][c] = nextVal+1;
    							changeMade = true;
    						}
    						//cell right
    						if(c+1 < size)
    						if(playMap.getCells(currentTile)[r][c+1].getObject().getDisplay() != '#'  && lengthMap[r][c+1] == -1)
    						{
    							lengthMap[r][c+1] = nextVal+1;
    							changeMade = true;
    						}
    						//cell left
    						if(c-1 >= 0)
    						if(playMap.getCells(currentTile)[r][c-1].getObject().getDisplay() != '#'  && lengthMap[r][c-1] == -1)
    						{
    							lengthMap[r][c-1] = nextVal+1;
    							changeMade = true;
    						}		
    					}	
    				}
    			}
    			
    				
    			nextVal++;
    		}
			
			//how far away is the enemy?
			int distance = lengthMap[eY][eX];
			
			
			
			//now, using the another lengthmap, pathfind from the enenmy to the AI.
			int lengthMapBack[][] = new int[size][size];
			
				//initial values of -1
    		for(int r = 0; r < size; r++)  	
    			for(int c = 0; c < size; c++)	
    				lengthMapBack[r][c] = -1;
    		  		
    		nextVal = 0;
    		changeMade = true;
    		//initialize the start point.
    		//in the second runthrough, the start point is the enemy location
    		lengthMapBack[eY][eX] = 0;
    		
    		while(changeMade)
    		{ 			
    			//needed to break out
    			changeMade = false;
    			for(int r = 0; r < size-1; r++)
    			{
    				for(int c = 0; c < size-1; c++)
    				{ 					
    					if(lengthMapBack[r][c] == nextVal)
    					{
    						//cell below
    						if(r+1 < size)
    						if(playMap.getCells(currentTile)[r+1][c].getObject().getDisplay() != '#' && lengthMapBack[r+1][c] == -1)
    						{
    							lengthMapBack[r+1][c] = nextVal+1;
    							changeMade = true;
    						}
    						//cell above
    						if(r-1 >= 0)
    						if(playMap.getCells(currentTile)[r-1][c].getObject().getDisplay() != '#'  && lengthMapBack[r-1][c] == -1)
    						{
    							lengthMapBack[r-1][c] = nextVal+1;
    							changeMade = true;
    						}
    						//cell right
    						if(c+1 < size)
    						if(playMap.getCells(currentTile)[r][c+1].getObject().getDisplay() != '#'  && lengthMapBack[r][c+1] == -1)
    						{
    							lengthMapBack[r][c+1] = nextVal+1;
    							changeMade = true;
    						}
    						//cell left
    						if(c-1 >= 0)
    						if(playMap.getCells(currentTile)[r][c-1].getObject().getDisplay() != '#'  && lengthMapBack[r][c-1] == -1)
    						{
    							lengthMapBack[r][c-1] = nextVal+1;
    							changeMade = true;
    						}		
    					}	
    				}
    			}
    			
    				
    			nextVal++;
    		}
			
			
			
			//after both of these runs, we now have the abiliy to find the next move. 
			/*@ is the starting value of 0
			 *
			 *3 2 1 @ 1 2 3 
			 *4 3 2 1 2 3 4
			 *5 4 3 2 3 4 5
			 *6 5 4 E 4 5 6
			 *
			 *E is at a distance of 3 
			 *the first iteration is complete
			 *
			 *now, we start from E
			 *
			 *6 5 4 @ 4 5 6
			 *5 4 3 2 3 4 5
			 *4 3 2 1 2 3 4
			 *3 2 1 E 1 2 3
			 *
			 *now, we subtract from both of these arrays.
			 *clearly, the shortest path is
			 *   x 0 x
			 *   x 1 x
			 *   x 2 x
			 *   x 3 x
			 * 
			 *and, for the reverse
			 *   x 3 x
			 *   x 2 x
			 *   x 1 x
			 *   x 0 x
			 *
			 *now, using the calculated distance, 3 in this example,
			 *we can subtract to ensure we are on the proper path.
			 *
			 *if (initial lengthMap)[r][c] == distance - (backwards lengthMap)[r][c]
			 *then we know we are on the right path.
			 *	0 == (3) - 3 (true)
			 *	1 == (3) - 2 (true)
			 *	2 == (3) - 1 (true)
			 *	3 == (3) - 0 (true)
			 *
			 *if we exclude the tiles where lengthMap is 0 (the starting tiles)
			 *we return the second case, the tile of distance 1.
			 *
			 *this is the closest tile to the enemy.
			 */
		
			 
			Cell nextMove = null;
			for(int i = 0; i < size; i++)
			{
				for(int k = 0; k < size; k++)
				{
					if(lengthMap[i][k] == distance-lengthMapBack[i][k] && lengthMapBack[i][k] >= 0 && lengthMap[i][k] > 0)
					{
						 
						 nextMove = playMap.getCells(currentTile)[i][k];
						 
						 if(!(Math.abs(k - ax) > 1))
							 axNew = k;
							else continue;
						 
						 if(!(Math.abs(i - ay) > 1))	
							 ayNew = i;
							else continue;
						 
						 //System.out.println("enemy distance: " +distance);
						 //System.out.println("new x: " +ayNew + " new y " + axNew);
						 
						 return nextMove;
					}		
				}
			}
			
    	return nextMove;
    }
    
   
  
    
    
    public char getDisplay()
    {
    	return display;
    }
    
}