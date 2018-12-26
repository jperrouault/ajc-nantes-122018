package fr.loupgarou.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="personnage")
public class Personnage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PERS_ID")
	private int id;
	
	@Column(name="PERS_LIBELLE", length=150, nullable=false)
	@NotNull
	@NotEmpty
	@Size(max=150)
	private String libelle;
	
	@OneToOne
	@JoinColumn(name="PERS_POUVOIR_ID")
	private Pouvoir pouvoir;

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

	public Pouvoir getPouvoir() {
		return pouvoir;
	}

	public void setPouvoir(Pouvoir pouvoir) {
		this.pouvoir = pouvoir;
	}
}