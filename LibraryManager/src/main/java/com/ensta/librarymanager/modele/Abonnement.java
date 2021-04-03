package com.ensta.librarymanager.modele;

public enum Abonnement
{
	BASIC(2),
	PREMIUM(5),
	VIP(20);
	
	public final int NB_EMPRUNTS;
	
	private Abonnement(int nbEmprunts)
	{
		NB_EMPRUNTS = nbEmprunts;
	}
}
