package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.LogInManagement;
import main.Users.Teacher;

import org.junit.jupiter.api.BeforeEach;

public class LogInManagementTest {
    Teacher t1;
    LogInManagement l1, l2;
    @BeforeEach
    public void beforeATest() {
        t1 = new Teacher("delille", "isabelle", "id");
        l1 = new LogInManagement();
        l2 = new LogInManagement(t1);
    }

    @Test
    public void testToString() { 
        assertEquals("null", l1.toString());
        assertEquals(t1.toString(), l2.toString());
    }
}
