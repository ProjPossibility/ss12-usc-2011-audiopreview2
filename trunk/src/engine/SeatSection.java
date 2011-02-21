/*
 * (c) Copyright 2011, Jonathan Sun, Ryan Tenorio, Brehon Humphrey, Zach Boehm,
 * Andy Ybarra, Kyle Mylonakis
 * 
 * This file is part of AudioPreview.
 *
 * AudioPreview is free software: you can redistribute it and/or modify
 * it under the terms of the New BSD License.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED 
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS 
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF 
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * You should have received a copy of the new BSD License along with AudioPreview.  
 * If not, see <http://www.opensource.org/licenses/bsd-license.php>.
 */

package engine;


import java.io.File;

import javax.vecmath.*;

/**
 * 
 * The SeatSection class is a simple structure containing the location,
 * name, and voice name of a seating section in a concert hall.
 * 
 * @author Brehon Humphrey
 *
 */
public class SeatSection {
	
	/** The location of the seat */
	Point3d loc;
	
	/** The name of the section */
	String name;
	
	/** A voice recording of the name */
	File voiceFile;
	
	/**
	 * Constructor makes a new SeatSection
	 * 
	 * @param loc the location of the seat in space
	 * @param name the name of the section
	 * @param voiceFile the recording of the name
	 */
	public SeatSection(Point3d loc, String name, File voiceFile){
		this.loc = loc;
		this.name = name;
		this.voiceFile = voiceFile;
	}
	
	/**
	 * returns the location of the SeatSection
	 * 
	 * @return the location
	 */
	public Point3d getLocation(){
		return loc;
	}
	
	/**
	 * Returns the name of the section
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the voiceFile to be played
	 * 
	 * @return the voice file
	 */
	public File getVoiceFile()
	{
		return voiceFile;
	}
	
	/**
	 * returns the name and location of the seat
	 * @return a string representation of this object
	 */
	public String toString()
	{
		return ("Seat "+name+"  Coordinates: ("+loc.x+","+loc.y+","+loc.z+")");
	}

}
