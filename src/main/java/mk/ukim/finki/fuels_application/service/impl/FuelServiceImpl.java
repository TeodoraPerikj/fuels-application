package mk.ukim.finki.fuels_application.service.impl;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;
import mk.ukim.finki.fuels_application.model.exceptions.*;
import mk.ukim.finki.fuels_application.repository.jpa.FuelRepository;
import mk.ukim.finki.fuels_application.service.FuelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;

    public FuelServiceImpl(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @Override
    public Optional<Fuel> findByName(String name) {
        return Optional.of(this.fuelRepository.findByName(name).orElseThrow(()->new FuelByNameNotFoundException(name)));
    }

    @Override
    public List<Fuel> findAll() {
        return this.fuelRepository.findAll();
    }

    @Override
    public Optional<Fuel> findById(Long id) {
        return Optional.of(this.fuelRepository.findById(id).orElseThrow(()->new FuelNotFoundException(id)));
    }

}