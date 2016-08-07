package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI_Tile extends JPanel
{
	private JTextArea text = new JTextArea("");
	
	private Color color;
	
	private int value = 0;
	
	public GUI_Tile(int initialValue)
	{
		text.setFont(new Font(text.getFont().getName(), text.getFont().getStyle(), 28));
		this.add(text);
		
		setValue(initialValue);
	}
	
	public void setValue(int toValue)
	{
		value = toValue;
		
		calculateColor();
		
		text.setText(value + "");
		text.setBackground(color);
		
		this.setBackground(color);
	}
	
	private void calculateColor()
	{
//		int number = (122988 * toValue) + (toValue * toValue); // good
//		int number = (322788 * toValue) + (toValue * toValue); // better
//		int number = (951159 * toValue) + (toValue * toValue); // good scale
//		int number = (420024 * toValue) + (toValue * toValue); // good scale for 8192 plus
//		int number = (2147000000 / (toValue + 1)) + (toValue * toValue); // Best scale yet
//		int number = (1947000000 / (toValue + 1)) + (toValue * toValue); // good scale
		
		color = Color.lightGray;
		
		int mod = value - 16384;
		int number = (2 - (mod * mod + mod * mod) / 2) * 7; // Best
		
		if(value == 2) 		color = (Color.decode((number + 9) + "")).brighter();
		if(value == 4) 		color = (Color.decode((number - 25) + ""));
		if(value == 8) 		color = (Color.decode((number - 5) + ""));
		if(value == 16) 	color = (Color.decode((number + 10) + "")).brighter();
		if(value == 32) 	color = (Color.decode(number + "")).darker();
		if(value == 64) 	color = (Color.decode(number + "")).brighter();
		if(value == 128) 	color = (Color.decode(number + "")).brighter();
		if(value == 256)	color = (Color.decode(number + "")).brighter().brighter().brighter();
		if(value == 512) 	color = (Color.decode(number + "")).brighter();
		if(value == 1024) 	color = (Color.decode(number + ""));
		if(value == 2048) 	color = (Color.decode(number + ""));
		if(value > 2048) 	color = (Color.decode(number + "")).brighter().brighter();
	}
}
