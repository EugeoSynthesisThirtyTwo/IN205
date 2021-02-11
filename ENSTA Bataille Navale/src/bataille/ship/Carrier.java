package bataille.ship;

import bataille.Orientation;

public class Carrier extends AbstractShip
{
	public Carrier()
	{
		this(Orientation.WEST);
	}
	
	public Carrier(Orientation orientation)
	{
		/*
		 * TODO définir la taille des bateaux,
		 * et les coordonnées des cases, quand
		 * l'énoncé donnera la forme des bateaux.
		 */
		super('C', "Carrier", orientation, );
	}
}
