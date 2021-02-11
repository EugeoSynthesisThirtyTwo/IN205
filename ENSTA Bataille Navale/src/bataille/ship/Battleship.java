package bataille.ship;

import bataille.Orientation;

public class Battleship extends AbstractShip
{
	public Battleship()
	{
		this(Orientation.WEST);
	}
	
	public Battleship(Orientation orientation)
	{
		/*
		 * TODO définir la taille des bateaux,
		 * et les coordonnées des cases, quand
		 * l'énoncé donnera la forme des bateaux.
		 */
		super('B', "Battleship", orientation, );
	}
}
