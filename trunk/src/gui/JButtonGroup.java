package gui;

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
	private int groupFloor;
	public JButtonGroup(int numGroups, State state, int floor){
		groupFloor = floor;
		changePanelSize(numGroups);
		group = new ArrayList<JButton>();
		addButtons(numGroups);
		setGroupsIcon(state);
	}
	
	public int getFloor(){
		return groupFloor;
	}
	public void changePanelSize(int numGroups){
		if(numGroups == 1){
			
		}
		else if(numGroups == 2){
			
		}
		else if(numGroups == 3){
			
		}
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
	//Handle the different clicks
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public void setGroupsIcon(State state){
		
	}
}
