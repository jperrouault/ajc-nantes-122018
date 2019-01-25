package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.projection.Views;

@Entity
@Table(name = "achat")
public class Achat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACH_ID")
	@JsonView(Views.Common.class)
	private int id;

	@Column(name = "ACH_PRIX_UNITAIRE", nullable = false)
	@Positive
	@JsonView(Views.ProduitWithAchatsAndFournisseur.class)
	private float prixUnitaire;

	@Column(name = "ACH_QUANTITE", nullable = false)
	@JsonView(Views.ProduitWithAchatsAndFournisseur.class)
	private int quantite;

	@Column(name = "ACH_REMARQUE", columnDefinition = "TEXT")
	private String remarque;

	@ManyToOne
	@JoinColumn(name = "ACH_COMMANDE_ID")
	private Commande commande;

	@ManyToOne
	@JoinColumn(name = "ACH_PRODUIT_ID")
	private Produit produit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
}