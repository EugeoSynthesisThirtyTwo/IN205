package bataille;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import bataille.board.Board;
import bataille.board.Hit;
import bataille.ship.AbstractShip;
import bataille.ship.Orientation;

/** yooooo */
public class Player implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    transient protected Scanner sc;

    /**
     * Constructeur
     * 
     * @sc peut valoir null si le scanner n'est jamais utilisé
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships, Scanner sc) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[ships.size()]);
        this.opponentBoard = opponentBoard;
        this.sc = sc;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coordinates.
     */
    public void putShips() {
        int numero = 1;

        System.out.println("\nPlacement des navires de " + board.getName() + " :");
        /**
         * si le nombre de bateaux est différents de 5 ça va planter
         * donc j'ai changé.
         */
        for (AbstractShip s : ships)
        {
            boolean success = false;
        	System.out.println();
        	board.print();
            
            while (!success)
            {
                String msg = String.format("\nPlacer %d : %s(%d)", numero, s.getName(), s.getLength());
                System.out.println(msg);
                InputHelper.ShipInput res = InputHelper.readShipInput(sc);
                
                switch (res.orientation.charAt(0))
                {
                	case 'n':
                		s.setOrientation(Orientation.NORTH);
                		break;
                	case 's':
                		s.setOrientation(Orientation.SOUTH);
                		break;
                	case 'e':
                		s.setOrientation(Orientation.EAST);
                		break;
                	case 'w':
                		s.setOrientation(Orientation.WEST);
                		break;
                }
                
                success = board.putShip(s, res.x, res.y);
                
                if (!success)
                	System.out.println("Le positionnement n'est pas valide, il y a intersection.");
            }
            
            numero++;
        }

        board.print();
    }

    /**
     * send hit from keyboard
     * @param coords array must be of size 2. Will hold the coord of the send hit.
     * @return the status of the hit.
     */
    public Hit sendHit(int[] coords) {
        boolean valide = false;
        Hit hit = null;
        int size = opponentBoard.getSize();

        while (!valide)
        {
            System.out.print(board.getName() + " : où frapper ? ");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput(sc);
            int x = hitInput.x, y = hitInput.y;
            
            valide = (x >= 0 && x < size && y >= 0 && y < size);
            
            if (valide)
            {
                hit = opponentBoard.sendHit(x, y);
                board.setHit(hit != Hit.MISS, x, y);
                coords[0] = x;
                coords[1] = y;
            }
            else
            {
            	System.out.println("La position est en dehors des limites.");
            }
        }

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
    
    public boolean lose()
    {
    	return board.isCleared();
    }
    
    public void setScanner(Scanner sc)
    {
    	this.sc = sc;
    }
}
