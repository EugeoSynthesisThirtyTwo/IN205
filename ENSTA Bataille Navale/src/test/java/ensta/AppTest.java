/**
 * aucune des importations ne fonctionne
 * à cause de l'arborescence du projet.
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import Board;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     * Vérification visuelle de l'affichage de la grille
     */
    public void testBoard();
    {
    	Board board = new Board("Joueur 0", 15);
    	
    	System.out.println(board);
    }
}
*/