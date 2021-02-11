package bataille;

import bataille.ship.AbstractShip;
import bataille.ship.ShipState;

public class Board implements IBoard
{
	private String name;
	public final int size;
	private ShipState[][] navires;
	
	/**
	 * null : la case ne s'est pas faite tirer dessus<br>
	 * false : le tir a fini dans l'eau<br>
	 * true : le tir a touché un bateau
	 */
	private Boolean[][] frappes;
	
	/**
	 * créé une grille de taille size x size
	 * @param name : le nom de la grille
	 * @param size : la taille de la grille
	 */
	public Board(String name, int size)
	{
		this.setName(name);
		this.size = size;
		navires = new ShipState[size][size];
		frappes = new Boolean[size][size];
		
		for (int y = 0; y < size; y++)
			for (int x = 0; x < size; x++)
			{
				navires[y][x] = new ShipState(null);
				frappes[y][x] = null;
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
	
	public void print()
	{
		System.out.print("Navires :");
		
		for (int i = 0; i < 2 * size - 3; i++)
			System.out.print(" ");
		
		System.out.print("Frappes :\n  ");
		
		for (int i = 0; i < size; i++)
			System.out.print(" " + (char) ('A' + i));

		System.out.print("      ");
		
		for (int i = 0; i < size; i++)
			System.out.print(" " + (char) ('A' + i));
		
		System.out.print("\n");
		
		for (int line = 1; line <= size; line++)
		{
			if (line < 10)
				System.out.print("0" + line);
			else
				System.out.print(line);

			for (int i = 0; i < size; i++)
				System.out.print(" " + navires[line - 1][i]);
			
			System.out.print("    ");

			if (line < 10)
				System.out.print("0" + line);
			else
				System.out.print(line);

			for (int i = 0; i < size; i++)
			{
				Boolean hit = frappes[line - 1][i];
				
				if (hit == null)
					System.out.print(" .");
				else if (hit)
					System.out.print(" x");
				else
					System.out.print(ColorUtil.colorize(" x", ColorUtil.Color.RED));
			}
			
			System.out.print("\n");
		}
	}

	@Override
	public int getSize()
	{
		return size;
	}

	@Override
	public boolean putShip(AbstractShip ship, int x, int y)
	{
		int length = ship.getLength();
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
			if (x_ < 0 || x_ >= size || y_ < 0 || y_ >= size || navires[y_][x_].isAShip())
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
			navires[y_][x_] = new ShipState(ship);
			x_ += dx;
			y_ += dy;
		}
		
		return true;
	}

	@Override
	public boolean hasShip(int x, int y)
	{
		return navires[y][x].isAShip();
	}

	@Override
	public void setHit(boolean hit, int x, int y)
	{
		frappes[y][x] = hit;
	}

	@Override
	public Boolean getHit(int x, int y)
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
