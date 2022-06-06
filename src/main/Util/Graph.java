package main.Util;

import java.util.ArrayList;
import java.util.Collections;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import main.Users.Student;
import main.Users.Tutor;
import main.Users.Tutored;
import main.Subject;
import main.Users.Level;

public abstract class Graph {
    private static int idx = 5000;

    public static GrapheNonOrienteValue<Student> createGraph(ArrayList<Tutored> tutoré, ArrayList<Tutor> tuteurs, int subjectID) {

        for (Tutor s : tuteurs) {
            s.setTmp(subjectID);
        }
        Collections.sort(tuteurs);
        for (Tutored s : tutoré) {
            s.setTmp(subjectID);
        }
        Collections.sort(tutoré);

        GrapheNonOrienteValue<Student> graph = new GrapheNonOrienteValue<>();

        for (int i = 0; i < tutoré.size(); i++) {
            graph.ajouterSommet(tutoré.get(i));
        }

        for (int i = 0; i < tuteurs.size(); i++) {
            graph.ajouterSommet(tuteurs.get(i));
        }

        Double malus = 1.0; // On crée donc les arrétes avec les consignes fourni dans le rapport
        for (int i = 0; i < tuteurs.size(); i++) {
            for (int j = 0; j < tutoré.size(); j++) {
                graph.ajouterArete(tuteurs.get(i), tutoré.get(j), (tutoré.get(j).getScore()[subjectID] - tuteurs.get(i).getScore()[subjectID]) * malus);
                malus = malus - 0.02;
            }
        }
        return graph;
    }

    public static void turnOnAbsence(ArrayList<Tutored> tutoré, int subjectID) {
        if (!tutoré.get(0).isAbsences()) {
            for (int i = 0; i < tutoré.size(); i++) {
                if (!tutoré.get(i).getFixed()[subjectID]) {
                    tutoré.get(i).getScore()[subjectID] = tutoré.get(i).getScore()[subjectID] + tutoré.get(i).getModifier() * 0.1;
                }

            }
            for (Tutored s : tutoré) {
                s.setTmp(subjectID);
                s.setAbsences(true);
            }
            Collections.sort(tutoré);
        }
    }

    public static void turnOffAbsence(ArrayList<Tutored> tutoré, int subjectID) {
        if (tutoré.get(0).isAbsences()) {
            for (int i = 0; i < tutoré.size(); i++) {
                if (!tutoré.get(i).getFixed()[subjectID]) {
                    tutoré.get(i).getScore()[subjectID] = tutoré.get(i).getScore()[subjectID] - tutoré.get(i).getModifier() * 0.1;
                }
            }
            for (Tutored s : tutoré) {
                s.setTmp(subjectID);
                s.setAbsences(false);
            }
            Collections.sort(tutoré);
        }
    }

    public static void turnOnMoyPremiere(ArrayList<Tutor> tutor, int subjectID) {
        if (!tutor.get(0).isMoyPremiere()) {

            for (int i = 0; i < tutor.size(); i++) {
                if (!tutor.get(i).getFixed()[subjectID]) {
                    tutor.get(i).getScore()[subjectID] = tutor.get(i).getScore()[subjectID] + tutor.get(i).getModifier() * 0.1;
                }
            }
            for (Tutor s : tutor) {
                s.setTmp(subjectID);
                s.setMoyPremiere(true);
            }
            Collections.sort(tutor);
        }
    }

    public static void turnOffMoyPremiere(ArrayList<Tutor> tutor, int subjectID) {
        if (tutor.get(0).isMoyPremiere()) {

            for (int i = 0; i < tutor.size(); i++) {
                if (!tutor.get(i).getFixed()[subjectID]) {
                    tutor.get(i).getScore()[subjectID] = tutor.get(i).getScore()[subjectID] - tutor.get(i).getModifier() * 0.1;
                }
            }
            for (Tutor s : tutor) {
                s.setTmp(subjectID);
                s.setMoyPremiere(false);
            }
            Collections.sort(tutor);
        }
    }

    public static void fixCouple(ArrayList<Tutored> tutoréList, ArrayList<Tutor> tuteurList, int tutoré, int tuteur, int subjectID) {
        // On fixe les couples de notre choix. On ne peux pas fixer un couple avec un
        // étudiants qui a déjà été fixé
        if (!tuteurList.get(tuteur).getFixed()[subjectID] && !tutoréList.get(tutoré).getFixed()[subjectID]) {
            tuteurList.get(tuteur).getScore()[subjectID] = idx;
            tuteurList.get(tuteur).setFixed(true, subjectID);
            tutoréList.get(tutoré).getScore()[subjectID] = -idx;
            tutoréList.get(tutoré).setFixed(true, subjectID);
            idx += 1;
            Collections.sort(tutoréList);
            Collections.sort(tuteurList);
        } else {
            System.out.println("Au moins un des etudiants a déjà été fixé !");
        }
    }

    public static CalculAffectation<Student> compute(ArrayList<Tutored> tutorés, ArrayList<Tutor> tuteurs, ArrayList<Subject> subjects, int subjectID) {
        int idx = 0;
        while (tutorés.size() != tuteurs.size()) {
            if (tutorés.size() < tuteurs.size()) {
                double[] tmp = new double[5];
                for (int i = 0; i < 5; i++) {
                    tmp[i] = (20 + idx * 0.1);
                }
                tutorés.add(new Tutored("" + idx, "Fictif", "password", tmp, "1", "0"));
            } else if (tutorés.size() > tuteurs.size()) {
                Tutor res = null;
                for (int i = 0; i < tuteurs.size(); i++) {
                    if (tuteurs.get(i).getLevel().equals(Level.third)) {
                        try {
                            res = tuteurs.get(i).clone();
                        } catch (CloneNotSupportedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                if (res == null) {
                    double[] tmp = new double[5];
                    for (int i = 0; i < 5; i++) {
                        tmp[i] = (0 - idx * 0.1);
                    }
                    res = new Tutor("" + idx, "Fictif", "password", tmp, "2", "0");
                }
                tuteurs.add(res);
            }
        }

        GrapheNonOrienteValue<Student> graph = Graph.createGraph(tutorés, tuteurs, subjectID);
        ArrayList<Student> tuteursG = new ArrayList<>();
        ArrayList<Student> tutorésG = new ArrayList<>();
        for (Tutored s : tutorés) {
            tutorésG.add((Student) s);
        }

        for (Tutor s : tuteurs) {
            tuteursG.add((Student) s);
        }

        CalculAffectation<Student> calcul = new CalculAffectation<Student>(graph, tuteursG, tutorésG);

        for (int i = 0; i < calcul.getAffectation().size(); i++) {
            Student s1 = calcul.getAffectation().get(i).getExtremite1();
            Student s2 = calcul.getAffectation().get(i).getExtremite2();
            tuteurs = new ArrayList<>();
            tutorés = new ArrayList<>();
            if (s1 instanceof Tutor) {
                tuteurs.add((Tutor) s1);
            } else {
                tutorés.add((Tutored) s1);
            }
            if (s2 instanceof Tutor) {
                tuteurs.add((Tutor) s2);
            } else {
                tutorés.add((Tutored) s2);
            }
        }
        return calcul;
    }
}
