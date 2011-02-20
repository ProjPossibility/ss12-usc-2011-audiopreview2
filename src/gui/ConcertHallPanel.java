package gui;

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
	
	public  ConcertHallPanel()
	{
		bgrect = new Rectangle(0,0,WINDOW_WIDTH,WINDOW_WIDTH);
		setLayout(null);
		JButton b = new JButton("click here");
		b.setLocation(0, 0);
		b.setSize(WINDOW_WIDTH, WINDOW_HEIGHT / 2);
		add(b);
	}
	
	public void setUpSectionGroups()
	{
		sectionGroups = new ArrayList<JButtonGroup>();
		sectionGroups.add( new JButtonGroup(this, 2, State.NOT_HOVERING, 2));
		sectionGroups.add( new JButtonGroup(this, 2, State.NOT_HOVERING, 3));
		lastLeftSectionGroup = 1;
		
		sectionGroups.add(  new JButtonGroup(this, 3, State.HOVERING, 1));
		sectionGroups.add( new JButtonGroup(this, 3, State.NOT_HOVERING, 2));
		sectionGroups.add(  new JButtonGroup(this, 3, State.NOT_HOVERING, 3));
		lastMidSectionGroup = 4;
		
		sectionGroups.add(  new JButtonGroup(this, 2, State.NOT_HOVERING, 2));
		sectionGroups.add(  new JButtonGroup(this, 1, State.NOT_HOVERING, 3));
		lastRightSectionGroup = 6;
		
		currentSectionSelected = 1;
		
		for (int i = 0; i < sectionGroups.size(); i++)
		{
			if (i <= lastLeftSectionGroup)
			{
				sectionGroups.get(i).setLocation(0, 0);
			}
		}
		
		
	}
	/*public void actionPerformed( ActionEvent ae ) {  //called every time the timer fires, updates and redraws the game
		    
		repaint();
	}*/

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		int keypressed = event.getKeyCode();
		switch(keypressed)
		{
			case KeyEvent.VK_UP:
				updateButtonGroupDisplays();
				break;
			case KeyEvent.VK_DOWN:
				updateButtonGroupDisplays();
				break;
			case KeyEvent.VK_LEFT:
				updateButtonGroupDisplays();
				break;
			case KeyEvent.VK_RIGHT:
				
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
