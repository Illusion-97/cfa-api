package fr.dawan.entities;

import java.io.File;
import java.util.Date;
import java.util.List;

public class Cours {

	private long id;
	private Date dateDebut;
	private Date dateFin;
	private String noteInformation;
	private String noteEntraide;
	
	private ProgrammeCours programmeCours;
	private Promotion promotion;
	private List<Formateur> formateur;
	private List<Examen> examens;
	private List<Devoir> devoirs;
	private List<File> supportCours;
	
	
}
