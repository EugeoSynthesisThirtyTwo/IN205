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
		 * TODO d�finir la taille des bateaux,
		 * et les coordonn�es des cases, quand
		 * l'�nonc� donnera la forme des bateaux.
		 */
		super('C', "Carrier", orientation, );
	}
}
