package bataille.ship;

import bataille.ship.ships.Orientation;

public abstract class AbstractShip
{
	private char label;
	private String nom;
	private Orientation orientation;
	private int length;
	private int strikeCount;
	
	/**
	 * @param label le charactere qui sera affiché à l'écran
	 * @param nom le nom du bateau
	 * @param orientation l'orientation du bateau
	 * @param length la longueur du bateau
	 */
	public AbstractShip(char label, String nom, Orientation orientation, int length)
	{
		setLabel(label);
		setName(nom);
		setOrientation(orientation);
		this.length = length;
		strikeCount = 0;
	}
	
	/**
	 * incrémente le compteur de coups
	 */
	public void addStrike() throws RuntimeException
	{
		if (strikeCount == length)
			throw new RuntimeException("addStrike a été appellée plus de fois que la longueur du bateau.");
		
		strikeCount++;
	}
	
	/**
	 * @return un booléen indiquant si le bateau est coulé
	 */
	public boolean isSunk()
	{
		return strikeCount == length;
	}
	
	/**
	 * @return le charactere qui sera affiché à l'écran
	 */
	public char getLabel()
	{
		return label;
	}
	
	/**
	 * @param label le charactere qui sera affiché à l'écran
	 */
	public void setLabel(char label)
	{
		this.label = label;
	}

	/**
	 * @return le nom du bateau
	 */
	public String getName()
	{
		return nom;
	}

	/**
	 * @param nom le nom du bateau
	 */
	public void setName(String nom)
	{
		this.nom = nom;
	}

	/**
	 * @return l'orientation du bateau
	 */
	public Orientation getOrientation()
	{
		return orientation;
	}

	/**
	 * @param orientation l'orientation du bateau
	 */
	public void setOrientation(Orientation orientation)
	{
		this.orientation = orientation;
	}

	/**
	 * @return la longueur du bateau
	 */
	public int getLength()
	{
		return length;
	}
	
	public String toString()
	{
		return nom + "(length = " + length + ", orientation = " + orientation + ")";
	}
}
