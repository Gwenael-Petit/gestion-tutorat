package main.Users;

import java.util.HashMap;
import java.util.Map;

import main.Subject;

/**
 * Classe qui cree un tutore a partir d'un Student
 */
public class Tutored extends Student{
    // Map qui associe une ArrayList de tuteurs a une matiere
	private Map<Subject,Tutor> tutor;    

	/**
	 * Constructeur d'un Tutored entierement specifie et ajout la Map composee de matieres et d'ArrayList des tuteurs a la Map de base
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param password
	 * @param moyenne
	 * @param annee
	 * @param tutor
	 */
    public Tutored(String nom, String prenom, String login, String password, double[] moyenne, String annee,Map<Subject,Tutor> tutor) {
        super(nom, prenom, login, password, moyenne, annee);
        this.tutor = tutor;
    }

    /**
     * Constructeur d'un Tutored (sans son login) et ajout de la Map composee de matieres et d'ArrayList des tuteurs a la Map de base
     * @param nom
     * @param prenom
     * @param password
     * @param moyenne
     * @param annee
     * @param tutor
     */
    public Tutored(String nom, String prenom, String password, double[] moyenne, String annee,Map<Subject,Tutor> tutor) {
        super(nom, prenom, password, moyenne, annee);
        this.tutor = tutor;
    }

    /**
     * Constructeur d'un Tutor (sans son login) et ajout de la Map composee de matieres et instanciation d'une HashMap qui associe une matiere a une ArrayList de tuteurs
     * @param nom
     * @param prenom
     * @param password
     * @param moyenne
     * @param annee
     */
    public Tutored(String nom, String prenom, String password, double[] moyenne, String annee) {
        super(nom, prenom, password, moyenne, annee);
        this.tutor = new HashMap<Subject,Tutor>();
    }

    /**
     * Renvoie la Map qui associe une ArrayList de tuteurs par matiere
     * @return
     */
    public Map<Subject, Tutor> getTutor() {
        return tutor;
    }

    /* public boolean addTutor(Subject s, Tutor t){
        if(tutor.get(s)==null){
            return false;
        }
        if(t.getLevel()==Level.second && t.getTutored().get(s).size()>0){
            return false;
        }
        tutor.put(s, t);
        t.getTutored().get(s).add(this);
        return true;
    }*/

    /**
     * Renvoie les informations sur le tutore
     */
    public String toString() {
        return super.toString();
    }
}
