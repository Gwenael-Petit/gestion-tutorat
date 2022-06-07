package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import main.Subject;
import main.Users.Student;
import main.Users.Tutor;
import main.Users.Tutored;
import main.Util.Graph;

public class GraphTest {
    ArrayList<Tutor> tuteurs;
    ArrayList<Tutored> tutore;
    GrapheNonOrienteValue<Student> graph;

    List<List<String>> l;
    String path;
    String delimiter;
    ArrayList<Subject> subjects;
    BufferedReader br;

    Tutor t1;
    Tutor t2;
    Tutored t3;
    Tutored t4;
    Tutored t6;

    @BeforeEach
    public void beforeATest() {
        l = new ArrayList<>();
        path = "res.csv";
        delimiter = ",";
        subjects = new ArrayList<>();
        String[] subjectNames = { "Math", "Base de Donnée", "Java", "Réseau" };
        for (int i = 0; i < subjectNames.length; i++) {
            subjects.add(new Subject(50, subjectNames[i], i));
        }

        double[] t1m = { 1.682, 10.42, 1.568, 12.09, 2.409 };
        t1 = new Tutor("houhou", "abdelmalek", "ah", t1m, "2", "5");// 0
        double[] t2m = { 0.727, 2.505, 1.664, 13.44, 12.84 };
        t2 = new Tutor("bonnet", "tanguy", "tb", t2m, "3", "0"); // 248
        double[] t3m = { 17.15, 18.54, 7.538, 11.47, 5.678 };
        t3 = new Tutored("sotoca", "corentin", "cs", t3m, "1", "19"); // 78
        double[] t4m = { 2.098, 8.087, 15.09, 14.65, 1.227 };
        t4 = new Tutored("mansue", "clement", "cm", t4m, "1", "6"); // 74
        double[] t6m = { 20, 20, 20, 20, 20 };
        t6 = new Tutored("0", "fictif", "f0", t6m, "1", "0");

        tuteurs = new ArrayList<Tutor>();
        tutore = new ArrayList<Tutored>();

        tuteurs.add(t1);
        tuteurs.add(t2);

        tutore.add(t3);
        tutore.add(t4);
        tutore.add(t6);
    }

    @Test
    public void testCreateGraph() {
        graph = Graph.createGraph(tutore, tuteurs, 1);
        assertEquals(5.58, Math.floor(graph.getPoids(tuteurs.get(0), tutore.get(0)) * 100) / 100);
        assertEquals(7.47, Math.floor(graph.getPoids(tuteurs.get(1), tutore.get(1)) * 100) / 100);
    }

    @Test
    public void testAbsences() throws CloneNotSupportedException {
        double moyenne = tutore.get(0).getScore()[1];
        Tutored t = tutore.get(0);
        for (int i = 0; i < 2; i++) {
            
            Graph.turnOnAbsence(tutore, 1);
            assertEquals(t.getScore()[1], moyenne + t.getModifier() * 0.1);
        }

        for (int i = 0; i < 2; i++) {
            Graph.turnOffAbsence(tutore, 1);
            assertEquals(t.getScore()[1], moyenne);
        }

    }

    @Test
    public void testMoyPremiere() throws CloneNotSupportedException {
        double moyenne = tuteurs.get(0).getScore()[1];
        Tutor t = tuteurs.get(0);
        for (int i = 0; i < 2; i++) {
            Graph.turnOnMoyPremiere(tuteurs, 1);
            assertEquals(t.getScore()[1], moyenne + t.getModifier() * 0.1);
        }
        for (int i = 0; i < 2; i++) {
            Graph.turnOffMoyPremiere(tuteurs, 1);
            assertEquals(t.getScore()[1], moyenne);
        }
    }

    @Test
    public void testFixCouple() {
        Graph.fixCouple(tutore, tuteurs, 1, 0, 1);
        assertEquals("clement.mansue.etu : -5000.0 -> abdelmalek.houhou.etu : 5000.0", tutore.get(0).getLogin() + " : " + tutore.get(0).getScore()[1] + " -> " + tuteurs.get(0).getLogin() + " : " + tuteurs.get(0).getScore()[1]);
    }

    @Test
    public void testCompute() {
        CalculAffectation<Student> calcul = Graph.compute(tutore, tuteurs, subjects, 1);
        assertEquals("[Arete([Login : tanguy.bonnet.etu ; Score : [ 0.727, 2.505, 1.664, 13.44, 12.84 ] ; Level = THIRD], [Login : fictif.0.etu ; Score : [ 20.0, 20.0, 20.0, 20.0, 20.0 ] ; Level = FIRST]), Arete([Login : tanguy.bonnet.etu ; Score : [ 0.727, 2.505, 1.664, 13.44, 12.84 ] ; Level = THIRD], [Login : clement.mansue.etu ; Score : [ 2.098, 8.087, 15.09, 14.65, 1.227 ] ; Level = FIRST]), Arete([Login : abdelmalek.houhou.etu ; Score : [ 1.682, 10.42, 1.568, 12.09, 2.409 ] ; Level = SECOND], [Login : corentin.sotoca.etu ; Score : [ 17.15, 18.54, 7.538, 11.47, 5.678 ] ; Level = FIRST])]",calcul.getAffectation().toString());
    }

}