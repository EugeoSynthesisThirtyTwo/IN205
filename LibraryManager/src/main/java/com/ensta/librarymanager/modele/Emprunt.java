package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt
{
	private int id;
	private int idMembre;
	private int idLivre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	
	public Emprunt()
	{
		setId(-1);
		setIdMembre(-1);
		setIdLivre(-1);
		setDateEmprunt(null);
		setDateRetour(null);
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

	public LocalDate getDateEmprunt()
	{
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt)
	{
		this.dateEmprunt = dateEmprunt;
	}

	public LocalDate getDateRetour()
	{
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour)
	{
		this.dateRetour = dateRetour;
	}
}
