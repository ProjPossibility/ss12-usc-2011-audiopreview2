package util.midi;

import java.io.File;
import java.util.Scanner;

import javax.sound.midi.*;

import engine.*;
import engine.Instrument;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * 
 * MidiControl.java
 * Purpose:Interface between the GUI and the engine classes to process/edit the midi file
 * 
 * @author Ryan
 *
 */

public class MidiControl {
	
	/** Array storing the instrument objects */
	Instrument[] instruments = new Instrument[16];
	
	/** Array storing the instrument channel volumes to be modified */
	int[] instrumentVolumes = new int[16];
	
	/** Intermediate array storing the scaled volume values */
	double[] scaledVolumes = new double[16];
	
	/** Array storying the Point3d for each instrument */
	Point3d[] instrumentPoints = new Point3d[16];
	
	/** Files that must be modified by the programmer to load the correct files */
	File songFile, textFile;
	
	/** Sequence object that loads the midi files */
	Sequence sequence;
	
	/** Sequencer object that plays the sequence through the default Sequencer */
	Sequencer sequencer;
	
	/** Receiver object to send messages to the MIDI file */
	Receiver receiver;
	
	/** Synthesizer object that produced sound with the default Synthesizer */
	Synthesizer synthesizer;
	
	/** Transmitter object that works as a pair with the Receiever object to send messages to the MIDI file */
	Transmitter transmitter;
	
	/** number of channels being used by the MIDI file */
	int channels;
	
	/** SeatSection object representing the location in the audience to emulate noise to */
	SeatSection seat;
	
	/** constant double value used for vector calculations */
	private static final double ROOTTWO = Math.sqrt(2);
	
	/** boolean keeping track of if GM should be loaded */
	boolean firstRun;
	
	/**
	 * Default constructor
	 */
	public MidiControl()
	{
		//ripChannels(seat);
		firstRun = true;
	}
	
	/**public static void main(String[] args)
	{
		MidiControl m = new MidiControl();
		m.play();
		
	}*/
	
	
	/**
	 * Create a new array of Instruments by pulling out the individual channels in the sequence
	 * 
	 * @param sequence the base .MID file sent from the GUI
	 */
	public void ripChannels(SeatSection s)
	{
		seat = s;
		
		/**
		 * MODIFY these two files depending on the midi and text file currently in use
		 */
		try
		{
			songFile = new File("../midi/verdi_requiem.mid");
			textFile = new File("../midi/verdi_requiem.txt");
		}
		catch(Exception e)
		{
			
		}
		
		System.out.println(songFile.getAbsolutePath());
		
		try
		{
            sequencer = MidiSystem.getSequencer(false);
            receiver = MidiSystem.getReceiver();
            // open the sequencer and wire up the receiver
            // and transmitter

            sequencer.open();
            transmitter = sequencer.getTransmitter();
            transmitter.setReceiver(receiver);
            
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            if(firstRun)
            {
            	synthesizer.loadAllInstruments(MidiSystem.getSoundbank(new File("../midi/soundbank-deluxe.gm")));
            	firstRun = false;
            }
            sequencer.setSequence(sequence);
            
            
            System.out.println("done");
            
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}            
            
        	try
        	{
        		channels = 0;
        		Scanner input = new Scanner(textFile);
        		while(input.hasNextLine())
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
        				e.printStackTrace();
        			}
        			if(directed)
        			{
        				Vector3d v;
        				if(name.equals("TROMBONE"))
        				{
        					v = new Vector3d(1/ROOTTWO,-1/ROOTTWO,0);
        				}
        				else if(name.equals("TRUMPET"))
        				{
        					v = new Vector3d(1,0,0);
        				}
        				else if(name.equals("TUBA"))
        				{
        					v = new Vector3d(1/ROOTTWO,1/ROOTTWO,0);
        				}
        				else
        				{
        					//default as tuba
        					v = new Vector3d(1/ROOTTWO,1/ROOTTWO,0);
        				}
        				DirectedInstrument i = new DirectedInstrument(name, channels, volume, instrumentPoints[channels], v, (double)0.5);
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
        		e.printStackTrace();
        	}
        	
        	normalizeVolumes();
        	changeVolumes();      
		
	}
	
	/**
	 * Reduces the volumes of every channel to prevent volume maxing out
	 */
	public void normalizeVolumes()
	{
		for(int i = 0; i < channels; i++)
		{
			instrumentVolumes[i] -= 20;
			System.out.println(instruments[i].getName()+" Instrument normalized volume " + i + " " + instrumentVolumes[i]);
		}
	}
	
	/**
	 * Puts each instruments volume through the algorithm needed to generate the proper volume
	 */
	public void changeVolumes()
	{
		Double volumeAverage = 0.0;
		Double scaleAverage = 0.0;
		System.out.println(channels);
		for(int i = 0; i < channels; i++)
		{
			volumeAverage += instrumentVolumes[i];
			System.out.println(instruments[i].getName()+" Instrument initial volume " + i + " " + instrumentVolumes[i]);
		}
		volumeAverage = volumeAverage / (channels+1);
		System.out.println("VAverage" + volumeAverage);
		for(int i = 0; i < channels; i++)
		{
			//SeatSection seatA = new SeatSection(new Point3d(-1,2,.5), "lol", songFile); //use if applet is not passing a seat
			scaledVolumes[i] = instruments[i].getAdjustedVolume(seat);
			System.out.println("Scaled volume " + i + " " + scaledVolumes[i]);
		}
		for(int i = 0; i < channels; i++)
		{
			scaleAverage += scaledVolumes[i];
		}
		scaleAverage = scaleAverage / (channels+1);
		System.out.println("SAverage " + scaleAverage);
		
		double ratio = volumeAverage / scaleAverage;
		System.out.println("Ratio " + ratio);
		
		for(int i = 0; i < channels; i++)
		{
			instrumentVolumes[i] = (int)(scaledVolumes[i]*ratio);
			System.out.println(instruments[i].getName()+" Instrument final volume " + i + " " + instrumentVolumes[i]);
		}
		volumeAverage = 0.0;
		for(int i = 0; i < channels; i++)
		{
			volumeAverage += instrumentVolumes[i];
		}
		volumeAverage = volumeAverage / (channels+1);
		System.out.println("VAverage final" + volumeAverage);
		
		
		channels = 0;
		
	}
	
	/**
	 * Play the sequencer
	 */
	public void play()
	{
		try{
			sequence = MidiSystem.getSequence(songFile);
	        sequencer = MidiSystem.getSequencer(false);
	        receiver = MidiSystem.getReceiver();
	        // open the sequencer and wire up the receiver
	        // and transmitter
	
	        sequencer.open();
	        transmitter = sequencer.getTransmitter();
	        transmitter.setReceiver(receiver);
	        
	        synthesizer = MidiSystem.getSynthesizer();
	        synthesizer.open();
	        //synthesizer.loadAllInstruments(MidiSystem.getSoundbank(new File("../midi/soundbank-deluxe.gm")));
	        sequencer.setSequence(sequence);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		/**try {
			sequencer.setSequence(sequence);
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}*/
		
		sequencer.start();
		
		ShortMessage volumeMessage = new ShortMessage();
		
		for(int i = 0; i < 9; i++)
        {
            try {
				volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, 0);
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			}
            try {
				MidiSystem.getReceiver().send(volumeMessage, -1);
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
        }
        
        
        for (int i = 11; i < instruments.length; i++) {
                try {
					volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, 0);
				} catch (InvalidMidiDataException e) {
					e.printStackTrace();
				}
                try {
					MidiSystem.getReceiver().send(volumeMessage, -1);
				} catch (MidiUnavailableException e) {
					e.printStackTrace();
				}
        }
        
	}
	
	/**
	 * Stop the sequencer
	 */
	public void stop()
	{
		try
		{
			sequencer.stop();
		}
		catch(Exception e)
		{
			
		}
	}

}
