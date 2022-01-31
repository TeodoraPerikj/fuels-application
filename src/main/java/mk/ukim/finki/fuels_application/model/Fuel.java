package mk.ukim.finki.fuels_application.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float latitude;

    private Float longitude;
    private String imageUrl;
    private String pageLink;

    public Fuel(String name, Float latitude, Float longitude, String imageUrl, String pageLink) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.pageLink = pageLink;
    }
}
