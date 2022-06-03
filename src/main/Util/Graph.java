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

    public static GrapheNonOrienteValue<Student> createGraph(ArrayList<Tutored> tutore, ArrayList<Tutor> tuteurs,
            int subjectID) {

        for (Tutor s : tuteurs) {
            s.setTmp(subjectID);
        }
        Collections.sort(tuteurs);
        for (Tutored s : tutore) {
            s.setTmp(subjectID);
        }
        Collections.sort(tutore);

        GrapheNonOrienteValue<Student> graph = new GrapheNonOrienteValue<>();

        for (int i = 0; i < tutore.size(); i++) { // tuteurs.size() = tutore.size() pour la partie graph
            graph.ajouterSommet(tutore.get(i));
            graph.ajouterSommet(tuteurs.get(i));
        }

        Double malus = 1.0; // On crÈe donc les arrËtes avec les consignes fourni dans le rapport
        for (int i = 0; i < tuteurs.size(); i++) {
            for (int j = 0; j < tutore.size(); j++) {
                graph.ajouterArete(tuteurs.get(i), tutore.get(j),
                        (tutore.get(j).getScore()[subjectID] - tuteurs.get(i).getScore()[subjectID]) * malus);
                malus = malus - 0.02;
            }
        }
        return graph;
    }

    public static void turnOnAbsence(ArrayList<Tutored> tutore, int subjectID) {
        for (int i = 0; i < tutore.size(); i++) {
            if (!tutore.get(i).getFixed()[subjectID]) {
                tutore.get(i).getScore()[subjectID] = tutore.get(i).getScore()[subjectID]
                        + tutore.get(i).getModifier() * 0.1;
            }

        }
        for (Tutored s : tutore) {
            s.setTmp(subjectID);
        }
        Collections.sort(tutore);
    }

    public static void turnOffAbsence(ArrayList<Tutored> tutore, int subjectID) {
        for (int i = 0; i < tutore.size(); i++) {
            if (!tutore.get(i).getFixed()[subjectID]) {
                tutore.get(i).getScore()[subjectID] = tutore.get(i).getScore()[subjectID]
                        - tutore.get(i).getModifier() * 0.1;
            }
        }
        for (Tutored s : tutore) {
            s.setTmp(subjectID);
        }
        Collections.sort(tutore);
    }

    public static void turnOnMoyPremiere(ArrayList<Tutor> tutor, int subjectID) {
        for (int i = 0; i < tutor.size(); i++) {
            if (!tutor.get(i).getFixed()[subjectID]) {
                tutor.get(i).getScore()[subjectID] = tutor.get(i).getScore()[subjectID]
                        + tutor.get(i).getModifier() * 0.1;
            }
        }
        for (Tutor s : tutor) {
            s.setTmp(subjectID);
        }
        Collections.sort(tutor);
    }

    public static void turnOffMoyPremiere(ArrayList<Tutor> tutor, int subjectID) {
        for (int i = 0; i < tutor.size(); i++) {
            if (!tutor.get(i).getFixed()[subjectID]) {
                tutor.get(i).getScore()[subjectID] = tutor.get(i).getScore()[subjectID]
                        - tutor.get(i).getModifier() * 0.1;
            }
        }
        for (Tutor s : tutor) {
            s.setTmp(subjectID);
        }
        Collections.sort(tutor);
    }

    public static void fixCouple(ArrayList<Student> tutoreList, ArrayList<Student> tuteurList, int tutore, int tuteur,
            int subjectID) {
        // On fixe les couples de notre choix. On ne peux pas fixer un couple avec un
        // Etudiants qui a deja† ete fixe
        if (!tuteurList.get(tuteur).getFixed()[subjectID] && !tutoreList.get(tutore).getFixed()[subjectID]) {
            tuteurList.get(tuteur).getScore()[subjectID] = idx;
            tuteurList.get(tuteur).setFixed(true, subjectID);
            tutoreList.get(tutore).getScore()[subjectID] = idx;
            tutoreList.get(tutore).setFixed(true, subjectID);
            idx += 1;
            Collections.sort(tutoreList);
            Collections.sort(tuteurList);
        } else {
            System.out.println("Au moins un des etudiants a d√©j√† √©t√© fix√© !");
        }
    }

    public static CalculAffectation<Student> compute(ArrayList<Tutored> tutores, ArrayList<Tutor> tuteurs, ArrayList<Subject> subjects, int subjectID) {
        int idx=0;
        while(tutores.size()!=tuteurs.size()){
            if(tutores.size()<tuteurs.size()){
                double[] tmp = new double[5];
                for(int i =0; i<5;i++){
                    tmp[i]=(20+idx*0.1);
                }
                tutores.add(new Tutored(""+idx, "Fictif", "password", tmp, "1"));
            }else if (tutores.size()>tuteurs.size()){   
                Tutor res=null;
                for (int i = 0; i < tuteurs.size(); i++) {
                    if(tuteurs.get(i).getLevel().equals(Level.third)){
                        try{
                            res =  tuteurs.get(i).clone();
                        }catch(CloneNotSupportedException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
                if(res==null){
                    double[] tmp = new double[5];
                    for(int i =0; i<5;i++){
                        tmp[i]=(0-idx*0.1);
                    }
                    res = new Tutor(""+idx, "Fictif", "password", tmp, "2");
                }
                tuteurs.add(res);
            }
        }

        GrapheNonOrienteValue<Student> graph = Graph.createGraph(tutores, tuteurs, subjectID);
        ArrayList<Student> tuteursG = new ArrayList<>();
        ArrayList<Student> tutoresG = new ArrayList<>();
        for (Tutored s : tutores) {
            tutoresG.add((Student) s);
        }

        for (Tutor s : tuteurs) {
            tuteursG.add((Student) s);
        }

        CalculAffectation<Student> calcul = new CalculAffectation<Student>(graph, tuteursG, tutoresG);

        for (int i = 0; i < calcul.getAffectation().size(); i++) {
            Student s1 = calcul.getAffectation().get(i).getExtremite1();
            Student s2 = calcul.getAffectation().get(i).getExtremite2();
            tuteurs = new ArrayList<>();
            tutores = new ArrayList<>();
            if (s1 instanceof Tutor) {
                tuteurs.add( (Tutor) s1);
            } else {
                tutores.add( (Tutored) s1);
            }
            if (s2 instanceof Tutor) {
                tuteurs.add( (Tutor) s2);
            } else {
                tutores.add( (Tutored) s2);
            }
        }
        return calcul;
    }
}
