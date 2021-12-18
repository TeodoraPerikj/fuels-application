package mk.ukim.finki.fuels_application.service;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;

import java.util.List;
import java.util.Optional;

public interface FuelService {

    Optional<Fuel> findByName(String name);

    List<Fuel> findAll();

    Optional<Fuel> findById(Long id);

}
