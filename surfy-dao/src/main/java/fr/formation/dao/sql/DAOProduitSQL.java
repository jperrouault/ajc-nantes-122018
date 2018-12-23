package fr.formation.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class DAOProduitSQL extends DAOSQL implements IDAOProduit {
	public Produit map(ResultSet result) throws SQLException {
		Produit p = new Produit();
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		p.setId(result.getInt("PRO_ID"));
		p.setModele(result.getString("PRO_MODELE"));
		p.setPrix(result.getFloat("PRO_PRIX"));
		
		//ON ASSOCIE LE FOURNISSEUR AU PRODUIT
		Fournisseur f = new Fournisseur();
		f.setId(result.getInt("PRO_FOURNISSEUR_ID"));
		p.setFournisseur(f);
		
		return p;
	}
	
	
	public List<Produit> findAll() {
		List<Produit> mesProduits = new ArrayList<Produit>();
		
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM produit");

			while (myResult.next()) {
				Produit p = this.map(myResult);
				
				//AJOUT DU PRODUIT DANS LA LISTE
				mesProduits.add(p);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mesProduits;
	}
	
	
	public Produit findById(int id) {
		Produit monProduit = null;
		
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("SELECT * FROM produit WHERE PRO_ID = ?");
			
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				monProduit = this.map(myResult);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return monProduit;
	}
	
	
	/**
	 * La méthode qui sauvegarde un produit en base de données
	 * @param p Le produit à sauvegarder
	 * @return Le produit qui a été sauvegardé
	 */
	public Produit save(Produit p) {
		try {
			this.connect();
			String myQuery = "";
			
			if (p.getId() == 0) { //Ajout du produit
				myQuery = "INSERT INTO produit (PRO_MODELE, PRO_PRIX, PRO_FOURNISSEUR_ID)"
						+ " VALUES (?, ?, ?)";
			}
			
			else { //Mise à jour du produit
				myQuery = "UPDATE produit SET PRO_MODELE = ?,"
						+ "	PRO_PRIX = ?, PRO_FOURNISSEUR_ID = ? "
						+ " WHERE PRO_ID = ?";
			}
			
			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
			
			myStatement.setString(1, p.getModele());
			myStatement.setFloat(2, p.getPrix());
			//ON PRECISE L'IDENTIFIANT DU FOURNISSEUR POUR CE PRODUIT
			myStatement.setInt(3, p.getFournisseur().getId());
			
			if (p.getId() > 0) {
				myStatement.setInt(4, p.getId());
			}
			
			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	
	public void deleteById(int id) {
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("DELETE FROM produit WHERE PRO_ID = ?");
			
			myStatement.setInt(1, id);
			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(Produit p) {
		this.deleteById(p.getId());
	}
}
