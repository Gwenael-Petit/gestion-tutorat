package main;
import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class UseGraph {
    public static void main(String[] args) {
        ArrayList<Student> tuteurs = new ArrayList<>();
        ArrayList<Student> tutoré = new ArrayList<>();
        GrapheNonOrienteValue<Student> graph = Graph.createGraph(tutoré, tuteurs);

        CalculAffectation<Student> test = new CalculAffectation<Student>(graph, tuteurs, tutoré);
        System.out.println(test.getAffectation().toString().replace("]), ", "]),\n"));
    }
}
