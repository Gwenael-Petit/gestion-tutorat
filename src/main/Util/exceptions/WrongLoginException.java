package main.Util.exceptions;
public class WrongLoginException extends Exception{
    @Override
    public String getMessage() {
        return  "Votre login/mot de passe est errone. Veuillez les retaper.";
    }
}
