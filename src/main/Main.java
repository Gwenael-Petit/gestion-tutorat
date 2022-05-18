package main;

import java.util.ArrayList;

import main.Users.Person;
import main.Users.Student;
import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;
import main.Util.LogInManagement;

public class Main {
    public static void main(String[] args) {
        Tutored student = new Tutored("Smith", "Jane", "password","13","2");
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(student);

        LogInManagement log = new LogInManagement();
        log.connect(list);

        if(log.isLogged()){
            if(log.getLogged().isStudent()){
                studentScreen((Student) log.getLogged());
            }else if(log.getLogged().isTeacher()){
                teacherScreen((Teacher) log.getLogged());
            }
        }

    }

    public static void studentScreen(Student student){
        if(student instanceof Tutored){
            Tutored tutored = (Tutored) student;
            if(tutored.getTutor().isEmpty()){
                System.out.println("Vous n'êtes pas inscrit.");
            }
            System.out.println("Dans quel formation voulez vous vous inscrire ?");
        }else if (student instanceof Tutor){
            Tutor tutor = (Tutor) student;
            if(tutor.getTutored().isEmpty()){
                System.out.println("Vous n'êtes pas inscrit.");
            }
            System.out.println("Dans quel formation voulez vous vous inscrire ?");
        }else{
            System.out.println("quelque chose ne vas pas :  student n'est pas une instance de Tutored ou Tutor.");
        }
    }

    public static void teacherScreen(Teacher teacher){
        System.out.println("Entrez Y ou N pour respectivement accepter ou refuser chaque candidature.");
    }
}
