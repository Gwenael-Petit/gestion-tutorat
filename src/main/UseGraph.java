package main;
import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class UseGraph {
    public static void main(String[] args) {
        // On initialise nos deux listes
        ArrayList<Student> tuteurs = new ArrayList<Student>();
        ArrayList<Student> tutoré = new ArrayList<Student>();

        // On rempli nos listes
        // Graph.fillTab(tutoré, tuteurs);     
        // Graph.fillTabAvecAbsence(tutoré, tuteurs); 
        Graph.fillTabAvecMoyPremiere(tutoré, tuteurs);

        // On fixe les couples de notre choix
        Graph.fixCouple(tutoré, tuteurs, 5, 0);
        Graph.fixCouple(tutoré, tuteurs, 3, 3);

        // On crée le graph avec nos deux listes
        System.out.println("Tuteur : \n-------------------");
        System.out.println(tuteurs.toString().replace(", ", ",\n"));
        System.out.println("Tutoré : \n-------------------");
        System.out.println(tutoré.toString().replace(", ", ",\n"));
        GrapheNonOrienteValue<Student> graph = Graph.createGraph(tutoré, tuteurs);

        // On calcul les affectations
        CalculAffectation<Student> test = new CalculAffectation<Student>(graph, tuteurs, tutoré);

        // On les affiches
        System.out.println("Affectation : \n-------------------");
        System.out.println(test.getAffectation().toString().replace("]), ", "]),\n"));
    }
}
