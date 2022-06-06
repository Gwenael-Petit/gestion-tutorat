package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Users.Student;

public class StudentTest {
    Student s1, s2, s3, s4;

    @BeforeEach
    public void beforeATest() {
        double[] t1m = { 1.682, 10.42, 1.568, 12.09, 2.409 };
        double[] t2m = { 0.727, 2.505, 1.664, 13.44, 12.84 };
        double[] t3m = { 17.15, 18.54, 7.538, 11.47, 5.678 };
        s1 = new Student("jhonson", "maxime", "password", t1m, "2", "15");
        s2 = new Student("leleu", "simon", "password", t2m, "1", "5");
        s3 = new Student("smith", "arthur", "password", t3m, "3", "12");
        s4 = new Student("petit", "arnold", "password", t3m, "1", "5");
    }

    @Test
    public void test_toString() {
        assertEquals("[Login : maxime.jhonson.etu ; Score : [ 1.682, 10.42, 1.568, 12.09, 2.409 ] ; Level = second]", s1.toString());
        assertEquals("[Login : simon.leleu.etu ; Score : [ 0.727, 2.505, 1.664, 13.44, 12.84 ] ; Level = first]", s2.toString());
        assertEquals("[Login : arthur.smith.etu ; Score : [ 17.15, 18.54, 7.538, 11.47, 5.678 ] ; Level = third]", s3.toString());
        assertNotEquals(s3.toString(), s2.toString());
    }

    @Test
    public void test_compareTo() {
        s1.setTmp(1);
        s2.setTmp(1);
        s3.setTmp(1);
        s4.setTmp(1);
        assertTrue(s1.compareTo(s3) > 0);
        assertTrue(s3.compareTo(s1) < 0);
        assertTrue(s2.compareTo(s4)<0);
        assertTrue(s4.compareTo(s2)>0);
    }

    @Test
    public void test_equals() {
        assertFalse(s1.equals(s2));
        assertFalse(s2.equals(s3));
        assertTrue(s3.equals(s3));
    }

}