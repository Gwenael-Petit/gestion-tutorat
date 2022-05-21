package test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import main.Users.Student;
import main.Util.Graph;

public class GraphTest {/*
    ArrayList<Student> tuteurs,tutoré;
    GrapheNonOrienteValue<Student> graph;
    
    
    @BeforeEach
    public void beforeATest(){
        tuteurs = new ArrayList<Student>();
        tutoré = new ArrayList<Student>();
    }

    @Test
    public void test_fixCouple() {
        Graph.fillTab(tutoré, tuteurs);
        graph = Graph.createGraph(tutoré, tuteurs);
        Graph.fixCouple(tutoré, tuteurs, 5, 0);
        assertEquals("[Bernard;-5000.0;1] -> [William;5000.0;3]", tutoré.get(0).toString()+" -> "+tuteurs.get(0).toString());
        Graph.fixCouple(tutoré, tuteurs, 3, 3);
        assertEquals("[Delphine;-5001.0;1] -> [Zoé;5001.0;2]", tutoré.get(0).toString()+" -> "+tuteurs.get(0).toString());
    }
    
    @Test
    public void test_fillTab() {
        Graph.fillTab(tutoré, tuteurs);
        assertEquals("[[Edouard;6.0;1], [Caroline;8.0;1], [Delphine;9.0;1], [Alexandre;11.0;1], [Fabrice;12.0;1], [Bernard;14.0;1]]", tutoré.toString());
        assertEquals("[[William;25.0;3], [Xavier;24.0;3], [Vincent;22.0;3], [Zoé;16.0;2], [Ylann;12.0;2], [Fictif1;1.0;2]]", tuteurs.toString());
    }

    @Test
    public void test_fillTabAvecAbsence() {
        Graph.fillTabAvecAbsence(tutoré, tuteurs);
        assertEquals("[[Edouard;6.0;1], [Caroline;8.4;1], [Delphine;9.1;1], [Alexandre;11.1;1], [Fabrice;12.3;1], [Bernard;14.0;1]]", tutoré.toString());
        assertEquals("[[William;25.0;3], [Xavier;24.0;3], [Vincent;22.0;3], [Zoé;16.0;2], [Ylann;12.0;2], [Fictif1;1.0;2]]", tuteurs.toString());
    }

    @Test
    public void test_fillTabAvecMoyPremiere() {
        Graph.fillTabAvecMoyPremiere(tutoré, tuteurs);
        assertEquals("[[Edouard;6.0;1], [Caroline;8.0;1], [Delphine;9.0;1], [Alexandre;11.0;1], [Fabrice;12.0;1], [Bernard;14.0;1]]", tutoré.toString());
        assertEquals("[[William;26.3;3], [Xavier;25.8;3], [Vincent;23.0;3], [Zoé;17.7;2], [Ylann;13.3;2], [Fictif1;1.0;2]]", tuteurs.toString());
    }

    @Test
    public void test_createGraph(){
        Graph.fillTab(tutoré, tuteurs);
        graph = Graph.createGraph(tutoré, tuteurs);
        assertEquals(-19.0, Math.floor(graph.getPoids(tuteurs.get(0), tutoré.get(0))*100)/100 );
        assertEquals(0, Math.floor(graph.getPoids(tuteurs.get(4), tutoré.get(4))*100)/100);
        assertEquals(-6.4, Math.floor(graph.getPoids(tuteurs.get(3), tutoré.get(0))*100)/100 );
        assertEquals(-9.6, Math.floor(graph.getPoids(tuteurs.get(1), tutoré.get(4))*100)/100 );
    }*/
}