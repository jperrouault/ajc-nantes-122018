package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.projection.Views;

@Entity
@Table(name = "carte")
public class Carte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAR_ID")
	@JsonView(Views.Common.class)
	private int id;
	
	@Column(name="CAR_LIBELLE", length=150, nullable=false)
	@NotNull
	@NotEmpty
	@Size(max=150)
	@JsonView({ Views.Carte.class, Views.Plateau.class, Views.CaseReponse.class })
	private String libelle;

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
}