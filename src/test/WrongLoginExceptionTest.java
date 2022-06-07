package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import main.Util.exceptions.WrongLoginException;


public class WrongLoginExceptionTest {
    WrongLoginException e;

    @BeforeEach
    public void beforeATest() {
        e = new WrongLoginException();
    }

    @Test
    public void testGetMessage() { 
        assertEquals("Votre login/mot de passe est erroné. Veuillez les retaper.", e.getMessage());
    }
}
