package ensta;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

public class ServiceTest
{
	public static void testMembreService() throws ServiceException
	{
		MembreServiceImpl service = MembreServiceImpl.getInstance();
		int id = service.create("LeSurvivant", "Ken", "Japon", "omaewa@mou.shindeiru", "1656247477");
		Membre KenLeSurvivant = service.getById(id);
		System.out.println("create and get by id: " + KenLeSurvivant);
		
		KenLeSurvivant.setAbonnement(Abonnement.VIP);
		service.update(KenLeSurvivant);
		System.out.println("\nupdate: " + KenLeSurvivant);
		System.out.println("\ncount: " + service.count());
		
		List<Membre> membres = service.getList();
		System.out.println("\nget list:");
		
		for (Membre m : membres)
			System.out.println(m);
		
		service.delete(id);
		System.out.println("\ndelete id = " + id);
		
		membres = service.getList();
		System.out.println("\nget list:");
		
		for (Membre m : membres)
			System.out.println(m);
		
		membres = service.getListMembreEmpruntPossible();
		System.out.println("\nget list membre emprunt possible:");
		
		for (Membre m : membres)
			System.out.println(m);
	}
	
	public static void testLivreService() throws ServiceException
	{
		LivreServiceImpl service = LivreServiceImpl.getInstance();
		int id = service.create("Les cigognes sont immortelles", "Alain Mabanckou", "978-2-02-130452-7");
		Livre cigognes = service.getById(id);
		System.out.println("create and get by id: " + cigognes);
		
		cigognes.setTitre("L'univers n'est pas immortelles donc les cigognes non plus");
		service.update(cigognes);
		System.out.println("\nupdate: " + cigognes);
		
		System.out.println("\ncount: " + service.count());
		
		List<Livre> livres = service.getList();
		System.out.println("\nget list:");
		
		for (Livre livre : livres)
			System.out.println(livre);
		
		service.delete(id);
		System.out.println("\ndelete id = " + id);
		
		livres = service.getList();
		System.out.println("\nget list:");

		for (Livre livre : livres)
			System.out.println(livre);
		
		livres = service.getListDispo();
		System.out.println("\nget list dispo:");

		for (Livre livre : livres)
			System.out.println(livre);
	}
	
	public static void testEmpruntService() throws ServiceException
	{
		EmpruntServiceImpl service = EmpruntServiceImpl.getInstance();
		System.out.println("count: " + service.count());

		List<Emprunt> emprunts = service.getList();
		Emprunt element = emprunts.get(1);
		System.out.println("\nget list:");
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		emprunts = service.getListCurrent();
		System.out.println("\nget list current:");
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		emprunts = service.getListCurrentByMembre(element.getIdMembre());
		System.out.println("\nget list current by membre id = " + element.getIdMembre() + " :");
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		emprunts = service.getListCurrentByLivre(element.getIdLivre());
		System.out.println("\nget list current by livre id = " + element.getIdLivre() + " :");
		
		for (Emprunt e : emprunts)
			System.out.println(e);
		
		System.out.println("\nget by id, id = " + element.getId() + " : " + service.getById(element.getId()));
		
		LivreServiceImpl livreService = LivreServiceImpl.getInstance();
		Livre livre = livreService.getById(2);
		System.out.println("\nisLivreDispo(" + livre + ") : " + service.isLivreDispo(livre.getId()));

		MembreServiceImpl membreService = MembreServiceImpl.getInstance();
		Membre membre = membreService.getById(2);
		System.out.println("\nisEmpruntPossible(" + membre + ") : " + service.isEmpruntPossible(membre));
		
		// on ne va pas tester les fonctions create et returnBook car elles sont irreversibles
	}
	
	public static void main(String[] args) throws ServiceException
	{
		System.out.println("-------------------------------- MembreService Test --------------------------------\n\n");
		testMembreService();
		System.out.println("\n\n\n-------------------------------- LivreService Test --------------------------------\n\n");
		testLivreService();
		System.out.println("\n\n\n-------------------------------- EmpruntService Test --------------------------------\n\n");
		testEmpruntService();
	}
}
