package mk.ukim.finki.fuels_application.service.impl;

import mk.ukim.finki.fuels_application.model.Street;
import mk.ukim.finki.fuels_application.model.exceptions.StreetByNameNotFoundException;
import mk.ukim.finki.fuels_application.model.exceptions.StreetNotFoundException;
import mk.ukim.finki.fuels_application.repository.jpa.StreetRepository;
import mk.ukim.finki.fuels_application.service.StreetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreetServiceImpl implements StreetService {

    private final StreetRepository streetRepository;

    public StreetServiceImpl(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }


    @Override
    public Optional<Street> findByName(String name) {
        return Optional.of(this.streetRepository.findByName(name)
                .orElseThrow(()->new StreetByNameNotFoundException(name)));
    }

    @Override
    public Optional<Street> findById(Long id) {
        return Optional.of(this.streetRepository.findById(id)
                .orElseThrow(()-> new StreetNotFoundException(id)));
    }

    @Override
    public List<Street> findAll() {
        return this.streetRepository.findAll();
    }
}
