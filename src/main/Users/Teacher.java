package main.Users;

import java.util.ArrayList;

import main.Subject;
/**
 * Un Teacher est une Person responsable d'une liste de matieres
 */
public class Teacher extends Person {

    /**
     * Liste de matiere dont le prof est les responsable
     */
    private ArrayList<Subject> subjects;

    /**
     * Permet de creer in Teacher a partir de ses informations et sa liste de matiere
     * Utilisation du constructeur de personne
     * @param lastName : nom de famille
     * @param name : prenom
     * @param login : identifiant
     * @param password : mot de passe
     * @param subjects : liste de matiere
     * @see Person
     */
    public Teacher(String lastName, String name, String login, String password, ArrayList<Subject> subjects) {
        super(lastName, name, login, password);
        this.subjects = subjects;
    }

    /**
     * Permet de creer in Teacher a partir de ses informations et creation d'une liste de matiere
     * Utilisation du constructeur de personne
     * @param lastName : nom de famille
     * @param name : prenom
     * @param login : identifiant
     * @param password : mot de passe
     * @see Person
     */
    public Teacher(String lastName, String name, String login, String password) {
        super(lastName, name, login, password);
        this.subjects = new ArrayList<Subject>();
    }

    /**
     * Permet de creer in Teacher a partir de ses informations, creation d'une liste de matiere et d'un identifiant
     * Utilisation du constructeur de personne
     * @param lastName : nom de famille
     * @param name : prenom
     * @param login : identifiant
     * @param password : mot de passe
     * @see Person
     */
    public Teacher(String lastName, String name, String password) {
        super(lastName, name, name.toLowerCase()+"."+lastName.toLowerCase(), password);
        this.subjects = new ArrayList<Subject>();
    }

    /**
     * Renvoie un enseignant de la forme Person.toString()
     * @see Person
     * @return informations de l'enseignant
     */
    public String toString() {
        return super.toString();
    }

    /**
     * Ajoute une matiere a la liste de l'enseigant et en fait le responsable
     * @param s : matiere a ajouter
     */
    public void addSubjects(Subject s){
        subjects.add(s);
        s.setInCharge(this);
    }

    /**
     * Retire une matiere de la liste de l'enseigant et le supprime du poste responsable
     * @param s : matiere a retirer
     */
    public void removeSubjects(Subject s){
        subjects.remove(s);
        s.setInCharge(null);
    }

    /**
     * Verifie si l'enseignant est responsable d'une matiere donnee
     * @param s : matiere a verifier
     */
    public boolean isInCharge(Subject s){
        return subjects.contains(s);
    }

    /**
     * Renvoie les matiere dont l'enseignant est responsable
     * @return subjects : Liste de matieres
     */
    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
}
