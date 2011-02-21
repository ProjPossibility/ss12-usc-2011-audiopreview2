
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

package util.midi;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.midi.*;

/**
 * MidiSynthesizerSample.java
 * Purpose: Sample code to be used for reference. Acts as a simple midi player that can change volumes of channels
 * Use when testing/changing new MIDI channel properties
 * 
 * @author Ryan Tenorio
 *
 */

public class MidiSynthesizerSample {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        // get all the default stuff
        try {
            Sequence sequence = MidiSystem.getSequence(new File("midi/verdi_requiem.mid"));
            Sequencer sequencer = MidiSystem.getSequencer(false);
            Receiver receiver = MidiSystem.getReceiver();
            // open the sequencer and wire up the receiver
            // and transmitter

            sequencer.open();
            sequencer.getTransmitter().setReceiver(receiver);
            // play the MIDI
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            synthesizer.loadAllInstruments(MidiSystem.getSoundbank(new File("midi/soundbank-deluxe.gm")));
            //synthesizer.loadAllInstruments(MidiSystem.getSoundbank(new File("creativeSoundFont.gm")));
            sequencer.setSequence(sequence);
            sequencer.start();
            //setReverb(7);
            setVolume(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            //setVolume(63);
            

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            //setVolume(127);
            //setVolume(0);

            //sequencer.close();
            //receiver.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Change the reverb (channel 91)
     * @param value
     */
    public static void setReverb(int value)
    {
        //int value = 60;
        try{
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        Receiver receiver = synthesizer.getReceiver();
        Sequencer sequencer = MidiSystem.getSequencer(false);
        Transmitter transmitter = sequencer.getTransmitter();
        transmitter.setReceiver( receiver );
        javax.sound.midi.MidiChannel[] channels = synthesizer.getChannels();
        ShortMessage volumeMessage = new ShortMessage();
        for(int i = 0; i < 9; i++)
        {
            volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 91, value);
            MidiSystem.getReceiver().send(volumeMessage, -1);
            
        }
        for (int i = 11; i < channels.length; i++) {
                volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 91, value);
                MidiSystem.getReceiver().send(volumeMessage, -1);
        }
    }
    catch(Exception e){}
    }
    
    /**
     * 
     * @param value
     */
    public static void setVolume(int value) {
        
        /**try {
            ShortMessage volumeMessage = new ShortMessage();
            for (int i = 0; i < 9; i++) {
                volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, value);
                MidiSystem.getReceiver().send(volumeMessage, -1);
            }
            for (int i = 11; i < 16; i++) {
                volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, value);
                MidiSystem.getReceiver().send(volumeMessage, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        
        try{
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        Receiver receiver = synthesizer.getReceiver();
        Sequencer sequencer = MidiSystem.getSequencer(false);
        Transmitter transmitter = sequencer.getTransmitter();
        transmitter.setReceiver( receiver );
        javax.sound.midi.MidiChannel[] channels = synthesizer.getChannels();
        ShortMessage volumeMessage = new ShortMessage();
        for(int i = 0; i < 9; i++)
        {
            volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, value);
            MidiSystem.getReceiver().send(volumeMessage, -1);
        }
        for (int i = 11; i < channels.length; i++) {
                volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, value);
                MidiSystem.getReceiver().send(volumeMessage, -1);
        }
        //for(int i = 0; i < channels.length; i++)
        //{
            //System.out.println(synthesizer.getChannels());
        //}
        /**for(int i = 0; i < channels.length; i++)
        {
            if(i < 9 || i > 10)
                channels[i].allSoundOff();
        }*/
    }
    catch(Exception e){}
        
        
        
    }
}
