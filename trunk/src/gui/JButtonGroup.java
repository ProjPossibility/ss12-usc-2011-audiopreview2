package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

enum deadState {HOVERING,NOT_HOVERING,GREYED_OUT,SELECTED};

public class JButtonGroup extends JPanel implements ActionListener {
	
	ConcertHallPanel parent;
	//ImageIcon icon;
	final int BUTTON_WIDTH = 100;
	final int BUTTON_HEIGHT = 100;
	ArrayList<JButton> group;
	private int groupFloor;
	private int numOfButtons;
	
	public JButtonGroup(ConcertHallPanel p, int numGroups, deadState state, int floor){
		parent = p;
		groupFloor = floor;
		numOfButtons = numGroups;
		
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
			this.setSize(BUTTON_WIDTH,3*BUTTON_HEIGHT);
			this.setPreferredSize(new Dimension(BUTTON_WIDTH,3*BUTTON_HEIGHT));
			this.setMinimumSize(new Dimension(BUTTON_WIDTH,3*BUTTON_HEIGHT));
			this.setMaximumSize(new Dimension(BUTTON_WIDTH,3*BUTTON_HEIGHT));
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			System.out.println("three buttongroup created");
		}
		else if(numGroups == 2){
			this.setSize(BUTTON_WIDTH,BUTTON_HEIGHT*2);
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		}
		else if(numGroups == 3){
			this.setSize(3*BUTTON_WIDTH,BUTTON_HEIGHT);
			this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		}
	}
	public void storeButton(JButton j){
		group.add(j);
		add(j);
	}
	
	public void remove(int index){
		group.remove(index);
	}
	
	public void addButtons(int num){
		for(int k = 0; k <num;k++){
			
			JButton temp = new JButton(" ");
			if(numOfButtons == 1){
				temp.setSize(BUTTON_WIDTH,BUTTON_HEIGHT*2);
				temp.setMinimumSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT*2));
				temp.setMaximumSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT*2));
				temp.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT*2));
			}
			else{
				temp.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
				temp.setMinimumSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
				temp.setMaximumSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
				temp.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
			}
			storeButton(temp);
		}
	}
	//Handle the different clicks
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public void setGroupsIcon(deadState state){
		switch(state){
		case HOVERING:
			colorButtons(Color.BLUE);
			break;
		case NOT_HOVERING:
			colorButtons(Color.CYAN);
			break;
		case GREYED_OUT: 
			//greyed out
			colorButtons(Color.GRAY);
			break;
		case SELECTED:
			//red selected
			colorButtons(Color.RED);
			break;
		default:
			System.out.println("Error has occured, enum value not found");
			break;
		}
	}
	
	public void setGroupsIcon(deadState state, int index){
		if(index != -1){
			//When you're selecting one particular subsection
			if(state == state.SELECTED){
				for(int j = 0; j< group.size();j++){
					if(j != index){
						group.get(j).setBackground(Color.CYAN);
						group.get(j).setForeground(Color.CYAN);
						group.get(j).setOpaque(true);
						group.get(j).setBorderPainted(false);
					}
					else{
						group.get(j).setBackground(Color.RED);
						group.get(j).setBackground(Color.RED);
						group.get(j).setOpaque(true);
						group.get(j).setBorderPainted(false);
					}
				}
			}
		}
		else{
			setGroupsIcon(state);
		}
	}
	
	public void colorButtons(Color c){
		for(JButton j : group){
			j.setBackground(c);
			j.setForeground(c);
			j.setOpaque(true);
			j.setBorderPainted(false);
		}
	}
}
