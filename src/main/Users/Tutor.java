package main.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.Subject;

public class Tutor extends Student{
    private Map<Subject,ArrayList<Tutored>> tutored;    

    public Tutor(String nom, String prenom, String login, String password, double[] moyenne, String annee,Map<Subject,ArrayList<Tutored>> tutored) {
        super(nom, prenom, login, password, moyenne, annee);
        this.tutored = tutored;
    }

    public Tutor(String nom, String prenom, String password, double[] moyenne, String annee,Map<Subject,ArrayList<Tutored>> tutored) {
        super(nom, prenom, prenom+"."+nom, password, moyenne, annee);
        this.tutored = tutored;
    }

    public Tutor(String nom, String prenom, String password, double[] moyenne, String annee) {
        super(nom, prenom, password, moyenne, annee);
        this.tutored = new HashMap<Subject,ArrayList<Tutored>>();
    }

    public Map<Subject, ArrayList<Tutored>> getTutored() {
        return tutored;
    }

    public boolean addSubject(Subject s){
        tutored.put(s, new ArrayList<Tutored>());
        return true;
    }

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
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
