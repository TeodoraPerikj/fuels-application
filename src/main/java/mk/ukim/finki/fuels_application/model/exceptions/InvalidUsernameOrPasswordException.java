package mk.ukim.finki.fuels_application.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {

    public InvalidUsernameOrPasswordException(){
        super("Invalid username or password.");
    }

}
