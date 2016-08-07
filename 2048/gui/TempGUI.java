package gui;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class TempGUI extends JFrame 
{
	private static final long serialVersionUID = 263923948582088141L;
	
	private static final int FRAME_X_POSIT = 1000;
	private static final int FRAME_Y_POSIT = 100;
	private static final int FRAME_X_WIDTH = 350;
	private static final int FRAME_Y_HEIGHT = 350;
	
	private Border brdr = BorderFactory.createLineBorder(Color.black);
	private Container contentPane = this.getContentPane();
	private JTextArea view = new JTextArea();
	
	private KeyEventAdapter input;
	
	public TempGUI(KeyEventAdapter toInput)
	{
		input = toInput;
		
		this.addKeyListener(input);
		//this.setFocusable(true);
		
		this.init();
	}

	private void init() 
	{
		this.setTitle("2048");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(FRAME_X_WIDTH, FRAME_Y_HEIGHT);
		this.setLocation(FRAME_X_POSIT, FRAME_Y_POSIT);
		this.setResizable(false);
		
		contentPane.setLayout(null);
		
		view.setBounds(10,10,325,300);
		view.setEditable(false);
		view.setBorder(brdr);
		view.setText("");
		contentPane.add(view);
		
		this.setVisible(true);
		contentPane.repaint();
	}
	
	public void update(String str)
	{
		view.setText(str);
		contentPane.repaint();
	}
}
