package engine;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.jonsun.debug.DebugUtil;

public class DirectedInstrument extends Instrument
{
	public static final int DISTANCE_DROP_FACTOR = 1;
	
	protected Vector3d iDirection;
	protected double iForwardPortion;
	protected double iBackwardPortion;
	
	public DirectedInstrument(String name, int channelNum, int channelVol, 
		Point3d location, Vector3d direction, double forwardPortion)
	{
		super(name, channelNum, channelVol, location);
		iDirection = direction;
		iForwardPortion = forwardPortion;
		iBackwardPortion = 1 - forwardPortion;
	}

	public double getAdjustedVolume(SeatSection seat)
	{
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
			System.out.println("SEAT " + seat + " IS IN FRONT OF INSTRUMENT " + this);
			
			return iForwardPortion * channelVolume / 
				Math.pow(iLocation.distance(seat.getLocation()), DISTANCE_DROP_FACTOR);
		}
		//the seat is in back of the instrument
		else
		{
			System.out.println("SEAT " + seat + " IS IN BACK OF INSTRUMENT " + this);
			
			return iBackwardPortion * channelVolume /
				Math.pow(iLocation.distance(seat.getLocation()), DISTANCE_DROP_FACTOR);	
		}
			
	}

}
