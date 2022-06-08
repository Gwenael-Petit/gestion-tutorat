package main;

import java.util.ArrayList;

import main.Users.Student;
import main.Users.Tutor;
import main.Users.Tutored;
/**
 * Classe caracterisant une liste d'attente pour les etudiants qu'ils soient tuteurs ou tutores
 */
public class WaitingList {
	//une matiere de type matiere
    private Subject subject;
    //une ArrayList de tuteurs
    private ArrayList<Tutor> tutor;
    //une ArrayListe de tutore
    private ArrayList<Tutored> tutored; 

    /**
     * Cree une liste d'attente (WaitingList) pour une matiere donnee en parametre, composee d'une liste de tuteurs et d'une liste de tutores
     * @param subject : une matiere
     */
    public WaitingList(Subject subject){
        tutored = new ArrayList<>();
        tutor=new ArrayList<>();
        this.subject=subject;
    }

    /**
     * Cree une liste d'attente (WaitingList) pour une matiere donneee en parametre, composee d'une liste de tuteurs et d'une liste de tutores donnees en parametres egalement
     * @param subject : une matiere
     * @param tutored : une ArrayList de tutores
     * @param tutor : une ArrayList de tuteurs
     */
    public WaitingList(Subject subject, ArrayList<Tutored> tutored, ArrayList<Tutor> tutor){
        this.tutored =tutored ;
        this.tutor = tutor;
        this.subject=subject;
    }

    /**
     * Ajoute un tuteur à l'ArrayList des tuteurs
     * @param t : un tuteur
     */
    public void addTutor(Tutor t){
        tutor.add(t);
    }

    /**
     * Ajoute un tutore à l'ArrayList des tutores
     * @param t : un tutoré
     */
    public void addTutored(Tutored t){
        tutored.add(t);
    }

    /**
     * Verifie si le tutore donne en parametre fait partie de l'ArrayList Tutored
     * @param t : un tutore
     * @return true si le tutore fait bien parti de l'ArrayList des tutores, false sinon
     */
    public boolean contains(Tutored t){
        return tutored.contains(t);
    }

    /**
     * Verifie si le tuteur donne en parametre fait partie de l'ArrayList Tutor
     * @param t : un tuteur
     * @return true si le tuteur fait bien partie de l'ArrayList des tuteurs, false sinon
     */
    public boolean contains(Tutor t){
        return tutor.contains(t);
    }

    /**
     * Renvoie la matiere concernee par la file d'attente
     * @return subject soit la matiere en question
     */
    public Subject getSubject() {
        return this.subject;
    }

    /**
     * Modifie la matiere de la file d'attente
     * @param subject : une nouvelle matiere
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Renvoie la liste des tuteurs
     * @return tutor soit l'ArrayList des tuteurs en file d'attente
     */
    public ArrayList<Tutor> getTutor() {
        return this.tutor;
    }

    /**
     * Renvoie la liste des tutores
     * @return tutored soit l'ArrayList des tutores en file d'attente
     */
    public ArrayList<Tutored> getTutored() {
        return this.tutored;
    }

    /**
     * Renvoie une chaine de caractere composee d'une matiere en file d'attente avec sa liste de tuteurs attentes et sa liste de tutores en attentes
     * @return res soit la chaine de caracteres en question
     */
    public String toString() {
        String res = subject.toString() + "\nTuteurs :\n" +tutor.toString() + "\nTutores :\n"+ tutored.toString();
        return res;
    }
}
