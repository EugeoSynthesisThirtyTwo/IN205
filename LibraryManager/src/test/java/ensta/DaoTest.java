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
		MembreDaoImpl dao = new MembreDaoImpl();
		int id = dao.create("LeSurvivant", "Ken", "Japon", "omaewa@mou.shindeiru", "1656247477");
		Membre KenLeSurvivant = dao.getById(id);
		
		KenLeSurvivant.setAbonnement(Abonnement.VIP);
		dao.update(KenLeSurvivant);
		
		dao.count();
		
		System.out.println();
		
		List<Membre> membres = dao.getList();
		
		for (Membre m : membres)
			System.out.println(m);
		
		System.out.println();
		
		dao.delete(id);
	}
	
	public static void testLivreDao() throws DaoException
	{
		LivreDaoImpl dao = new LivreDaoImpl();
		int id = dao.create("Les cigognes sont immortelles", "Alain Mabanckou", "978-2-02-130452-7");
		Livre cigognes = dao.getById(id);
		
		cigognes.setTitre("L'univers n'est pas immortelles donc les cigognes non plus");
		dao.update(cigognes);
		
		dao.count();
		
		System.out.println();
		
		List<Livre> livres = dao.getList();
		
		for (Livre livre : livres)
			System.out.println(livre);
		
		System.out.println();
		
		dao.delete(id);
	}
	
	public static void testEmpruntDao() throws DaoException
	{
		EmpruntDaoImpl dao = new EmpruntDaoImpl();
		dao.count();

		System.out.println();
		List<Emprunt> emprunts = dao.getList();
		
		Emprunt element = emprunts.get(1);
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		emprunts.clear();
		System.out.println();
		emprunts = dao.getListCurrent();
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		emprunts.clear();
		System.out.println();
		emprunts = dao.getListCurrentByMembre(element.getIdMembre());
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		emprunts.clear();
		System.out.println();
		emprunts = dao.getListCurrentByLivre(element.getIdLivre());
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		emprunts.clear();
		System.out.println();
		dao.getById(element.getId());

		// on ne va pas tester la fonction create car elle est irreversible
	}
	
	public static void main(String[] args) throws DaoException
	{
		//testMembreDao();
		//testLivreDao();
		testEmpruntDao();
	}
}
