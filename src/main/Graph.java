package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public abstract class Graph {
    private static int idx = 5000;

    public static GrapheNonOrienteValue<Student> createGraph(ArrayList<Student> tutoré, ArrayList<Student> tuteurs){
        GrapheNonOrienteValue<Student> graph = new GrapheNonOrienteValue<>();

        for (int i = 0; i < tutoré.size(); i++) {   // tuteurs.size() = tutoré.size() pour la partie graph
            graph.ajouterSommet(tutoré.get(i));
            graph.ajouterSommet(tuteurs.get(i));
        }

        Double malus = 1.0;                         // On crée donc les arrétes avec les consignes fourni dans le rapport
        for (int i = 0; i < tuteurs.size(); i++) {
            for (int j = 0; j < tutoré.size(); j++) {
                graph.ajouterArete(tuteurs.get(i), tutoré.get(j),(tutoré.get(j).moyenne - tuteurs.get(i).moyenne) * malus);
                malus = malus - 0.02;
            }
        }

        return graph;
    }

    public static void fillTab(ArrayList<Student> tutoré, ArrayList<Student> tuteurs){ // On rempli les listes avec comme seul critére la moyenne
        List<List<String>> res = CsvFileHelper.getCSV();
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).get(1).equals("1")) {
                Student tmp = new Student("Smith", res.get(i).get(0), res.get(i).get(2), res.get(i).get(1));
                tutoré.add(tmp);
            } else {
                Student tmp = new Student("Smith", res.get(i).get(0), res.get(i).get(2), res.get(i).get(1));
                tuteurs.add(tmp);
            }
        }
        Collections.sort(tutoré);
        Collections.sort(tuteurs);
    }

    public static void fillTabAvecAbsence(ArrayList<Student> tutoré, ArrayList<Student> tuteurs){ // On rempli les listes avec moyenne & absence pour les premiére année
        List<List<String>> res = CsvFileHelper.getCSV();
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).get(1).equals("1")) {
                Student tmp = new Student("Smith", res.get(i).get(0), res.get(i).get(2), res.get(i).get(1),res.get(i).get(3));
                tutoré.add(tmp);
            } else {
                Student tmp = new Student("Smith", res.get(i).get(0), res.get(i).get(2), res.get(i).get(1));
                tuteurs.add(tmp);
            }
        }
        Collections.sort(tutoré);
        Collections.sort(tuteurs);
    }

    public static void fillTabAvecMoyPremiere(ArrayList<Student> tutoré, ArrayList<Student> tuteurs){ // On rempli les listes avec moyenne & moyenne premiére année pour les tuteurs
        List<List<String>> res = CsvFileHelper.getCSV();
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).get(1).equals("1")) {
                Student tmp = new Student("Smith", res.get(i).get(0), res.get(i).get(2), res.get(i).get(1));
                tutoré.add(tmp);
            } else {
                Student tmp = new Student("Smith", res.get(i).get(0), res.get(i).get(2), res.get(i).get(1),res.get(i).get(3));
                tuteurs.add(tmp);
            }
        }
        Collections.sort(tutoré);
        Collections.sort(tuteurs);
    }

    public static void fixCouple(ArrayList<Student> tutoréList, ArrayList<Student> tuteurList,int tutoré, int tuteur){ // On fixe les couples de notre choix. On ne peux pas fixer un couple avec un étudiants qui a déjà été fixé
        if(!tuteurList.get(tuteur).fixed && !tutoréList.get(tutoré).fixed){
            tuteurList.get(tuteur).moyenne=idx;
            tuteurList.get(tuteur).fixed=true;
            tutoréList.get(tutoré).moyenne=-idx;
            tutoréList.get(tutoré).fixed=true;
            idx+=1;
            Collections.sort(tutoréList);
            Collections.sort(tuteurList);
        }else{
            System.out.println("Au moins un des etudiants a déjà été fixé !");
        }
    }
}
