package mk.ukim.finki.fuels_application.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "application_user")
public class User {

    @Id
    private String username;

    private String name;

    private String surname;

    private String password;

    public User(){

    }

    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }
}
