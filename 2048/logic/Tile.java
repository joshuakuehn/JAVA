package logic;

import java.util.Random;

public class Tile 
{
	private int xCoor = 0;
	private int yCoor = 0;
	
	private boolean occupied = false;
	
	private int value = 0;
	private int spawns = 0;
	
	private Random rando = new Random();;
	
	public Tile(int toX, int toY)
	{
		xCoor = toX;
		yCoor = toY;
	}
	
	public void spawn()
	{
		if(occupied == false)
		{
			spawns++;
			occupied = true;
			value = (rando.nextDouble() > 0.95) ? 4 : 2 ;
//			value = 2; // TODO: Make random chance for 4 instead of 2
		}
	}
	
	public int getSpawns()
	{
		return spawns;
	}
	
	public void setValue(int toValue)
	{
		occupied = true;
		value = toValue;
	}
	
	public void addToValue(int valueToAdd)
	{
		if(valueToAdd > 0)
		{
			occupied = true;
			value += valueToAdd;
		}
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int removeValue()
	{
		int prev = value;
		occupied = false;
		value = 0;
		return prev;
	}
	
	public boolean isOccupied()
	{
		return occupied;
	}
}
