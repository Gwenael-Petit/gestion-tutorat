package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import main.Subject;
import main.Users.Teacher;

public class TeacherTest {
    Teacher t1, t2;

    @BeforeEach
    public void beforeATest() {
        t1 = new Teacher("delille", "isabelle", "id");
        t2 = new Teacher("mathieu", "philippe", "pm");

    }

    // Ce test plante sur nos machine mais ne devrais pas. Un des deux strings a 38 char alors que l'autre en a 37, impossible de trouver ou est celui en trop
    @Test
    public void test_toString() { 
        assertEquals("[Login : philippe.mathieu ; PWD : pm]\n",t2.toString());
        assertEquals("[Login : isabelle.delille ; PWD : id]\n",t1.toString());
    }

    @Test
    public void test_Subject(){
        Subject s = new Subject(50, "Oui", 1);
        t1.addSubjects(s);
        assertTrue(t1.getSubjects().contains(s));
        assertFalse(t2.getSubjects().contains(s));
        t1.removeSubjects(s);
        assertFalse(t1.getSubjects().contains(s));
    }

    @Test
    public void test_equals(){
        Teacher t3 = new Teacher("delille", "isabelle", "id");
        assertTrue(t3.equals(t1));
        assertFalse(t3.equals(t2));
    }

}
