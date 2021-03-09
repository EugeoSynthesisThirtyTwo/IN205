package ensta;

import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class ModeleTest
{
	/*
	 * Verification de l'incrementation de l'id.
	 * Le reste n'est que getter et setter.
	 */
	public static void main(String[] args)
	{
		Livre l1 = new Livre();
		System.out.println(l1);
		
		Livre l2 = new Livre();
		System.out.println(l2);
		
		Membre m1 = new Membre();
		System.out.println(m1);
		
		Membre m2 = new Membre();
		System.out.println(m2);
		
		Emprunt e1 = new Emprunt();
		System.out.println(e1);
		
		Emprunt e2 = new Emprunt();
		System.out.println(e2);
	}
}
