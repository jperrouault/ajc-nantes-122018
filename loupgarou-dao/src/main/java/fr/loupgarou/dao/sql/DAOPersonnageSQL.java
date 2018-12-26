package fr.loupgarou.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.loupgarou.dao.IDAOPersonnage;
import fr.loupgarou.model.Personnage;
import fr.loupgarou.model.Pouvoir;

public class DAOPersonnageSQL extends DAOSQL implements IDAOPersonnage {
	public Personnage map(ResultSet result) throws SQLException {
		Personnage myPersonnage = new Personnage();
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myPersonnage.setId(result.getInt("PERS_ID"));
		myPersonnage.setLibelle(result.getString("PERS_LIBELLE"));
		
		//ON ASSOCIE LE POUVOIR AU PERSONNAGE
		myPersonnage.setPouvoir(new Pouvoir());
		myPersonnage.getPouvoir().setId(result.getInt("PERS_POUVOIR_ID"));
		
		return myPersonnage;
	}
	
	
	public List<Personnage> findAll() {
		List<Personnage> myPersonnages = new ArrayList<Personnage>();
		
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM personnage");

			while (myResult.next()) {
				//AJOUT DU PERSONNAGE DANS LA LISTE
				myPersonnages.add(this.map(myResult));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPersonnages;
	}
	
	
	public Personnage findById(int id) {
		Personnage myPersonnage = null;
		
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("SELECT * FROM personnage WHERE PERS_ID = ?");
			
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				myPersonnage = this.map(myResult);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPersonnage;
	}
	
	
	public Personnage save(Personnage entity) {
		try {
			this.connect();
			String myQuery = "";
			
			if (entity.getId() == 0) { //Ajout du personnage
				myQuery = "INSERT INTO personnage (PERS_LIBELLE, PERS_POUVOIR_ID)"
						+ " VALUES (?, ?)";
			}
			
			else { //Mise à jour du personnage
				myQuery = "UPDATE personnage SET PERS_LIBELLE = ?,"
						+ "	PERS_POUVOIR_ID = ? "
						+ " WHERE PERS_ID = ?";
			}
			
			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
			
			myStatement.setString(1, entity.getLibelle());
			myStatement.setInt(2, entity.getPouvoir().getId());
			
			if (entity.getId() > 0) {
				myStatement.setInt(3, entity.getId());
			}
			
			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	
	public void deleteById(int id) {
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("DELETE FROM personnage WHERE PERS_ID = ?");
			
			myStatement.setInt(1, id);
			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(Personnage entity) {
		this.deleteById(entity.getId());
	}
}