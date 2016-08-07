package logic;

import java.util.ArrayList;

public class Gameboard 
{
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	int xDim = 0;
	int yDim = 0;
	
	int moves = 0;
	int score = 0;
	
	int internalMoves = 0;
	
	public Gameboard(int toX, int toY)
	{
		xDim = toX;
		yDim = toY;
		
		populateTiles();
	}
	
	private void populateTiles()
	{
		for(int y = 0; y < xDim; y++)
		{
			for(int x = 0; x < yDim; x++)
			{
				tiles.add(new Tile(x, y));
			}
		}
		
		//tiles.get((int) (Math.random() * 15)).spawn();
		//tiles.get((int) (Math.random() * 15)).spawn();
		
		//tiles.get(2*xDim+3).spawn(); // TODO: Remove these after testing
		//tiles.get(1*xDim+1).spawn();
		//tiles.get(3*xDim+2).spawn();
		
		
		spawnNewValue();
		spawnNewValue();
	}
	
	/**
	 * Performs a move action on the gameboard.
	 * Steps are as follows:
	 * 		Moves all tile values as far as possible in the direction specified
	 * 		Adds any identical tile values that are next to each other after the first move
	 * 		Moves all tile values to fill in any gaps created by addition of identical tile values
	 * 		Spawn a new base value in one of the open spaces, preferentially for the least spawned in tile
	 * 		Check if game is lost
	 * 
	 * @param direction :: String detailing which direction to move.
	 */
	public void moveAction(String direction)
	{
		internalMoves = 0;
		
		int dX = 0;
		int dY = 0;
		
		int xStart = 0;
		int yStart = 0;
		
		int xMod = 0;
		int yMod = 0;
		
		switch(direction)
		{
			case "UP":
				dX = 0;
				dY = -1;
				xStart = 0;
				yStart = 0;
				xMod = 1;
				yMod = 1;
				break;
				
			case "DOWN":
				dX = 0;
				dY = 1;
				xStart = xDim - 1;
				yStart = yDim - 1;
				xMod = -1;
				yMod = -1;
				break;
				
			case "LEFT":
				dX = -1;
				dY = 0;
				xStart = 0;
				yStart = 0;
				xMod = 1;
				yMod = 1;
				break;
				
			case "RIGHT":
				dX = 1;
				dY = 0;
				xStart = xDim - 1;
				yStart = yDim - 1;
				xMod = -1;
				yMod = -1;
				break;
		}
		
		moveAll(dY,dX);
		addValues(dY,dX,xStart,yStart,xMod,yMod);
		moveAll(dY,dX);
		spawnIfPossible();
		gameLost();
	}
	
	private void moveAll(int delY, int delX)
	{
		boolean moveComplete = false;
		
		int tarX = 0;
		int tarY = 0;
		
		while(!moveComplete)
		{
			moveComplete = true;
			
			for(int x = 0; x < xDim; x++)
			{
				for(int y = 0; y < yDim; y++)
				{
					tarX = x + delX;
					tarY = y + delY;
					
					if( (tarX >= 0 && tarX < xDim) && (tarY >= 0 && tarY < yDim) )
					{
						Tile current = tiles.get(x * xDim + y);
						Tile target = tiles.get(tarX * xDim + tarY);
						
						if(!target.isOccupied() && current.getValue() != 0)
						{
							internalMoves++;
							target.setValue(current.removeValue());
							moveComplete = false;
						} 
					}
					
				}
			}
			
		}
		
	}
	
	private void addValues(int delY, int delX, int xStart, int yStart, int xMod, int yMod) 
	{
		int tarX = 0;
		int tarY = 0;
		
		for(int x = xStart; (x < xDim && x >= 0); x += xMod)
		{
			for(int y = yStart; (y < yDim && y >= 0); y += yMod)
			{
				tarX = x + delX * xMod;
				tarY = y + delY * yMod;
				
				if( (tarX >= 0 && tarX < xDim) && (tarY >= 0 && tarY < yDim) )
				{
					Tile current = tiles.get(x * xDim + y);
					Tile target = tiles.get(tarX * xDim + tarY);
					
					if(current.getValue() != 0 && target.getValue() == current.getValue())
					{
						internalMoves++;
						current.addToValue(target.removeValue());
						score += current.getValue();
					} 
				}
				
			}
		}
		
	}
	
	private void spawnIfPossible()
	{
		if(internalMoves > 0)
		{
			moves++;
			spawnNewValue();
		}
	}
	
	private void spawnNewValue() // TODO: Improve randomness
	{
		Tile target = tiles.get(0);
		
		for(int x = 0; x < xDim; x++)
		{
			for(int y = 0; y < yDim; y++)
			{
				Tile possible = tiles.get(x * xDim + y);
				
				if(!possible.isOccupied())
				{
//					if(possible.getSpawns() < target.getSpawns()) target = possible;
//					if(possible.getSpawns() == target.getSpawns()) target = (Math.random() > 0.9)? possible : target;
					
					if(possible.getSpawns() <= target.getSpawns()) target = (Math.random() > 0.85)? possible : target;
				}
				
			}
		}
		
		if(!target.isOccupied())
		{
			target.spawn();
		}
		
	}
	
	private void gameLost()
	{
		// TODO: This whole damn thing
	}
	
	public ArrayList<Integer> getState()
	{
		ArrayList<Integer> stateArr = new ArrayList<Integer>();
		
		for(int x = 0; x < xDim; x++)
		{
			for(int y = 0; y < yDim; y++)
			{
				stateArr.add(tiles.get(x * xDim + y).getValue());
			}
		}
		
		stateArr.add(score);
		stateArr.add(moves);
		
		return stateArr;
	}
	
	public String toString()
	{
		String out = "";
		out += "Score:\t" + score + "\tMoves:\t" + moves;
		out += "\n-\t-\t-\t-\n";
		
		for(int y = 0; y < xDim; y++)
		{	
			for(int x = 0; x < yDim; x++)
			{
				out += tiles.get(x * xDim + y).getValue() + "\t";
			}
			
			out += "\n";
		}
		
		out += "-\t-\t-\t-\n";
		
		return out;
	}
}
