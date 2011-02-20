package util.midi;

import java.io.File;
import java.util.Scanner;

import javax.sound.midi.*;

import engine.*;
import engine.Instrument;

import javax.vecmath.Point3d;

public class MidiControl {
	
	Instrument[] instruments = new Instrument[16];
	int[] instrumentVolumes = new int[16];
	double[] scaledVolumes = new double[16];
	Point3d[] instrumentPoints = new Point3d[16];
	double volumeAverage = 0, scaleAverage;
	File songFile, textFile;
	Sequence sequence;
	Sequencer sequencer;
	Receiver receiver;
	Synthesizer synthesizer;
	int channels;
	SeatSection seat;
	
	
	/**
	 * Create a new array of Instruments by pulling out the individual channels in the sequence
	 * 
	 * @param sequence the base .MID file sent from the GUI
	 */
	public void ripChannels(SeatSection s)
	{
		seat = s;
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
        				//build point
        				double x = Double.parseDouble(input.nextLine());
        				double y = Double.parseDouble(input.nextLine());
        				double z = Double.parseDouble(input.nextLine());
        				Point3d p = new Point3d(x,y,z);
        				instrumentPoints[channels] = p;
        				
        				volume = Integer.parseInt(input.nextLine());
        				instrumentVolumes[channels] = volume;
        			}
        			catch(Exception e)
        			{
        				System.out.println("MIDI text information not readable");
        			}
        			if(directed)
        			{
        				DirectedInstrument i = new DirectedInstrument(name, channels, volume, instrumentPoints[channels], null, (double)0.5);
        				instruments[channels] = i;
        			}
        			else
        			{
        				UndirectedInstrument i = new UndirectedInstrument(name, channels, volume, instrumentPoints[channels]);
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
        	
        	changeVolumes();
            
            
		
	}
	
	public void changeVolumes()
	{
		for(int i = 0; i <= channels; i++)
		{
			volumeAverage += instrumentVolumes[i];
		}
		volumeAverage = volumeAverage / (channels+1);
		for(int i = 0; i <= channels; i++)
		{
			scaledVolumes[i] = instruments[i].getAdjustedVolume(seat);
		}
		for(int i = 0; i <= channels; i++)
		{
			scaleAverage += scaledVolumes[i];
		}
		scaleAverage = scaleAverage / (channels+1);
		
		double ratio = volumeAverage / scaleAverage;
		
		for(int i = 0; i <= channels; i++)
		{
			instrumentVolumes[i] = (int)(scaledVolumes[i]*ratio);
		}
		
	}
	
	public void play()
	{
		sequencer.start();
		
		ShortMessage volumeMessage = new ShortMessage();
        for(int i = 0; i <= channels; i++)
        {
        	try
        	{
        		volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, instrumentVolumes[i]);
        		MidiSystem.getReceiver().send(volumeMessage, -1);
        	}
        	catch(Exception e)
        	{
        		System.out.println("Error changing the volumes");
        	}
        }
	}
	
	public void stop()
	{
		sequencer.stop();
	}

}
