package bataille;
import java.util.List;

import bataille.board.Board;
import bataille.board.Hit;
import bataille.ship.AbstractShip;

public class AIPlayer extends Player
{
	private static final long serialVersionUID = 1L;
	
	/* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships, null);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }
    


    /**
     * Randomly put ships on the board
     */
    public void putShips()
    {
    	ai.putShips(ships);
    }
    
    /**
     * send hit from AI
     * @param coords array must be of size 2. Will hold the coord of the send hit.
     * @return the status of the hit.
     */
    public Hit sendHit(int[] coords)
    {
    	return ai.sendHit(coords);
    }
}
