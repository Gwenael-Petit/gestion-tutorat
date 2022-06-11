package main.ihm;

import main.Users.Student;

public class Couple {
	private Student tuteur;
	private Student tutore;
	
	public Couple(Student tuteur, Student tutore) {
		this.tuteur = tuteur;
		this.tutore = tutore;
	}

	public Student getTuteur() {
		return tuteur;
	}

	public Student getTutore() {
		return tutore;
	}
	
	
}
