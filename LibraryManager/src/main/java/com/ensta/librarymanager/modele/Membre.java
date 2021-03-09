package com.ensta.librarymanager.modele;

public class Membre
{
	private static int globalId = 0;
	
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String telephone;
	private Abonnement abonnement;
	
	public Membre()
	{
		setId(globalId);
		globalId++;
		
		setNom("");
		setPrenom("");
		setAdresse("");
		setEmail("");
		setTelephone("");
		setAbonnement(Abonnement.BASIC);
	}

	@Override
	public String toString()
	{
		return "Membre [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email
				+ ", telephone=" + telephone + ", abonnement=" + abonnement + "]";
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public Abonnement getAbonnement()
	{
		return abonnement;
	}

	public void setAbonnement(Abonnement abonnement)
	{
		this.abonnement = abonnement;
	}
}
