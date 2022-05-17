package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Users.Student;

public class StudentTest {
    Student s1, s2, s3;
    @BeforeEach
    public void beforeATest(){
        s1 = new Student("Jhonson", "Maxime","login","password", "13.5", "3","12.3");
        s2 = new Student("Leleu", "Simon","login","password", "12", "2","14.0");
        s3 = new Student("Smith", "Arthur","login","password", "16", "1","15");
    }

    @Test
    public void test_toString() {
        assertEquals("[Maxime;24.73;3]", s1.toString());
        assertEquals("[Simon;13.4;2]", s2.toString());
        assertEquals("[Arthur;17.5;1]", s3.toString());
        assertNotEquals("[Maxime;23.5,3]", s1.toString());
        assertNotEquals(s3.toString(), s2.toString());
    }
    
    @Test
    public void test_compareTo(){
        assertTrue(s1.compareTo(s2)<0);
        assertTrue(s2.compareTo(s1)>0);
    }

    @Test
    public void test_equals(){
        assertFalse(s1.equals(s2));
        assertFalse(s2.equals(s3));
        assertTrue(s3.equals(s3));
    }
    
}