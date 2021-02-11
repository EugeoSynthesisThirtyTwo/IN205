package bataille.ship;

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
		// on ne connais pas la taille des bateaux
		// donc on choisi une taille arbitraire
		super('D', "Destroyer", orientation, 2);
	}
}
