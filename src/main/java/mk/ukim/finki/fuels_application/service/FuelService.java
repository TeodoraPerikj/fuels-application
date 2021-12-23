package mk.ukim.finki.fuels_application.service;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;

import java.util.List;
import java.util.Optional;

public interface FuelService {

    Optional<Fuel> findByName(String name);

    List<Fuel> findAll();

    Optional<Fuel> findById(Long id);

    List<Fuel> findFirstTwo(Street street);

    List<Double> findDistances(List<Fuel> fuels, Street street);

    List<String> findTimes(List<Double> distances);

    Fuel addNewFuel(String name, Float latitude, Float longitude);

    void deleteById(Long id);

    Fuel editFuel(Long id, String name, Float latitude, Float longitude);

}
