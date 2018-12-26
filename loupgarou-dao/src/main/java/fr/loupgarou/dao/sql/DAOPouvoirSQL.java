package fr.loupgarou.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.loupgarou.dao.IDAOPouvoir;
import fr.loupgarou.model.Pouvoir;

public class DAOPouvoirSQL extends DAOSQL implements IDAOPouvoir {
	public Pouvoir map(ResultSet result) throws SQLException {
		Pouvoir myPouvoir = new Pouvoir();
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myPouvoir.setId(result.getInt("POUV_ID"));
		myPouvoir.setLibelle(result.getString("POUV_LIBELLE"));
		
		return myPouvoir;
	}
	
	
	public List<Pouvoir> findAll() {
		List<Pouvoir> myPouvoirs = new ArrayList<Pouvoir>();
		
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM pouvoir");

			while (myResult.next()) {
				//AJOUT DU POUVOIR DANS LA LISTE
				myPouvoirs.add(this.map(myResult));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPouvoirs;
	}
	
	
	public Pouvoir findById(int id) {
		Pouvoir myPouvoir = null;
		
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("SELECT * FROM pouvoir WHERE POUV_ID = ?");
			
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				myPouvoir = this.map(myResult);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPouvoir;
	}
	
	
	public Pouvoir save(Pouvoir entity) {
		try {
			this.connect();
			String myQuery = "";
			
			if (entity.getId() == 0) { //Ajout du pouvoir
				myQuery = "INSERT INTO pouvoir (POUV_LIBELLE)"
						+ " VALUES (?)";
			}
			
			else { //Mise à jour du pouvoir
				myQuery = "UPDATE pouvoir SET POUV_LIBELLE = ?"
						+ " WHERE POUV_ID = ?";
			}
			
			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
			
			myStatement.setString(1, entity.getLibelle());
			
			if (entity.getId() > 0) {
				myStatement.setInt(2, entity.getId());
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
					.prepareStatement("DELETE FROM pouvoir WHERE POUV_ID = ?");
			
			myStatement.setInt(1, id);
			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(Pouvoir entity) {
		this.deleteById(entity.getId());
	}
}