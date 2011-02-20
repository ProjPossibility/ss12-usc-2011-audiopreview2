package engine;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class DirectedInstrument extends Instrument
{
	public static final int DISTANCE_DROP_FACTOR = 1;
	
	protected Vector3d iDirection;
	protected double iForwardPortion;
	
	public DirectedInstrument(String name, int channelNum, int channelVol, 
		Point3d location, Vector3d direction, double forwardPortion)
	{
		super(name, channelNum, channelVol, location);
		iDirection = direction;
		iForwardPortion = forwardPortion;
	}

	public double getAdjustedVolume(SeatSection s)
	{
		return channelVolume / 
			Math.pow(iLocation.distance(s.getLocation()), DISTANCE_DROP_FACTOR);
	}

}
