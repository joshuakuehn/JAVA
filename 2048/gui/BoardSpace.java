package gui;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BoardSpace extends JPanel
{
	private static final long serialVersionUID = 5519588953687280353L;

	private Border brdr = BorderFactory.createLineBorder(Color.black);
	
	private ArrayList<GUI_Tile> guiTiles = new ArrayList<GUI_Tile>();

	private int xDim = 0;
	private int yDim = 0;

	public BoardSpace(int toXDim, int toYDim)
	{
		xDim = toXDim;
		yDim = toYDim;
		
		this.setLayout(null);
	}

	public void buildTiles()
	{
		
		for(int x = 0; x < xDim; x++)
		{
			for(int y = 0; y < yDim; y++)
			{
				GUI_Tile toAdd = new GUI_Tile(0);
				
				toAdd.setBounds(100 * x, 100 * y, 100, 100);
				toAdd.setBorder(brdr);
				
				this.add(toAdd);
				
				guiTiles.add(toAdd);
			}
		}
		
	}
	
	public void update(ArrayList<Integer> stateArr)
	{
		int am = 1;
		for(int x = 0; x < xDim; x++)
		{
			for(int y = 0; y < yDim; y++)
			{
				am += am;
//				guiTiles.get(x * xDim + y).setValue(am); // TODO: Delete after testing
				
				guiTiles.get(x * xDim + y).setValue(stateArr.get(x * xDim + y));
			}
		}
		
		this.repaint();
	}
}
