package main.Users;

import java.util.ArrayList;

import main.Subject;

public class Teacher extends Person {
    private ArrayList<Subject> subjects;

    public Teacher(String lastName, String name, String login, String password, ArrayList<Subject> subjects) {
        super(lastName, name, login, password);
        this.subjects = subjects;
    }

    public Teacher(String lastName, String name, String login, String password) {
        super(lastName, name, login, password);
        this.subjects = new ArrayList<Subject>();
    }

    public Teacher(String lastName, String name, String password) {
        super(lastName, name, name.toLowerCase()+"."+lastName.toLowerCase(), password);
        this.subjects = new ArrayList<Subject>();
    }

    public String toString() {
        return super.toString();
    }

    public void addSubjects(Subject s){
        subjects.add(s);
        s.setInCharge(this);
    }

    public void removeSubjects(Subject s){
        subjects.remove(s);
        s.setInCharge(null);
    }

    public boolean isInCharge(Subject s){
        return subjects.contains(s);
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
}
