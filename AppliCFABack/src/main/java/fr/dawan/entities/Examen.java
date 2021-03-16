package fr.dawan.entities;

import java.util.Date;
import java.util.List;

public class Examen {

	private long id;
	private Date date;
	
	private List<Note> notes;
	private Cours cours;
}
