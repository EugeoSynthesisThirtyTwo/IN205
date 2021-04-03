package com.ensta.librarymanager.service;

import java.util.Iterator;
import java.util.List;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;

public class MembreServiceImpl implements MembreService
{
	private static final MembreServiceImpl instance = new MembreServiceImpl();

	private MembreServiceImpl()
	{
		
	}
	
	public static MembreServiceImpl getInstance()
	{
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws ServiceException
	{
		MembreDao dao = MembreDaoImpl.getInstance();
		return dao.getList();
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException
	{
		MembreDao dao = MembreDaoImpl.getInstance();
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		List<Membre> membres = dao.getList();
		Iterator<Membre> it = membres.iterator();
		
		while (it.hasNext())
			if (!empruntService.isEmpruntPossible(it.next()))
				it.remove();
		
		return membres;
	}

	@Override
	public Membre getById(int id) throws ServiceException
	{
		MembreDao dao = MembreDaoImpl.getInstance();
		return dao.getById(id);
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException
	{
		MembreDao dao = MembreDaoImpl.getInstance();
		return dao.create(nom, prenom, adresse, email, telephone);
	}

	@Override
	public void update(Membre membre) throws ServiceException
	{
		MembreDao dao = MembreDaoImpl.getInstance();
		dao.update(membre);
	}

	@Override
	public void delete(int id) throws ServiceException
	{
		MembreDao dao = MembreDaoImpl.getInstance();
		dao.delete(id);
	}

	@Override
	public int count() throws ServiceException
	{
		MembreDao dao = MembreDaoImpl.getInstance();
		return dao.count();
	}
}
