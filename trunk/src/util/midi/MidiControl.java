package util.midi;

import java.io.File;
import java.util.Scanner;

import javax.sound.midi.*;

import engine.*;
import engine.Instrument;

import javax.vecmath.Point3d;

public class MidiControl {
	
	Instrument[] instruments = new Instrument[16];
	File songFile, textFile;
	Sequence sequence;
	Sequencer sequencer;
	Receiver receiver;
	Synthesizer synthesizer;
	Point3d pTemp;
	int channels;
	
	
	/**
	 * Create a new array of Instruments by pulling out the individual channels in the sequence
	 * 
	 * @param sequence the base .MID file sent from the GUI
	 */
	public void ripChannels()
	{
		
		try
		{
			songFile = new File("verdi_requiem.mid");
			textFile = new File("verdi_requiem.txt");
		}
		catch(Exception e)
		{
			
		}
		
		
		try
		{
			sequence = MidiSystem.getSequence(songFile);
            sequencer = MidiSystem.getSequencer(false);
            receiver = MidiSystem.getReceiver();
            // open the sequencer and wire up the receiver
            // and transmitter

            sequencer.open();
            sequencer.getTransmitter().setReceiver(receiver);
            
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            synthesizer.loadAllInstruments(MidiSystem.getSoundbank(new File("soundbank-deluxe.gm")));
            sequencer.setSequence(sequence);
            
            
		}
		catch(Exception e)
		{
		}            
            
        	try
        	{
        		channels = 0;
        		Scanner input = new Scanner(textFile);
        		while(input.hasNext())
        		{
        			String name = null;	
        			boolean directed = false;
        			int volume = 0;
        			try
        			{
        				name = input.nextLine();
        				if(input.nextLine().equals("DIRECTED"))
        				{
        					directed = true;
        				}
        				else
        				{
        					directed = false;
        				}
        				volume = Integer.parseInt(input.nextLine());
        			}
        			catch(Exception e)
        			{
        				System.out.println("MIDI text information not readable");
        			}
        			if(directed)
        			{
        				DirectedInstrument i = new DirectedInstrument(name, channels, volume, pTemp, null, (double)0.5);
        				instruments[channels] = i;
        			}
        			else
        			{
        				UndirectedInstrument i = new UndirectedInstrument(name, channels, volume, pTemp);
        				instruments[channels] = i;
        			}
        			
        			channels++;
        			if(channels > 15)
        			{
        				break;
        			}
        		}
        	}
        	catch(Exception e)
        	{
        		
        	}
            
            
		
	}

}
