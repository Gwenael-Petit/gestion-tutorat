package main;

import java.util.ArrayList;

import main.Users.Student;
import main.Users.Tutor;
import main.Users.Tutored;

public class WaitingList {
    private Subject subject;
    private ArrayList<Tutor> tutor;
    private ArrayList<Tutored> tutored; 

    /**
     * Crée une liste d'attente (WaitingList) pour une matière donnée en paramètre, composée d'une liste de tuteurs et d'une liste de tutorés
     * @param subject : une matière
     */
    public WaitingList(Subject subject){
        tutored = new ArrayList<>();
        tutor=new ArrayList<>();
        this.subject=subject;
    }

    /**
     * Crée une liste d'attente (WaitingList) pour une matière donnée en paramètre, composée d'une liste de tuteurs et d'une liste de tutorés données en paramètres également
     * @param subject : une matière
     * @param tutored : une ArrayList de tutorés
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
     * Ajoute un tutoré à l'ArrayList des tutorés
     * @param t : un tutoré
     */
    public void addTutored(Tutored t){
        tutored.add(t);
    }

    /**
     * Vérifie si le tutoré donné en paramètre fait partie de l'ArrayList Tutored
     * @param t : un tutoré
     * @return true si le tutoré fait bien parti de l'ArrayList des tutorés, false sinon
     */
    public boolean contains(Tutored t){
        return tutored.contains(t);
    }

    /**
     * Vérifie si le tuteur donné en paramètre fait partie de l'ArrayList Tutor
     * @param t : un tuteur
     * @return true si le tuteur fait bien partie de l'ArrayList des tuteurs, false sinon
     */
    public boolean contains(Tutor t){
        return tutor.contains(t);
    }

    /**
     * Vérifie si le Student t est un tuteur ou un tutoré ou aucun des deux
     * @param t : un étudiant (type Student)
     * @return true si l'étudiant t est bien un tuteur ou un tutoré, false sinon
     */
    public boolean contains(Student t){
        if(t instanceof Tutored)        return contains((Tutored) t);
        else if(t instanceof Tutor)     return contains((Tutor) t);
        else return false;
    }

    /**
     * Renvoie la matière concernée par la file d'attente
     * @return subject soit la matière en question
     */
    public Subject getSubject() {
        return this.subject;
    }

    /**
     * Modifie la matière de la file d'attente
     * @param subject : une nouvelle matière
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
     * Renvoie la liste des tutorés
     * @return tutored soit l'ArrayList des tutorés en file d'attente
     */
    public ArrayList<Tutored> getTutored() {
        return this.tutored;
    }

    @Override
    /**
     * Renvoie une chaîne de caractère composée d'une matière en file d'attente avec sa liste de tuteurs attentes et sa liste de tutorés en attentes
     * @return res soit la chaîne de caractères en question
     */
    public String toString() {
        String res = subject.toString() + "\nTuteurs :\n" +tutor.toString() + "\nTutorés :\n"+ tutored.toString();
        return res;
    }
}
