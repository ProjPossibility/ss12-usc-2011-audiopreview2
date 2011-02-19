package util;

import javax.swing.JApplet;
import javax.swing.JFrame;

import gui.ConcertHallPanel;

public class ZachTest extends JApplet
{
	//zach's class
	ConcertHallPanel c;
	 public void init() {
		   /* Container cp = getContentPane();
		    cp.setLayout(new FlowLayout());
		    cp.add(b1);
		    cp.add(b2);*/
		 System.out.println("init called");
			c = new ConcertHallPanel();
			c.setSize( c.WINDOW_WIDTH,c.WINDOW_HEIGHT);	
			c.setVisible(true);
			/*JFrame frame = new JFrame();
			frame.add(c);
			frame.setVisible(true);*/
			this.add(c);
			
		  }
	/*public static void main(String [] args)
	{
		ZachTest zt = new ZachTest();
		
				//c.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				zt.init();
				zt.start();
	}*/
}
