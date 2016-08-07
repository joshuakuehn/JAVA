package logic;

import gui.GUI;
import gui.TempGUI;

public class Hub 
{
	GUI gui;
	Gameboard gb;
	
	public Hub()
	{
		// LOL GG
	}
	
	public void performAction(String action)
	{
		gb.moveAction(action);
		gui.update(gb.getState());
	}
	
	public void link(GUI toGUI, Gameboard toGb)
	{
		gui = toGUI;
		gb = toGb;
	}
	
	public void autoCircleMove(int numCircles)
	{
		for(int i = 0; i < numCircles; i++)
		{
			performAction("DOWN");
			performAction("RIGHT");
			performAction("UP");
			performAction("LEFT");
		}
	}
}
