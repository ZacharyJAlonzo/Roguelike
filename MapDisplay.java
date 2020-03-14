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

public class MapDisplay 
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
	
	//entire map
	private Tile map;
	int currentDisplay;

    public MapDisplay(Tile t)
    {
    	map = t;
    	currentDisplay = 0;
    	
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
   			HPField.setBounds(420,20,40,20);
   			HPField.setFont(new Font("Monospaced", Font.PLAIN, 15));
   			statWindow.add(HPField);
   		
   		
   		AV = new JLabel("AV:");
   		AV.setBounds(150,20,40,15);
   		AV.setFont(new Font("Monospaced", Font.PLAIN, 15));
   		statWindow.add(AV);
   			
   			AVField = new JTextField();
   			AVField.setBounds(180,20,40,20);
   			AVField.setFont(new Font("Monospaced", Font.PLAIN, 15));
   			statWindow.add(AVField);
   		
   		DV = new JLabel("DV:");
   		DV.setBounds(270,20,40,15);
   		DV.setFont(new Font("Monospaced", Font.PLAIN, 15));
   		statWindow.add(DV);
   			
   			DVField = new JTextField();
   			DVField.setBounds(300,20,40,20);
   			DVField.setFont(new Font("Monospaced", Font.PLAIN, 15));
   			statWindow.add(DVField);
   			
   			
   		LV = new JLabel("LV:");
   		LV.setBounds(30,20,40,15);
   		LV.setFont(new Font("Monospaced", Font.PLAIN, 15));
   		statWindow.add(LV);
   			
   			LVField = new JTextField();
   			LVField.setBounds(60,20,40,20);
   			LVField.setFont(new Font("Monospaced", Font.PLAIN, 15));
   			statWindow.add(LVField);
    	  	
    	 lookDisplay = new JTextArea();
    	 lookDisplay.setBounds(100,100,125,150);
    	 lookDisplay.setBorder(BorderFactory.createTitledBorder(statBorder, "LOOK", TitledBorder.CENTER, TitledBorder.TOP, new Font("monospaced", Font.BOLD, 13), Color.black));
    	 
    	 lookDisplay.setVisible(false);
    	 
    	 allWindows.add(lookDisplay);
    	 allWindows.setLayer(lookDisplay, 1);
		
		 logDisplay = new JTextArea();
		 logDisplay.setEditable(false);
		
		 logWindow = new JScrollPane(logDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 logWindow.setBorder(BorderFactory.createTitledBorder(statBorder, "LOG", TitledBorder.CENTER, TitledBorder.TOP, new Font("monospaced", Font.BOLD, 13), Color.black));
		 logWindow.setBounds(1175,20,350,500);
		 allWindows.add(logWindow);
		
    	printTile();
    	frame.repaint();   
    }
    
    
    public void printTile()
    {
    	Cell[][] arr = map.getCells(currentDisplay);
    	
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
    	    	
    }
    
    
}