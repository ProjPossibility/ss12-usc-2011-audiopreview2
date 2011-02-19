package util.midi;

import javax.sound.midi.*;

public class RipChannels {
	
	/**
	 * Create a new array of Instruments by pulling out the individual channels in the sequence
	 * 
	 * @param sequence the base .MID file sent from the GUI
	 */
	public void ripChannels(Sequence sequence)
	{
		try
		{
			Synthesizer synthesizer = MidiSystem.getSynthesizer();
			synthesizer.open();
			MidiChannel[] channels = synthesizer.getChannels();
		}
		catch(Exception e)
		{
			
		}
	}

}
