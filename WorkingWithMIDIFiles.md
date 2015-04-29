# Introduction #

Our applet supports the switching between two midi files at once. The MIDI files are read through plain text files that the programmer must do. The creation of an automated system at the time of writing was not implemented due to the lack of a standard for instruments to take specific channels, so it would be unrealistic to automate this part of the program.


# File Setups #

Within the midi folder are three important text files and two midi files. These are:
  * song\_1.txt
  * song\_2.txt
  * song\_information.txt
  * song\_1.mid
  * song\_2.mid

song\_1.mid and song\_2.mid are the two midi files that will be loaded by the applet. To keep track of what pieces these actually represent, you should modify song\_information.txt to keep track of what they are.

song\_1.txt and song\_2.txt hold the information required to interpret the midi channels and adjust the volumes properly. These text files hold many important points of information: channel names, type of instrument, location, and channel volume.

## Text File Layout ##
  * Line 1: Name of instrument in ALLCAPSANDNOSPACES
  * Line 2: Type of instrument (DIRECTED or UNDIRECTED). The only directed instruments are TUBAS, FRENCHHORN, TRUMPET, and TROMBONE.
  * Line 3-5: X,Y, and Z, location of instrument on separate lines according to following:
    * Strings and Woodwinds(-0.5,0,0)
    * Tubas(-1,1,0)
    * Trombones(-1,0.5,0)
    * Trumpets(-1,0,0)
    * Percussion(-1.2,0,0)
    * French Horn(-1,-0.5,0)
  * Line 6: Channel volume

Repeat this for all 16 channels. If there is an unused channel, the name should be UNUSED, DIRECTED, (0,0,0), and the volume should be 0.

## Example Text File Layout ##
This is an example of what a song\_1.txt might look like:
```
FLUTE
UNDIRECTED
-0.5
0
0
90
OBOE
UNDIRECTED
-0.5
0
0
90
CLARINET
UNDIRECTED
-0.5
0
0
90
BASSOON 
UNDIRECTED
-0.5
0
0
90
FRENCHHORN 
DIRECTED
-1
-0.5
0
120
TRUMPET 
DIRECTED
-1
0
0
120
TROMBONE 
DIRECTED
-1
-0.5
0
120
TUBA 
DIRECTED
-1
1
0
120
TIMPANI 
UNDIRECTED
-1.2
0
0
127
BASSDRUM 
UNDIRECTED
-1.2
0
0
127
CHOIR 
UNDIRECTED
-1.3
0
0
127
VIOLIN1 
UNDIRECTED
-0.5
0
0
100
VIOLIN2 
UNDIRECTED
-0.5
0
0
100
VIOLA 
UNDIRECTED
-0.5
0
0
100
CELLO 
UNDIRECTED
-0.5
0
0
100
DOUBLEBASS 
UNDIRECTED
-0.5
0
0
100
```