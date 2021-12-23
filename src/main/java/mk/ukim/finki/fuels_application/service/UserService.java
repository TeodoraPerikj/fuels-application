package mk.ukim.finki.fuels_application.service;

import mk.ukim.finki.fuels_application.model.Role;
import mk.ukim.finki.fuels_application.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

}
