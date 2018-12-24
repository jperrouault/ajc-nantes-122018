package fr.formation.model;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name="commande")
public class Commande {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CMD_ID")
	private int id;

	
	@ManyToOne
	@JoinColumn(name="CMD_CLIENT_ID")
	private Client client;

	@Column(name="CMD_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="CMD_PRIXTOTAL", nullable=false)
	@Positive
	private int prixTotal;

	@Column(name="CMD_TRANSPORTEUR")
	@NotEmpty
	@NotNull
	private String transporteur;

	@Column(name="CMD_ETAT", nullable=false)
	private int etat;

	@OneToMany(mappedBy="commande", cascade = CascadeType.PERSIST)
	private List<Achat> achats;
	
	public void ajouterProduit(Produit p, int quantité) {
		
		if (achats.size()!=0) {
		Achat newAch= new Achat();
		
		newAch.setProduit(p);
		newAch.setQuantité(quantité);
		newAch.setPrixunitaire(p.getPrix());
		
	this.achats.add(newAch);
	newAch.setCommande(this);
		
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(int prixTotal) {
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

}
