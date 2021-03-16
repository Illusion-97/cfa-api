package fr.dawan.AppliCFABack.entities;

import java.util.List;

public class User {

	private long id;
	private String login;
	private String password;
	private String prenom;
	private String nom;
	
	private Adresse adresse;
	private List<Role> roles;
	
	enum Role{
		ETUDIANT, FORMATEUR, REFERENT, CEF, ADMIN
	}

}
