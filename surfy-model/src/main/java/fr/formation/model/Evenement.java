package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="evenement")
public class Evenement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVT_ID")
	private int id;
	
	@Column(name="EVT_NOM", nullable=false)
	@NotNull
	@NotEmpty
	private String nom;
	
	@ManyToMany
	@JoinTable(
		name="participation",
		uniqueConstraints=
			@UniqueConstraint(columnNames = { "PART_EVENEMENT_ID", "PART_CLIENT_ID" }),
		joinColumns=
			@JoinColumn(name="PART_EVENEMENT_ID", referencedColumnName="EVT_ID"),
		inverseJoinColumns=
			@JoinColumn(name="PART_CLIENT_ID", referencedColumnName="CLI_ID")
	)
	private List<Client> clientsQuiViennentMeVoir;
}