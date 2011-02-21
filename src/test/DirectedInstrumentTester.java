/*
 * (c) Copyright 2011, Jonathan Sun, Ryan Tenorio, Brehon Humphrey, Zach Boehm,
 * Andy YBarra, Kyle Mylonakis
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

package test;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import engine.DirectedInstrument;
import engine.SeatSection;

/**
 * Test class for the DirectedInstrument class
 * 
 * @author Jonathan Sun
 */
public class DirectedInstrumentTester
{
	public static void main(String[] args)
	{
		System.out.println("DIRECTED INSTRUMENT TESTER");
		
		Point3d seatLoc = new Point3d(1, 1, 1);
		Point3d instLoc = new Point3d(0, 0, 0);
		Vector3d instDir = new Vector3d(-1, -1, -1);
		
		SeatSection seat = new SeatSection(seatLoc, "test", null);
		
		DirectedInstrument inst = new DirectedInstrument("test", 0, 10, instLoc, instDir, .5);
		
		System.out.println("NEW VOLUME: " + inst.getAdjustedVolume(seat));
	}
}
