package main.Users;

import java.util.ArrayList;
import java.util.Map;

import main.Subject;

public class Tutor extends Student{
    private Map<Subject,ArrayList<Tutored>> tutored;    

    public Tutor(String nom, String prenom, String login, String password, String moyenne, String annee,Map<Subject,ArrayList<Tutored>> tutored) {
        super(nom, prenom, login, password, moyenne, annee);
        this.tutored = tutored;
    }

}
