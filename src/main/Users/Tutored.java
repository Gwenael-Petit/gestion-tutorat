package main.Users;

import java.util.HashMap;
import java.util.Map;

import main.Subject;


public class Tutored extends Student implements Cloneable{
    private Map<Subject,Tutor> tutor; 
    boolean absences;  

    public boolean isAbsences() { return absences; }
    public void setAbsences(boolean absences) { this.absences = absences; }

    public Tutored(String nom, String prenom, String login, String password, double[] moyenne, String annee,Map<Subject,Tutor> tutor) {
        super(nom, prenom, login, password, moyenne, annee);
        this.tutor = tutor;
        absences=false;
    }

    public Tutored(String nom, String prenom, String password, double[] moyenne, String annee,Map<Subject,Tutor> tutor) {
        super(nom, prenom, password, moyenne, annee);
        this.tutor = tutor;
        absences=false;
    }

    public Tutored(String nom, String prenom, String password, double[] moyenne, String annee, String modifier) {
        super(nom, prenom, password, moyenne, annee);
        this.setModifier(Integer.parseInt(modifier));
        this.tutor = new HashMap<Subject,Tutor>();
        absences=false;
    }

    public Map<Subject, Tutor> getTutor() {
        return tutor;
    }

    public Tutored clone() throws CloneNotSupportedException{
        return (Tutored) super.clone();
    }

    /*

    public boolean addTutor(Subject s, Tutor t){
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

    @Override
    public String toString() {
        return super.toString();
    }
}
