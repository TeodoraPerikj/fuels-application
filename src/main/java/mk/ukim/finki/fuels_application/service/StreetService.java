package mk.ukim.finki.fuels_application.service;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;

import java.util.List;
import java.util.Optional;

public interface StreetService {

    Optional<Street> findByName(String name);

    Optional<Street> findById(Long id);

    List<Street> findAll();

    Street addNewStreet(String name, Float latitude, Float longitude);

    void deleteById(Long id);

    Street editStreet(Long id, String name, Float latitude, Float longitude);
}
