package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import main.Subject;
import main.Users.Person;
import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;
import main.Util.CsvFileHelper;

public class CSVFileHelperTest {/*
	List<List<String>> l;
	String path;
	String delimiter;
	ArrayList<Subject> subjects;
	BufferedReader br;

	Tutor t1;
	Tutor t2;
	Tutored t3;
	Tutored t4;
	Teacher t5;

	Tutored t6;
	
	@BeforeEach
    public void beforeATest() throws FileNotFoundException{
        l = new ArrayList<>();
		path = "res.csv";
		delimiter=",";
		subjects = new ArrayList<>();
        String[] subjectNames = { "Math", "Base de Donnée", "Java", "Réseau" };
        for (int i = 0; i < subjectNames.length; i++) {
            subjects.add(new Subject(50, subjectNames[i], i));
        }

		double[] t1m={1.682,10.42,1.568,12.09,2.409};
        t1 = new Tutor("houhou", "abdelmalek", "ah",t1m,"2","5");// 0
		double[] t2m={0.727,2.505,1.664,13.44,12.84};
		t2 = new Tutor("bonnet", "tanguy", "tb", t2m, "3","0"); // 248
		double[] t3m={17.15,18.54,7.538,11.47,5.678};
        t3 = new Tutored("sotoca", "corentin", "cs", t3m, "1","19"); // 78
		double[] t4m={2.098,8.087,15.09,14.65,1.227};
        t4 = new Tutored("mansue", "clement", "cm", t4m, "1","6"); // 74
		ArrayList<Subject> list = new ArrayList<>();
		list.add(subjects.get(1));
		t5 = new Teacher("delille", "isabelle", "isabelle.delille", "id",list); // 285

        t6 = new Tutored("0", "fictif", "f0", t4m, "1","0");

		br = new BufferedReader(new FileReader(path));
	}

	@Test
    public void test_getCSV() throws IOException{
		l = CsvFileHelper.getCSV(path, delimiter);
		
		assertEquals(t1.getName(), l.get(0).get(0)); 
		assertEquals(t2.getLastName(), l.get(248).get(1));
		assertEquals(t3.getModifier(), l.get(78).get(9));
		assertEquals(t4.getPassword(), l.get(74).get(2));
		assertEquals(t5.getSubjects().get(0).getId(), l.get(285).get(3));
    }

	@Test
    public void test_csvToList() throws IOException{
		ArrayList<Person> list = CsvFileHelper.csvToList(path,delimiter,subjects);

		assertTrue(list.contains(t1)); 
		assertTrue(list.contains(t2)); 
		assertTrue(list.contains(t3)); 
		assertTrue(list.contains(t4)); 
		assertTrue(list.contains(t5)); 
		assertFalse(list.contains(t6));
    }*/
}
