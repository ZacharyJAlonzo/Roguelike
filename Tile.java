
/**
 * @(#)Tile.java
 *
 *
 * @author 
 * @version 1.00 2020/2/23
 */


public class Tile 
{		
	private	char[][] patternList = {{' ',' ',' ',' '}, {' ',' ',' ','#'}, {' ',' ','#',' '}, //0, 1, 2
     							{' ',' ','#','#'}, {' ','#',' ',' '}, {' ','#',' ','#'}, //3, 4, 5
     							{' ','#','#',' '}, {' ','#','#','#'}, {'#',' ',' ',' '}, //6, 7, 8 
     							{'#',' ',' ','#'}, {'#',' ','#',' '}, {'#',' ','#','#'}, //9, 10, 11
     							{'#','#',' ',' '}, {'#','#',' ','#'}, {'#','#','#',' '}, {'#','#','#','#'}}; //12, 13, 14, 15
     							
      
	public Cell[][][] cells;
	private int[]keys;
	private int size;
	
    public Tile(int sz) 
    {
    	size = sz;
    	
    	//3x3 array of 10x10 cells. 
    	//0 1 2
    	//3 4 5
    	//6 7 8    	
    	cells = new Cell[9][size][size];
    	
    	//represents the same structure
    	//0 1 2
    	//3 4 5
    	//6 7 8 
    	keys = new int[9];
    	generateKeys();
        generateCells();
        
        //the initial clean tends to open up the pathways. 
        //Clean();
        	
        for(int i = 0; i < 1; i++)
        	Generate();
		
		//for(int j = 0; j < 1; j++)
        Clean();
        
        //sizes > 40 tend to have connectivity problems with only 1 final clean. have to obliterate it harder.
        int times = (int)Math.sqrt(size/2);
        if(times < 4)
        	times = 1;
        else times = 2;
        
        
        for(int k =0; k < times+1; k++)    	
        {
        	finalClean();
        	absoluteLastClean();       
        }
        //these method names ended up being pretty ironic
        killTwoWallPairs();
        absoluteLastClean();
    }     
    	
    	
    //Tile is used to generate terrain.
    
    /*Each tile is initially given a pattern.
     *	# # #
     *	# * * -- upper corner   
     *	# * #
     *
     *	# * #
     *	# * * -- path through North/South. Key = 3
     *	# * #
     *
     *	# . #
     *	. . # -- path through North/South. Key = 2
     *	# . #
     *
     *	# # #
     *  . . . -- path through East/West. Key = 1
     *	# . #
     * 
     *	# . #
     *	. . . -- path through all. Key = 0
     *	# . #
     *
     *Cells are given initial values based on the assignment.
     *Walls are represented by #.
     */

     //randomly assigns to a parallel array key values for generation.
     private void generateKeys()
     {
     	/*for(int i = 0; i < 9; i++)
     	{     		    
     		keys[i] = (int)(Math.random()*4);   			
     	}*/
     	
     	//for testing
     	keys[0] = 3;
     	keys[1] = 1;
     	keys[2] = 2;
     	keys[3] = 3;
     	keys[4] = 0;
     	keys[5] = 2;
     	keys[6] = 0;
     	keys[7] = 0;
     	keys[8] = 0;
     }
     
     /////////initial generation////////////    
     private void generateCells()
     {
     	
     	for(int i = 0; i < 9; i++)
     	{
     		switch(keys[i])
     		{
     			case 0:
     				cells[i] = Zero();
     				break;
     				
     			case 1:
     				cells[i] = One();
     				break;
     				
     			case 2:
     				cells[i] = Two();
     				break;
     				
     			default:
     				cells[i] = Three();
     				break;
     			
     		}
     		
     	}
	
     } 
     	       
     public Cell[][] Zero()
     {   	
     	Cell[][] Carr = new Cell[size][size];
     	//halfway point, round-up for odd sizes
     	double half = Math.ceil(size/2.0)-1;
     	    	
     	
     	for(int i = 0; i < size; i++)
     	{
     		for(int k = 0; k < size; k++)
     		{
     			if(i == half || k == half)
     			{
     				Carr[i][k] = new Cell(new Path());
     			}
     			else Carr[i][k] = new Cell(new Wall());     			
     		}
     	}
     	     	
     	return Carr;
     }
     
     public Cell[][] One()
     {
     	Cell[][] Carr = new Cell[size][size];
     	//halfway point, round-up for odd sizes
     	double half = Math.ceil(size/2.0)-1;
     	
     	for(int i = 0; i < size; i++)
     	{
     		for(int k = 0; k < size; k++)
     		{
     			if(i >= half)
     			{
     				if(i == half || k == half)
     					Carr[i][k] = new Cell(new Path());
     				else 
     					Carr[i][k] = new Cell(new Wall()); 
     			}
     			else Carr[i][k] = new Cell(new Wall());     			
     		}
     	}
     	     	
     	return Carr;
     }
     
     public Cell[][] Two()
     {
     	Cell[][] Carr = new Cell[size][size];
     	//halfway point, round-up for odd sizes
     	double half = Math.ceil(size/2.0)-1;
     	
     	for(int i = 0; i < size; i++)
     	{
     		for(int k = 0; k < size; k++)
     		{
     			if(k <= half)
     			{
     				if(i == half || k == half)
     					Carr[i][k] = new Cell(new Path());
     				else 
     					Carr[i][k] = new Cell(new Wall()); 
     			}
     			else Carr[i][k] = new Cell(new Wall());     			
     		}
     	}
     	     	
     	return Carr;
     }
     
     public Cell[][] Three()
     {
     	Cell[][] Carr = new Cell[size][size];
     	//halfway point, round-up for odd sizes
     	double half = Math.ceil(size/2.0)-1;
     	
     	for(int i = 0; i < size; i++)
     	{
     		for(int k = 0; k < size; k++)
     		{
     			if(k >= half)
     			{
     				if(i == half || k == half)
     					Carr[i][k] = new Cell(new Path());
     				else 
     					Carr[i][k] = new Cell(new Wall()); 
     			}
     			else Carr[i][k] = new Cell(new Wall());     			
     		}
     	}
     	     	
     	return Carr;
     }
     ///////////////////////////////////////
     
     
     //generate the caves (very rough)
     public void Generate()
     {
     	//iterate in 2x2 blocks
     	/*{.,.,.,.} {.,.,.,#} {.,.,#,.} {.,.,#,#} {.,#,.,.} {.,#,.,#} {.,#,#,.} {.,#,#,#} {#,.,.,.} {#,.,.,#} {#,.,#,.} {#,.,#,#} {#,#,.,.} {#,#,.,#} {#,#,#,.} {#,#,#,#}*/
     	//read as
     	//0 1
     	//2 3
     	
     	//dimensions of automata pattern
     	final int AUTOMATA_SIZE = 2;
     	
     	int index = 0;
     	char[] pattern = new char[4];
     	
     	//column indexes
     	int jValue = 1;
     	int mValue = 1;
     	
     	//iterate over the grid
     	for(int i = 0; i < cells.length; i++)
     	{
     		//iterate down the rows of the grid
     		for(int k = 1; k < cells[i].length-2; k++)
     		{  		
     			//iterate along the columns of the grid. jVal and mVal manage actual column index.
     			for(int p = 1; p < cells[i][k].length; p++)
     			{
     			
     				
     				//the two cells on top (0, 1)
     				for(int j = 0; j < AUTOMATA_SIZE; j++)
     				{    					   					 					
     					if(jValue >= cells[i][k].length-1)
     						break;
	
     					pattern[index] = cells[i][k][jValue].getObject().getDisplay();
     					jValue++;
     					index++;   
     				}
     				
     				//the two cells below (2, 3)
     				for(int m = 0; m < AUTOMATA_SIZE; m++)
     				{	
     					if(mValue >= cells[i][k].length-1)
     						break;
     						
     					pattern[index] = cells[i][k+1][mValue].getObject().getDisplay();
     					mValue++;
     					index++;	
     				}
     				
     				index = 0;
     				
     				
     			   /*Now we have the pattern, and must compare it to the others.
     				*Once the pattern is matched, alternative patterns are suggested.
     			 	*/
     			 	PatternChange(pattern, (jValue-AUTOMATA_SIZE), (mValue-AUTOMATA_SIZE), i, k);
     			}
     			
     			//reset values
     			jValue = 1;
     			mValue = 1;
     		
     		}
     	}	
     }
     
    ///////////////////////////////////////
    private void PatternChange(char[] pattern, int jVal, int mVal, int i, int k)
     {

     	int patternMatch = 0;						 
 		for(int r = 0; r < patternList.length; r++)
 		{
 			if(compArr(pattern, patternList[r]))
 			{
 				patternMatch = r;
 				break;
 			}
 		}	
 
 		switch(patternMatch)
 		{
 			case 6:
 					
			case 3:
			{
				//switch to pattern 1, 2, 3, 4, 5, 11, 12, 13, 14
				int[] options = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
				int changeCase = (int)(Math.random()*options.length);
				//System.out.println(changeCase);
				changeArr(patternList[options[changeCase]], jVal, mVal, i, k);

				break;	
			}
			
		
 				
 			case 7:
 				
 			case 8:
 			{
 				//1 ,2 ,4 ,8
 				int[] options = {1,2,3,9,10,11,12,13,14,15};
				int changeCase = (int)(Math.random()*options.length);
				//System.out.println(changeCase);
				changeArr(patternList[options[changeCase]], jVal, mVal, i, k);

				break;	
 			}
 			
 			case 4:
 			{
 				//0,1,2,4,8
 				int[] options = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
				int changeCase = (int)(Math.random()*options.length);
				//System.out.println(changeCase);
				changeArr(patternList[options[changeCase]], jVal, mVal, i, k);

				break;	
 			}

 			case 5:
 			{
 				//1,2,4,6,7,8,9
 				int[] options = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
				int changeCase = (int)(Math.random()*options.length);
				//System.out.println(changeCase);
				changeArr(patternList[options[changeCase]], jVal, mVal, i, k);

				break;	
 			}
 			
 			case 9:
 				
 			case 10:
 			
 				
 			case 12:
 			{
 				//1,2,8,9,12,13,14
 				int[] options = {1,2,3,4,5,6,7,8,9,10,11,12,13,14}; 
				
				int changeCase = (int)(Math.random()*options.length);			
				changeArr(patternList[options[changeCase]], jVal, mVal, i, k);

				break;
 			}
 					
 			case 11:
 				
 			case 13:
 			{
 				//8,9,10,11,12,14
				int[] options = {8,9,14,15}; 
				
				int changeCase = (int)(Math.random()*options.length);
				//System.out.println(changeCase);
				changeArr(patternList[options[changeCase]], jVal, mVal, i, k);

				break;	
 			}
 				
 			case 14:
 				
 			case 15:
 			{
 				//11,13,14,15
 				int[] options = {11,13,14,15}; 
				
				int changeCase = (int)(Math.random()*options.length);
				//System.out.println(changeCase);
				changeArr(patternList[options[changeCase]], jVal, mVal, i, k);

				break;	
 			}

 			default:
 				break;
 					
 		}
 	
 	
     }
     
   	private boolean compArr(char[] pattern1, char[] pattern2)
   	{
   		for(int i = 0; i < pattern1.length; i++)
   		{
   			if(pattern1[i] != pattern2[i])
   				return false;
   		}
   		return true;
   	}
    
    private void changeArr(char[] pattern, int jVal, int mVal, int i, int k)
    {
    	for(int x = 0; x < pattern.length/2; x++)
		{
					
			if(pattern[x] == '#')
			{
				cells[i][k][jVal].removeObject();
				cells[i][k][jVal].addObject(new Wall());	
			}
			else
			{
				cells[i][k][jVal].removeObject();
				cells[i][k][jVal].addObject(new Path());
			}
			jVal++;
					
					
			if(pattern[x+2] == '#')
			{
						
				cells[i][k+1][mVal].removeObject();
				cells[i][k+1][mVal].addObject(new Wall());	
			}
			else
			{
				cells[i][k+1][mVal].removeObject();
				cells[i][k+1][mVal].addObject(new Path());
			}
			mVal++;
					
		}
    }
    //////////////////////////////////////
    
    
    //clean the caves
    private void Clean()
    {
    	
    	//if all four are 1, cell becomes that value. 3 it remains. 2 = path. 1 = path.
    	// # = 1, . = 0
    	int top, bot, left, right;
    	
    	
    	for(int i = 0; i < cells.length; i++)
    	{
    		for(int k = 1; k < cells[i].length-1; k++)
    		{
    			for(int j = 1; j < cells[i][k].length-1; j++)
    			{
    				if(cells[i][k-1][j].getObject().getDisplay() == '#')
    					top = 1;
    				else top = 0;
    				
    				if(cells[i][k+1][j].getObject().getDisplay() == '#')
    					bot = 1;
    				else bot = 0;
    				
    				if(cells[i][k][j-1].getObject().getDisplay() == '#')
    					left = 1;
    				else left = 0;
    				
    				if(cells[i][k][j+1].getObject().getDisplay() == '#')
    					right = 1;
    				else right = 0;

	
    				if(top+bot+left+right == 4)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Wall());
    				}  				
    				/*else if(top+bot+left+right == 2)
    				{   				
    						cells[i][j][k].removeObject();
    						cells[i][j][k].addObject(new Path());   								
    				}*/
    				else if(top+bot+left+right == 0)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    							
    			}
    		}
    	}
    	
    }
    
    private void killTwoWallPairs()
    {
    				
    	int top, bot, left, right;
    	
    	//length/5 because, in general, the individual pieces of wall that are left in open areas are not longer than 1/3rd the array.
    	//for(int L = 0; L < (cells.length/3); L++)
    	//{
    		
    	
    	for(int i = 0; i < cells.length; i++)
    	{
    		for(int k = 1; k < cells[i].length-1; k++)
    		{
    			for(int j = 1; j < cells[i][k].length-1; j++)
    			{
    				
    				if(cells[i][k-1][j].getObject().getDisplay() == '#')
    					top = 1;
    				else top = 0;
    				
    				if(cells[i][k+1][j].getObject().getDisplay() == '#')
    					bot = 1;
    				else bot = 0;
    				
    				if(cells[i][k][j-1].getObject().getDisplay() == '#')
    					left = 1;
    				else left = 0;
    				
    				if(cells[i][k][j+1].getObject().getDisplay() == '#')
    					right = 1;
    				else right = 0;
    				
    				
    				
    				//kill off the 'two walls next to eachother in a completely open area' thing
    				int mine = 0;
    				if(cells[i][k][j].getObject().getDisplay() == '#')
    					mine = 1;
    				else mine = 0;
    				
    				if(top + mine == 2 && bot + left + right == 0)
    				{
    					cells[i][k-1][j].removeObject();
    					cells[i][k-1][j].addObject(new Path());
    					
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				else if( bot + mine == 2 && top + right + left == 0)
    				{
    					cells[i][k+1][j].removeObject();
    					cells[i][k+1][j].addObject(new Path());
    					
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				else if(right + mine == 2 && top + bot + left == 0)
    				{
    					cells[i][k][j+1].removeObject();
    					cells[i][k][j+1].addObject(new Path());
    					
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				else if(left + mine == 2 && top + bot + right == 0)
    				{
    					cells[i][k][j-1].removeObject();
    					cells[i][k][j-1].addObject(new Path());
    					
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    			}
    		}
    	}
    	
    	//}
    }
    
    
    
    //obliterates the caves if not first cleaned. changes certain patterns to attemp to ensure connectivity.
    private void finalClean()
    {
    	//if all four are 1, cell becomes that value. 3 it remains. 2 = path. 1 = path.
    	// # = 1, . = 0
    	int top, bot, left, right;
    	
    	
    	for(int i = 0; i < cells.length; i++)
    	{
    		for(int k = 1; k < cells[i].length-1; k++)
    		{
    			for(int j = 1; j < cells[i][k].length-1; j++)
    			{
    				if(cells[i][k-1][j].getObject().getDisplay() == '#')
    					top = 1;
    				else top = 0;
    				
    				if(cells[i][k+1][j].getObject().getDisplay() == '#')
    					bot = 1;
    				else bot = 0;
    				
    				if(cells[i][k][j-1].getObject().getDisplay() == '#')
    					left = 1;
    				else left = 0;
    				
    				if(cells[i][k][j+1].getObject().getDisplay() == '#')
    					right = 1;
    				else right = 0;
    				
    				
    			
	
    				if(top+bot+left+right == 4)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Wall());
    				}
    				else if(top + bot == 2 && left + right == 0)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				else if(top + left == 2 && bot + right == 0)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				else if(top + right == 2 && bot+ left == 0)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				else if(top + bot == 0)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				if(top+bot+left+right == 1)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				if(top+bot+left+right == 0)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				
    				
    				
    			
    			}
    		}
    	}
    }
    //final clean tends to leave individual walls behind, last clean removes these, and calls CloseUnreachable.
    private void absoluteLastClean()
    {
    	/*Final clean tends to leave behind the individual walls.
    	 *(it creates them occasionally)
    	 *For the final time, we clean those here.
    	 */
    	 int top, bot, left, right;
    	for(int i = 0; i < cells.length; i++)
    	{
    		for(int k = 1; k < cells[i].length-1; k++)
    		{
    			for(int j = 1; j < cells[i][k].length-1; j++)
    			{
    				if(cells[i][k-1][j].getObject().getDisplay() == '#')
    					top = 1;
    				else top = 0;
    				
    				if(cells[i][k+1][j].getObject().getDisplay() == '#')
    					bot = 1;
    				else bot = 0;
    				
    				if(cells[i][k][j-1].getObject().getDisplay() == '#')
    					left = 1;
    				else left = 0;
    				
    				if(cells[i][k][j+1].getObject().getDisplay() == '#')
    					right = 1;
    				else right = 0;
    				
    				
					if(top + left + right == 0)
					{
						cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
					}
					if(bot + right + left == 0)
					{
						cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
					}
					if(top + bot + right == 0)
					{
						cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
					}
					if(left + top + bot == 0)
					{
						cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
					}
					
    				if(top+bot+left+right == 0)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Path());
    				}
    				
    				
    				
    				 
    			}
    		}
    	}
    	
    	//fix the edge of the screen
    	for(int i = 0; i < cells.length; i++)
    	{
    		for(int k = 0; k < cells[i].length; k++)
    		{
    			for(int j = 0; j < cells[i][k].length; j++)
    			{
    				if(k == 0 || k == cells[i].length-1)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Wall());
    				}
    				
    				if(j == 0 || j == cells[i].length-1)
    				{
    					cells[i][k][j].removeObject();
    					cells[i][k][j].addObject(new Wall());
    				}
    			}
    		}
    	}
    	
    	
    	
    	
    	//fix original pathways. edge of the screen needs to be reachable in order to move to next tile.   	
    		double half = Math.ceil(size/2.0)-1;
    	for(int i = 0; i < 9; i++)
     	{
     		switch(keys[i])
     		{
     			case 0:
     			{
     				for(int k = 0; k < size; k++)
     				{
     					for(int j = 0; j < size; j++)
     					{
     						if(k == half || j == half)
     						{
     							cells[i][k][j].removeObject();
     							cells[i][k][j].addObject(new Path());
     						}   								
     					}
     				}
     				break;
     			}
     				
     				
     				
     			case 1:
     			{
     				for(int k = 0; k < size; k++)
     				{
     					for(int j = 0; j < size; j++)
     					{
     						if(k >= half)
     						{
     							if(k == half || j == half)
     							{
     								cells[i][k][j].removeObject();
     								cells[i][k][j].addObject(new Path());
     							}		 
     						} 								
     					}
     				}
     				
     				break;
     			}
     				
     				
     				
     			case 2:
     			{
     				for(int k = 0; k < size; k++)
     				{
     					for(int j = 0; j < size; j++)
     					{
     						if(j <= half)
     						{
     							if(k == half || j == half)
     							{
     								cells[i][k][j].removeObject();
     								cells[i][k][j].addObject(new Path());
     							}	
     						}				
     					}
     				}
     				
     				break;	
     			}
     			
     				
     				
     			default:
     			{
     				for(int k = 0; k < size; k++)
     				{
     					for(int j = 0; j < size; j++)
     					{
     						if(j >= half)
     						{
     							if(k == half || j == half)
     							{
     								cells[i][k][j].removeObject();
     								cells[i][k][j].addObject(new Path());
     							}	
     						}				
     					}
     				}
     				
     				break;	
     			}

     		}
     		
     	}
     	
     	
     	/*Finally, we need to complete a breadth first pathfinding
     	 *to every reachable tile. Parallel 2D array of integers.
     	 *Intial value = -1
     	 *Starting point is an open edge (chosen based on key value for tile)
     	 *Branch outwards if the location is reachable, ie. not a wall
     	 *23
     	 *123
     	 *0123  you know the deal
     	 *123
     	 *23
     	 *
     	 *at the end of the branching, tiles that remain -1 become walls.
     	 *This should close off the unreachable areas of the map, and finish the clean.
     	 */

 		 //insert frank li's search here.
 		 CloseUnreachable();
    }
    //closes off unreachable areas via Frank Li's breadth first pathfinding
    private void CloseUnreachable()
    {
    	//cleans the entire tile. starting with tile 0.
    	//all keys are open in the bottom/middle.
    	//since we cant use the very bottom, use the one just above it. second to last row start point.
    	int sr = size-2;
    	int sc = size/2;
    	
    	//parallel int array
    	int[][] Iarr = new int[size][size];
    	  
    	//val to set surroundings to (if cells[][][] is not a wall.)
    	//1 for speed testing
    	for(int i = 0; i < cells.length; i++)
    	{  		   	
    		//initial values of -1
    		for(int r = 0; r < size; r++)  	
    			for(int c = 0; c < size; c++)	
    				Iarr[r][c] = -1;
    		  		
    		int nextVal = 0;
    		boolean changeMade = true;
    		//initialize the start point.
    		Iarr[sr][sc] = 0;
    		
    		while(changeMade)
    		{ 			
    			//needed to break out
    			changeMade = false;
    			for(int r = 1; r < size-1; r++)
    			{
    				for(int c = 1; c < size-1; c++)
    				{ 					
    					if(Iarr[r][c] == nextVal)
    					{
    						//cell below
    						if(cells[i][r+1][c].getObject().getDisplay() != '#' && Iarr[r+1][c] == -1)
    						{
    							Iarr[r+1][c] = nextVal+1;
    							changeMade = true;
    						}
    						//cell above
    						if(cells[i][r-1][c].getObject().getDisplay() != '#'  && Iarr[r-1][c] == -1)
    						{
    							Iarr[r-1][c] = nextVal+1;
    							changeMade = true;
    						}
    						//cell right
    						if(cells[i][r][c+1].getObject().getDisplay() != '#'  && Iarr[r][c+1] == -1)
    						{
    							Iarr[r][c+1] = nextVal+1;
    							changeMade = true;
    						}
    						//cell left
    						if(cells[i][r][c-1].getObject().getDisplay() != '#'  && Iarr[r][c-1] == -1)
    						{
    							Iarr[r][c-1] = nextVal+1;
    							changeMade = true;
    						}		
    					}	
    				}
    			}
    			
    				
    			nextVal++;
    		}

    		for(int r = 0; r < size; r++)
    		{
    			for(int c = 0; c < size; c++)
    			{
    				if(Iarr[r][c] == -1)
    				{
    					cells[i][r][c].removeObject();
    					cells[i][r][c].addObject(new Wall());
    				}
    			}
    			
    		}   		
    	}
	
    }
    
    public boolean spawn(RObject toSpawn,int curTile, int x, int y)
    {
    	System.out.println("spawning: " + toSpawn.getDisplay());
    	if(cells[curTile][y][x].addObject(toSpawn))
    	{
    		return true;
    	}
    	else System.out.println("no");
    	return false;
    }
  
  
	
	public Cell[][] getCells(int index)
	{
		return cells[index];
	}
	
	public int getSize()
	{
		return size;
	}
	
	
	public void callTicks(int whichTile)
	{
		for(int i = 0; i < size; i++)
		{
			for(int k = 0; k < size; k++)
			{
				if(cells[whichTile][i][k].getObject().CanTick() && !(cells[whichTile][i][k].getObject().DidTick()))
				{
					//System.out.println("here2");
					cells[whichTile][i][k].getObject().Tick();
				}
			}
		}
		resetTickStates(whichTile);
	}
	
	public void resetTickStates(int whichTile)
	{
		for(int i = 0; i < size; i++)
		{
			for(int k = 0; k < size; k++)
			{
				if(cells[whichTile][i][k].getObject().CanTick())
				{					
					cells[whichTile][i][k].getObject().resetTickState();
				}
			}
		}
	}
	
}