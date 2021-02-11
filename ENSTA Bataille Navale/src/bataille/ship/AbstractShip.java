package bataille.ship;

import bataille.Orientation;

public abstract class AbstractShip
{
	/*
	 * On pourrais simplement rendre les variables publiques
	 * mais l'énoncé pense qu'il est plus judicieux de faire
	 * des getters et des setters qui n'ont que le comportement
	 * par défaut... Et selon le cours les variables publiques
	 * sont absolument à éviter, pour des raisons...
	 * qui existent ?
	 */
	private char label;
	private String nom;
	private Orientation orientation;
	
	protected int size;
	
	public AbstractShip(char label, String nom, Orientation orientation, int size)
	{
		setLabel(label);
		setNom(nom);
		setOrientation(orientation);
		this.size = size;
	}
	
	public char getLabel()
	{
		return label;
	}
	
	public void setLabel(char label)
	{
		this.label = label;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public Orientation getOrientation()
	{
		return orientation;
	}

	public void setOrientation(Orientation orientation)
	{
		this.orientation = orientation;
	}
}
