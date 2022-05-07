package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import main.Graph;
import main.Student;

public class GraphTest {
    ArrayList<Student> tuteurs,tutoré;
    GrapheNonOrienteValue<Student> graph;
    CalculAffectation<Student> calcul;
    
    
    @BeforeEach
    public void beforeATest(){
        tuteurs = new ArrayList<>();
        tutoré = new ArrayList<>();
        graph = Graph.createGraph(tutoré, tuteurs);

        calcul = new CalculAffectation<Student>(graph, tuteurs, tutoré);
    }

    @Test
    public void test_affectation() {
        for(int i = 0; i<6;i++){
            assertTrue(calcul.getAffectation().contains(new Arete<>(tuteurs.get(i), tutoré.get(i))));
        }
    }
    
}