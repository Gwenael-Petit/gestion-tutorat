package main.Util;

import java.util.ArrayList;
import java.util.Collections;

import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import main.Users.Student;
import main.Users.Tutor;
import main.Users.Tutored;

public abstract class Graph {
    private static int idx = 5000;

    public static GrapheNonOrienteValue<Student> createGraph(ArrayList<Tutored> tutoré, ArrayList<Tutor> tuteurs, int subjectID) {
        GrapheNonOrienteValue<Student> graph = new GrapheNonOrienteValue<>();

        for (int i = 0; i < tutoré.size(); i++) { // tuteurs.size() = tutoré.size() pour la partie graph
            graph.ajouterSommet(tutoré.get(i));
            graph.ajouterSommet(tuteurs.get(i));
        }

        Double malus = 1.0; // On crée donc les arrétes avec les consignes fourni dans le rapport
        for (int i = 0; i < tuteurs.size(); i++) {
            for (int j = 0; j < tutoré.size(); j++) {
                graph.ajouterArete(tuteurs.get(i), tutoré.get(j),
                        (tutoré.get(j).getScore()[subjectID] - tuteurs.get(i).getScore()[subjectID]) * malus);
                malus = malus - 0.02;
            }
        }
        return graph;
    }

    public static void turnOnAbsence(ArrayList<Tutored> tutoré, int subjectID) { 
        for (int i = 0; i < tutoré.size(); i++) {
            tutoré.get(i).getScore()[subjectID]=tutoré.get(i).getScore()[subjectID]+tutoré.get(i).getModifier()*0.1;
        }
        Collections.sort(tutoré);
    }

    public static void turnOffAbsence(ArrayList<Tutored> tutoré, int subjectID) { 
        for (int i = 0; i < tutoré.size(); i++) {
            tutoré.get(i).getScore()[subjectID]=tutoré.get(i).getScore()[subjectID]-tutoré.get(i).getModifier()*0.1;
        }
        Collections.sort(tutoré);
    }

    public static void turnOnMoyPremiere(ArrayList<Tutor> tutor, int subjectID) { 
        for (int i = 0; i < tutor.size(); i++) {
            tutor.get(i).getScore()[subjectID]=tutor.get(i).getScore()[subjectID]+tutor.get(i).getModifier()*0.1;
        }
        Collections.sort(tutor);
    }

    public static void turnOffMoyPremiere(ArrayList<Tutor> tutor, int subjectID) { 
        for (int i = 0; i < tutor.size(); i++) {
            tutor.get(i).getScore()[subjectID]=tutor.get(i).getScore()[subjectID]-tutor.get(i).getModifier()*0.1;
        }
        Collections.sort(tutor);
    }

    public static void fixCouple(ArrayList<Student> tutoréList, ArrayList<Student> tuteurList, int tutoré, int tuteur, int subjectID) {
        // On fixe les couples de notre choix. On ne peux pas fixer un couple avec un étudiants qui a déjà été fixé
        if (!tuteurList.get(tuteur).getFixed()[subjectID] && !tutoréList.get(tutoré).getFixed()[subjectID]) {
            tuteurList.get(tuteur).getScore()[subjectID] =idx;
            tuteurList.get(tuteur).setFixed(true,subjectID);
            tutoréList.get(tutoré).getScore()[subjectID] = idx;
            tutoréList.get(tutoré).setFixed(true,subjectID);
            idx += 1;
            Collections.sort(tutoréList);
            Collections.sort(tuteurList);
        } else {
            System.out.println("Au moins un des etudiants a déjà été fixé !");
        }
    }
}
