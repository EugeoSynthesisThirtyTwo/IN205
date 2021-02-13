package bataille;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import bataille.board.Board;
import bataille.board.Hit;
import bataille.ship.AbstractShip;
import bataille.ship.Battleship;
import bataille.ship.Carrier;
import bataille.ship.Destroyer;
import bataille.ship.Submarine;

public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private Player player2;
    
    private Scanner sc;

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        if (!load()) {
            // init attributes
            System.out.println("entre ton nom:");
            sc = new Scanner(System.in);
            String nom = sc.nextLine();

            Board b1 = new Board(nom);
            Board b2 = new Board("bot");

    		List<AbstractShip> ships1 = createDefaultShips();
            player1 = new Player(b1, b2, ships1, sc);
    		List<AbstractShip> ships2 = createDefaultShips();
    		player2 = new AIPlayer(b2, b1, ships2);

            player1.putShips();
            player2.putShips();
        }
        
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board, b2 = player2.board;
        Hit hit;
        
        do
        {
        	save();
        	
        	System.out.println();
        	hit = player1.sendHit(coords);
			System.out.println(b1.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
        	hit = player2.sendHit(coords);
			System.out.println(b2.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
			b1.print();
        }
        while (!b1.isCleared() && !b2.isCleared());

        sc.close();
        SAVE_FILE.delete();
        System.out.println((player1.lose() ? b2.getName() : b1.getName()) + " gagne");
    }


    private void save() {
        /*try {
            // TODO bonus 2 : uncomment
            //  if (!SAVE_FILE.exists()) {
            //      SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            //  }

            // TODO bonus 2 : serialize players

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private boolean load() {
        /*if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        return false;
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}
