/**
 * @(#)MapDisplay.java
 *
 *
 * @author 
 * @version 1.00 2020/2/27
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.*;

public class MapDisplay implements KeyListener
{
	
	private JLayeredPane allWindows;
	
	private JTextArea logDisplay;
	private JScrollPane logWindow;
	
	
	private JPanel lookWindow;	
	private JTextArea lookDisplay;
	
	private JPanel statWindow;
	private Border statBorder;
	private JLabel HP;
		private JTextField HPField;
	private JLabel AV;
		private JTextField AVField;
	private JLabel DV;
		private JTextField DVField;
	private JLabel LV;
		private JTextField LVField;
	
	private JTextArea display;
	private JFrame frame;
	
	private Player player;
	//entire map
	private Tile map;
	private int inputMode;
	
	private int lookX, lookY;
	private int lookDisplayWidth, lookDisplayHeight;
	private int curScreenX, curScreenY;
	
    public MapDisplay(Tile t, Player p)
    {
    	map = t;
    	player = p;
    	inputMode = 0;
    	
    	
    	
    	lookX = 0;
    	lookY = 0;
    	curScreenX = 0;
    	curScreenY = 0;
    	
    	frame = new JFrame("map");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.setResizable(true);
    	//
    	frame.setVisible(true);
    	frame.setLayout(null);

		allWindows = new JLayeredPane();
		allWindows.setBounds(0,0,1920,1080);
		allWindows.setLayout(null);
		frame.add(allWindows);
		frame.addKeyListener(this);

    	  	
    	display = new JTextArea();
    	display.setBounds(10,20,640,755);
    	display.setVisible(true);
		display.setFont(new Font("monospaced", Font.PLAIN, 10));
    	display.setLineWrap(false);
    	display.setEditable(false);
    	display.setBorder(BorderFactory.createLineBorder(Color.black));
    	allWindows.add(display);
    	
    	
 		statBorder = BorderFactory.createLineBorder(Color.black);		
    	statWindow = new JPanel();
    	statWindow.setBounds(660,10,500,50);
    	statWindow.setBorder(BorderFactory.createTitledBorder(statBorder, "STATS", TitledBorder.CENTER, TitledBorder.TOP, new Font("monospaced", Font.BOLD, 15), Color.black));
    	statWindow.setLayout(null);
    	allWindows.add(statWindow);
    	
   		HP = new JLabel("HP:");
   		HP.setBounds(390,20,40,15);
   		HP.setFont(new Font("Monospaced", Font.PLAIN, 15));
   		statWindow.add(HP);
   			
   			HPField = new JTextField();
   			HPField.setBounds(420,20,70,20);
   			HPField.setFont(new Font("Monospaced", Font.PLAIN, 15));
   			HPField.setEditable(false);
   			statWindow.add(HPField);
   		
   		
   		AV = new JLabel("AV:");
   		AV.setBounds(150,20,40,15);
   		AV.setFont(new Font("Monospaced", Font.PLAIN, 15));
   		statWindow.add(AV);
   			
   			AVField = new JTextField();
   			AVField.setBounds(180,20,70,20);
   			AVField.setFont(new Font("Monospaced", Font.PLAIN, 15));
   			AVField.setEditable(false);
   			statWindow.add(AVField);
   		
   		DV = new JLabel("DV:");
   		DV.setBounds(270,20,40,15);
   		DV.setFont(new Font("Monospaced", Font.PLAIN, 15));
   		statWindow.add(DV);
   			
   			DVField = new JTextField();
   			DVField.setBounds(300,20,70,20);
   			DVField.setFont(new Font("Monospaced", Font.PLAIN, 15));
   			DVField.setEditable(false);
   			statWindow.add(DVField);
   			
   			
   		LV = new JLabel("LV:");
   		LV.setBounds(30,20,40,15);
   		LV.setFont(new Font("Monospaced", Font.PLAIN, 15));
   		statWindow.add(LV);
   			
   			LVField = new JTextField();
   			LVField.setBounds(60,20,70,20);
   			LVField.setFont(new Font("Monospaced", Font.PLAIN, 15));
   			LVField.setEditable(false);
   			statWindow.add(LVField);
    	  	
    	  	
    	 lookDisplay = new JTextArea();
    	 
    	 lookDisplay.setBorder(BorderFactory.createTitledBorder(statBorder, "LOOK", TitledBorder.CENTER, TitledBorder.TOP, new Font("monospaced", Font.BOLD, 13), Color.black));
    	 lookDisplay.setFont(new Font("Monospaced",Font.PLAIN,12));	 
    	 lookDisplay.setVisible(false);   	 
    	 
    	 allWindows.add(lookDisplay);
    	 lookDisplayWidth = 200;
    	 lookDisplayHeight = 150;
    	 lookDisplay.setBounds(100,100,lookDisplayWidth,lookDisplayHeight);
    	 allWindows.setLayer(lookDisplay, 1);
		
		 logDisplay = new JTextArea();
		 logDisplay.setEditable(false);
		
		 logWindow = new JScrollPane(logDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 logWindow.setBorder(BorderFactory.createTitledBorder(statBorder, "LOG", TitledBorder.CENTER, TitledBorder.TOP, new Font("monospaced", Font.BOLD, 13), Color.black));
		 logWindow.setBounds(1175,10,350,500);
		 allWindows.add(logWindow);
		
    	printTile();
    	frame.repaint(); 
    		
		  
    }
    public void setPlayer(Player p)
    {
    	player = p;
    }
    
    public void keyPressed(KeyEvent e)
    {
    	
    	int key = e.getKeyCode();

    	if (key == KeyEvent.VK_LEFT)
    	{
        	switch(inputMode)
        	{
        		//player input mode
        		case 0:
        		{System.out.println("player move");
        			player.moveL();
        			map.callTicks(player.getCurrentTile());
        			printTile();  
        				break;  			
        		}
        		//look window input mode
        		case 1:
        		{
        			//a turn has not passed, dont do AI stuff
        			updateLookTarget(lookX-1, lookY);
        			break;
        		}
        		//inventory input mode, TBI
        		case 2:	
        		{
        			
        		}
        	}
    	}

    	if (key == KeyEvent.VK_RIGHT)
    	{
        	switch(inputMode)
        	{
        		//player input mode
        		case 0:
        		{System.out.println("player move");
        			player.moveR();
        			//do AI stuff, IE a turn has passed
        			map.callTicks(player.getCurrentTile());
        			printTile(); 
        				break;    			
        		}
        		//look window input mode
        		case 1:
        		{
        			//a turn has not passed, dont do AI stuff
        			updateLookTarget(lookX+1, lookY);
        			break;
        		}
        		//inventory input mode, TBI
        		case 2:	
        		{
        			
        		}
        	}
        	
    	}

    	if (key == KeyEvent.VK_UP)
    	{
        	switch(inputMode)
        	{
        		//player input mode
        		case 0:
        		{
        			System.out.println("player move");
        			player.moveU();
        			//do AI stuff, IE a turn has passed
        			//System.out.println("here");
        			map.callTicks(player.getCurrentTile());
        			printTile();
        			break;     			
        		}
        		//look window input mode
        		case 1:
        		{
        			//a turn has not passed, dont do AI stuff
        			updateLookTarget(lookX, lookY-1);
        			break;
        		}
        		//inventory input mode, TBI
        		case 2:	
        		{
        			
        		}
        	}
        	
        	
    	}

    	if (key == KeyEvent.VK_DOWN)
    	{
        switch(inputMode)
        	{
        		//player input mode
        		case 0:
        		{System.out.println("player move");
        			player.moveD();
        			//do AI stuff, IE a turn has passed
        			map.callTicks(player.getCurrentTile());
        			printTile();
        			break;     			
        		}
        		//look window input mode
        		case 1:
        		{
        			//a turn has not passed, dont do AI stuff
        			updateLookTarget(lookX, lookY+1);
        			break;
        		}
        		//inventory input mode, TBI
        		case 2:	
        		{
        			
        		}
        	}
    	}
    	
    	if(key == KeyEvent.VK_L)
    	{
    		if(lookDisplay.isVisible())
    		{
    			lookDisplay.setVisible(false);
    			inputMode = 0;
    			return;
    		}
    		else 
    		{
    			lookDisplay.setVisible(true);
  					updateLookTarget(player.getAX(), player.getAY());
    			inputMode = 1;
    			return;
    		}
    	}

    }
    
    
    public void keyReleased(KeyEvent e)
    {
    	
    }
    public void keyTyped(KeyEvent e)
    {
    	
    }
    
    public void updateLookTarget(int x, int y)
    {
    	Cell lookTarget = player.look(player.getAX(), player.getAY());
    	
    	if(x <= map.getCells(player.getCurrentTile()).length-1 && x >= 0)
    	lookX = x;
    	if(y <= map.getCells(player.getCurrentTile()).length-1 && y >= 0)
    	lookY = y;
    	
    	//default, dont change position if the look window would leave the map area
    	int newScreenX = curScreenX;
    	int newScreenY = curScreenY;
    	
    	
    	if(lookX >= map.getCells(player.getCurrentTile()).length/2)
    	{
    		//display on the left side of the object
    		newScreenX = (lookX*13)-190;
    		curScreenX = newScreenX;
    	}
    	else
    	{
    		//display on the right side of the object
    		newScreenX = (lookX*13)+15;
    		curScreenX = newScreenX;
    	}
    	
    	
    	if(lookY >= map.getCells(player.getCurrentTile()).length/2)
    	{
    		//display on the top side of the object
    		newScreenY = (lookY*15)-120;
    		curScreenY = newScreenY;
    	}
    	else 
    	{
    		//display on the bottom side of the object
    		newScreenY = (lookY*15)+30;
    		curScreenY = newScreenY;
    	}
    	
    	
    	lookDisplay.setBounds(newScreenX, newScreenY, lookDisplayWidth, lookDisplayHeight);
    	//update screen position
    	//display lookTarget information.
    	printLookTarget(lookX, lookY);
    }
    
    private void printLookTarget(int lookX, int lookY)
    {
    	Cell target = map.getCells(player.getCurrentTile())[lookY][lookX];
    	char obj = target.getObject().getDisplay();
    	String info = "";
    	
    	switch(obj)
    	{
    		case '#':
    		{
    			info = "It's a wall\n";
    			lookDisplay.setText(info);
    			break;
    		}
    		case ' ':
    		{
    			info = "It's the ground\n";
    			lookDisplay.setText(info);
    			break;
    		}
    		case '@':
    		{
    			info = "It's you\nHP:"+player.getCurrentHP()+"/"+player.getTotalHP();
    			lookDisplay.setText(info);
    			break;
    		}
    		case 'E':
    		{
    			MovingObject en = (MovingObject)target.getObject();
    			info = "It's an enemy\nHP:"+en.getCurrentHP()+"/"+en.getTotalHP();
    			lookDisplay.setText(info);
    			break;
    		}
    	}
    }

    
    public void printTile()
    {
    	display.setText("");
    	Cell[][] arr = map.getCells(player.getCurrentTile());
    	
    	String line = "";
    	
    	for(int i = 0; i < arr.length; i++)
    	{
    		line+="";
    		for(int k = 0; k < arr.length; k++)
    		{
    			
    			line+=arr[i][k].getObject().getDisplay();
    			line+=" ";
    		}
    		line+='\n';
    		display.append(line);
    		line = "";
    	}
    	
    	//update HP/AV/DV values
    	HPField.setText(player.getCurrentHP()+"/"+player.getTotalHP());
    	    	
    }
    
    
}