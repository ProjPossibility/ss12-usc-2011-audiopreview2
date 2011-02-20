package util.midi;

import java.io.File;
import java.util.Scanner;

import javax.sound.midi.*;

import engine.*;
import engine.Instrument;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class MidiControl {
	
	Instrument[] instruments = new Instrument[16];
	int[] instrumentVolumes = new int[16];
	double[] scaledVolumes = new double[16];
	Point3d[] instrumentPoints = new Point3d[16];
	double volumeAverage, scaleAverage;
	File songFile, textFile;
	Sequence sequence;
	Sequencer sequencer;
	Receiver receiver;
	Synthesizer synthesizer;
	Transmitter transmitter;
	int channels;
	SeatSection seat;
	
	private static final double ROOTTWO = Math.sqrt(2);
	
	/**
	 * Default constructor
	 */
	public MidiControl()
	{
		//ripChannels(seat);
		volumeAverage = 0;
		scaleAverage = 0;
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
			songFile = new File("midi/verdi_requiem.mid");
			textFile = new File("midi/verdi_requiem.txt");
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
            transmitter = sequencer.getTransmitter();
            transmitter.setReceiver(receiver);
            
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            synthesizer.loadAllInstruments(MidiSystem.getSoundbank(new File("midi/soundbank-deluxe.gm")));
            sequencer.setSequence(sequence);
            
            
            System.out.println("done");
            
		}
		catch(Exception e)
		{
			System.out.println(e);
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
        				System.out.println(e);
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
        				DirectedInstrument i = new DirectedInstrument(name, channels, volume, instrumentPoints[channels], new Vector3d(1,0,0), (double)0.5);
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
	
	/**
	 * Puts each instruments volume through the algorithm needed to generate the proper volume
	 */
	public void changeVolumes()
	{
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
			SeatSection seatA = new SeatSection(new Point3d(-1,2,.5), "lol", songFile);
			scaledVolumes[i] = instruments[i].getAdjustedVolume(seatA);
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
		volumeAverage = 0;
		for(int i = 0; i < channels; i++)
		{
			volumeAverage += instrumentVolumes[i];
		}
		volumeAverage = volumeAverage / (channels+1);
		System.out.println("VAverage final" + volumeAverage);
		
		//reset average values for use
		volumeAverage = 0;
		scaleAverage = 0;
		channels = 0;
		
	}
	
	/**
	 * Play the sequencer
	 */
	public void play()
	{
		
		sequencer.start();
		
		ShortMessage volumeMessage = new ShortMessage();
		
		for(int i = 0; i < 9; i++)
        {
            try {
				volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, 0);
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println(MidiSystem.getSynthesizer().getChannels()[i].getController(7));
            try {
				MidiSystem.getReceiver().send(volumeMessage, -1);
			} catch (MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        for (int i = 11; i < instruments.length; i++) {
                try {
					volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, 0);
				} catch (InvalidMidiDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					MidiSystem.getReceiver().send(volumeMessage, -1);
				} catch (MidiUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        
	}
	
	/**
	 * Stop the sequencer
	 */
	public void stop()
	{
		sequencer.stop();
	}

}
