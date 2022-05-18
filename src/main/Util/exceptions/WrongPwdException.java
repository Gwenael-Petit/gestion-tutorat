package main.Util.exceptions;
public class WrongPwdException extends Exception {
    @Override
    public String getMessage() {
        return "Mauvais password, veuillez réessayer.";
    }
}
