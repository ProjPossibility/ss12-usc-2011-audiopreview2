package engine;


import java.io.File;

import javax.sound.sampled.*;
import javax.vecmath.*;
/**
 * 
 * The SeatSection class is a simple structure containing the location,
 * name, and voice name of a seating section in a concert hall.
 * 
 * @author Brehon Humphrey
 *
 */
public class SeatSection {
	
	/** The location of the seat */
	Point3d loc;
	
	/** The name of the section */
	String name;
	
	/** A voice recording of the name */
	File voiceFile;
	
	/**
	 * Constructor makes a new SeatSection
	 * 
	 * @param loc the location of the seat in space
	 * @param name the name of the section
	 * @param voiceFile the recording of the name
	 */
	public SeatSection(Point3d loc, String name, File voiceFile){
		this.loc = loc;
		this.name = name;
		this.voiceFile = voiceFile;
	}
	
	/**
	 * returns the location of the SeatSection
	 * 
	 * @return
	 */
	public Point3d getLocation(){
		return loc;
	}
	
	/**
	 * Returns the name of the section
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the voiceFile to be played
	 * 
	 * @return
	 */
	public File getVoiceFile()
	{
		return voiceFile;
	}
	
	/**
	 * returns the name and location of the seat
	 */
	public String toString()
	{
		return ("Seat "+name+"  Coordinates: ("+loc.x+","+loc.y+","+loc.z+")");
	}

}
