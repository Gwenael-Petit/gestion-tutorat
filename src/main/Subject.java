package main;

import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import main.Users.Student;
import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;

public class Subject {
    private final int MAX_STUDENT;
    private final String NAME;
    private Teacher inCharge;
    private ArrayList<Tutored> tutoredList;
    private ArrayList<Tutor> tutorList;
    private int id;
    private CalculAffectation<Student> calcul;

    
    /**
     * Crée une matière (subject) entièrement spécifiée avec son nombre maximum d'étudiants pour le tutorat, son nom, son ID et
     * l'attribution d'une liste de tuteurs et d'une liste de tutorés
     * @param MAX_STUDENT : Nombre maximum d'étudiants tuteurs et tutorés compris
     * @param NAME : Nom de la matière
     * @param id : ID de la matière
     */
    public Subject(int MAX_STUDENT, String NAME,int id) {
        this.MAX_STUDENT = MAX_STUDENT;
        this.NAME = NAME;
        tutorList=new ArrayList<Tutor>();
        tutoredList = new ArrayList<Tutored>();
        this.id=id;
    }

    /**
     * Renvoie l'ID de la matière demandée
     * @return ID de la matière
     */
    public int getId() {
        return id;
    }

    /**
     * Renvoie le professeur en charge de la matière demandée
     * @return inCharge donc le professeur en question
     */
    public Teacher getInCharge(){
        return inCharge;
    }

    /**
     * Permet d'attribuer / de mofifier le professeur en charge de la matière demandée
     * @param inCharge donc un professeur
     */
    public void setInCharge(Teacher inCharge){
        if(inCharge.isInCharge(this)){
            this.inCharge = inCharge;
        }
    }

    /**
     * Renvoie le nombre maximum d'étudiants pour le tutorat de la matière demandée défini à la création de la matière
     * @return MAX_STUDENT
     */
    public int getMAX_STUDENT() {
        return this.MAX_STUDENT;
    }

    /**
     * Renvoie le nom de ma matière demandée
     * @return name soit le nom de la matière
     */
    public String getNAME() {
        return this.NAME;
    }

    /**
     * Renvoie la liste des étudiants qui sont des tutorés
     * @return tutoredList soit l'ArrayList des tutorés
     */
    public ArrayList<Tutored> getTutoredList() {
        return this.tutoredList;
    }

    /**
     * Renvoie la liste des étudiants qui sont des tuteurs
     * @return tutorList soit l'ArrayList des tuteurs
     */
    public ArrayList<Tutor> getTutorList() {
        return this.tutorList;
    }

    /**index
     * Vérifie si la liste des tutorés contient un tutoré donné en paramètre
     * @param t : un tutoré
     * @return true si le tutoré fait bien partie de la liste des tutorés, false sinon
     */
    public boolean contains(Tutored t){
        return tutoredList.contains(t);
    }

    /**
     * Vérifie si la liste des tuteurs contient un tuteur donné en paramètre
     * @param t : un tuteur
     * @return true si le tuteur fait bien partie de liste des tuteurs, false sinon
     */
    public boolean contains(Tutor t){
        return tutorList.contains(t);
    }

    /**
     * Renvoie le calcul d'affectation de Student
     * @return calcul qui est un calcul d'affectation des graphes
     */
    public CalculAffectation<Student> getCalcul() {
        return calcul;
    }

    /**
     * Permet d'attribuer / modifier le calcul d'affectation de Students
     * @param calcul : un calcul d'affectation de Student
     */
    public void setCalcul(CalculAffectation<Student> calcul) {
        this.calcul = calcul;
    }

    /**
     * Vérifie si l'étudiant t est un tuteur ou un tutoré ou aucun des deux
     * @param t : un étudiant (type Student)
     * @return true si l'étudiant t est bien un tuteur ou un tutoré, false sinon
     */
    public boolean contains(Student t){
        if(t instanceof Tutored)        return contains((Tutored) t);
        else if(t instanceof Tutor)     return contains((Tutor) t);
        else return false;
    }

    @Override
    /**
     * Renvoie le nom de la matière demandée
     * @return NAME soit le nom de la matière
     */
    public String toString() {
        return NAME;
    }

    /**
     * Renvoie le tuteur qui a été affecté au tutoré donné en paramètre
     * @param tutored : un tutoré
     * @return le tuteur (Tutor) qui est affecté au tutoré et null si il n'y en a pas 
     */
    public Tutor getAffectation(Tutored tutored){
        for (Arete<Student> a : getCalcul().getAffectation()) {
            if(a.getExtremite1().equals((Student) tutored)){
                return (Tutor) a.getExtremite2();
            }else if(a.getExtremite2().equals((Student) tutored)){
                return (Tutor) a.getExtremite1();
            }
        }
        return null;
    }

    /**
     * Renvoie la liste des tutorés qui ont été affectés au tuteur donné en paramètre
     * @param tutor : un tuteur
     * @rerurn res : l'ArrayList des tutorés qui ont été affectés 
     */
    public ArrayList<Tutored> getAffectation(Tutor tutor){
        ArrayList<Tutored> res = new ArrayList<>();
        for (Arete<Student> a : getCalcul().getAffectation()) {
            if(a.getExtremite1().equals((Student) tutor)){
                res.add((Tutored) a.getExtremite2());
            }else if(a.getExtremite2().equals((Student) tutor)){
                res.add((Tutored) a.getExtremite1());
            }
        }
        return res;
    }

}
