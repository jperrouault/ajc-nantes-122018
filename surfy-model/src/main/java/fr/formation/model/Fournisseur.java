package fr.formation.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fournisseur")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="FOU_ID")),
	@AttributeOverride(name="nom", column=@Column(name="FOU_NOM", nullable=false)),
	@AttributeOverride(name="adresse", column=@Column(name="FOU_ADRESSE", nullable=false)),
	@AttributeOverride(name="email", column=@Column(name="FOU_EMAIL", nullable=false)),
	@AttributeOverride(name="telephone", column=@Column(name="FOU_TELEPHONE", length=20))
})
public class Fournisseur extends Personne {
	@OneToMany(mappedBy="fournisseur")
	private List<Produit> produits;

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
}