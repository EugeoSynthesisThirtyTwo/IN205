package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDaoImpl implements EmpruntDao
{
	private static final String CREATE_QUERY = "INSERT INTO emprunt (idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM emprunt;";
	private static final String SELECT_ONE_QUERY = "SELECT * FROM emprunt WHERE id=?;";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM emprunt;";
	private static final String SELECT_CURRENT = "SELECT * FROM emprunt WHERE dateRetour IS NULL;";
	private static final String SELECT_BY_MEMBRE = "SELECT * FROM emprunt WHERE idMembre=?;";
	private static final String SELECT_BY_LIVRE = "SELECT * FROM emprunt WHERE idLivre=?;";
	private static final String UPDATE_QUERY = "UPDATE emprunt SET idMembre=?, idLivre=?, dateEmprunt=?, dateRetour=? WHERE id=?;";
	
	@Override
	public List<Emprunt> getList() throws DaoException
	{
		ArrayList<Emprunt> emprunts = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			res = preparedStatement.executeQuery();
			
			while (res.next())
			{
				Emprunt emprunt = new Emprunt();
				emprunt.setId(res.getInt("id"));
				emprunt.setIdMembre(res.getInt("idMembre"));
				emprunt.setIdLivre(res.getInt("idLivre"));
				
				Date date = res.getDate("dateEmprunt");
				
				if (date != null)
					emprunt.setDateEmprunt(date.toLocalDate());
				
				date = res.getDate("dateRetour");
				
				if (date != null)
					emprunt.setDateRetour(date.toLocalDate());
				
				emprunts.add(emprunt);
			}
			
			System.out.println("GET LIST: " + emprunts);
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
		}
		finally
		{
			try
			{
				res.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				preparedStatement.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return emprunts;
	}
	
	public List<Emprunt> getListCurrent() throws DaoException
	{
		ArrayList<Emprunt> emprunts = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CURRENT);
			res = preparedStatement.executeQuery();
			
			while (res.next())
			{
				Emprunt emprunt = new Emprunt();
				emprunt.setId(res.getInt("id"));
				emprunt.setIdMembre(res.getInt("idMembre"));
				emprunt.setIdLivre(res.getInt("idLivre"));
				
				Date date = res.getDate("dateEmprunt");
				
				if (date != null)
					emprunt.setDateEmprunt(date.toLocalDate());
				
				date = res.getDate("dateRetour");
				
				if (date != null)
					emprunt.setDateRetour(date.toLocalDate());
				
				emprunts.add(emprunt);
			}
			
			System.out.println("GET LIST CURRENT: " + emprunts);
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
		}
		finally
		{
			try
			{
				res.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				preparedStatement.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return emprunts;
	}
	
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException
	{
		ArrayList<Emprunt> emprunts = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_BY_MEMBRE);
			preparedStatement.setInt(1, idMembre);
			res = preparedStatement.executeQuery();
			
			while (res.next())
			{
				Emprunt emprunt = new Emprunt();
				emprunt.setId(res.getInt("id"));
				emprunt.setIdMembre(res.getInt("idMembre"));
				emprunt.setIdLivre(res.getInt("idLivre"));
				
				Date date = res.getDate("dateEmprunt");
				
				if (date != null)
					emprunt.setDateEmprunt(date.toLocalDate());
				
				date = res.getDate("dateRetour");
				
				if (date != null)
					emprunt.setDateRetour(date.toLocalDate());
				
				emprunts.add(emprunt);
			}
			
			System.out.println("GET LIST BY MEMBRE: " + emprunts);
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
		}
		finally
		{
			try
			{
				res.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				preparedStatement.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return emprunts;
	}
	
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException
	{
		ArrayList<Emprunt> emprunts = new ArrayList<>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_BY_LIVRE);
			preparedStatement.setInt(1, idLivre);
			res = preparedStatement.executeQuery();
			
			while (res.next())
			{
				Emprunt emprunt = new Emprunt();
				emprunt.setId(res.getInt("id"));
				emprunt.setIdMembre(res.getInt("idMembre"));
				emprunt.setIdLivre(res.getInt("idLivre"));
				
				Date date = res.getDate("dateEmprunt");
				
				if (date != null)
					emprunt.setDateEmprunt(date.toLocalDate());
				
				date = res.getDate("dateRetour");
				
				if (date != null)
					emprunt.setDateRetour(date.toLocalDate());
				
				emprunts.add(emprunt);
			}
			
			System.out.println("GET LIST BY LIVRE: " + emprunts);
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
		}
		finally
		{
			try
			{
				res.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				preparedStatement.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return emprunts;
	}

	@Override
	public Emprunt getById(int id) throws DaoException
	{
		Emprunt emprunt = new Emprunt();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
			preparedStatement.setInt(1, id);
			res = preparedStatement.executeQuery();
			
			if (res.next())
			{
				emprunt.setId(res.getInt("id"));
				emprunt.setIdMembre(res.getInt("idMembre"));
				emprunt.setIdLivre(res.getInt("idLivre"));
				
				Date date = res.getDate("dateEmprunt");
				
				if (date != null)
					emprunt.setDateEmprunt(date.toLocalDate());
				
				date = res.getDate("dateRetour");
				
				if (date != null)
					emprunt.setDateRetour(date.toLocalDate());
			}
			
			System.out.println("GET: " + emprunt);
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération de l'emprunt: id=" + id, e);
		}
		finally
		{
			try
			{
				res.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				preparedStatement.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return emprunt;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException
	{
		Emprunt emprunt = new Emprunt();
		emprunt.setIdMembre(idMembre);
		emprunt.setIdLivre(idLivre);
		emprunt.setDateEmprunt(dateEmprunt);
		
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, emprunt.getIdMembre());
			preparedStatement.setInt(2, emprunt.getIdLivre());
			
			LocalDate date = emprunt.getDateEmprunt();
			
			if (date != null)
				preparedStatement.setDate(3, Date.valueOf(date));
			else
				preparedStatement.setDate(3, null);
			
			date = emprunt.getDateRetour();

			if (date != null)
				preparedStatement.setDate(4, Date.valueOf(date));
			else
				preparedStatement.setDate(4, null);
			
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			
			if (res.next())
				emprunt.setId(res.getInt(1));

			System.out.println("CREATE: " + emprunt);
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la création de l'emprunt: " + emprunt, e);
		}
		finally
		{
			try
			{
				res.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				preparedStatement.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			
			preparedStatement.setInt(1, emprunt.getIdMembre());
			preparedStatement.setInt(2, emprunt.getIdLivre());
			
			LocalDate date = emprunt.getDateEmprunt();
			
			if (date != null)
				preparedStatement.setDate(3, Date.valueOf(date));
			else
				preparedStatement.setDate(3, null);
			
			date = emprunt.getDateRetour();

			if (date != null)
				preparedStatement.setDate(4, Date.valueOf(date));
			else
				preparedStatement.setDate(4, null);
			
			preparedStatement.setInt(5, emprunt.getId());
			preparedStatement.executeUpdate();

			System.out.println("UPDATE: " + emprunt);
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la mise à jour de l'emprunt: " + emprunt, e);
		}
		finally
		{
			try
			{
				preparedStatement.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public int count() throws DaoException
	{
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = -1;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_COUNT);
			res = preparedStatement.executeQuery();
			
			if (res.next())
			{
				count = res.getInt(1);		
			}
			
			System.out.println("COUNT(emprunt): " + count);
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération du nombre d'emprunts", e);
		}
		finally
		{
			try
			{
				res.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				preparedStatement.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return count;
	}
}
