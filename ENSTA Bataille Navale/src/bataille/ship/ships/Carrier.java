package bataille.ship.ships;

import bataille.ship.AbstractShip;

/**
 * bateau de longueur 5
 */
public class Carrier extends AbstractShip
{
	public Carrier()
	{
		this(Orientation.WEST);
	}
	
	public Carrier(Orientation orientation)
	{
		super('C', "Carrier", orientation, 5);
	}
}
