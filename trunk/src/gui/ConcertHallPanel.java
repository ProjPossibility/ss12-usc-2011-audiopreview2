package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.midi.MidiControl;

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
	
	MidiControl midicontrol = new MidiControl();
	Rectangle bgrect;
	
	int NUM_ROWS = 4;
	int NUM_COLS = 5;
	GUISeatSection[][] seatsections;
	
	int lastRightSectionGroup;
	int currentSelectedRow = 1;
	int currentSelectedCol = 2;
	Boolean inSubSectionMode;
	
	public  ConcertHallPanel()
	{
		bgrect = new Rectangle(0,0,WINDOW_WIDTH,WINDOW_WIDTH);
		setLayout(null);
		
		setUpSectionGroups();
		addKeyListener(this);
		inSubSectionMode = false;
	}
	
	private void setUpSectionGroups()
	{
		seatsections = new GUISeatSection[4][5];
		for (int i = 0; i < NUM_ROWS; i++)
			for (int j = 0; j < NUM_COLS; j++)
			{
				seatsections[i][j] = null;
				if (i == 3)
				{
					if (j == 0 || j == 4)
						continue;
				}
				if (i == 0)
				{
					if (j == 1 || j == 2 || j == 3)
						continue;
				}
				seatsections[i][j] = new GUISeatSection(this, i,j);
				//seatsections[i][j].setLabel(i + " , " + j);
				seatsections[i][j].setLocation(50 + 125 * j, 50 + 125 * i);
				seatsections[i][j].setSize( 125 ,  125 );
				add(seatsections[i][j]);
			}
		updateButtonGroupDisplays();
	}

	

	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		int keypressed = event.getKeyCode();
		//midicontrol.stop();
		switch(keypressed)
		{
			case KeyEvent.VK_UP:
				if (currentSelectedRow == 0)
					return;
				currentSelectedRow -= 1;
				if (seatsections[currentSelectedRow][currentSelectedCol] == null)
					currentSelectedRow += 1;
				updateButtonGroupDisplays();
				playSoundDescription();
				break;
			case KeyEvent.VK_DOWN:
				if (currentSelectedRow == 3)
					return;
				currentSelectedRow += 1;
				if (seatsections[currentSelectedRow][currentSelectedCol] == null)
					currentSelectedRow -= 1;
				updateButtonGroupDisplays();
				playSoundDescription();
				break;
			case KeyEvent.VK_LEFT:
				if (currentSelectedCol == 0)
					return;
				if (currentSelectedCol == 4 && currentSelectedRow == 0)
					currentSelectedRow = 1;
				currentSelectedCol--;
				while (seatsections[currentSelectedRow][currentSelectedCol] == null)
				{
					currentSelectedRow--;
				}
				
				updateButtonGroupDisplays();
				playSoundDescription();
				break;
			case KeyEvent.VK_RIGHT:
				if (currentSelectedCol == 4)
					return;
				if (currentSelectedCol == 3 && currentSelectedRow == 3)
					currentSelectedRow = 2;
				currentSelectedCol++;
				while (seatsections[currentSelectedRow][currentSelectedCol] == null)
				{
					currentSelectedRow++;
				}
				
				updateButtonGroupDisplays();
				playSoundDescription();
				break;
			case KeyEvent.VK_SPACE:
				//seatsections[currentSelectedRow][currentSelectedCol]
				//play soundbyte
				midicontrol.stop();
				midicontrol.play();
				break;
		}
	}
	
	private void updateButtonGroupDisplays()
	{
		for (int i = 0; i < NUM_ROWS; i++)
			for (int j = 0; j < NUM_COLS; j++)
			{
				if (seatsections[i][j] != null)
				seatsections[i][j].setGSS_Color(State.NOT_SELECTED);
			}
		seatsections[currentSelectedRow][currentSelectedCol].setGSS_Color(State.SELECTED);
		midicontrol.stop();
		midicontrol.ripChannels(seatsections[currentSelectedRow][currentSelectedCol].getSeatSection());
	}
	
	private void playSoundDescription()
	{
		//play description name
		//seatsections[currentSelectedRow][currentSelectedCol]
		 
		    
			try {
				 File soundFile = seatsections[currentSelectedRow][currentSelectedCol].getSeatSection().getVoiceFile();
				System.out.println("\n\n" + soundFile.toString());
				 AudioInputStream sound;
				 sound = AudioSystem.getAudioInputStream(soundFile);
				DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
				
				 Clip clip;
					clip = (Clip) AudioSystem.getLine(info);
				
					clip.open(sound);

				    // due to bug in Java Sound, explicitly exit the VM when
				    // the sound has stopped.
				    clip.addLineListener(new LineListener() {
				      public void update(LineEvent event) {
				        if (event.getType() == LineEvent.Type.STOP) {
				          event.getLine().close();
				          //System.exit(0);
				        }
				      }
				    });

				    // play the sound clip
				    clip.start();
				    
			} catch (UnsupportedAudioFileException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    
		  

			
		    
		
	}
	
	public void buttonClicked(GUISeatSection guiseat)
	{
		for (int i = 0; i < NUM_ROWS; i++)
		{
			for (int j = 0; j < NUM_COLS; j++)
			{
				if (seatsections[i][j] != null)
				seatsections[i][j].setGSS_Color(State.NOT_SELECTED);
			}
		}
		for (int i = 0; i < NUM_ROWS; i++)
		{
			for (int j = 0; j < NUM_COLS; j++)
			{
				if (seatsections[i][j] != null)
				if (seatsections[i][j] == guiseat)
				{
					guiseat.setGSS_Color(State.SELECTED);
					currentSelectedRow = i;
					currentSelectedCol = j;
				}
			}
		}
		
		setFocusable(true);
		requestFocus();
		System.out.println("a button was clicked");
	}
	
	//List<JButtonGroup> sectionGroups;
	//int lastLeftSectionGroup;
	//int lastMidSectionGroup;
	//int currentSectionSelected;
	
	/*public void setUpSectionGroups()
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
					height = 325;
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
					height = 325;
				if (i == sectionGroups.size() - 1)
					sectionGroups.get(i).setLocation(600, height);
				else
					sectionGroups.get(i).setLocation(600, height);
			}
			add(sectionGroups.get(i));
		}
		
		
	}*/
	
	/*@Override
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
				if (currentSectionSelected > sSectionGroup)   // if in the right side
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
		
	}*/
	
	/*private void updateButtonGroupDisplays()
	{
		for (int i = 0; i < sectionGroups.size(); i++)
		{
			sectionGroups.get(i).setGroupsIcon(State.NOT_HOVERING);
		}
		sectionGroups.get(currentSectionSelected).setGroupsIcon(State.HOVERING);
	}*/
}
