package fr.dawan.AppliCFABack.entities;

import java.io.File;
import java.util.List;

public class Etudiant {

	private long id;
	
	private User user;
	private List<Promotion> promotions;
	private List<Absence> abscences;
	private List<Groupe> groupes;
	private List<File> fiches;
	private Entreprise entreprise;
	
}
