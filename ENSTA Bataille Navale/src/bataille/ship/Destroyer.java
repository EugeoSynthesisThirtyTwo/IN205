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
		 * TODO d�finir la taille des bateaux,
		 * et les coordonn�es des cases, quand
		 * l'�nonc� donnera la forme des bateaux.
		 */
		super('D', "Destroyer", orientation, );
	}
}
