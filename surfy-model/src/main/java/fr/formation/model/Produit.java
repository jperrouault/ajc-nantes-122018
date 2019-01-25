package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.projection.Views;

@Entity
@Table(name = "produit")
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID")
	@JsonView(Views.Common.class)
	private int id;

	@Column(name = "PRO_CATEGORIE")
	private int categorie;

	@Column(name = "PRO_TYPE")
	private int type;

	@Column(name = "PRO_MODELE", nullable = false)
	@NotEmpty(message="Le nom du modèle doit être saisi")
	@NotNull
	@JsonView(Views.Produit.class)
	private String modele;

	@Column(name = "PRO_TAILLE")
	private int taille;

	@Column(name = "PRO_PRIX")
	@Positive(message="Le prix doit être strictement positif")
	@JsonView(Views.Produit.class)
	private float prix;

	@Column(name = "PRO_DISPOS")
	private int disponibilites;

	@Column(name = "PRO_PHOTO")
	private String photo;

	@Column(name = "PRO_DESCRIPTION", columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "PRO_FOURNISSEUR_ID")
	@JsonView(Views.ProduitWithAchatsAndFournisseur.class)
	private Fournisseur fournisseur;

	@OneToMany(mappedBy = "produit")
	@JsonView(Views.ProduitWithAchatsAndFournisseur.class)
	private List<Achat> achats;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getDisponibilites() {
		return disponibilites;
	}

	public void setDisponibilites(int disponibilites) {
		this.disponibilites = disponibilites;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}
}