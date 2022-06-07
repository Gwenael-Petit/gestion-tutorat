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

/**
 * Classe principale qui affiche l'interface des etudiants et l'interface pour les professeurs et effectue les affectations de tuteurs et tutores
 */
public abstract class Main {
	
	/**
	 * Main classique qui verifie qui est connecter et renvoie l'interface necessaire en fonction
	 * @param args
	 */
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Person> list = new ArrayList<Person>();

        WaitingList[] wait = new WaitingList[5];
        ArrayList<Subject> subjects = new ArrayList<>();
        String[] subjectNames = { "Math", "Base de Donnee", "Java", "Reseau" };
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
            System.out.print("\n");

            if (log.isLogged()) {
                if (log.getLogged().isStudent()) {
                    studentScreen((Student) log.getLogged(), wait, subjects, br);
                } else if (log.getLogged().isTeacher()) {
                    Teacher t = (Teacher) log.getLogged();
                    teacherScreen(t, wait, subjects, br);
                }
            }
            System.out.println("\n---------------------------------------------\n");
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

    /**
     * Affiche le resultat de l'affectation de l'etudiant connecte soit son tuteur soit ses tutores
     * @param student : l'etudiant connecte
     * @param wait : la liste d'attente
     * @param subjects : la liste des differentes matieres 
     * @param br : un BufferedReader
     */
    public static void studentScreen(Student student, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {

        if (student instanceof Tutored) {
            giveAffectation((Tutored) student, wait, subjects, br);
        } else if (student instanceof Tutor) {
            giveAffectation((Tutor) student, wait, subjects, br);
        } else {
            System.out.println("quelque chose ne vas pas :  student n'est pas une instance de Tutored ou Tutor.");
        }

    }

    /**
     * Affiche les differentes actions effectuables par un professeur et verifie ce qu'il veut faire
     * @param teacher : le professeur connecte
     * @param wait : la liste d'attentes des etudiants
     * @param subjects : la liste des matieres
     * @param br : un BufferedReader
     */
    public static void teacherScreen(Teacher teacher, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {

        int idx = getSubjectID(teacher, br);

        // Quel methode de verificaiton utiliser ? Filtrage automatique ? Ou Manuel ?
        boolean flag = true;
        while (flag) {
            System.out.print("\n");
            System.out.println("Voulez vous filtrer a la main[M] ou filtrer automatiquement[A] ?");
            String in = "";
            try {
                in = br.readLine();
            } catch (IOException e) {
                System.out.println("L'entree est incorrect, veuillez recommencer.");
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

    /**
     * Lit l'entree de professeur connecte concernant l'ID de la matiere a laquelle il s'interesse et le retourne si elle correspond a une matiere 
     * existante et si le professeur est le referant de la matiere demandee
     * @param teacher : le professeur connecte
     * @param br : un BufferedReader
     * @return int idx : 
     */
    public static int getSubjectID(Teacher teacher, BufferedReader br) {
        int idx = -1; // Indice pour la matiere qui interesse l'enseignant
        boolean flag = true;
        while (flag) { // Si la matiere n'existe pas ou si le prof n'as pas le droit, on continue de
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

    /**
     * Lit l'entree du professeur concernant l'ID de la matiere chosie et le retourne si il correspond a l'ID d'une matiere existante
     * @param br
     * @return int idx : l'ID de la matiere demandee par le professeur
     */
    public static int getSubjectID(BufferedReader br) {
        int idx = -1; // Indice pour la matiere qui interesse l'etudiant

        while (idx <= 0 || idx >= 5) { // Si la matiére n'existe pas on continue de boucler
            System.out.println("Quelle matiére vous interesse ? [0-4]");
            try {
                idx = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Cette entree n'est pas correcte. Reessayez:");
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un chiffre entre 0 et 4 inclus. Reessayez:");
            }
        }

        return idx;
    }

    /**
     * Met en place un dialogue avec le professeur connecte et lui demande de valider ou non les etudiants candidats pour le tutorats de maniere manuelle, sans criteres 
     * @param wait : la liste d'attente de candidats
     * @param br : un BufferedReader
     * @param subjects : la liste des matieres
     * @param idx : un indice
     */
    public static void manualVerification(WaitingList[] wait, BufferedReader br, ArrayList<Subject> subjects, int idx) {
        if (wait[idx].getTutor().size() > 0) {
            System.out.println("\nEntrez Y ou N pour respectivement accepter ou refuser chaque candidature de tuteurs:");
            while (wait[idx].getTutor().size() > 0) {
                System.out.println(wait[idx].getTutor().get(0).toString(idx));
                int ok = -1;
                while (ok == -1) {
                    String in = "";
                    try {
                        in = br.readLine();
                    } catch (IOException e) {
                        System.out.println("Cette entree n'est pas correcte. Reessayez:");
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
            System.out.println("Entrez Y ou N pour respectivement accepter ou refuser chaque candidature de tutores:");
            while (wait[idx].getTutored().size() > 0) {
                System.out.println(wait[idx].getTutored().get(0).toString(idx));
                int ok = -1;
                while (ok == -1) {
                    String in = "";
                    try {
                        in = br.readLine();
                    } catch (IOException e) {
                        System.out.println("Cette entree n'est pas correcte. Reessayez:");
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

    /**
     * Met en place le dialogue avec le professeur connecte pour la validation ou non du profil de candidats avec 
     * ici des criteres predefinis pour choisir des candidats specifique ou les eliminer
     * @param wait : la liste d'attente de candidats
     * @param br : un BufferedReader
     * @param subjects : la liste des matieres
     * @param idx : un indice
     */
    public static void autoVerification(WaitingList[] wait, BufferedReader br, ArrayList<Subject> subjects, int idx) {
        if (wait[idx].getTutor().size() > 0) {
            boolean flag = true;
            while (flag) {
                System.out.println("Quel critere voulez vous mettre en filtre pour les tuteurs ?\nMoyenne[M]    Moyenne Premiere Annee[P]   Aucun[X]");
                String in = "";
                try {
                    in = br.readLine();
                    in.toUpperCase();
                } catch (IOException e) {
                    System.out.println("Entree incorrecte !");
                }
                if (in.equals("M")) {
                    System.out.println("Quel est la moyenne minimum pour vos tuteurs ?");
                    double nb = -1;
                    while (nb < 0) {
                        try {
                            in = br.readLine();
                            nb = Double.parseDouble(in);
                            if (nb < 0) {
                                throw new Exception("La valeur ne peut pas etre inferieur a 0 !");
                            }
                        } catch (IOException e) {
                            System.out.println("Entree incorrecte !");
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

                } else if (in.equals("P")) {
                    System.out.println("Quel est la moyenne de premiere annee minimum pour vos tuteurs ?");
                    double nb = -1;
                    while (nb < 0) {
                        try {
                            in = br.readLine();
                            nb = Double.parseDouble(in);
                            if (nb < 0) {
                                throw new Exception("La valeur ne peut pas etre inferieur a 0 !");
                            }
                        } catch (IOException e) {
                            System.out.println("Entree incorrecte !");
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
                System.out.println("Quel critere voulez vous mettre en filtre pour les tutores ?\nMoyenne[M]    Absences[P]   Aucun[X]");
                String in = "";
                try {
                    in = br.readLine();
                    in.toUpperCase();
                } catch (IOException e) {
                    System.out.println("Entree incorrecte !");
                }
                if (in.equals("M")) {
                    System.out.println("Quel est la moyenne maximum pour vos tutores ?");
                    double nb = -1;
                    while (nb < 0) {
                        try {
                            in = br.readLine();
                            nb = Double.parseDouble(in);
                            if (nb < 0) {
                                throw new Exception("La valeur ne peut pas etre inferieur a 0 !");
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

                } else if (in.equals("P")) {
                    System.out.println("Quel est le nombre d'absences maximum pour vos tutores ?");
                    double nb = -1;
                    while (nb < 0) {
                        try {
                            in = br.readLine();
                            nb = Double.parseDouble(in);
                            if (nb < 0) {
                                throw new Exception("La valeur ne peut pas être inferieur a 0 !");
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

    /**
     * Menu avec plusieurs actions pour le professeur connecte comme l'affectation des etudiants valides pour le tutorat ou l'activation/desactivation des criteres d'affectation
     * @param tutores : ArrayList de tutores en attentes d'affectation
     * @param tuteurs : ArrayList de tuteurs
     * @param idx : un indice
     * @param br : un BufferedReader
     * @param calcul : appel a la methode du Calcul d'affectation pour des Students
     * @param subjects : la liste des matieres disponibles pour le tutorat
     * @return calcul : le calcul d'affectation � la fin de la gestion des affectations tuteurs/tutores
     */
    public static CalculAffectation<Student> mainMenuTeacher(ArrayList<Tutored> tutores, ArrayList<Tutor> tuteurs, int idx, BufferedReader br, CalculAffectation<Student> calcul, ArrayList<Subject> subjects) {
        // Menu principal du prof
        String in = "";
        boolean flag = true;
        boolean absence = false;
        boolean moyPremiere = false;

        while (flag) {
            System.out.println("\n---------------------------------\n\nQue voulez vous faire ?\nFixer[F]   Calculer[C]  Toggle absence[A]\nToggle moyenne de premiere annee pour tuteurs[P] Montrer les affectations[M]\nSe deconnecter[X]");
            try {
                in = br.readLine();
                in = in.toUpperCase();
            } catch (IOException e) {
                System.out.println("Cette entree n'est pas correcte. Reessayez:");
            }

            if (in.equals("F")) {
                String tuteur="";
                String tutore="";
                try{
                    System.out.println("Quel tuteur voulez vous fixer ? (entrez un login)");
                    tuteur = br.readLine();
                    System.out.println("Et avec quel tutoré voulez vous le fixer ? (entrez un login)");
                    tutore = br.readLine();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
                int tuteuridx=-1;
                int tutoreidx=-1;

                for (int i = 0; i < tuteurs.size(); i++) {
                    if(tuteurs.get(i).getLogin().equals(tuteur)){
                        tuteuridx=i;
                    }
                }

                for (int i = 0; i < tutores.size(); i++) {
                    if(tutores.get(i).getLogin().equals(tutore)){
                        tutoreidx=i;
                    }
                }

                if(tutoreidx!= -1 && tuteuridx != -1){
                    Graph.fixCouple(tutores, tuteurs, tutoreidx, tuteuridx, idx); // Todo : Probleme a fix ici
                }else{
                    System.out.println("Ce couple n'existe pas. Veuillez rééssayer.");
                }
            } else if (in.equals("C")) {
                for (int i = 0; i < tuteurs.size(); i++) {
                    if(tuteurs.get(i).getName().equals("Fictif")){
                        tuteurs.remove(i);
                        i--;
                    }
                }
                for (int i = 0; i < tutores.size(); i++) {
                    if(tutores.get(i).getName().equals("Fictif")){
                        tutores.remove(i);
                        i--;
                    }
                }
                calcul = Graph.compute(tutores, tuteurs, subjects,idx);
            } else if (in.equals("A")) {
                if (absence) {
                    Graph.turnOffAbsence(tutores, idx);
                    absence = false;
                    System.out.println("\nVous avez desactive la prise en compte des absences de premiere annee dans le calcul d'affectation.");
                } else {
                    Graph.turnOnAbsence(tutores, idx);
                    absence = true;
                    System.out.println("\nVous avez active la prise en compte des absences de premiere annee dans le calcul d'affectation.");
                }
            } else if (in.equals("P")) {
                if (moyPremiere) {
                    Graph.turnOffMoyPremiere(tuteurs, idx);
                    absence = false;
                    System.out.println("\nVous avez desactive la prise en compte des moyennes de premiere annee des tuteurs dans le calcul d'affectation.");
                } else {
                    Graph.turnOnMoyPremiere(tuteurs, idx);
                    absence = true;
                    System.out.println("\nVous avez active la prise en compte des moyennes de premiere annee des tuteurs dans le calcul d'affectation.");
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

    /**
     * Renvoie les informations par rapport a la situation du tuteur donne en parametre dans une matiere, soit il n'existe pas et donc est cr�e, soit il est en file d'attente, soit il est affecte avec un/plusieurs tutores et le mail de son/ses tutore(s) est/sont envoy�(s) 
     * @param tutored : un tuteur
     * @param wait : la liste d'attente
     * @param subjects : la liste des matieres
     * @param br : un BufferedReader
     */
    public static void giveAffectation(Tutored tutored, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {
        int idx = getSubjectID(br);

        // Affiche les informations demandes ou inscrites dans la liste d'attente
        if (!(wait[idx].contains(tutored) || subjects.get(idx).contains(tutored))) {
            System.out.println("Aucune inscription de recense. Vous voici maintenant inscrit.");
            wait[idx].addTutored(tutored);
        } else {
            if (wait[idx].contains(tutored)) {
                System.out.println("Vous etes en files d'attente. Veuillez revenir plus tard.");
            } else {
                System.out.println("Vous etes accepte, vous pouvez contacter votre tuteur via cette adresse email: " + subjects.get(idx).getAffectation(tutored).getLogin() + "@univ-lille.fr");
            }
        }
    }

    /**
     * Renvoie les informations sur la situation d'un tutore donne en parametre dans une matiere donnee, non inscrit, en liste d'attente, affecte et renvoie le mail de son tuteur
     * @param tutor : un tutore
     * @param wait : la liste d'attente
     * @param subjects : la liste des matieres
     * @param br : un BufferedReader
     */
    public static void giveAffectation(Tutor tutor, WaitingList[] wait, ArrayList<Subject> subjects, BufferedReader br) {
        int idx = getSubjectID(br);

        while (idx <= 0 || idx >= 5) { // Si la matiere n'existe pas on continue de boucler
            System.out.println("Dans quel matiere voulez vous vous inscrire ?");
            try {
                idx = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Cette entree n'est pas correcte. Reessayez:");
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un chiffre entre 0 et 4 inclus. Reessayez:");
            }
        }

        // Affiche les informations demande ou inscrit dans la liste d'attente
        if (!(wait[idx].contains(tutor) || subjects.get(idx).contains(tutor))) {
            System.out.println("Aucune inscription de recense. Vous voici maintenant inscrit.");
            wait[idx].addTutor(tutor);
        } else {
            if (wait[idx].contains(tutor)) {
                System.out.println("Vous etes en files d'attente. Veuillez revenir plus tard.");
            } else {
                System.out.println("Vous etes accepte, vous pouvez contacter vos tutores via ces adresses email:");
                for (int i = 0; i < subjects.get(idx).getAffectation(tutor).size(); i++) {
                    System.out.println(subjects.get(idx).getAffectation(tutor).get(i).getLogin() + "@univ-lille.fr");
                }
            }
        }
    }
}
