package mk.ukim.finki.fuels_application.service;


import mk.ukim.finki.fuels_application.model.User;

public interface AuthService {

    User login(String username, String password);

    User editProfile(String username, String name, String surname);

}
