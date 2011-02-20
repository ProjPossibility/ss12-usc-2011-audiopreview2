package gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.SeatSection;

import util.midi.MidiControl;

/**
 * The main panel that has all the buttons.  It manages arrow key movement and mouse clicks.
 * Manages what sound to play and when.  Passes the button currently selected to the midi when the 
 * spacebar is pressed.
 * @author Zach
 *
 */
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
	/**
	 * controller for how the song is changed
	 */
	MidiControl midicontrol1 = new MidiControl(1);
	MidiControl midicontrol2 = new MidiControl(2);
	
	/**
	 * the number of rows
	 */
	int NUM_ROWS = 4;
	/**
	 * the number of cols
	 */
	int NUM_COLS = 5;
	/**
	 * 2d array of seatsections
	 */
	GUISeatSection[][] seatsections;
	
	/**
	 * current row of seats cursor is on
	 */
	int currentSelectedRow = 1;
	
	/**
	 * current col of seats cursor is on
	 */
	int currentSelectedCol = 2;
	
	/**
	 * the sound clip that tells what seat you have selected
	 */
	Clip clip;
	/**
	 * the linelistener that listens for the clip's end
	 */
	ALineListener thelinelistener;
	/**
	 * the stage of the clip
	 * 0 = not playing
	 * 1 = section location
	 * 2= "press 1 for preview 1"
	 * 3 = "press 2 for preview 2"
	 */
	int clipStage = 0;
	/**
	 * the image of the band playing at the top
	 */
	JLabel orchestraImage;
	
	/**
	 * make the rows of buttons
	 */
	public  ConcertHallPanel()
	{
		setLayout(null);
		
		setUpSectionGroups();
		addKeyListener(this);
		LoadConcertImage();
		
	}
	
	/**
	 * the image of the band playing at the top
	 */
	private void LoadConcertImage()
	{
		orchestraImage = new JLabel();
		//File sourceimage = new File("..\\images\\"+sectionName +".PNG");
		Image orchestra = Toolkit.getDefaultToolkit().getImage("..\\images\\orchestraSmall.png");
		ImageIcon tempIcon = new ImageIcon(orchestra);
		orchestraImage.setIcon(tempIcon);
		//orchestraImage.setSize(orchestra.getHeight(new ImageObserver(), orchestra.getHeight(new ImageOvserver()));
		orchestraImage.setSize(tempIcon.getIconWidth(), tempIcon.getIconHeight());
		PlaceConcertImage();
		
		//return toReturn;
	}
	
	/**
	 * center the image of the band at the top and add it to the screen
	 */
	private void PlaceConcertImage()
	{
		//orchestraImage.setSize(width, height)
		orchestraImage.setLocation(213, 0);
		add(orchestraImage);
	}
	
	/**
	 * set up the 2d array of seats
	 */
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
				seatsections[i][j].setLocation(88 + 125 * j, 100 + 125 * i);
				seatsections[i][j].setSize( 125 ,  125 );
				add(seatsections[i][j]);
			}
		updateButtonGroupDisplays();
		playSoundDescription();
	}

	

	

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keypressed = event.getKeyCode();
		//midicontrol1.stop();
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
			case KeyEvent.VK_1:
				//seatsections[currentSelectedRow][currentSelectedCol]
				//play soundbyte
				stopSoundClip();
				midicontrol2.stop();
				midicontrol1.stop();
				midicontrol1.play();
				System.out.println("Playing the first song");
				break;
			case KeyEvent.VK_2:
				stopSoundClip();
				midicontrol1.stop();
				midicontrol2.stop();
				midicontrol2.play();
				System.out.println("Playing the second song");
				break;
		}
	}
	
	/**
	 * set currently selected button to SELECTED and rip the channels, 
	 * then set all other buttons to NOT_SELECTED
	 */
	private void updateButtonGroupDisplays()
	{
		for (int i = 0; i < NUM_ROWS; i++)
			for (int j = 0; j < NUM_COLS; j++)
			{
				if (seatsections[i][j] != null)
				seatsections[i][j].setGSS_Color(State.NOT_SELECTED);
			}
		seatsections[currentSelectedRow][currentSelectedCol].setGSS_Color(State.SELECTED);
		
		SeatSection seat = seatsections[currentSelectedRow][currentSelectedCol].getSeatSection();

		midicontrol1.stop();
		midicontrol1.ripChannels(seat);
		midicontrol2.stop();
		midicontrol2.ripChannels(seat);
	}
	
	/**
	 * 	call stopSoundClip()
	 *  call playSoundClip()
	 */
	private void playSoundDescription()
	{
		//play description name
		//seatsections[currentSelectedRow][currentSelectedCol]
		stopSoundClip();
		playSoundClip();
	}
	
	/**
	 * play the soundclip of seat location selected
	 * or the instructions of what button to press.
	 * Increment clipStage
	 * Listen for end of sound clip to know when to play next sound clip.
	 */
	private void playSoundClip()
	{
		try 
		{
			clipStage++;
			File soundFile;
			switch(clipStage)
			{
			case 1:
				soundFile = seatsections[currentSelectedRow][currentSelectedCol].getSeatSection().getVoiceFile();
				break;
			case 2:
				soundFile = new File("..\\sounds\\PressOne.wav");
				break;
			case 3:
				soundFile = new File("..\\sounds\\PressTwo.wav");
				break;
			default:
				return;
			}
			 AudioInputStream sound;
			 sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			
			// Clip clip;
			
				clip = (Clip) AudioSystem.getLine(info);
				clip.close();
				clip.open(sound);
				
				
			    // due to bug in Java Sound, explicitly exit the VM when
			    // the sound has stopped.
				thelinelistener = new ALineListener();
			    clip.addLineListener(thelinelistener);
	
			    // play the sound clip
			    clip.start();
			    
		} catch (UnsupportedAudioFileException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * stop the sound and remove the listener it has
	 */
	private void stopSoundClip()
	{
		if (clip != null)
			if (clip.isRunning())
			{
				clip.removeLineListener(thelinelistener);
				clip.stop();
				clip.close();
				
			}
		clipStage = 0;
	}
	
	/**
	 * handle a button clicked, called from GUISeat
	 * @param guiseat  the seat that was clicked on
	 */
	public void buttonClicked(GUISeatSection guiseat)
	{
		for (int i = 0; i < NUM_ROWS; i++)
		{
			for (int j = 0; j < NUM_COLS; j++)
			{
				if (seatsections[i][j] != null)
				if (seatsections[i][j] == guiseat)
				{
					//guiseat.setGSS_Color(State.SELECTED);
					currentSelectedRow = i;
					currentSelectedCol = j;
				}
			}
		}
		updateButtonGroupDisplays();
		
		playSoundDescription();
		setFocusable(true);
		requestFocus();
	}
	
	/**
	 * 
	 * @author Zach Boehm
	 * internal class that implements LineListener
	 * used for listener when sound is done and calls playSoundClip that will handle the next listener
	 */
	class ALineListener implements LineListener
	{
	      public void update(LineEvent event) 
	      {
	        if (event.getType() == LineEvent.Type.STOP) 
	        {
	        	playSoundClip();
	        	event.getLine().close();
	          //System.exit(0);
	        }
	      }
	}
	//List<JButtonGroup> sectionGroups;
	//int lastLeftSectionGroup;
	//int lastMidSectionGroup;
	//int currentSectionSelected;
	//int lastRightSectionGroup;
	//Boolean inSubSectionMode;
	
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
