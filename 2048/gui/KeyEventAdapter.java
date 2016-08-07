package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import logic.Hub;

public class KeyEventAdapter extends KeyAdapter 
{
	Hub hub;
	
	private String action = "NULL";
	
	public KeyEventAdapter(Hub toHub)
	{
		hub = toHub;
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		action = "NULL";
		
		int key = e.getKeyCode();
		
		switch(key)
		{
			case KeyEvent.VK_LEFT : 
				action = "LEFT";
				break;
				
			case KeyEvent.VK_RIGHT : 
				action = "RIGHT";
				break;

			case KeyEvent.VK_UP : 
				action = "UP";
				break;
				
			case KeyEvent.VK_DOWN : 
				action = "DOWN";
				break;
		}
		
		hub.performAction(action);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		action = "NULL";
		
	}
}
