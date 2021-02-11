package bataille.ship;

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
		// on ne connais pas la taille des bateaux
		// donc on choisi une taille arbitraire
		super('C', "Carrier", orientation, 5);
	}
}
