package fr.formation.model;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//TABLE_PER_CLASS
@Entity
@Table(name="client")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="CLI_ID")),
	@AttributeOverride(name="nom", column=@Column(name="CLI_NOM", nullable=false)),
	@AttributeOverride(name="adresse", column=@Column(name="CLI_ADRESSE", nullable=false)),
	@AttributeOverride(name="email", column=@Column(name="CLI_EMAIL", nullable=false)),
	@AttributeOverride(name="telephone", column=@Column(name="CLI_TELEPHONE", length=20))
})
public class Client extends Personne {
	@Column(name="CLI_PRENOM", length=100, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=100)
	private String prenom;

	@Column(name="CLI_NIVEAU")
	private int niveau;

	@Column(name="CLI_TAILLE")
	private int taille;

	@Column(name="CLI_POIDS")
	private int poids;

	@Column(name="CLI_DATE_NAISSANCE")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	@Column(name="CLI_PASSWORD", nullable=false, length=300)
	@NotEmpty
	private String password;
	
	
	@ManyToMany(mappedBy="clientsQuiViennentMeVoir")
	private List<Evenement> participations;
	

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}