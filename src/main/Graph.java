package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public abstract class Graph {

    public static GrapheNonOrienteValue<Student> createGraph(ArrayList<Student> tutoré, ArrayList<Student> tuteurs){
        fillTab(tutoré, tuteurs);      

        GrapheNonOrienteValue<Student> graph = new GrapheNonOrienteValue<>();

        for (int i = 0; i < tutoré.size(); i++) {   // tuteurs.size() = tutoré.size()
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

    public static void fillTab(ArrayList<Student> tutoré, ArrayList<Student> tuteurs){
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
}
