package fr.dawan.AppliCFABack.entities;

import java.util.Date;
import java.util.List;

public class Promotion {

	private long id;
	private Date dateDebut;
	private Date dateFin;
	
	private Centre centre;
	private ProgrammePromotion promotion;
	private List<Etudiant> etudiants;
	private List<Cours> cours;
	private Referent referent;
}
