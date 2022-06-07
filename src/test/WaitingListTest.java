package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import main.Subject;
import main.WaitingList;
import main.Users.Student;
import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;

public class WaitingListTest {
    WaitingList w;
    Subject s;
    Tutor t1;
    Tutored t2;
    @BeforeEach
    public void beforeATest() {
        s = new Subject(50, "Oui", 0);
        w = new WaitingList(s);
    }

    @Test
    public void testAdd(){
        w.addTutor(t1);
        assertTrue(w.contains(t1));
        assertFalse(w.contains(t2));
        w.addTutored(t2);
        assertTrue(w.contains(t2));
    }

    @Test
    public void testContains(){
        w.addTutor(t1);
        assertFalse(w.contains(t2));
        assertTrue(w.contains(t1));
        w.addTutored(t2);
        assertTrue(w.contains(t2));

    }

}
