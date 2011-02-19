package engine;

import javax.sound.midi.MidiChannel;
import javax.vecmath.Point3d;

/**
 * The Instrument class stores a single instrument within an orchestra or band.
 * 
 * @author Jonathan Sun
 */
public abstract class Instrument
{
	private String iName;
	private MidiChannel iChannel;
	private Point3d iLocation;
	
	/**
	 * Constructs a new Instrument
	 */
	public Instrument(String name, MidiChannel channel, Point3d location)
	{
		iName = name;
	}
	
	public String getName()
	{
		return iName;
	}
	
	public MidiChannel getChannel()
	{
		return iChannel;
	}
	
	public Point3d getLocation()
	{
		return iLocation;
	}
}
