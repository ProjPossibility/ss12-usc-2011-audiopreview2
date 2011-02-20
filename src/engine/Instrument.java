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
	public Instrument(String name, int channelNum, Point3d location)
	{
		iName = name;
		channelNumber = channelNum;
		iLocation = location;
	}
	
	public abstract double getVolume();
	
	public String getName()
	{
		return iName;
	}
	
	public int getChannelNumber()
	{
		return channelNumber;
	}
	
	public Point3d getLocation()
	{
		return iLocation;
	}
}
