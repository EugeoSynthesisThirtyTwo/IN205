package bataille.board;

import java.io.Serializable;

import bataille.ColorUtil;
import bataille.ship.AbstractShip;

/**
 * Sert de case pour la grille de Board.
 */
public class ShipState implements Serializable
{
	private static final long serialVersionUID = 1L;
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
	 * Renvoie "x" en blanc si la case est touchée, "." en blanc sinon.
	 */
	public String toString()
	{
		if (isAShip())
		{
			if (struck)
				return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED);
			
			return String.valueOf(ship.getLabel());
		}

		if (struck)
			return "x";
		
		return ".";
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
	 * renvoie false par défaut
	 */
	public boolean isSunk()
	{
		if (isAShip())
			return ship.isSunk();
		
		return false;
	}
}
