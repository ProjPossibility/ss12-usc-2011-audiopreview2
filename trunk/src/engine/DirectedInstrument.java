package engine;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * The DirectedInstrument class represents instruments like trombones that
 * have a directed sound and thus do not spread evenly in all directions.
 * DirectedInstruments contain vectors representing their direction in space
 * as well as an adjustment factor for forward/backward sounds.
 * 
 * @author Jonathan Sun
 */
public class DirectedInstrument extends Instrument
{
	/** The default falling distance adjustment factor */
	public static final int DISTANCE_DROP_FACTOR = 1;
	
	/** The direction the instrument is pointed in */
	protected Vector3d iDirection;
	
	/** The forward percentage of the sound */
	protected double iForwardPortion;
	
	/** The backwards percentage of the sound */
	protected double iBackwardPortion;
	
	/** 
	 * Makes a new DirectedInstrument
	 * @param name the instrument name
	 * @param channelNum the MIDI channel number
	 * @param channelVol the balanced channel volume
	 * @param location the location of the instrument in space
	 * @param direction the direction the instrument is pointed in
	 * @param forwardPortion the forward percentage of the sound
	 */
	public DirectedInstrument(String name, int channelNum, int channelVol, 
		Point3d location, Vector3d direction, double forwardPortion)
	{
		super(name, channelNum, channelVol, location);
		iDirection = direction;
		iForwardPortion = forwardPortion;
		iBackwardPortion = 1 - forwardPortion;
	}

	/**
	 * Calculates the adjusted volume of this instrument at a given seat.
	 * @return the adjusted volume at a seat
	 * @param seat the seat to calculate the volume at
	 */
	public double getAdjustedVolume(SeatSection seat)
	{
		if(iDirection == null || iDirection.length() == 0)
		{
			return channelVolume/Math.pow(iLocation.distance(seat.getLocation()),1.2);
		}
		
		//check to see if the seat is in FRONT of the instrument
		Vector3d seatVector = new Vector3d(seat.getLocation().x - this.getLocation().x,
			seat.getLocation().y - this.getLocation().y, 
			seat.getLocation().z - this.getLocation().z);
		
		double temp = seatVector.dot(iDirection) / 
			(seatVector.length() * iDirection.length());
		
		if(temp > 1)
		{
			temp = 1;
		}
		else if(temp < -1)
		{
			temp = -1;
		}
		
		double theta = Math.acos(temp);
		
		System.out.println("THETA " + theta);
		
		//the seat is in front of the instrument
		if(theta <= Math.PI / 2)
		{
			//System.out.println("SEAT " + seat + " IS IN FRONT OF INSTRUMENT " + this);
			
			return iForwardPortion * channelVolume / 
				Math.pow(iLocation.distance(seat.getLocation()), DISTANCE_DROP_FACTOR);
		}
		//the seat is in back of the instrument
		else
		{
			//System.out.println("SEAT " + seat + " IS IN BACK OF INSTRUMENT " + this);
			
			return iBackwardPortion * channelVolume /
				Math.pow(iLocation.distance(seat.getLocation()), DISTANCE_DROP_FACTOR);	
		}
			
	}

}
