package mk.ukim.finki.fuels_application.model.exceptions;

public class StreetByNameNotFoundException extends RuntimeException {
    public StreetByNameNotFoundException(String name) {
        super(String.format("Street with name %s not found.",name));
    }
}
