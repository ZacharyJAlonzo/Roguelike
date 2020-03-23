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
   	 Enemy e = new Enemy(x, 24,9);
   	 MapDisplay y = new MapDisplay(x,p);
   	 //e.findRouteToHostile(p);
   	 
 	
 	 	
   	 
    
   }
    
}