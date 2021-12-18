package mk.ukim.finki.fuels_application.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float latitude;

    private Float longitude;

    public Fuel() {
    }

    public Fuel(String name, Float latitude, Float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
