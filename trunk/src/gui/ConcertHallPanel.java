package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ConcertHallPanel extends JPanel implements KeyListener
{
	/**
	 * Width of the JPanel
	 */
	public int WINDOW_WIDTH = 800;
	/**
	 * Height of JPanel
	 */
	public int WINDOW_HEIGHT = 600;
	Rectangle bgrect;
	List<JButtonGroup> sectionGroups;
	int lastLeftSectionGroup;
	int lastMidSectionGroup;
	int currentSectionSelected;
	int lastRightSectionGroup;
	Boolean inSubSectionMode;
	
	public  ConcertHallPanel()
	{
		bgrect = new Rectangle(0,0,WINDOW_WIDTH,WINDOW_WIDTH);
		setLayout(null);
		
		JButton b = new JButton("");
		b.setLocation(0, 0);
		b.setSize(100, 100);
		
		b.setBackground(Color.white);
		b.setOpaque(true);
		b.setBorderPainted(false);
		add(b);
		
		setUpSectionGroups();
		addKeyListener(this);
		inSubSectionMode = false;
	}
	
	public void setUpSectionGroups()
	{
		sectionGroups = new ArrayList<JButtonGroup>();
		sectionGroups.add( new JButtonGroup(this, 2, State.NOT_HOVERING, 2));
		sectionGroups.add( new JButtonGroup(this, 1, State.NOT_HOVERING, 3));
		lastLeftSectionGroup = 1;
		
		sectionGroups.add(  new JButtonGroup(this, 3, State.HOVERING, 1));
		sectionGroups.add( new JButtonGroup(this, 3, State.NOT_HOVERING, 2));
		sectionGroups.add(  new JButtonGroup(this, 3, State.NOT_HOVERING, 3));
		lastMidSectionGroup = 4;
		
		sectionGroups.add(  new JButtonGroup(this, 2, State.NOT_HOVERING, 2));
		sectionGroups.add(  new JButtonGroup(this, 1, State.NOT_HOVERING, 3));
		lastRightSectionGroup = 6;
		
		currentSectionSelected = 1;
		
		int i = 0;
		for ( i = 0; i < sectionGroups.size(); i++)
		{
			if (i <= lastLeftSectionGroup)
			{
				int height = 50;
				if (i == lastLeftSectionGroup)
					height = 275;
				sectionGroups.get(i).setLocation(100, height);
				
			}
			else if (i <= lastMidSectionGroup)
			{
				int height = 250;
				if (i == lastLeftSectionGroup + 2)
					height = 375;
				if (i == lastMidSectionGroup)
					height = 500;
				sectionGroups.get(i).setLocation(250, height);
			}
			else
			{
				int height = 50;
				if (i == lastRightSectionGroup)
					height = 275;
				if (i == sectionGroups.size() - 1)
					sectionGroups.get(i).setLocation(600, height);
				else
					sectionGroups.get(i).setLocation(600, height);
			}
			add(sectionGroups.get(i));
		}
		
		
	}
	/*public void actionPerformed( ActionEvent ae ) {  //called every time the timer fires, updates and redraws the game
		    
		repaint();
	}*/

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		System.out.println("key input detected");
		int keypressed = event.getKeyCode();
		switch(keypressed)
		{
			case KeyEvent.VK_UP:
				switch(currentSectionSelected)
				{
					case 1:
						currentSectionSelected = 0;
						break;
					case 3:
						currentSectionSelected = 2;
						break;
					case 4:
						currentSectionSelected = 3;
						break;
					case 6:
						currentSectionSelected = 5;
						break;
				}
				updateButtonGroupDisplays();
				break;
			case KeyEvent.VK_DOWN:
				switch(currentSectionSelected)
				{
					case 0:
						currentSectionSelected = 1;
						break;
					case 2:
						currentSectionSelected = 3;
						break;
					case 3:
						currentSectionSelected = 4;
						break;
					case 5:
						currentSectionSelected = 6;
						break;
				}
				updateButtonGroupDisplays();
				break;
			case KeyEvent.VK_LEFT:
				if (currentSectionSelected > lastMidSectionGroup)   // if in the right side
				{
					currentSectionSelected = lastMidSectionGroup;  //go to the middle
				}
				else if (currentSectionSelected > lastLeftSectionGroup) //else if in the middle
				{
					currentSectionSelected = lastLeftSectionGroup; //go to the left
				}
				updateButtonGroupDisplays();
				break;
			case KeyEvent.VK_RIGHT:
				if (currentSectionSelected <= lastLeftSectionGroup)  //if in the left side
				{
					currentSectionSelected = lastMidSectionGroup;  //go to the middle
				}
				else if (currentSectionSelected <= lastMidSectionGroup)  //else if in the middle
				{
					currentSectionSelected = lastRightSectionGroup; //go to the right
				}
				updateButtonGroupDisplays();
				break;
			case KeyEvent.VK_SPACE:
				if (inSubSectionMode)  
				{
					
				}
				else 
				{
					
				}
				updateButtonGroupDisplays();
				break;
		}
		
	}

	private void updateButtonGroupDisplays()
	{
		for (int i = 0; i < sectionGroups.size(); i++)
		{
			sectionGroups.get(i).setGroupsIcon(State.NOT_HOVERING);
		}
		sectionGroups.get(currentSectionSelected).setGroupsIcon(State.HOVERING);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
