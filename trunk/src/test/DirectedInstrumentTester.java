package test;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import engine.DirectedInstrument;
import engine.SeatSection;

public class DirectedInstrumentTester
{
	public static void main(String[] args)
	{
		System.out.println("DIRECTED INSTRUMENT TESTER");
		
		Point3d seatLoc = new Point3d(0, 0, 0);
		Point3d instLoc = new Point3d(1, 1, 1);
		Vector3d instDir = new Vector3d(5, 5, 5);
		
		SeatSection seat = new SeatSection(seatLoc, "test", null);
		
		DirectedInstrument inst = new DirectedInstrument("test", 0, 10, instLoc, instDir, .5);
		
		System.out.println("NEW VOLUME: " + inst.getAdjustedVolume(seat));
	}
}
