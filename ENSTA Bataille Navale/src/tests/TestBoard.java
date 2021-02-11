package tests;

import java.util.ArrayList;

import bataille.Board;
import bataille.Player;
import bataille.ship.AbstractShip;
import bataille.ship.Battleship;
import bataille.ship.Carrier;
import bataille.ship.Destroyer;
import bataille.ship.Submarine;

public class TestBoard
{
	/**
	 * Vérification visuelle de l'affichage de la grille
	 */
	public static void main(String[] args)
	{
		ArrayList<AbstractShip> ships = new ArrayList<>();
		ships.add(new Destroyer());
		ships.add(new Submarine());
		ships.add(new Submarine());
		ships.add(new Battleship());
		ships.add(new Carrier());

		Board joueur1 = new Board("Joueur 1", 10);
		Board joueur2 = new Board("Joueur 2", 10);
		Player player = new Player(joueur1, joueur2, ships);
		
		player.putShips();
	}
}

