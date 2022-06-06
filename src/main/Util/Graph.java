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

/**
 * Class permetant de generer des graphes et de calculer des affectations en
 * foctions de ces graphes Les graphes sont generer a partir de criteres que
 * l'on peut activer et desactiver
 */
public abstract class Graph {

    /**
     * Coefficient que l'on applique pour fixer des affcetions manuellement
     */
    private static int idx = 5000;

    /**
     * Permet de creer un graphe a partir de deux listes et d'une matiere
     * 
     * @param tutore    : Liste de tutores
     * @param tuteurs   : Liste de tuteurs
     * @param subjectID : Entier identifiant une matiere
     * @return gragh : graphe reliant tuteurs et tutores
     * @see Tutor
     * @see Tutored
     */
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

        Double malus = 1.0; // On cr�e donc les arr�tes avec les consignes fourni dans le rapport
        for (int i = 0; i < tuteurs.size(); i++) {
            for (int j = 0; j < tutoré.size(); j++) {
                graph.ajouterArete(tuteurs.get(i), tutoré.get(j), (tutoré.get(j).getScore()[subjectID] - tuteurs.get(i).getScore()[subjectID]) * malus);
                malus = malus - 0.02;
            }
        }
        return graph;
    }

    /**
     * Permet de trier une liste de tutores donnee dans une matiere en fonctions des
     * absences Cela permet d'activer le critere des absences pour les affectations
     * 
     * @param tutore    : Liste de tutores
     * @param subjectID : Entier identifiant une matiere
     */
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

    /**
     * Permet de trier une liste de tutores donnee dans une matiere en excluant les
     * absences Cela permet de desactiver le critere des absences pour les
     * affectations
     * 
     * @param tutore    : Liste de tutores
     * @param subjectID : Entier identifiant une matiere
     */
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

    /**
     * Permet de trier une liste de tuteur donnee dans une matiere en fonctions de
     * la moyenne de premiere annee Cela permet d'activer le critere de moyenne de
     * premiere annee pour les affectations
     * 
     * @param tutor     : Liste de tuteurs
     * @param subjectID : Entier identifiant une matiere
     */
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

    /**
     * Permet de trier une liste de tuteur donnee dans une matiere en excluant la
     * moyenne de premiere annee Cela permet de desactiver le critere de moyenne de
     * premiere annee pour les affectations
     * 
     * @param tutor     : Liste de tuteurs
     * @param subjectID : Entier identifiant une matiere
     */
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



    /**
     * Permet de fixer un couple de tuteur et tutore se trouvant dans les listes pour une matiere donnee
     * @param tutoreList : Liste de tutores
     * @param tuteurList : Lsite de tuteurs
     * @param tutore : entier identifiant un tutore dans la liste
     * @param tuteurs : entier identifiant un tuteur dans la liste
     * @param subjectID: entier identifiant une matiere
     */
    public static void fixCouple(ArrayList<Tutored> tutoréList, ArrayList<Tutor> tuteurList, int tutoré, int tuteur, int subjectID) {
        // On fixe les couples de notre choix. On ne peux pas fixer un couple avec un
        // Etudiants qui a dejà ete fixe
        if (!tuteurList.get(tuteur).getFixed()[subjectID] && !tutoréList.get(tutoré).getFixed()[subjectID]) {
            tuteurList.get(tuteur).getScore()[subjectID] = idx;
            tuteurList.get(tuteur).setFixed(true, subjectID);
            tutoréList.get(tutoré).getScore()[subjectID] = -idx;
            tutoréList.get(tutoré).setFixed(true, subjectID);
            idx += 1;

            for (Tutor tutor : tuteurList) {
                tutor.setTmp(subjectID);
            }

            for (Tutored t : tutoréList) {
                t.setTmp(subjectID);
            }
            Collections.sort(tutoréList);
            Collections.sort(tuteurList);
        } else {
            System.out.println("Au moins un des etudiants a déjà été fixé !");
        }
    }


    
    /**
     * Permet de calculer les affectations pour former des couples de tuteurs et de tutores pour une matiere donnee
     * Passage par une creation de graphe pour calculer les meilleurs affectations
     * @param tutores : Liste de tutores
     * @param tuteurs : Liste de tuteurs
     * @param subjetcs : Liste de matiere
     * @param subjectID : entier identifiant la matiere dans la liste
     * @return calcul : Calcul d'affectation d'un graphe et de deux listes
     * @see CalculeAffectation
     */
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
                    if (tuteurs.get(i).getLevel().equals(Level.THIRD)) {
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
        ArrayList<Student> tutoresG = new ArrayList<>();
        for (Tutored s : tutorés) {
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
