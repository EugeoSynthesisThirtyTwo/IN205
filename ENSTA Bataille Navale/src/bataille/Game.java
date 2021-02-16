package bataille;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private boolean player1Played;

    public Game() {}

    /**
     * tente de charger la sauvegarde, et sinon,
     * demande a l'utilisateur de générer la partie.
     * @return this
     */
    public Game init()
    {
        sc = new Scanner(System.in);
        player1Played = false;
        
        boolean loaded = false;
        
		loaded = load();
        
        if (!loaded)
        {
            // init attributes
        	Board b1, b2;
            
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
        else
        {
			player1.setScanner(sc);
			player2.setScanner(sc);
			
			if (!(player1 instanceof AIPlayer))
			{
				System.out.println("Tableau de " + player1.board.getName() + " :");
				player1.board.print();
			}
			
			if (!(player2 instanceof AIPlayer))
			{
				System.out.println("\nTableau de " + player2.board.getName() + " :");
				player2.board.print();
			}
        }
        
        return this;
    }

    /**
     * Lance la partie.
     * Cette fonction se termine à la fin de la partie.
     */
    public void run()
    {
        int[] coords = new int[2];
        Board b1 = player1.board, b2 = player2.board;
        Hit hit;
        
        do
        {
        	save();
        	
        	switch (nbJoueurs)
        	{
        		case 0:
        			if (!player1Played)
        			{
                    	System.out.println();
                    	hit = player1.sendHit(coords);
            			System.out.println(b1.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
            			b1.print();
            			player1Played = true;
                    	save();
            			sc.nextLine();
        			}
        			
                	System.out.println();
                	hit = player2.sendHit(coords);
        			System.out.println(b2.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
        			b2.print();
        			player1Played = false;
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
        			if (!player1Played)
        			{
                    	System.out.println();
                    	hit = player1.sendHit(coords);
            			System.out.println(b1.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
            			b1.print();
            			player1Played = true;
                    	save();
        			}
        			
                	System.out.println();
                	hit = player2.sendHit(coords);
        			System.out.println(b2.getName() + " : " + String.valueOf((char) ('A' + coords[0])) + (coords[1] + 1) + " => " + hit);
        			b2.print();
        			player1Played = false;
        			break;
        	}
        }
        while (!b1.isCleared() && !b2.isCleared());

        sc.close();
        SAVE_FILE.delete();
        System.out.println("\n" + (player1.lose() ? b2.getName() : b1.getName()) + " gagne");
    }

    /**
     * @return true if saved successfully into SAVE_FILE
     */
    boolean save()
    {
    	try
    	{
            if (!SAVE_FILE.exists())
            	SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(SAVE_FILE)));
            oos.writeInt(nbJoueurs);
            oos.writeObject(player1);
            oos.writeObject(player2);
            oos.writeBoolean(player1Played);
            oos.close();
            
            return true;
    	}
    	catch (IOException e)
    	{
        	return false;
    	}
    }

    /**
     * @return true if loaded successfully from SAVE_FILE
     */
    private boolean load()
    {
        if (SAVE_FILE.exists())
        {
        	try
        	{
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE)));
                nbJoueurs = ois.readInt();
                player1 = (Player) ois.readObject();
                player2 = (Player) ois.readObject();
                player1Played = ois.readBoolean();
                ois.close();
                
            	return true;
        	}
        	catch (IOException | ClassNotFoundException e)
        	{
            	return false;
        	}
        }
        
        return false;
    }

    /**
     * @return {Destroyer, Submarine, Submarine, BattleShip, Carrier}
     */
    private static List<AbstractShip> createDefaultShips()
    {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    public static void main(String args[])
    {
        new Game().init().run();
    }
}
