/**
 * @(#)Map.java
 *
 *
 * @author 
 * @version 1.00 2020/2/23
 */
import java.awt.*;
import java.awt.event.*;

public class Map 
{

   
   public static void main(String [] args)
   {
   
   	 Tile x = new Tile(50);
   	 
   	 System.out.println();
   	 
   	 Player p = new Player(x);
   	 MapDisplay y = new MapDisplay(x,p);
   	 
   	 
 	 for(int i = 0; i< 50; i++)
 	 {
 	 	for(int k = 0; k < 50; k++)
 	 	{
 	 		System.out.print(x.getCells(0)[i][k].getObject().getDisplay());
 	 	}
 	 	System.out.println();
 	 }
 	 	
   	 
   	 
   }
    
}