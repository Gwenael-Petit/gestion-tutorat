package main.Users;

import java.util.Map;

import main.Subject;


public class Tutored extends Student{
    private Map<Subject,Tutor> tutor;    

    public Tutored(String nom, String prenom, String login, String password, String moyenne, String annee,Map<Subject,Tutor> tutor) {
        super(nom, prenom, login, password, moyenne, annee);
        this.tutor = tutor;
    }
}
