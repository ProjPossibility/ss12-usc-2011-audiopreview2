package engine;

import javax.vecmath.Point3d;

/**
 * The Instrument class stores a single instrument within an orchestra or band.
 * 
 * @author Jonathan Sun
 */
public abstract class Instrument
{
	private String iName;
	private int channelNumber;
	private int channelVolume;
	private Point3d iLocation;
	
	/**
	 * Default constructor
	 */
	public Instrument()
	{
		
	}
	
	/**
	 * Constructs a new Instrument
	 */
	public Instrument(String name, int channelNum, int channelVol, Point3d location)
	{
		iName = name;
		channelNumber = channelNum;
		channelVolume = channelVol;
		iLocation = location;
	}
	
	/**
	 * @return the adjusted volume for a seat section
	 */
	public abstract double getAdjustedVolume(SeatSection s);
	
	
	public String getName()
	{
		return iName;
	}
	
	public int getChannelNumber()
	{
		return channelNumber;
	}
	
	public int getChannelVolume()
	{
		return channelVolume;
	}
	
	public Point3d getLocation()
	{
		return iLocation;
	}
}
