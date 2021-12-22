package mk.ukim.finki.fuels_application.service.impl;

import mk.ukim.finki.fuels_application.model.User;
import mk.ukim.finki.fuels_application.model.exceptions.*;
import mk.ukim.finki.fuels_application.repository.jpa.UserRepository;
import mk.ukim.finki.fuels_application.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);

    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(name,surname,username, password);
        return userRepository.save(user);

    }

    @Override
    public User editProfile(String username, String password, String name, String surname) {

        User user = this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UserNotFoundException(username));

        user.setName(name);
        user.setSurname(surname);

        return this.userRepository.save(user);
    }
}
