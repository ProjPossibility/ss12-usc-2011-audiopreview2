package util;

import javax.vecmath.*;

public class SeatSection {
	
	Point3d loc;
	String name;
	
	public SeatSection(Point3d loc, String name){
		this.loc = loc;
		this.name = name;
	}
	
	public Point3d getLocation(){
		return loc;
	}
	
	public String getName()
	{
		return name;
	}

}
