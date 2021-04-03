package ensta;

import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class DaoTest
{
	public static void testMembreDao() throws DaoException
	{
		MembreDaoImpl dao = MembreDaoImpl.getInstance();
		int id = dao.create("LeSurvivant", "Ken", "Japon", "omaewa@mou.shindeiru", "1656247477");
		Membre KenLeSurvivant = dao.getById(id);
		System.out.println("create and get by id: " + KenLeSurvivant);
		
		KenLeSurvivant.setAbonnement(Abonnement.VIP);
		dao.update(KenLeSurvivant);
		System.out.println("\nupdate: " + KenLeSurvivant);
		System.out.println("\ncount: " + dao.count());
		
		List<Membre> membres = dao.getList();
		System.out.println("\nget list:");
		
		for (Membre m : membres)
			System.out.println(m);
		
		dao.delete(id);
		System.out.println("\ndelete id = " + id);
		
		membres = dao.getList();
		System.out.println("\nget list:");
		
		for (Membre m : membres)
			System.out.println(m);
	}
	
	public static void testLivreDao() throws DaoException
	{
		LivreDaoImpl dao = LivreDaoImpl.getInstance();
		int id = dao.create("Les cigognes sont immortelles", "Alain Mabanckou", "978-2-02-130452-7");
		Livre cigognes = dao.getById(id);
		System.out.println("create and get by id: " + cigognes);
		
		cigognes.setTitre("L'univers n'est pas immortelles donc les cigognes non plus");
		dao.update(cigognes);
		System.out.println("\nupdate: " + cigognes);
		
		System.out.println("\ncount: " + dao.count());
		
		List<Livre> livres = dao.getList();
		System.out.println("\nget list:");
		
		for (Livre livre : livres)
			System.out.println(livre);
		
		dao.delete(id);
		System.out.println("\ndelete id = " + id);
		
		livres = dao.getList();
		System.out.println("\nget list:");

		for (Livre livre : livres)
			System.out.println(livre);
	}
	
	public static void testEmpruntDao() throws DaoException
	{
		EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
		System.out.println("count: " + dao.count());

		List<Emprunt> emprunts = dao.getList();
		Emprunt element = emprunts.get(1);
		System.out.println("\nget list:");
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		emprunts = dao.getListCurrent();
		System.out.println("\nget list current:");
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		System.out.println();
		emprunts = dao.getListCurrentByMembre(element.getIdMembre());
		System.out.println("\nget list current by membre id = " + element.getIdMembre() + " :");
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		System.out.println();
		emprunts = dao.getListCurrentByLivre(element.getIdLivre());
		System.out.println("\nget list current by livre id = " + element.getIdLivre() + " :");
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		System.out.println("\nget by id, id = " + element.getId() + " : " + dao.getById(element.getId()));

		// on ne va pas tester la fonction create car elle est irreversible
	}
	
	public static void main(String[] args) throws DaoException
	{
		System.out.println("-------------------------------- MembreDao Test --------------------------------\n\n");
		testMembreDao();
		System.out.println("\n\n\n-------------------------------- LivreDao Test --------------------------------\n\n");
		testLivreDao();
		System.out.println("\n\n\n-------------------------------- EmpruntDao Test --------------------------------\n\n");
		testEmpruntDao();
	}
}
