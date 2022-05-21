package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import main.Users.Person;
import main.Users.Student;
import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;
import main.Util.CsvFileHelper;
import main.Util.Graph;


public abstract class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Person> list = new ArrayList<Person>();
        
        WaitingList[] wait = new WaitingList[5];
        ArrayList<Subject> subjects = new ArrayList<>();
        String[] subjectNames = { "Math", "Base de Donnée", "Java", "Réseau" };
        for (int i = 0; i < wait.length-1; i++) {
            subjects.add( new Subject(50, subjectNames[i], i));
            wait[i] = new WaitingList(subjects.get(i));
        }

        try {
            System.out.println("1");
            list = CsvFileHelper.csvToList("res.csv", ",",subjects);
            System.out.println(2);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        boolean flag=true;
        while(flag){
            LogInManagement log = new LogInManagement();
            log.connect(list,br);
            System.out.println(3);
    
            if (log.isLogged()) {
                if (log.getLogged().isStudent()) {
                    studentScreen((Student) log.getLogged(), wait, subjects,br);
                } else if (log.getLogged().isTeacher()) {
                    Teacher t = (Teacher) log.getLogged();
                    teacherScreen(t, wait, subjects,br);
                }
            }
        }

        flag=true;
        while(flag){
            try{
                br.close();
                flag=false;
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }     
    }

    public static void studentScreen(Student student, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {
        if (student instanceof Tutored) {
            Tutored tutored = (Tutored) student; // Cast de student dans tutored pour avoir accés aux méthodes
            int idx = -1; // Indice pour la matiére qui interesse l'étudiant

            while (idx <= 0 || idx > 5) { // Si la matiére n'existe pas on continue de boucler
                System.out.println("Dans quel matiére voulez vous vous inscrire ?");
                try {
                    idx = Integer.parseInt(br.readLine());
                } catch (IOException e) {
                    System.out.println("Cette entrée n'est pas correcte. Réessayez:");
                }
            }

            // Affiche les informations demandé ou inscrit dans la liste d'attente
            if (!(wait[idx].contains(tutored) || subjects.get(idx).contains(tutored))) {
                System.out.println("Aucune inscription de recensé. Vous voici maintenant inscrit.");
                System.out.println(wait[idx].toString());
                wait[idx].addStudent(tutored);
            } else {
                if (wait[idx].contains(tutored)) {
                    System.out.println("Vous êtes en files d'attente. Veuillez revenir plus tard.");
                } else {
                    System.out.println("Vous êtes accepté, vous pouvez contacter votre tuteur via cette adresse email: "
                            + tutored.getTutor().get(subjects.get(idx)).getLogin() + "@univ-lille.fr");
                }
            }

        } else if (student instanceof Tutor) {
            Tutor tutor = (Tutor) student; // Cast de student dans tutored pour avoir accés aux méthodes

            int idx = -1; // Indice pour la matiére qui interesse l'étudiant

            while (idx <= 0 || idx > 5) { // Si la matiére n'existe pas on continue de boucler
                System.out.println("Dans quel matiére voulez vous vous inscrire ?");
                try {
                    idx = Integer.parseInt(br.readLine());
                } catch (IOException e) {
                    System.out.println("Cette entrée n'est pas correcte. Réessayez:");
                }
            }

            // Affiche les informations demandé ou inscrit dans la liste d'attente
            if (!(wait[idx].contains(tutor) || subjects.get(idx).contains(tutor))) {
                System.out.println("Aucune inscription de recensé. Vous voici maintenant inscrit.");
                System.out.println(wait[idx].toString());
                wait[idx].addStudent(tutor);
            } else {
                if (wait[idx].contains(tutor)) {
                    System.out.println("Vous êtes en files d'attente. Veuillez revenir plus tard.");
                } else {
                    System.out.println("Vous êtes accepté, vous pouvez contacter vos tutorés via ces adresses email:");
                    for (int i = 0; i < tutor.getTutored().get(subjects.get(idx)).size(); i++) {
                        System.out.println(tutor.getTutored().get(subjects.get(idx)).get(i) + "@univ-lille.fr");
                    }
                }
            }
        } else {
            System.out.println("quelque chose ne vas pas :  student n'est pas une instance de Tutored ou Tutor.");
        }
    }

    public static void teacherScreen(Teacher teacher, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {
        int idx = -1; // Indice pour la matiére qui interesse l'enseignant
        boolean flag = true;
        while (flag) { // Si la matiére n'existe pas ou si le prof n'as pas le droit, on continue de boucler
            System.out.println("Quel matiére voulez vous superviser ?");
            try {
                idx = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Cette entrée n'est pas correcte. Réessayez:");
            }
            int i = 0;
            while(flag && i<5){
                if(teacher.getSubjects().get(i).getId()==idx) flag=false;
                else i++;
            }
        }
        
        if(wait[idx].getTutor().size()>0) System.out.println("Entrez Y ou N pour respectivement accepter ou refuser chaque candidature de tuteurs:");
        while(wait[idx].getTutor().size()>0){
            System.out.println(wait[idx].getTutor().get(0));
            int ok = -1;
            while (ok==-1) {
                String in="";
                try {
                    in = br.readLine();
                } catch (IOException e) {
                    System.out.println("Cette entrée n'est pas correcte. Réessayez:");
                }
                if(in.toLowerCase().equals("y")) ok = 1;
                else if (in.toLowerCase().equals("n")) ok =0;
            }

            if(ok==1){
                subjects.get(idx).getTutorList().add(wait[idx].getTutor().get(0));
            }
            wait[idx].getTutor().remove(0);
        }

        if(wait[idx].getTutored().size()>0) System.out.println("Entrez Y ou N pour respectivement accepter ou refuser chaque candidature de tutorés:");
        while(wait[idx].getTutored().size()>0){
            System.out.println(wait[idx].getTutored().get(0));
            int ok = -1;
            while (ok==-1) {
                String in="";
                try {
                    in = br.readLine();
                } catch (IOException e) {
                    System.out.println("Cette entrée n'est pas correcte. Réessayez:");
                }
                if(in.toLowerCase().equals("y")) ok = 1;
                else if (in.toLowerCase().equals("n")) ok =0;
            }

            if(ok==1){
                subjects.get(idx).getTutoredList().add(wait[idx].getTutored().get(0));
            }
            wait[idx].getTutored().remove(0);
        }

        // Menu principal du prof
        String in = "";
        flag = true;
        boolean absence=false;
        boolean moyPremiere=false;
        ArrayList<Tutored> tutorés = subjects.get(idx).getTutoredList();
        ArrayList<Tutor> tuteurs = subjects.get(idx).getTutorList();

        while (flag) {
            System.out.println("Que voulez vous faire ?\nFixer [F]   Calculer[C]    Activer/Desactiver la prise en compte des absences [A]  Activer/Desactiver la prise en compte des moyenne de premiére année[P]");
            try {
                in = br.readLine();
            } catch (IOException e) {
                System.out.println("Cette entrée n'est pas correcte. Réessayez:");
            }
            
            if(in.equals("F")){
                System.out.println("Vous pouvez fixer ici");
            }else if(in.equals("C")){
                GrapheNonOrienteValue<Student> graph =  Graph.createGraph(tutorés, tuteurs, idx);
                ArrayList<Student> tuteursG=new ArrayList<>();
                ArrayList<Student> tutorésG=new ArrayList<>();
                for (Tutored s : tutorés) {
                    tuteursG.add((Student) s);
                }

                for (Tutor s : tuteurs) {
                    tutorésG.add((Student) s);
                }

                CalculAffectation<Student> calcul = new CalculAffectation<Student>(graph, tuteursG, tutorésG);
                System.out.println(calcul.getAffectation().toString().replace("]), ", "]),\n"));
            }else if(in.equals("A")){
                if(absence){
                    Graph.turnOffAbsence(tutorés, idx);
                    absence=false;
                    System.out.println("Vous avez desactivé la prise en compte des absences de premiére année dans le calcul d'affectation.");
                }else{
                    Graph.turnOnAbsence(tutorés, idx);
                    absence=true;
                    System.out.println("Vous avez activé la prise en compte des absences de premiére année dans le calcul d'affectation.");
                }
            }else if(in.equals("P")){
                if(moyPremiere){
                    Graph.turnOffMoyPremiere(tuteurs, idx);
                    absence=false;
                    System.out.println("Vous avez desactivé la prise en compte des moyennes de premiére année des tuteurs dans le calcul d'affectation.");
                }else{
                    Graph.turnOnMoyPremiere(tuteurs, idx);
                    absence=true;
                    System.out.println("Vous avez activé la prise en compte des moyennes de premiére année des tuteurs dans le calcul d'affectation.");
                }
            }
        }

    }
}
