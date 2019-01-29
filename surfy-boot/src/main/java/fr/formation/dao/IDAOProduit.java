package fr.formation.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.model.Produit;

public interface IDAOProduit extends JpaRepository<Produit, Integer> {
	@Query("select p from Produit p where p.modele = :modele")
	public Produit findByModele(@Param("modele") String modele);
	
	public List<Produit> findByPrixBetween(float a, float b);
	
	
	@Query("select p from Produit p left join p.achats a where a.commande.client.id = :id")
	public List<Produit> findByClientId(@Param("id") int id);
	
	public List<Produit> findByFournisseurId(int id);
}