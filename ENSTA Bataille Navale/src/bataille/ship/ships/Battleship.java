package bataille.ship.ships;

import bataille.ship.AbstractShip;

/**
 * bateau de longueur 4
 */
public class Battleship extends AbstractShip
{
	public Battleship()
	{
		this(Orientation.WEST);
	}
	
	public Battleship(Orientation orientation)
	{
		super('B', "Battleship", orientation, 4);
	}
}
