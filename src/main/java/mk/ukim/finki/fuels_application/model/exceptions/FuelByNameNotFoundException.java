package mk.ukim.finki.fuels_application.model.exceptions;

public class FuelByNameNotFoundException extends RuntimeException {
    public FuelByNameNotFoundException() {
        super("You have to choose fuel from the list!");
    }
}
