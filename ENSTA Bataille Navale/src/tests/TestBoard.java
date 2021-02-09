package tests;

import bataille.Board;

public class TestBoard
{	
	/**
	 * Vérification visuelle de l'affichage de la grille
	 */
	public static void main(String[] args)
	{
		Board board = new Board("Joueur 0", 15);
		
		System.out.println(board);
	}
}

