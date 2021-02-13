package tests;

import bataille.BattleShipsAI;
import bataille.board.Board;
import bataille.board.Hit;
import bataille.ship.AbstractShip;
import bataille.ship.Battleship;
import bataille.ship.Carrier;
import bataille.ship.Destroyer;
import bataille.ship.Submarine;

public class TestGame
{
	public static void main(String[] args)
	{
		Board board = new Board("Board");
		
		AbstractShip ships[] = {
				new Destroyer(),
				new Submarine(),
				new Submarine(),
				new Battleship(),
				new Carrier(),
		};
		
		BattleShipsAI AI = new BattleShipsAI(board, board);
		AI.putShips(ships);
		int coords[] = new int[2];
		Hit hit;
		
		board.print();
		int round = 1;
		
		while (!board.isCleared())
		{
			hit = AI.sendHit(coords);
			
			System.out.println("\nround " + round);
			System.out.println(String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " : " + hit);
			board.print();
			round++;
			
			try
			{
				Thread.sleep(300);
			}
			catch (InterruptedException e)
			{
				// pas grave si on n'attend pas
			}
		}
	}
}
