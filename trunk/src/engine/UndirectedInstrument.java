
package engine;

import javax.vecmath.Point3d;

/**
 * UndirectedInstruments are instruments that generally produce sound that spreads
 * evenly in all directions, such as strings and percussions.
 * 
 * @author Brehon Humphrey
 */
public class UndirectedInstrument extends Instrument
{
	/** The default adjustment distance factor */
	private static final double DISTANCE_DROP_FACTOR = 2.0;
	
	/**
	 * Constructs a new Instrument
	 * @param name the name of the instrument
	 * @param channelNum the MIDI channel of the instrument
	 * @param channelVol the balanced channel volume
	 * @param location the location of the instrument in space
	 */
	public UndirectedInstrument(String name, int channelNum, int channelVol, Point3d location)
	{
		super(name, channelNum, channelVol, location);
	}

	/**
	 * Returns the relative adjusted volume at a specified seat
	 * @param s the SeatSection to get the volume at
	 */
	public double getAdjustedVolume(SeatSection s)
	{
		return channelVolume / Math.pow(iLocation.distance(s.getLocation()), DISTANCE_DROP_FACTOR);

	}

}
