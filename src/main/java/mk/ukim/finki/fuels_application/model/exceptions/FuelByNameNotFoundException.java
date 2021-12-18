package mk.ukim.finki.fuels_application.model.exceptions;

public class FuelByNameNotFoundException extends RuntimeException {
    public FuelByNameNotFoundException(String name) {
        super(String.format("Fuel with name %s not found.",name));
    }
}
