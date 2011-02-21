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

import javax.vecmath.Point3d;

/**
 * The Instrument class stores a single instrument within an orchestra or band.
 * Instruments are transient objects and exist only for calculations for
 * the distortion with seats in mind.
 * 
 * @author Jonathan Sun
 */
public abstract class Instrument
{
	/** The Instrument name */
	protected String iName;
	
	/** The channel number of the instrument */
	protected int channelNumber;
	
	/** The unadjusted volume of the channel of the instrument */
	protected int channelVolume;
	
	/** The relative location of the instrument */
	protected Point3d iLocation;
	
	/**
	 * Default constructor
	 */
	public Instrument()
	{
		
	}
	
	/**
	 * Constructs a new Instrument
	 * @param name the name of the instrument
	 * @param channelNum the temporary MIDI channel index of this instrument
	 * @param channelVol the temporary MIDI balanced channel volume
	 * @param location the relative location in space of the instrument
	 */
	public Instrument(String name, int channelNum, int channelVol, Point3d location)
	{
		iName = name;
		channelNumber = channelNum;
		channelVolume = channelVol;
		iLocation = location;
	}
	
	/**
	 * @return the relative adjusted volume for a seat section
	 * @param s the SeatSection to calculate the relative volume for
	 */
	public abstract double getAdjustedVolume(SeatSection s);
	
	/**
	 * Returns the name of the instrument
	 * @return the name of the instrument
	 */
	public String getName()
	{
		return iName;
	}
	
	/**
	 * Returns the MIDI channel number of the instrument
	 * @return the MIDI channel number of the instrument
	 */
	public int getChannelNumber()
	{
		return channelNumber;
	}
	
	/**
	 * Returns the MIDI balanced channel volume
	 * @return the MIDI balanced channel volume
	 */
	public int getChannelVolume()
	{
		return channelVolume;
	}
	
	/**
	 * Returns the location of the instrument
	 * @return the location of the instrument
	 */
	public Point3d getLocation()
	{
		return iLocation;
	}
	
	/**
	 * Returns a string representation of the instrument
	 * @return a string representation of the instrument
	 */
	public String toString()
	{
		return "instrument " + iName + iLocation.x + "," + iLocation.y + "," + iLocation.z;
	}
}
