import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

enum State {HOVERING,NOT_HOVERING,NOT_GREYED_OUT,GREYED_OUT,SELECTED};

public class JButtonGroup extends JPanel implements ActionListener {
	ConcertHallPanel parent;
	ImageIcon icon;
	final int BUTTON_WIDTH = 30;
	final int BUTTON_HEIGHT = 30;
	ArrayList<JButton> group;
	
	public JButtonGroup(int numGroups, State state){
		
		group = new ArrayList<JButton>();
		addButtons(numGroups);
		//setGroupsIcon(state);
	}
	
	public void storeButton(JButton j){
		group.add(j);
	}
	
	public void remove(int index){
		group.remove(index);
	}
	
	public void addButtons(int num){
		for(int k = 0; k <num;k++){
			JButton temp = new JButton();
			temp.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
			storeButton(temp);
			//make separate calls to set the imageicon
		}
	}
	//Handle the different 
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}
