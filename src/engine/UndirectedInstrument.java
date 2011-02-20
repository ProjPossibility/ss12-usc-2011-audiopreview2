package engine;

import javax.vecmath.Point3d;
/**
 * Object for instruments that produce undirected sound e.g. strings and woodwinds
 * 
 * @author Brehon Humphrey
 *
 */
public class UndirectedInstrument extends Instrument {


	
	/**
	 * Default constructor
	 */
	public UndirectedInstrument()
	{
		super();
	}
	
	/**
	 * Constructs a new Instrument
	 */
	public UndirectedInstrument(String name, int channelNum,int channelVol, Point3d location)
	{
		super(name,channelNum,channelVol,location);
	}
	


	@Override
	public double getAdjustedVolume(SeatSection s) {
		
		return channelVolume/Math.pow(iLocation.distance(s.getLocation()),1.2);
	}

}
