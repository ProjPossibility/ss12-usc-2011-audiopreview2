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

import engine.SeatSection;
import engine.UndirectedInstrument;

/**
 * Test class for the UndirectedInstrument class
 * 
 * @author Jonathan
 */
public class UndirectedInstrumentTester {

	public static void main(String [] args)
	{
		SeatSection seat;
		UndirectedInstrument instrument;
		
		seat = new SeatSection(new Point3d(0,0,0),"1A",null);
		instrument = new UndirectedInstrument("Test",1,50,new Point3d(2,2,2));
		System.out.println(instrument.getAdjustedVolume(seat));
	}
}
