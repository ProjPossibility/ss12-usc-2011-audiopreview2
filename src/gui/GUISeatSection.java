package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.vecmath.Point3d;
import engine.SeatSection;

enum State {NOT_SELECTED,SELECTED};

/**
 * GUISeatSection.java
 * Purpose: Takes the row and column values and map them to the correct 
 * SeatSection objects by finding the corresponding name and vector components
 * @author Andy Ybarra
 *
 */
public class GUISeatSection extends JButton implements ActionListener{
	
	/**	Own SeatSection variable to be instantiated later*/
	SeatSection mySeatSection;
	
	/**	Own Point3d location used for holding the 3 component vector values*/
	Point3d location;
	
	/** File voiceFile to be loaded and passed to the SeatSection */
	File voiceFile;
	
	/** Current enum state of the button */
	State GSS_state;
	
	/** Image icon for the seat */
	ImageIcon seatIcon;
	
	/** Parent panel to report button clicks to*/
	ConcertHallPanel parentPanel;
	
	/** Image to put on the ImageIcon for selected states*/
	Image redSeat;
	
	/** Image to put on the ImageIcon for not selected states*/
	Image blueSeat;
	
	/**
	 * 
	 * @param Parent panel used so we can report to button clicks.
	 * @param row int used for mapping the location to the actual GUI.
	 * @param col int used for mapping the location to the actual GUI.
	 */
	public GUISeatSection(ConcertHallPanel p,int row, int col){
		parentPanel = p;
		String name = lookUpNameAndLocation(row, col);
		mySeatSection = new SeatSection(location, name, voiceFile);
		GSS_state = State.NOT_SELECTED;
		addActionListener(this);
		seatIcon = new ImageIcon();
		loadImages();
		setGSS_Color(GSS_state);
	}
	
	/**
	 * Loads the two images using the grabImage method to get the images
	 * 
	 * @param none
	 */
	public void loadImages(){
		redSeat = grabImage("..\\images\\seatButton.png");
		blueSeat = grabImage("..\\images\\seatButtonSelected.png");
	}
	
	/**
	 * Gets the Image from the local directory and returns the image
	 * 
	 * @param String filename in directory.
	 */
	public Image grabImage(String filename){
		File sourceimage = new File(filename);
    	Image toReturn;
        toReturn = Toolkit.getDefaultToolkit().getImage(filename);
		return toReturn;
	}
	
	/**
	 * 
	 * @return the current 
	 */
	public State getState(){
		return GSS_state;
	}
	
	/**
	 * Sets the State based on the State passed in.
	 * @param selectedOrNot
	 */
	public void setState(State selectedOrNot){
		GSS_state = selectedOrNot;
	}
	
	/**
	 * 
	 * @return the SeatSection object to the caller
	 */
	public SeatSection getSeatSection(){
		return mySeatSection;
	}
	
	/**
	 * Sets the seatIcon ImageIcon based on the State enum passed in.
	 * @param selectedOrNot
	 */
	public void setGSS_Color(State selectedOrNot){
		switch(selectedOrNot){
		/** This will display the blue seats  */
		case NOT_SELECTED:
			this.setBackground(Color.BLUE);
			seatIcon.setImage(blueSeat);
			this.setIcon(seatIcon);
			//will have red chains image icon.
			//this.setBackground(Color.BLUE);
			//this.setForeground(Color.BLUE);
			//this.setBorderPainted(false);
			//this.setOpaque(true);
			break;
		/** This will set the ImageIcon to it's corresponding ConcertHall Image*/
		case SELECTED:
			this.setBackground(Color.RED);
			seatIcon.setImage(redSeat);
			this.setIcon(seatIcon);
			//will have the blue chains image icon.
			///this.setBackground(Color.RED);
			//this.setForeground(Color.RED);
			///this.setBorderPainted(false);
			//this.setOpaque(true);
			break;
		}
	}
	
	/**
	 * This will return the corresponding 
	 * @param row
	 * @param col
	 * @return
	 */
	public String lookUpNameAndLocation(int row, int col){
		String name = " ";
		location = new Point3d();
		switch(row){
		case 0:
			switch(col){
			case 0:
				name = "East Balcony Behind";
				location.set(-1,-2,.5);
				break;
			case 4:
				name = "West Balcony Behind";
				location.set(-1,2,.5);
				break;
			}
			break;
		case 1:
			switch(col){
			case 0:
				name = "East Balcony Front";
				location.set(1.5,-2,.5);
				break;
			case 1:
				name = "Front Orchestra East";
				location.set(1,-1,0);
				break;
			case 2:
				name = "Front Orchestra Center";
				location.set(1,0,0);
				break;
			case 3: 
				name = "Front Orchestra West";
				location.set(1,1,0);
				break;
			case 4:
				name = "West Balcony Front";
				location.set(1.5,2,.5);
				break;
			}
			break;
		case 2: 
			switch(col){
			case 0:
				name = "Terrace East";
				location.set(4,-1,2);
				break;
			case 1:
				name = "Orchestra Level East";
				location.set(2,-1,.5);
				break;
			case 2:
				name = "Orchestra Level Center";
				location.set(2,0,.5);
				break;
			case 3: 
				name = "Orchestra Level West";
				location.set(2,1,.5);
				break;
			case 4: 
				name = "Terrace West";
				location.set(4,1,2);
				break;
			}
			break;
		case 3: 
			switch(col){
			case 1:
				name = "Mezzanine East";
				location.set(3,-1,2);
				break;
			case 2:
				name = "Mezzanine Center";
				location.set(3,0,2);
				break;
			case 3:
				name = "Mezzanine West";
				location.set(3,1,2);
				break;
			}
			break;
		}
		return name;
	}

	/**
	 * 
	 */
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("button clicked");
		parentPanel.buttonClicked(this);
	}
}
