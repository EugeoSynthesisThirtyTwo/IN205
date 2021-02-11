package bataille.ship;

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
		// on ne connais pas la taille des bateaux
		// donc on choisi une taille arbitraire
		super('S', "Submarine", orientation, 3);
	}
}
