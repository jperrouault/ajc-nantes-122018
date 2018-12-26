package fr.loupgarou.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.loupgarou.dao.IDAOUtilisateur;
import fr.loupgarou.exception.AccountLockedException;
import fr.loupgarou.exception.NonUniqueUsernameException;
import fr.loupgarou.exception.UsernameOrPasswordNotFoundException;
import fr.loupgarou.model.Administrateur;
import fr.loupgarou.model.Joueur;
import fr.loupgarou.model.TypeUtilisateur;
import fr.loupgarou.model.Utilisateur;

public class DAOUtilisateurSQL extends DAOSQL implements IDAOUtilisateur {
	@Override
	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur save(Utilisateur entity) {
		try {
			this.connect();
			String myQuery = "";
			
			if (entity.getId() == 0) { //Ajout de l'utilisateur
				myQuery = "INSERT INTO utilisateur (UTI_NOM, UTI_PRENOM, UTI_USERNAME, UTI_PASSWORD, UTI_TYPE, UTI_PSEUDO)"
						+ " VALUES (?, ?, ?, ?, ?, ?)";
			}
			
			else { //Mise à jour de l'utilisateur
				myQuery = "UPDATE utilisateur SET UTI_NOM = ?,"
						+ "	UTI_PRENOM = ?, "
						+ " UTI_USERNAME = ?, "
						+ " UTI_PASSWORD = ?, "
						+ " UTI_TYPE = ?, "
						+ " UTI_PSEUDO = ? "
						+ " WHERE UTI_ID = ?";
			}
			
			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);

			myStatement.setString(1, entity.getNom());
			myStatement.setString(2, entity.getPrenom());
			myStatement.setString(3, entity.getUsername());
			myStatement.setString(4, entity.getPassword());
			myStatement.setInt(5, entity.getType().ordinal());
			
			if (entity.getType() == TypeUtilisateur.JOUEUR) {
				myStatement.setString(6, ((Joueur)entity).getPseudo());
			}
			
			if (entity.getId() > 0) {
				myStatement.setInt(7, entity.getId());
			}
			
			myStatement.execute();
		}
		
		catch (SQLIntegrityConstraintViolationException e) {
			throw new NonUniqueUsernameException();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	@Override
	public void delete(Utilisateur entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Utilisateur auth(String username, String password) throws UsernameOrPasswordNotFoundException, AccountLockedException {
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("SELECT * FROM utilisateur WHERE UTI_USERNAME = ? AND UTI_PASSWORD = ?");

			myStatement.setString(1, username);
			myStatement.setString(2, password);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				Utilisateur myUtilisateur = this.map(myResult);
				
				if (myUtilisateur.getType() == TypeUtilisateur.JOUEUR) {
					if (((Joueur)myUtilisateur).isBanni()) {
						throw new AccountLockedException();
					}
				}
				
				return myUtilisateur;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		throw new UsernameOrPasswordNotFoundException();
	}
	
	
	
	
	public Utilisateur map(ResultSet result) throws SQLException {
		Utilisateur myUtilisateur = null;
		
		if (result.getInt("UTI_TYPE") == TypeUtilisateur.JOUEUR.ordinal()) {
			myUtilisateur = new Joueur();
			((Joueur)myUtilisateur).setPseudo(result.getString("UTI_PSEUDO"));
			((Joueur)myUtilisateur).setBanni(result.getBoolean("UTI_EST_BANNI"));
		}
		
		else {
			myUtilisateur = new Administrateur();
		}
		
		try {
			//STRING VERS DATE
			String laDate = result.getString("UTI_DATE");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d H:m:s");
			
			Date d = formatter.parse(laDate);
			System.out.println(d);
			
			//DATE VERS STRING
			System.out.println(formatter.format(d));
		}
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myUtilisateur.setId(result.getInt("UTI_ID"));
		myUtilisateur.setNom(result.getString("UTI_NOM"));
		myUtilisateur.setPrenom(result.getString("UTI_PRENOM"));
		myUtilisateur.setUsername(result.getString("UTI_USERNAME"));
		
		return myUtilisateur;
	}
}