package main.Util.exceptions;
public class WrongInputLengthException extends Exception {
    @Override
    public String getMessage() {
        return "Les identifiants ne peuvent pas faire plus de 10 caractéres.";
    }
}
