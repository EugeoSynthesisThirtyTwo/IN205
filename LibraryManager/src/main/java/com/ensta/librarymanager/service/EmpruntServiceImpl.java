package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntServiceImpl implements EmpruntService
{
	private static final EmpruntServiceImpl instance = new EmpruntServiceImpl();

	private EmpruntServiceImpl()
	{
		
	}
	
	public static EmpruntServiceImpl getInstance()
	{
		return instance;
	}

	@Override
	public List<Emprunt> getList() throws ServiceException
	{
		EmpruntDao dao = EmpruntDaoImpl.getInstance();
		return dao.getList();
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException
	{
		EmpruntDao dao = EmpruntDaoImpl.getInstance();
		return dao.getListCurrent();
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException
	{
		EmpruntDao dao = EmpruntDaoImpl.getInstance();
		return dao.getListCurrentByMembre(idMembre);
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException
	{
		EmpruntDao dao = EmpruntDaoImpl.getInstance();
		return dao.getListCurrentByLivre(idLivre);
	}

	@Override
	public Emprunt getById(int id) throws ServiceException
	{
		EmpruntDao dao = EmpruntDaoImpl.getInstance();
		return dao.getById(id);
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException
	{
		EmpruntDao dao = EmpruntDaoImpl.getInstance();
		dao.create(idMembre, idLivre, dateEmprunt);
	}

	@Override
	public void returnBook(int id) throws ServiceException
	{
		EmpruntDao dao = EmpruntDaoImpl.getInstance();
		Emprunt emprunt = dao.getById(id);
		emprunt.setDateRetour(LocalDate.now());
		dao.update(emprunt);
	}

	@Override
	public int count() throws ServiceException
	{
		EmpruntDao dao = EmpruntDaoImpl.getInstance();
		return dao.count();
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException
	{
		List<Emprunt> emprunts = getListCurrentByLivre(idLivre);
		
		for (Emprunt emprunt : emprunts)
			if (emprunt.getDateRetour() == null)
				return false;
		
		return true;
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException
	{
		return getListCurrentByMembre(membre.getId()).size() < membre.getAbonnement().NB_EMPRUNTS;
	}
}
