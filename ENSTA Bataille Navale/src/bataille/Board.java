package bataille;

import bataille.ship.AbstractShip;

public class Board implements IBoard
{
	private String name;
	public final int size;
	private char[][] navires;
	private boolean[][] frappes;
	
	/**
	 * créé une grille de taille size x size
	 * @param name : le nom de la grille
	 * @param size : la taille de la grille
	 */
	public Board(String name, int size)
	{
		this.setName(name);
		this.size = size;
		navires = new char[size][size];
		frappes = new boolean[size][size];
		
		for (int y = 0; y < size; y++)
			for (int x = 0; x < size; x++)
			{
				navires[y][x] = '.';
				frappes[y][x] = false;
			}
	}
	
	/**
	 * @param name : le nom de la grille
	 * créé une grille de taille 10x10
	 */
	public Board(String name)
	{
		this(name, 10);
	}
	
	/**
	 * renvoie un String sur plusieurs lignes contenant
	 * la grille des navires et la grille des frappes côtes à côtes
	 */
	public String toString()
	{
		String result = "";
		
		result += "Navires :";
		
		for (int i = 0; i < 2 * size - 3; i++)
			result += " ";
		
		result += "Frappes :\n  ";
		
		for (int i = 0; i < size; i++)
			result += " " + (char) ('A' + i);

		result += "      ";
		
		for (int i = 0; i < size; i++)
			result += " " + (char) ('A' + i);
		
		result += "\n";
		
		for (int line = 0; line < size; line++)
		{
			if (line < 10)
				result += "0" + line;
			else
				result += line;

			for (int i = 0; i < size; i++)
				result += " " + navires[line][i];
			
			result += "    ";

			if (line < 10)
				result += "0" + line;
			else
				result += line;

			for (int i = 0; i < size; i++)
				if (frappes[line][i])
					result += " x";
				else
					result += " .";
			
			result += "\n";
		}
		
		return result;
	}
	
	/**
	 * System.out.print(this);
	 */
	public void print()
	{
		System.out.print(this);
	}

	@Override
	public int getSize()
	{
		return size;
	}

	@Override
	public boolean putShip(AbstractShip ship, int x, int y)
	{
		int length = ship.getSize();
		int dx = 0, dy = 0;
		
		switch (ship.getOrientation())
		{
			case NORTH:
				dy = -1;
				break;
			case SOUTH:
				dy = 1;
				break;
			case EAST:
				dx = 1;
				break;
			case WEST:
				dx = -1;
				break;
		}
		
		int x_ = x, y_ = y;
		boolean valide = true;
		
		// vérifie si le bateau va en chevaucher un autre
		// ou si le bateau finira en dehors des limites
		for (int i = 0; i < length && valide; i++)
		{
			if (x_ < 0 || x_ >= size || y_ < 0 || y_ >= size || navires[y_][x_] != '.')
				valide = false;
			
			x_ += dx;
			y_ += dy;
		}
		
		if (!valide)
			//on ne place pas le bateau
			return false;
		
		x_ = x;
		y_ = y;

		for (int i = 0; i < length; i++)
		{
			navires[y_][x_] = ship.getLabel();
			x_ += dx;
			y_ += dy;
		}
		
		return true;
	}

	@Override
	public boolean hasShip(int x, int y)
	{
		return navires[y][x] != '.';
	}

	@Override
	public void setHit(boolean hit, int x, int y)
	{
		frappes[y][x] = hit;
	}

	@Override
	public boolean getHit(int x, int y)
	{
		return frappes[y][x];
	}

	/**
	 * @return le nom de la grille
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name le nom de la grille
	 */
	public void setName(String name)
	{
		this.name = name;
	}
}
