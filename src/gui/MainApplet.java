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

package gui;

import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main applet.  Has a ConcertHallPanel that it adds to the screen and just runs.
 * @author Zach
 *
 */
public class MainApplet extends JApplet {
	ConcertHallPanel c;
	 public void init() {
		   /* Container cp = getContentPane();
		    cp.setLayout(new FlowLayout());
		    cp.add(b1);
		    cp.add(b2);*/
		 
		 System.out.println("init called");
		 c = new ConcertHallPanel();
		 setSize(c.WINDOW_WIDTH, c.WINDOW_HEIGHT);
			
			c.setSize( c.WINDOW_WIDTH,c.WINDOW_HEIGHT);	
			c.setVisible(true);
			c.setFocusable(true);
			c.requestFocus();
			/*JFrame frame = new JFrame();
			frame.add(c);
			frame.setVisible(true);*/
			this.add(c);
			c.setFocusable(true);
			c.requestFocus();
			c.setBackground(Color.DARK_GRAY);
			 this.setName("my name");
			 

			
		  }
}
