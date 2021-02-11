package bataille.ship.ships;

import bataille.ship.AbstractShip;

/**
 * bateau de longueur 2
 */
public class Destroyer extends AbstractShip
{
	public Destroyer()
	{
		this(Orientation.WEST);
	}
	
	public Destroyer(Orientation orientation)
	{
		super('D', "Destroyer", orientation, 2);
	}
}
