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
		 * TODO d�finir la taille des bateaux,
		 * et les coordonn�es des cases, quand
		 * l'�nonc� donnera la forme des bateaux.
		 */
		super('B', "Battleship", orientation, );
	}
}
