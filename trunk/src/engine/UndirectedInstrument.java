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
