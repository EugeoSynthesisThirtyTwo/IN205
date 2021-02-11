package bataille.ship;

import bataille.ColorUtil;

/**
 * Sert de case pour la grille de Board.
 */
public class ShipState
{
	private AbstractShip ship;
	private boolean struck;
	
	/**
	 * @param ship le bateau de la case concerné. Si la case
	 * n'est pas un bateau ship dois valoir null.
	 */
	public ShipState(AbstractShip ship)
	{
		this.ship = ship;
		struck = false;
	}
	
	/**
	 * @return le bateau concerné par la case,
	 * null si la case n'est pas un bateau
	 */
	public AbstractShip getShip()
	{
		return ship;
	}
	
	/**
	 * @return vrai si la case concerne un bateau
	 */
	public boolean isAShip()
	{
		return ship != null;
	}
	
	/**
	 * Si la case concerne un bateau :<br>
	 * Renvoie le label du bateau en rouge si le bateau est touché
	 * à cet endroit et en blanc sinon.<br>
	 * <br>
	 * Si la case ne concerne pas un bateau :<br>
	 * Renvoie "." en rouge si la case est touchée, "." en blanc sinon.
	 */
	public String toString()
	{
		if (!isAShip())
			return ".";
			
		if (struck)
			return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED);
		
		return String.valueOf(ship.getLabel());
	}
	
	/**
	 * @return vrai si la case est touchée
	 */
	public boolean isStruck()
	{
		return struck;
	}
	
	/**
	 * marque la case comme "touchée"
	 */
	public void addStrike()
	{
		if (!struck && isAShip())
			ship.addStrike();
			
		struck = true;
	}
	
	/**
	 * Si la case concerne un bateau :<br>
	 * renvoie vrai si le bateau est complètement détruit<br>
	 * <br>
	 * Si la case ne concerne pas un bateau :<br>
	 * renvoie vrai par défaut
	 */
	public boolean isSunk()
	{
		if (isAShip())
			return ship.isSunk();
		
		return true;
	}
}
