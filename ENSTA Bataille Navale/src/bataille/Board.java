package bataille;

public class Board
{
	private String name;
	private char[][] navires;
	private boolean[][] frappes;
	public final int size;
	
	/**
	 * créé une grille de taille size x size
	 * @param name : le nom de la grille
	 * @param size : la taille de la grille
	 */
	public Board(String name, int size)
	{
		this.name = name;
		this.navires = new char[size][size];
		this.frappes = new boolean[size][size];
		this.size = size;
	}
	
	/**
	 * @param name : le nom de la grille
	 * créé une grille de taille 10x10
	 */
	public Board(String name)
	{
		this(name, 10);
	}
	
	/**
	 * affiche la grille des navires et la grille des frappes côtes à côtes
	 */
	public String toString()
	{
		String result = "";
		
		result += "Navires :";
		
		for (int i = 0; i < 2 * size - 3; i++)
			result += " ";
		
		result += "Frappes :\n  ";
		
		for (int i = 0; i < size; i++)
			result += " " + (char) ('A' + i);

		result += "      ";
		
		for (int i = 0; i < size; i++)
			result += " " + (char) ('A' + i);
		
		result += "\n";
		
		for (int line = 0; line < size; line++)
		{
			if (line < 10)
				result += "0" + line;
			else
				result += line;

			for (int i = 0; i < size; i++)
				result += " .";
			
			result += "    ";

			if (line < 10)
				result += "0" + line;
			else
				result += line;

			for (int i = 0; i < size; i++)
				if (frappes[line][i])
					result += " x";
				else
					result += " .";
			
			result += "\n";
		}
		
		return result;
	}
}
