package com.ensta.librarymanager.service;

import java.util.Iterator;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

public class LivreServiceImpl implements LivreService
{
	private static final LivreServiceImpl instance = new LivreServiceImpl();

	private LivreServiceImpl()
	{
		
	}
	
	public static LivreServiceImpl getInstance()
	{
		return instance;
	}
	
	@Override
	public List<Livre> getList() throws ServiceException
	{
		LivreDao dao = LivreDaoImpl.getInstance();
		return dao.getList();
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException
	{
		LivreDao dao = LivreDaoImpl.getInstance();
		EmpruntService service = EmpruntServiceImpl.getInstance();
		List<Livre> livres = dao.getList();
		
		Iterator<Livre> it = livres.iterator();
		
		while (it.hasNext())
			if (!service.isLivreDispo(it.next().getId()))
				it.remove();
		
		return livres;
	}

	@Override
	public Livre getById(int id) throws ServiceException
	{
		LivreDao dao = LivreDaoImpl.getInstance();
		return dao.getById(id);
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException
	{
		LivreDao dao = LivreDaoImpl.getInstance();
		return dao.create(titre, auteur, isbn);
	}

	@Override
	public void update(Livre livre) throws ServiceException
	{
		LivreDao dao = LivreDaoImpl.getInstance();
		dao.update(livre);
	}

	@Override
	public void delete(int id) throws ServiceException
	{
		LivreDao dao = LivreDaoImpl.getInstance();
		dao.delete(id);
	}

	@Override
	public int count() throws ServiceException
	{
		LivreDao dao = LivreDaoImpl.getInstance();
		return dao.count();
	}
}
