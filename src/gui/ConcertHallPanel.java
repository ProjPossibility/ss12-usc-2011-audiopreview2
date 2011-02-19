package gui;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConcertHallPanel extends JPanel 
{
	public int WINDOW_WIDTH = 800;
	public int WINDOW_HEIGHT = 600;
	Rectangle bgrect;
	public  ConcertHallPanel()
	{
		bgrect = new Rectangle(0,0,WINDOW_WIDTH,WINDOW_WIDTH);
		//setBounds(50,50, WINDOW_WIDTH, WINDOW_HEIGHT);

		setLayout(new GridLayout(1,1));
		setLayout(null);
		JButton b = new JButton("click here");
		//b.setLocation(20, 30);
		add(b);
		System.out.println("button added");
		b.setSize(WINDOW_WIDTH, WINDOW_HEIGHT / 2);
		
	}
	
	/*public void actionPerformed( ActionEvent ae ) {  //called every time the timer fires, updates and redraws the game
		    
		repaint();
	}*/
	
	/*public static void main(String [] args)
	{
		ConcertHallLayout c = new ConcertHallLayout();
		c.setSize( c.WINDOW_WIDTH,c.WINDOW_HEIGHT);	
		c.setVisible(true);
				c.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}*/

	/*public void paint (Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor( Color.red );
		g2.fill( bgrect );

	}*/
	
}
