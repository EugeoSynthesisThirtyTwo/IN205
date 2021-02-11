package bataille.ship;

import bataille.Orientation;

public class Destroyer extends AbstractShip
{
	public Destroyer()
	{
		this(Orientation.WEST);
	}
	
	public Destroyer(Orientation orientation)
	{
		/*
		 * TODO définir la taille des bateaux,
		 * et les coordonnées des cases, quand
		 * l'énoncé donnera la forme des bateaux.
		 */
		super('D', "Destroyer", orientation, );
	}
}
