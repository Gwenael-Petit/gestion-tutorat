package main.Util.exceptions;
public class WrongLoginException extends Exception{
    @Override
    public String getMessage() {
        return  "Mauvais login, veuillez réessayer.";
    }
}
