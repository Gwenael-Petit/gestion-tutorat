package main;

import java.util.ArrayList;

import main.Users.Student;
import main.Users.Tutor;
import main.Users.Tutored;

public class WaitingList {
    private Subject subject;
    private ArrayList<Tutor> tutor;
    private ArrayList<Tutored> tutored; 

    public WaitingList(Subject subject){
        tutored = new ArrayList<>();
        tutor=new ArrayList<>();
        this.subject=subject;
    }

    public WaitingList(Subject subject, ArrayList<Tutored> tutored, ArrayList<Tutor> tutor){
        this.tutored =tutored ;
        this.tutor = tutor;
        this.subject=subject;
    }

    public void addStudent(Tutor t){
        tutor.add(t);
    }

    public void addStudent(Tutored t){
        tutored.add(t);
    }

    public void addStudent(Student t){
        if(t instanceof Tutor)          addStudent((Tutor) t);
        else if(t instanceof Tutored)   addStudent((Tutored) t);

    }

    public boolean contains(Tutored t){
        return tutored.contains(t);
    }

    public boolean contains(Tutor t){
        return tutor.contains(t);
    }

    public boolean contains(Student t){
        if(t instanceof Tutored)        return contains((Tutored) t);
        else if(t instanceof Tutor)     return contains((Tutor) t);
        else return false;
    }


    public Subject getSubject() {
        return this.subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ArrayList<Tutor> getTutor() {
        return this.tutor;
    }

    public ArrayList<Tutored> getTutored() {
        return this.tutored;
    }

    @Override
    public String toString() {
        String res = subject.toString() + "\nTuteurs :\n" +tutor.toString() + "\nTutorés :\n"+ tutored.toString();
        return res;
    }
}
