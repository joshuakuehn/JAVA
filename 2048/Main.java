import logic.*;

import java.awt.EventQueue;

import gui.*;

public class Main 
{	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				int xDim = 4;
				int yDim = 4;
				
				Gameboard gb = new Gameboard(xDim, yDim);
				
				Hub hub = new Hub();
				KeyEventAdapter input = new KeyEventAdapter(hub);
				
				GUI gui = new GUI(input,xDim,yDim);
				gui.update(gb.getState());
				
				hub.link(gui, gb);
				
//				hub.autoCircleMove(1000);
			}
		});
		
		
//		
//		System.out.println(gb);
//		gui.update(gb.toString());
//		
////		gb.moveAction("DOWN");
////		System.out.println(gb);
////		gb.moveAction("RIGHT");
////		System.out.println(gb);
////		gb.moveAction("UP");
////		System.out.println(gb);
////		gb.moveAction("LEFT");
////		System.out.println(gb);
////		gb.moveAction("DOWN");
////		System.out.println(gb);
//		
//		for(int i = 0; i < 4; i++)
//		{
//			gb.moveAction("DOWN");
////			System.out.println(gb);
//			gb.moveAction("RIGHT");
//			System.out.println(gb);
//			gui.update(gb.toString());
//		}
		
	}
}
