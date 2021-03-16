package fr.dawan.entities;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Adresse {

	private long id;
	private long numero;
	private String rue;
	private String ville;
	private String codePostal;	
}
