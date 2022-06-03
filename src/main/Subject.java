package main;

import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import main.Users.Student;
import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;
/**
 * Classe qui instancie les differentes matieres qui ont demande a avoir du tutorat
 */
public class Subject {
	//Nombre maximum d'etudiants (tuteurs et tutores)
    private final int MAX_STUDENT;
    //Nom de la matiere
    private final String NAME;
    //Professeur de type Teacher en charge de la matiere (on considere qu'il est seul a la gerer) 
    private Teacher inCharge;
    //ArrayList de tutores
    private ArrayList<Tutored> tutoredList;
    //ArrayList de tuteurs
    private ArrayList<Tutor> tutorList;
    //ID de la matiere
    private int id;
    //Calcul d'affectation d'etudiants de type Student
    private CalculAffectation<Student> calcul;

    
    /**
     * Cree une matiere (subject) entierement specifiee avec son nombre maximum d'etudiants pour le tutorat, son nom, son ID et
     * l'attribution d'une liste de tuteurs et d'une liste de tutores
     * @param MAX_STUDENT : Nombre maximum d'etudiants tuteurs et tutores compris
     * @param NAME : Nom de la matiere
     * @param id : ID de la matiere
     */
    public Subject(int MAX_STUDENT, String NAME,int id) {
        this.MAX_STUDENT = MAX_STUDENT;
        this.NAME = NAME;
        tutorList=new ArrayList<Tutor>();
        tutoredList = new ArrayList<Tutored>();
        this.id=id;
    }

    /**
     * Renvoie l'ID de la matiere demandee
     * @return ID de la matiere
     */
    public int getId() {
        return id;
    }

    /**
     * Renvoie le professeur en charge de la matiere demandee
     * @return inCharge donc le professeur en question
     */
    public Teacher getInCharge(){
        return inCharge;
    }

    /**
     * Permet d'attribuer / de mofifier le professeur en charge de la matiere demandee
     * @param inCharge donc un professeur
     */
    public void setInCharge(Teacher inCharge){
        if(inCharge.isInCharge(this)){
            this.inCharge = inCharge;
        }
    }

    /**
     * Renvoie le nombre maximum d'etudiants pour le tutorat de la matiere demandee defini à la creation de la matiere
     * @return MAX_STUDENT
     */
    public int getMAX_STUDENT() {
        return this.MAX_STUDENT;
    }

    /**
     * Renvoie le nom de ma matiere demandee
     * @return name soit le nom de la matiere
     */
    public String getNAME() {
        return this.NAME;
    }

    /**
     * Renvoie la liste des etudiants qui sont des tutores
     * @return tutoredList soit l'ArrayList des tutores
     */
    public ArrayList<Tutored> getTutoredList() {
        return this.tutoredList;
    }

    /**
     * Renvoie la liste des etudiants qui sont des tuteurs
     * @return tutorList soit l'ArrayList des tuteurs
     */
    public ArrayList<Tutor> getTutorList() {
        return this.tutorList;
    }

    /**
     * Verifie si la liste des tutores contient un tutore donne en parametre
     * @param t : un tutore
     * @return true si le tutore fait bien partie de la liste des tutores, false sinon
     */
    public boolean contains(Tutored t){
        return tutoredList.contains(t);
    }

    /**
     * Verifie si la liste des tuteurs contient un tuteur donne en parametre
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
     * Verifie si l'etudiant t est un tuteur ou un tutore ou aucun des deux
     * @param t : un etudiant (type Student)
     * @return true si l'etudiant t est bien un tuteur ou un tutore, false sinon
     */
    public boolean contains(Student t){
        if(t instanceof Tutored)        return contains((Tutored) t);
        else if(t instanceof Tutor)     return contains((Tutor) t);
        else return false;
    }

    @Override
    /**
     * Renvoie le nom de la matiere demandee
     * @return NAME soit le nom de la matiere
     */
    public String toString() {
        return NAME;
    }

    /**
     * Renvoie le tuteur qui a ete affecte au tutore donne en parametre
     * @param tutored : un tutore
     * @return le tuteur (Tutor) qui est affecte au tutore et null si il n'y en a pas 
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
     * Renvoie la liste des tutores qui ont ete affectes au tuteur donne en parametre
     * @param tutor : un tuteur
     * @rerurn res : l'ArrayList des tutores qui ont ete affectes 
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
