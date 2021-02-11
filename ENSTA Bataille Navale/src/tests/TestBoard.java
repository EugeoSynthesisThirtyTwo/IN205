package tests;

import bataille.Board;
import bataille.ship.AbstractShip;
import bataille.ship.Battleship;
import bataille.ship.Carrier;
import bataille.ship.Destroyer;
import bataille.ship.Orientation;
import bataille.ship.Submarine;

public class TestBoard
{
	/**
	 * Vérification visuelle de l'affichage de la grille
	 */
	public static void main(String[] args)
	{
		Board board = new Board("Joueur 0", 15);
		
		AbstractShip ship;
		boolean success;
		
		ship = new Battleship(Orientation.EAST);
		success = board.putShip(ship, 0, 0);
		System.out.println("putShip(" + ship + ", 0, 0) = " + (success ? "Succes" : "Failure"));

		ship = new Carrier(Orientation.WEST);
		success = board.putShip(ship, 6, 6);
		System.out.println("putShip(" + ship + ", 6, 6) = " + (success ? "Succes" : "Failure"));
		
		ship = new Destroyer(Orientation.NORTH);
		success = board.putShip(ship, 7, 7);
		System.out.println("putShip(" + ship + ", 7, 7) = " + (success ? "Succes" : "Failure"));
		
		ship = new Submarine(Orientation.SOUTH);
		success = board.putShip(ship, 9, 1);
		System.out.println("putShip(" + ship + ", 9, 1) = " + (success ? "Succes" : "Failure"));
		
		
		// devrait échouer car il y a une intersection avec le Submarine
		ship = new Carrier(Orientation.SOUTH);
		success = board.putShip(ship, 6, 2);
		System.out.println("putShip(" + ship + ", 6, 2) = " + (success ? "Succes" : "Failure"));
		
		// devrait échouer car il est en dehors des limites
		ship = new Carrier(Orientation.WEST);
		success = board.putShip(ship, 2, 9);
		System.out.println("putShip(" + ship + ", 2, 9) = " + (success ? "Succes" : "Failure"));
		
		System.out.println("\n" + board);
	}
}

