package main;

import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import main.Users.Student;
import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;

public class Subject {
    private final int MAX_STUDENT;
    private final String NAME;
    private Teacher inCharge;
    private ArrayList<Tutored> tutoredList;
    private ArrayList<Tutor> tutorList;
    private int id;
    private CalculAffectation<Student> calcul;

    public Subject(int MAX_STUDENT, String NAME,int id) {
        this.MAX_STUDENT = MAX_STUDENT;
        this.NAME = NAME;
        tutorList=new ArrayList<Tutor>();
        tutoredList = new ArrayList<Tutored>();
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public Teacher getInCharge(){
        return inCharge;
    }

    public void setInCharge(Teacher inCharge){
        if(inCharge.isInCharge(this)){
            this.inCharge = inCharge;
        }
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

    public boolean contains(Tutored t){
        return tutoredList.contains(t);
    }

    public boolean contains(Tutor t){
        return tutorList.contains(t);
    }

    public CalculAffectation<Student> getCalcul() {
        return calcul;
    }

    public void setCalcul(CalculAffectation<Student> calcul) {
        this.calcul = calcul;
    }

    public boolean contains(Student t){
        if(t instanceof Tutored)        return contains((Tutored) t);
        else if(t instanceof Tutor)     return contains((Tutor) t);
        else return false;
    }

    @Override
    public String toString() {
        return NAME;
    }

    public Tutor getAffectation(Tutored tutored){
        for (Arete<Student> a : getCalcul().getAffectation()) {
            if(a.getExtremite1().equals((Student) tutored)){
                return (Tutor) a.getExtremite2();
            }else if(a.getExtremite2().equals((Student) tutored)){
                return (Tutor) a.getExtremite1();
            }
        }
        return null;
    }

    public ArrayList<Tutored> getAffectation(Tutor tutor){
        ArrayList<Tutored> res = new ArrayList<>();
        for (Arete<Student> a : getCalcul().getAffectation()) {
            if(a.getExtremite1().equals((Student) tutor)){
                res.add((Tutored) a.getExtremite2());
            }else if(a.getExtremite2().equals((Student) tutor)){
                res.add((Tutored) a.getExtremite1());
            }
        }
        return res;
    }

}
