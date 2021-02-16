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
    private int nbJoueurs;

    public Game() {}

    public Game init() {
        if (!load()) {
            // init attributes
        	Board b1, b2;
            sc = new Scanner(System.in);
            
            do
            {
            	System.out.print("Nombre de joueurs humains : ");
                nbJoueurs = sc.nextInt();
                sc.nextLine();
                
                if (nbJoueurs < 0 || nbJoueurs > 2)
                	System.out.println("Le nombre de joueurs dois être compris entre 0 inclu et 2 inclu.");
            }
            while (nbJoueurs < 0 || nbJoueurs > 2);
            
            String nom1 = null, nom2 = null;
        	
        	switch (nbJoueurs)
        	{
        		case 0:
        			nom1 = "bot 1";
                    nom2 = "bot 2";
        			break;
        		case 1:
                    System.out.println("\npseudo : ");
                    nom1 = sc.nextLine();
                    nom2 = "bot";
        			break;
        		case 2:
                    System.out.println("\npseudo du joueur 1 : ");
                    nom1 = sc.nextLine();
                    System.out.println("\npseudo du joueur 2 : ");
                    nom2 = sc.nextLine();
        			break;
        	}

        	b1 = new Board(nom1);
        	b2 = new Board(nom2);
    		List<AbstractShip> ships1 = createDefaultShips();
    		List<AbstractShip> ships2 = createDefaultShips();

        	switch (nbJoueurs)
        	{
        		case 0:
                    player1 = new AIPlayer(b1, b2, ships1);
            		player2 = new AIPlayer(b2, b1, ships2);
        			break;
        		case 1:
                    player1 = new Player(b1, b2, ships1, sc);
            		player2 = new AIPlayer(b2, b1, ships2);
        			break;
        		case 2:
                    player1 = new Player(b1, b2, ships1, sc);
            		player2 = new Player(b2, b1, ships2, sc);
        			break;
        	}
        	
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

        	switch (nbJoueurs)
        	{
        		case 0:
                	System.out.println();
                	hit = player1.sendHit(coords);
        			System.out.println(b1.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
        			b1.print();
        			sc.nextLine();
        			
                	System.out.println();
                	hit = player2.sendHit(coords);
        			System.out.println(b2.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
        			b2.print();
        			sc.nextLine();
        			break;
        			
        		case 1:
                	System.out.println();
                	hit = player1.sendHit(coords);
        			System.out.println(b1.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
                	hit = player2.sendHit(coords);
        			System.out.println(b2.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
        			b1.print();
        			break;
        			
        		case 2:
                	System.out.println();
                	hit = player1.sendHit(coords);
        			System.out.println(b1.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
        			b1.print();
        			
                	System.out.println();
                	hit = player2.sendHit(coords);
        			System.out.println(b2.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
        			b2.print();
        			break;
        	}
        }
        while (!b1.isCleared() && !b2.isCleared());

        sc.close();
        SAVE_FILE.delete();
        System.out.println("\n" + (player1.lose() ? b2.getName() : b1.getName()) + " gagne");
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

    public static void main(String args[])
    {
        new Game().init().run();
    }
}
