package bataille.ship;

import bataille.Orientation;

public class Submarine extends AbstractShip
{
	public Submarine()
	{
		this(Orientation.WEST);
	}
	
	public Submarine(Orientation orientation)
	{
		/*
		 * TODO définir la taille des bateaux,
		 * et les coordonnées des cases, quand
		 * l'énoncé donnera la forme des bateaux.
		 */
		super('S', "Submarine", orientation, );
	}
}
