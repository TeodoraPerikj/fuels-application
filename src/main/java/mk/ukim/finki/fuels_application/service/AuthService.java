package mk.ukim.finki.fuels_application.service;


import mk.ukim.finki.fuels_application.model.User;

public interface AuthService {

    User login(String username, String password);

    User register(String username, String password, String repeatPassword, String name, String surname);

}
