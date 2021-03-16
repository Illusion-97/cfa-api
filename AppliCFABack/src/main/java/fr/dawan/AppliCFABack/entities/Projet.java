package fr.dawan.AppliCFABack.entities;

public class Projet {

	private long id;
	private String nom;
	private String description;
	
	private Groupe groupe;
	private Formateur formateur;
	private TypeProjet type;
	
	enum TypeProjet{
		ENTREPRISE, PEDAGOGIQUE
	}
}
