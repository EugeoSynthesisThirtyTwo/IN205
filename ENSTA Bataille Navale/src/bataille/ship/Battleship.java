package bataille.ship;

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
		// on ne connais pas la taille des bateaux
		// donc on choisi une taille arbitraire
		super('B', "Battleship", orientation, 4);
	}
}
