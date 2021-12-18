package mk.ukim.finki.fuels_application.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float latitude;

    private Float longitude;

    public Street() {
    }

    public Street(String name, Float latitude, Float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
