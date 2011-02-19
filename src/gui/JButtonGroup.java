package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
	
	public JButtonGroup(ConcertHallPanel p, int numGroups, State state, int floor){
		parent = p;
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
			this.setSize(30,80);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
		else if(numGroups == 2){
			this.setSize(30,60);
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		}
		else if(numGroups == 3){
			this.setSize(90,30);
			this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
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
