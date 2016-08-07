package gui;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 2941318999657277463L;

	private static final int FRAME_X_POSIT = 100;
	private static final int FRAME_Y_POSIT = 100;
	private static final int FRAME_X_WIDTH = 500;
	private static final int FRAME_Y_HEIGHT = 500;
	
	private Border brdr = BorderFactory.createLineBorder(Color.black);
	private Container contentPane = this.getContentPane();
	
	private KeyEventAdapter input;
	private JTextArea score;
	private BoardSpace bs;
	
	private int xDim = 0;
	private int yDim = 0;
	
	public GUI(KeyEventAdapter toInput, int toXDim, int toYDim)
	{
		init(toInput, toXDim, toYDim);
	}
	
	public void update(ArrayList<Integer> stateArr)
	{
//		score.setText("Score: " + stateArr.get(xDim * yDim));
		
		this.setTitle("2048      Score: " + stateArr.get(xDim * yDim));
		
		bs.update(stateArr);
		contentPane.repaint();
	}

	private void init(KeyEventAdapter toInput, int toXDim, int toYDim) 
	{
		buildWindow(toXDim, toYDim);
		linkInput(toInput);	
	}
	
	private void buildWindow(int toXDim, int toYDim)
	{
		this.setTitle("2048");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(FRAME_X_WIDTH, FRAME_Y_HEIGHT);
		this.setLocation(FRAME_X_POSIT, FRAME_Y_POSIT);
		this.setResizable(false);
		this.setVisible(true);
		
		xDim = toXDim;
		yDim = toYDim;
		
		buildScoreSpace();
		buildBoardSpace();
		
		contentPane.setLayout(null);
		contentPane.repaint();
	}
	
	private void linkInput(KeyEventAdapter toInput)
	{
		input = toInput;
		
		this.addKeyListener(input);
		this.setFocusable(true);
	}
	
	private void buildScoreSpace()
	{
		score = new JTextArea();
		
		score.setBounds(10,10,200,30);
		score.setBorder(brdr);
		
//		contentPane.add(score);
		contentPane.repaint();
	}
	
	private void buildBoardSpace()
	{
		 bs = new BoardSpace(xDim, yDim);
		 
		 bs.setBounds(50, 50, FRAME_X_WIDTH - 100, FRAME_Y_HEIGHT - 100);
		 bs.setBorder(brdr);
		 bs.buildTiles();
		 
		 contentPane.add(bs);
		 contentPane.repaint();
	}
}
