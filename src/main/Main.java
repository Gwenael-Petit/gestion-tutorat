package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
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
        for (int i = 0; i < wait.length - 1; i++) {
            subjects.add(new Subject(50, subjectNames[i], i));
            wait[i] = new WaitingList(subjects.get(i));
        }

        while (list.size() == 0) {
            try {
                list = CsvFileHelper.csvToList("res.csv", ",", subjects);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        boolean flag = true;
        while (flag) {
            LogInManagement log = new LogInManagement();
            log.connect(list, br);

            if (log.isLogged()) {
                if (log.getLogged().isStudent()) {
                    studentScreen((Student) log.getLogged(), wait, subjects, br);
                } else if (log.getLogged().isTeacher()) {
                    Teacher t = (Teacher) log.getLogged();
                    teacherScreen(t, wait, subjects, br);
                }
            }
        }

        flag = true;
        while (flag) {
            try {
                br.close();
                flag = false;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void studentScreen(Student student, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {

        if (student instanceof Tutored) {
            giveAffectation((Tutored) student, wait, subjects, br);
        } else if (student instanceof Tutor) {
            giveAffectation((Tutor) student, wait, subjects, br);
        } else {
            System.out.println("quelque chose ne vas pas :  student n'est pas une instance de Tutored ou Tutor.");
        }

    }

    public static void teacherScreen(Teacher teacher, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {

        int idx = getSubjectID(teacher, br);

        // Quel methode de verificaiton utiliser ? Filtrage automatique ? Ou Manuel ?
        boolean flag = true;
        while (flag) {
            System.out.println("Voulez vous filtrer a la main[M] ou filtrer automatiquement[A] ?");
            String in = "";
            try {
                in = br.readLine();
            } catch (IOException e) {
                System.out.println("L'entrée est incorrect, veuillez recommencer.");
            }
            if (in.equals("M")) {
                manualVerification(wait, br, subjects, idx);
                flag = false;
            } else if (in.equals("A")) {
                autoVerification(wait, br, subjects, idx);
                flag = false;
            }
        }

        subjects.get(idx).setCalcul(mainMenuTeacher(subjects.get(idx).getTutoredList(), subjects.get(idx).getTutorList(), idx, br, subjects.get(idx).getCalcul(),subjects));

    }

    public static int getSubjectID(Teacher teacher, BufferedReader br) {
        int idx = -1; // Indice pour la matiére qui interesse l'enseignant
        boolean flag = true;
        while (flag) { // Si la matiére n'existe pas ou si le prof n'as pas le droit, on continue de
                       // boucler
            idx = getSubjectID(br);
            int i = 0;
            while (flag && i < teacher.getSubjects().size()) {
                if (teacher.getSubjects().get(i).getId() == idx)
                    flag = false;
                else
                    i++;
            }
        }
        return idx;
    }

    public static int getSubjectID(BufferedReader br) {
        int idx = -1; // Indice pour la matiére qui interesse l'étudiant

        while (idx <= 0 || idx >= 5) { // Si la matiére n'existe pas on continue de boucler
            System.out.println("Quelle matiére vous interesse ?");
            try {
                idx = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Cette entrée n'est pas correcte. Réessayez:");
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un chiffre entre 0 et 4 inclus. Réessayez:");
            }
        }

        return idx;
    }

    public static void manualVerification(WaitingList[] wait, BufferedReader br, ArrayList<Subject> subjects, int idx) {
        if (wait[idx].getTutor().size() > 0) {
            System.out.println("Entrez Y ou N pour respectivement accepter ou refuser chaque candidature de tuteurs:");
            while (wait[idx].getTutor().size() > 0) {
                System.out.println(wait[idx].getTutor().get(0).toString(idx));
                int ok = -1;
                while (ok == -1) {
                    String in = "";
                    try {
                        in = br.readLine();
                    } catch (IOException e) {
                        System.out.println("Cette entrée n'est pas correcte. Réessayez:");
                    }
                    if (in.toLowerCase().equals("y"))
                        ok = 1;
                    else if (in.toLowerCase().equals("n"))
                        ok = 0;
                    else
                        System.out.println("Veuillez entrer un \"Y\"ou un \"N\"");
                }

                if (ok == 1) {
                    subjects.get(idx).getTutorList().add(wait[idx].getTutor().get(0));
                }
                wait[idx].getTutor().remove(0);
            }
        }

        if (wait[idx].getTutored().size() > 0) {
            System.out.println("Entrez Y ou N pour respectivement accepter ou refuser chaque candidature de tutorés:");
            while (wait[idx].getTutored().size() > 0) {
                System.out.println(wait[idx].getTutored().get(0).toString(idx));
                int ok = -1;
                while (ok == -1) {
                    String in = "";
                    try {
                        in = br.readLine();
                    } catch (IOException e) {
                        System.out.println("Cette entrée n'est pas correcte. Réessayez:");
                    }
                    if (in.toLowerCase().equals("y"))
                        ok = 1;
                    else if (in.toLowerCase().equals("n"))
                        ok = 0;
                    else
                        System.out.println("Veuillez entrer un \"Y\"ou un \"N\"");
                }

                if (ok == 1) {
                    subjects.get(idx).getTutoredList().add(wait[idx].getTutored().get(0));
                }
                wait[idx].getTutored().remove(0);
            }
        }
    }

    public static void autoVerification(WaitingList[] wait, BufferedReader br, ArrayList<Subject> subjects, int idx) {
        if (wait[idx].getTutor().size() > 0) {
            boolean flag = true;
            while (flag) {
                System.out.println("Quel critére voulez vous mettre en filtre pour les tuteurs ?\nMoyenne[M]    Moyenne Premiére Année[P]   Aucun[X]");
                String in = "";
                try {
                    in = br.readLine();
                    in.toUpperCase();
                } catch (IOException e) {
                    System.out.println("Entrée incorrecte !");
                }
                if (in.equals("M")) {
                    System.out.println("Quel est la moyenne minimum pour vos tuteurs ?");
                    double nb = -1;
                    while (nb < 0) {
                        try {
                            in = br.readLine();
                            nb = Double.parseDouble(in);
                            if (nb < 0) {
                                throw new Exception("La valeur ne peut pas être inférieur a 0 !");
                            }
                        } catch (IOException e) {
                            System.out.println("Entrée incorrecte !");
                        } catch (NumberFormatException e) {
                            System.out.println("Ceci n'est pas un nombre valide");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            nb = -1;
                        }
                    }

                    for (int i = 0; i < wait[idx].getTutor().size(); i++) {
                        if (wait[idx].getTutor().get(i).getScore()[idx] < nb) {
                            wait[idx].getTutor().remove(i);
                            i--;
                        }
                    }

                    // TODO : ajouter un message pour dire combien on en a éliminé
                } else if (in.equals("P")) {
                    System.out.println("Quel est la moyenne de premiére année minimum pour vos tuteurs ?");
                    double nb = -1;
                    while (nb < 0) {
                        try {
                            in = br.readLine();
                            nb = Double.parseDouble(in);
                            if (nb < 0) {
                                throw new Exception("La valeur ne peut pas être inférieur a 0 !");
                            }
                        } catch (IOException e) {
                            System.out.println("Entrée incorrecte !");
                        } catch (NumberFormatException e) {
                            System.out.println("Ceci n'est pas un nombre valide");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            nb = -1;
                        }
                    }

                    for (int i = 0; i < wait[idx].getTutor().size(); i++) {
                        if (wait[idx].getTutor().get(i).getModifier() < nb) {
                            wait[idx].getTutor().remove(i);
                            i--;
                        }
                    }

                } else if (in.equals("X")) {
                    flag = false;
                }
            }
            subjects.get(idx).getTutorList().addAll(wait[idx].getTutor());
            wait[idx].getTutor().removeAll(subjects.get(idx).getTutorList());
        }

        if (wait[idx].getTutored().size() > 0) {
            boolean flag = true;

            while (flag) {
                System.out.println("Quel critére voulez vous mettre en filtre pour les tutorés ?\nMoyenne[M]    Absences[P]   Aucun[X]");
                String in = "";
                try {
                    in = br.readLine();
                    in.toUpperCase();
                } catch (IOException e) {
                    System.out.println("Entrée incorrecte !");
                }
                if (in.equals("M")) {
                    System.out.println("Quel est la moyenne maximum pour vos tutorés ?");
                    double nb = -1;
                    while (nb < 0) {
                        try {
                            in = br.readLine();
                            nb = Double.parseDouble(in);
                            if (nb < 0) {
                                throw new Exception("La valeur ne peut pas être inférieur a 0 !");
                            }
                        } catch (IOException e) {
                            System.out.println("Entrée incorrecte !");
                        } catch (NumberFormatException e) {
                            System.out.println("Ceci n'est pas un nombre valide");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            nb = -1;
                        }
                    }

                    for (int i = 0; i < wait[idx].getTutored().size(); i++) {
                        if (wait[idx].getTutored().get(i).getScore()[idx] > nb) {
                            wait[idx].getTutored().remove(i);
                            i--;
                        }
                    }

                    // TODO : ajouter un message pour dire combien on en a éliminé
                } else if (in.equals("P")) {
                    System.out.println("Quel est le nombre d'absences maximum pour vos tutorés ?");
                    double nb = -1;
                    while (nb < 0) {
                        try {
                            in = br.readLine();
                            nb = Double.parseDouble(in);
                            if (nb < 0) {
                                throw new Exception("La valeur ne peut pas être inférieur a 0 !");
                            }
                        } catch (IOException e) {
                            System.out.println("Entrée incorrecte !");
                        } catch (NumberFormatException e) {
                            System.out.println("Ceci n'est pas un nombre valide");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            nb = -1;
                        }
                    }

                    for (int i = 0; i < wait[idx].getTutored().size(); i++) {
                        if (wait[idx].getTutored().get(i).getModifier() > nb) {
                            wait[idx].getTutored().remove(i);
                            i--;
                        }
                    }

                } else if (in.equals("X")) {
                    flag = false;
                }
            }
            subjects.get(idx).getTutoredList().addAll(wait[idx].getTutored());
            wait[idx].getTutored().removeAll(subjects.get(idx).getTutoredList());
        }
    }

    public static CalculAffectation<Student> mainMenuTeacher(ArrayList<Tutored> tutorés, ArrayList<Tutor> tuteurs, int idx, BufferedReader br, CalculAffectation<Student> calcul, ArrayList<Subject> subjects) {
        // Menu principal du prof
        String in = "";
        boolean flag = true;
        boolean absence = false;
        boolean moyPremiere = false;

        while (flag) {
            System.out.println("\n---------------------------------\n\nQue voulez vous faire ?\nFixer[F]   Calculer[C]  Toggle absence[A]\nToggle moyenne de premiére année pour tuteurs[P] Montrer les affectations[M]\nSe deconnecter[X]");
            try {
                in = br.readLine();
                in = in.toUpperCase();
            } catch (IOException e) {
                System.out.println("Cette entrée n'est pas correcte. Réessayez:");
            }

            if (in.equals("F")) {
                System.out.println("Vous pouvez fixer ici");
            } else if (in.equals("C")) {
                for (int i = 0; i < tuteurs.size(); i++) {
                    if(tuteurs.get(i).getName().equals("Fictif")){
                        tuteurs.remove(i);
                        i--;
                    }
                }
                for (int i = 0; i < tutorés.size(); i++) {
                    if(tutorés.get(i).getName().equals("Fictif")){
                        tutorés.remove(i);
                        i--;
                    }
                }
                calcul = Graph.compute(tutorés, tuteurs, subjects,idx);
            } else if (in.equals("A")) {
                if (absence) {
                    Graph.turnOffAbsence(tutorés, idx);
                    absence = false;
                    System.out.println("\nVous avez desactivé la prise en compte des absences de premiére année dans le calcul d'affectation.");
                } else {
                    Graph.turnOnAbsence(tutorés, idx);
                    absence = true;
                    System.out.println("\nVous avez activé la prise en compte des absences de premiére année dans le calcul d'affectation.");
                }
            } else if (in.equals("P")) {
                if (moyPremiere) {
                    Graph.turnOffMoyPremiere(tuteurs, idx);
                    absence = false;
                    System.out.println("\nVous avez desactivé la prise en compte des moyennes de premiére année des tuteurs dans le calcul d'affectation.");
                } else {
                    Graph.turnOnMoyPremiere(tuteurs, idx);
                    absence = true;
                    System.out.println("\nVous avez activé la prise en compte des moyennes de premiére année des tuteurs dans le calcul d'affectation.");
                }
            } else if (in.equals("M")) {
                if (calcul != null) {
                    System.out.println("\n" + calcul.getAffectation().toString().replace("]), ", "]),\n"));
                }
            } else if (in.equals("X")) {
                flag = false;
            }
        }
        return calcul;
    }

    public static void giveAffectation(Tutored tutored, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {
        int idx = getSubjectID(br);

        // Affiche les informations demandé ou inscrit dans la liste d'attente
        if (!(wait[idx].contains(tutored) || subjects.get(idx).contains(tutored))) {
            System.out.println("Aucune inscription de recensé. Vous voici maintenant inscrit.");
            wait[idx].addTutored(tutored);
        } else {
            if (wait[idx].contains(tutored)) {
                System.out.println("Vous êtes en files d'attente. Veuillez revenir plus tard.");
            } else {
                System.out.println("Vous êtes accepté, vous pouvez contacter votre tuteur via cette adresse email: " + subjects.get(idx).getAffectation(tutored).getLogin() + "@univ-lille.fr");
            }
        }
    }

    public static void giveAffectation(Tutor tutor, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {
        int idx = getSubjectID(br);

        while (idx <= 0 || idx >= 5) { // Si la matiére n'existe pas on continue de boucler
            System.out.println("Dans quel matiére voulez vous vous inscrire ?");
            try {
                idx = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Cette entrée n'est pas correcte. Réessayez:");
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un chiffre entre 0 et 4 inclus. Réessayez:");
            }
        }

        // Affiche les informations demandé ou inscrit dans la liste d'attente
        if (!(wait[idx].contains(tutor) || subjects.get(idx).contains(tutor))) {
            System.out.println("Aucune inscription de recensé. Vous voici maintenant inscrit.");
            wait[idx].addTutor(tutor);
        } else {
            if (wait[idx].contains(tutor)) {
                System.out.println("Vous êtes en files d'attente. Veuillez revenir plus tard.");
            } else {
                System.out.println("Vous êtes accepté, vous pouvez contacter vos tutorés via ces adresses email:");
                for (int i = 0; i < subjects.get(idx).getAffectation(tutor).size(); i++) {
                    System.out.println(subjects.get(idx).getAffectation(tutor).get(i).getLogin() + "@univ-lille.fr");
                }
            }
        }
    }
}
