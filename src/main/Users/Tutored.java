package main.Users;

import java.util.HashMap;
import java.util.Map;

import main.Subject;


public class Tutored extends Student{
    private Map<Subject,Tutor> tutor;    

    public Tutored(String nom, String prenom, String login, String password, String moyenne, String annee,Map<Subject,Tutor> tutor) {
        super(nom, prenom, login, password, moyenne, annee);
        this.tutor = tutor;
    }

    public Tutored(String nom, String prenom, String password, String moyenne, String annee,Map<Subject,Tutor> tutor) {
        super(nom, prenom, password, moyenne, annee);
        this.tutor = tutor;
    }

    public Tutored(String nom, String prenom, String password, String moyenne, String annee) {
        super(nom, prenom, password, moyenne, annee);
        this.tutor = new HashMap<Subject,Tutor>();
    }

    public Map<Subject, Tutor> getTutor() {
        return tutor;
    }

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
    }
}
