package main;

import java.util.ArrayList;

import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;

public class Subject {
    private final int MAX_STUDENT;
    private final String NAME;
    private final Teacher inCharge;
    private ArrayList<Tutored> tutoredList;
    private ArrayList<Tutor> tutorList;

    public Subject(int MAX_STUDENT, String NAME, Teacher inCharge) {
        this.MAX_STUDENT = MAX_STUDENT;
        this.NAME = NAME;
        this.inCharge=inCharge;
        tutorList=new ArrayList<Tutor>();
        tutoredList = new ArrayList<Tutored>();
    }

    public Teacher getInCharge(){
        return inCharge;
    }

    public int getMAX_STUDENT() {
        return this.MAX_STUDENT;
    }

    public String getNAME() {
        return this.NAME;
    }

    public ArrayList<Tutored> getTutoredList() {
        return this.tutoredList;
    }

    public ArrayList<Tutor> getTutorList() {
        return this.tutorList;
    }
}
