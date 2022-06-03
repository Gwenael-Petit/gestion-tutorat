package main.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.Subject;

/**
 * Classe qui cree un tuteur à partir d'un Student
 */
public class Tutor extends Student implements Cloneable{
    // Une Map associant une ArrayList de tutores a une matiere
	private Map<Subject,ArrayList<Tutored>> tutored;    

	/**
	 * Constructeur d'un Tutor entirement specifie et ajout de la Map composee de matieres et d'ArrayList des tutores a la Map de base
	 * @param nom : le nom du tuteur
	 * @param prenom : le prenom du tuteur
	 * @param login : le login du tuteur
	 * @param password : le mot de passe du tuteur
	 * @param moyenne : la moyenne du tuteur
	 * @param annee : l'annee de scolarite du tuteur
	 * @param tutored : l'ArrayList des tutores
	 */
    public Tutor(String nom, String prenom, String login, String password, double[] moyenne, String annee,Map<Subject,ArrayList<Tutored>> tutored) {
        super(nom, prenom, login, password, moyenne, annee);
        this.tutored = tutored;
    }

    /**
     * Constructeur d'un Tutor (sans son login) et ajout de la Map composee de matieres et d'ArrayList des tutores a la Map de base
     * @param nom : le nom du tuteur
     * @param prenom : le prenom du tuteur
     * @param password : le mot de passe du tuteur
     * @param moyenne : la moyenne du tuteur
     * @param annee : l'annee de scolarite du tuteur
     * @param tutored : l'ArrayList des tuteurs
     */
    public Tutor(String nom, String prenom, String password, double[] moyenne, String annee,Map<Subject,ArrayList<Tutored>> tutored) {
        super(nom, prenom, prenom+"."+nom, password, moyenne, annee);
        this.tutored = tutored;
    }

    /**
     * Constructeur d'un Tutor (sans son login) et instanciation d'une HashMap associant une matiere a une liste des tutores a la Map de base
     * @param nom
     * @param prenom
     * @param password
     * @param moyenne
     * @param annee
     */
    public Tutor(String nom, String prenom, String password, double[] moyenne, String annee) {
        super(nom, prenom, password, moyenne, annee);
        this.tutored = new HashMap<Subject,ArrayList<Tutored>>();
    }

    /**
     * Renvoie la Map des ArrayList de tutores pour chaque matiere
     * @return tutored : la Map 
     */
    public Map<Subject, ArrayList<Tutored>> getTutored() {
        return tutored;
    }

    /**
     * Verifie si l'ajout d'une matiere a la Map est possible et le fait si oui
     * @param s : une matiere
     * @return true si la matiere a ete ajoute a la Map et false sinon
     */
    public boolean addSubject(Subject s){
        tutored.put(s, new ArrayList<Tutored>());
        return true;
    }

    /**
     * Renvoie un clone d'un tuteur, dans le cas où on voudrait l'affecter a plusieurs tutores par exemple
     */
    public Tutor clone() throws CloneNotSupportedException{
        return (Tutor) super.clone();
    }

    /* public boolean addTutored(Subject s, Tutored t){
        if(tutored.get(s)==null){
            return false;
        }
        if(t.getTutor().get(s)!=null){
            return false;
        }
        tutored.get(s).add(t);
        t.getTutor().put(s, this);
        return true;
    }*/

    /**
     * Renvoie la chaine d'informations sur un tuteur
     */
    public String toString() {
        return super.toString();
    }
}
