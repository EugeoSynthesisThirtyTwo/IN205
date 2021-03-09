package com.ensta.librarymanager.modele;

public class Livre
{
	private static int globalId = 0;
	
	private int id;
	private String titre;
	private String auteur;
	private String isbn;
	
	public Livre()
	{
		setId(globalId);
		globalId++;
		
		setTitre("");
		setAuteur("");
		setIsbn("");
	}

	@Override
	public String toString()
	{
		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", isbn=" + isbn + "]";
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitre()
	{
		return titre;
	}

	public void setTitre(String titre)
	{
		this.titre = titre;
	}

	public String getAuteur()
	{
		return auteur;
	}

	public void setAuteur(String auteur)
	{
		this.auteur = auteur;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
}
