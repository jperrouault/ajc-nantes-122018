package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.model.Fournisseur;

public interface IDAOFournisseur extends JpaRepository<Fournisseur, Integer> {
	@Query("select f from Fournisseur f left join fetch f.produits p where f.id = :id")
	public Fournisseur findByIdWithProduits(@Param("id") int id);
}