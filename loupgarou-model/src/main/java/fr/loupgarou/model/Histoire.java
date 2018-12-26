package fr.loupgarou.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="histoire")
public class Histoire {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="HIST_ID")
	private int id;

	@Column(name="HIST_LIBELLE", length=50, nullable=false)
	@NotNull
	@NotEmpty
	@Size(max=50)
	private String libelle;
	
	@OneToMany(mappedBy="histoire")
	private List<Organisation> organisations;
	
	@Transient
	private List<Personnage> personnage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Personnage> getPersonnage() {
		return personnage;
	}

	public void setPersonnage(List<Personnage> personnage) {
		this.personnage = personnage;
	}
}