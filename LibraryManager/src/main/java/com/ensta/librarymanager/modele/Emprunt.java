package com.ensta.librarymanager.modele;

public class Emprunt
{
	private static int globalId = 0;
	
	private int id;
	private int idMembre;
	private int idLivre;
	private String dateEmprunt;
	private String dateRetour;
	
	public Emprunt()
	{
		setId(globalId);
		globalId++;
		
		setIdMembre(-1);
		setIdLivre(-1);
		setDateEmprunt("");
		setDateRetour("");
	}
	
	@Override
	public String toString()
	{
		return "Emprunt [id=" + id + ", idMembre=" + idMembre + ", idLivre=" + idLivre + ", dateEmprunt=" + dateEmprunt
				+ ", dateRetour=" + dateRetour + "]";
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdMembre()
	{
		return idMembre;
	}

	public void setIdMembre(int idMembre)
	{
		this.idMembre = idMembre;
	}

	public int getIdLivre()
	{
		return idLivre;
	}

	public void setIdLivre(int idLivre)
	{
		this.idLivre = idLivre;
	}

	public String getDateEmprunt()
	{
		return dateEmprunt;
	}

	public void setDateEmprunt(String dateEmprunt)
	{
		this.dateEmprunt = dateEmprunt;
	}

	public String getDateRetour()
	{
		return dateRetour;
	}

	public void setDateRetour(String dateRetour)
	{
		this.dateRetour = dateRetour;
	}
}
