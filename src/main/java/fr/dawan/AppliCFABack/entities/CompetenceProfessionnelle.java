package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class CompetenceProfessionnelle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String libelle;
	
	@Column(nullable = false)
	private byte numeroFiche;
	
	@ManyToMany(mappedBy = "competenceProfessionnelle")
	private List<Examen> examens;
}
