package bataille;

import java.util.List;

import bataille.ship.AbstractShip;
import bataille.ship.Orientation;

/** yooooo */
public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /**
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        
        // J'ai changé la ligne suivante car la longueur 0 ça ne va pas
        this.ships = ships.toArray(new AbstractShip[0]);
        
        //this.ships = ships.toArray(new AbstractShip[ships.size()]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * MÃ©thodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;

        int numero = 1;

        /**
         * si le nombre de bateaux est différents de 5 ça va planter
         * donc j'ai changé.
         */
        for (AbstractShip s : ships)
        {
            boolean success = false;
            
            while (!success)
            {
            	System.out.println("\n" + board);
                String msg = String.format("\nPlacer %d : %s(%d)", numero, s.getName(), s.getLength());
                System.out.println(msg);
                InputHelper.ShipInput res = InputHelper.readShipInput();
                
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
                
                success = board.putShip(s, res.x, res.y + 1);
                
                if (!success)
                	System.out.println("Le positionnement n'est pas valide, il y a intersection.");
            }
            
            numero++;
        }

        board.print();
    }

    public Hit sendHit(int[] coords) {
        boolean done = true;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
