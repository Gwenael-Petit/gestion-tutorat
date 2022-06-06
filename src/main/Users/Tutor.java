package main.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.Subject;

public class Tutor extends Student implements Cloneable{
    private Map<Subject,ArrayList<Tutored>> tutored;  
    boolean moyPremiere;

    public boolean isMoyPremiere() { return moyPremiere; }
    public void setMoyPremiere(boolean moyPremiere) { this.moyPremiere = moyPremiere; }

    public Tutor(String nom, String prenom, String login, String password, double[] moyenne, String annee,Map<Subject,ArrayList<Tutored>> tutored) {
        super(nom, prenom, login, password, moyenne, annee);
        this.tutored = tutored;
        moyPremiere=false;
    }

    public Tutor(String nom, String prenom, String password, double[] moyenne, String annee,Map<Subject,ArrayList<Tutored>> tutored) {
        super(nom, prenom, prenom+"."+nom, password, moyenne, annee);
        this.tutored = tutored;
        moyPremiere=false;
    }

    public Tutor(String nom, String prenom, String password, double[] moyenne, String annee, String modifier) {
        super(nom, prenom, password, moyenne, annee);
        this.setModifier(Integer.parseInt(modifier));
        this.tutored = new HashMap<Subject,ArrayList<Tutored>>();
        moyPremiere=false;
    }

    public Map<Subject, ArrayList<Tutored>> getTutored() {
        return tutored;
    }

   

    public boolean addSubject(Subject s){
        tutored.put(s, new ArrayList<Tutored>());
        return true;
    }

    public Tutor clone() throws CloneNotSupportedException{
        return (Tutor) super.clone();
    }

    /*

    public boolean addTutored(Subject s, Tutored t){
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

    @Override
    public String toString() {
        return super.toString();
    }
}
