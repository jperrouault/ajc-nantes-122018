package fr.formation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "commande")
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CMD_ID")
	private int id;

	@Column(name = "CMD_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "CMD_PRIX_TOTAL", nullable = false)
	@Positive
	private float prixTotal;

	@Column(name = "CMD_TRANSPORTEUR", length = 100)
	@Size(max = 100)
	private String transporteur;

	@Column(name = "CMD_ETAT", nullable = false)
	private int etat;

	@ManyToOne
	@JoinColumn(name = "CMD_CLIENT_ID")
	private Client client;

	@OneToMany(mappedBy = "commande", cascade = CascadeType.PERSIST)
	private List<Achat> produitsAchetes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(float prixTotal) {
		this.prixTotal = prixTotal;
	}

	public String getTransporteur() {
		return transporteur;
	}

	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Achat> getProduitsAchetes() {
		return produitsAchetes;
	}

	public void setProduitsAchetes(List<Achat> produitsAchetes) {
		this.produitsAchetes = produitsAchetes;
	}
	
	
	
	
	
	public void ajouterProduit(Produit p, int quantite) {
		if (this.produitsAchetes == null) { //SI LA LISTE N'EST PAS INSTANCIEE ...
			this.produitsAchetes = new ArrayList<Achat>();
		}
		
		//CRÉATION DE L'ACHAT
		Achat achat = new Achat();
		
		achat.setProduit(p);
		achat.setQuantite(quantite);
		achat.setPrixUnitaire(p.getPrix());
		
		//ON AJOUTE L'ACHAT A LA LISTE DE
		this.produitsAchetes.add(achat);
		achat.setCommande(this);
	}
	
	
	
	
	
	
	
}