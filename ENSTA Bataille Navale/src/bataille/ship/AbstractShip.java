package bataille.ship;

public abstract class AbstractShip
{
	private char label;
	private String nom;
	private Orientation orientation;
	private int size;
	
	/**
	 * @param label le charactere qui sera affiché à l'écran
	 * @param nom le nom du bateau
	 * @param orientation l'orientation du bateau
	 * @param size la longueur du bateau
	 */
	public AbstractShip(char label, String nom, Orientation orientation, int size)
	{
		setLabel(label);
		setNom(nom);
		setOrientation(orientation);
		this.size = size;
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
	public String getNom()
	{
		return nom;
	}

	/**
	 * @param nom le nom du bateau
	 */
	public void setNom(String nom)
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
	public int getSize()
	{
		return size;
	}
	
	public String toString()
	{
		return nom + "(size = " + size + ", orientation = " + orientation + ")";
	}
}
