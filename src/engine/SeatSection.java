package engine;


import java.io.File;

import javax.sound.sampled.*;
import javax.vecmath.*;
/**
 * 
 * Object that the name and coordinates of a section of seat in the concert hall
 * 
 * @author Brehon Humphrey
 *
 */
public class SeatSection {
	
	Point3d loc;
	String name;
	File voiceFile;
	
	/**
	 * Constructor
	 * 
	 * @param loc
	 * @param name
	 * @param voiceFile
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

}
