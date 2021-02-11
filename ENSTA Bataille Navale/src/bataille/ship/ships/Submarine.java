package bataille.ship.ships;

import bataille.ship.AbstractShip;

/**
 * bateau de longueur 3
 */
public class Submarine extends AbstractShip
{
	public Submarine()
	{
		this(Orientation.WEST);
	}
	
	public Submarine(Orientation orientation)
	{
		super('S', "Submarine", orientation, 3);
	}
}
