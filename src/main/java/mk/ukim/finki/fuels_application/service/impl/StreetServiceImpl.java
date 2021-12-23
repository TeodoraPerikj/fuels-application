package mk.ukim.finki.fuels_application.service.impl;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;
import mk.ukim.finki.fuels_application.model.exceptions.FuelNotFoundException;
import mk.ukim.finki.fuels_application.model.exceptions.StreetByNameNotFoundException;
import mk.ukim.finki.fuels_application.model.exceptions.StreetNotFoundException;
import mk.ukim.finki.fuels_application.repository.jpa.StreetRepository;
import mk.ukim.finki.fuels_application.service.StreetService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    @Transactional
    public Street addNewStreet(String name, Float latitude, Float longitude) {

        if(name.isEmpty())
            return null;

        this.streetRepository.deleteByName(name);
       return this.streetRepository.save(new Street(name, latitude, longitude));
    }

    @Override
    public void deleteById(Long id) {
        this.streetRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Street editStreet(Long id, String name, Float latitude, Float longitude) {
        Street street = this.streetRepository.findById(id)
                .orElseThrow(() -> new StreetNotFoundException(id));

        street.setName(name);
        street.setLatitude(latitude);
        street.setLongitude(longitude);

        return this.streetRepository.save(street);
    }
}
