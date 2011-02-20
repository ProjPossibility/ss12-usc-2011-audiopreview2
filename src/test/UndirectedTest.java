package test;

import javax.vecmath.Point3d;

import engine.SeatSection;
import engine.UndirectedInstrument;

public class UndirectedTest {

	public static void main(String [] args)
	{
		SeatSection seat;
		UndirectedInstrument instrument;
		
		seat = new SeatSection(new Point3d(0,0,0),"1A",null);
		instrument = new UndirectedInstrument("Test",1,50,new Point3d(2,2,2));
		System.out.println(instrument.getAdjustedVolume(seat));
	}
}
