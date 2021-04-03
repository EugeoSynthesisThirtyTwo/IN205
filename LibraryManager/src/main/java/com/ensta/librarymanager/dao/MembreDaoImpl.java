package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreDaoImpl implements MembreDao
{
	private static final MembreDaoImpl instance = new MembreDaoImpl();
	
	private static final String CREATE_QUERY = "INSERT INTO membre (nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, 'BASIC');";
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM membre;";
	private static final String SELECT_ONE_QUERY = "SELECT * FROM membre WHERE id=?;";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM membre;";
	private static final String UPDATE_QUERY = "UPDATE membre SET nom=?, prenom=?, adresse=?, email=?, telephone=?, abonnement=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM membre WHERE id=?;";

	private MembreDaoImpl()
	{
		
	}
	
	public static MembreDaoImpl getInstance()
	{
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws DaoException
	{
		ArrayList<Membre> membres = new ArrayList<>();
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
				Membre membre = new Membre();
				membre.setId(res.getInt("id"));
				membre.setNom(res.getString("nom"));
				membre.setPrenom(res.getString("prenom"));
				membre.setAdresse(res.getString("adresse"));
				membre.setEmail(res.getString("email"));
				membre.setTelephone(res.getString("telephone"));
				membre.setAbonnement(Abonnement.valueOf(res.getString("abonnement")));
				membres.add(membre);
			}
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération de la liste des membres", e);
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
		
		return membres;
	}

	@Override
	public Membre getById(int id) throws DaoException
	{
		Membre membre = new Membre();
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
				membre.setId(res.getInt("id"));
				membre.setNom(res.getString("nom"));
				membre.setPrenom(res.getString("prenom"));
				membre.setAdresse(res.getString("adresse"));
				membre.setEmail(res.getString("email"));
				membre.setTelephone(res.getString("telephone"));			
				membre.setAbonnement(Abonnement.valueOf(res.getString("abonnement")));
			}
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération du membre: id=" + id, e);
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
		
		return membre;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException
	{
		Membre membre = new Membre();
		membre.setNom(nom);
		membre.setPrenom(prenom);
		membre.setAdresse(adresse);
		membre.setEmail(email);
		membre.setTelephone(telephone);
		
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, membre.getNom());
			preparedStatement.setString(2, membre.getPrenom());
			preparedStatement.setString(3, membre.getAdresse());
			preparedStatement.setString(4, membre.getEmail());
			preparedStatement.setString(5, membre.getTelephone());
			
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			
			if (res.next())
				membre.setId(res.getInt(1));
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la création du membre: " + membre, e);
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
		
		return membre.getId();
	}

	@Override
	public void update(Membre membre) throws DaoException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			
			preparedStatement.setString(1, membre.getNom());
			preparedStatement.setString(2, membre.getPrenom());
			preparedStatement.setString(3, membre.getAdresse());
			preparedStatement.setString(4, membre.getEmail());
			preparedStatement.setString(5, membre.getTelephone());
			preparedStatement.setString(6, membre.getAbonnement().name());
			preparedStatement.setInt(7, membre.getId());
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la mise à jour du membre: " + membre, e);
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
	public void delete(int id) throws DaoException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la suppression du membre avec l'id: " + id, e);
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
		}
		catch (SQLException e)
		{
			throw new DaoException("Problème lors de la récupération du nombre de membres", e);
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
