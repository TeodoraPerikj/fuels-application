package mk.ukim.finki.fuels_application.service.impl;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;
import mk.ukim.finki.fuels_application.model.exceptions.*;
import mk.ukim.finki.fuels_application.repository.jpa.FuelRepository;
import mk.ukim.finki.fuels_application.service.FuelService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        return Optional.of(this.fuelRepository.findByName(name).orElseThrow(FuelByNameNotFoundException::new));
    }

    @Override
    public List<Fuel> findAll() {
        return this.fuelRepository.findAll();
    }

    @Override
    public Optional<Fuel> findById(Long id) {
        return Optional.of(this.fuelRepository.findById(id).orElseThrow(() -> new FuelNotFoundException(id)));
    }

    private Long getLowestFuelId(Street street, List<Fuel> fuels) {
        Long id = fuels.get(0).getId();

        Float distance1 = Math.abs(street.getLatitude() - fuels.get(0).getLatitude())
                + Math.abs(street.getLongitude() - fuels.get(0).getLongitude());


        for (Fuel fuel : fuels) {

            Float newDistance = Math.abs(street.getLatitude() - fuel.getLatitude())
                    + Math.abs(street.getLongitude() - fuel.getLongitude());

            if (distance1 > newDistance) {
                distance1 = newDistance;
                id = fuel.getId();
            }

        }

        return id;
    }

    @Override
    public List<Fuel> findFirstTwo(Street street) {

        List<Fuel> allFuels = this.fuelRepository.findAll();

        List<Fuel> firstTwo = new ArrayList<>();

        Long id1 = getLowestFuelId(street, allFuels);

        if (this.fuelRepository.findById(id1).isPresent())
            firstTwo.add(this.fuelRepository.findById(id1).get());

        allFuels.removeIf(f -> f.getId().equals(id1));

        Long id2 = getLowestFuelId(street, allFuels);

        if (this.fuelRepository.findById(id2).isPresent())
            firstTwo.add(this.fuelRepository.findById(id2).get());

        return firstTwo;
    }

    @Override
    public List<Double> findDistances(List<Fuel> fuels, Street street) {

        List<Double> distances = new ArrayList<>();

        for (Fuel fuel : fuels) {

            double firstCos = Math.cos((fuel.getLatitude() * Math.PI) / 180);
            double secondCos = Math.cos((street.getLatitude() * Math.PI) / 180);

            double latDistance = (Math.abs(fuel.getLatitude() - street.getLatitude()) * Math.PI) / 180;
            double lonDistance = (Math.abs(fuel.getLongitude() - street.getLongitude()) * Math.PI) / 180;

            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(firstCos)
                    * Math.cos(secondCos) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            double distanceInKm = 6371 * c;

            double roundedDistance = Math.round(distanceInKm * 1000.0) / 1000.0;

            distances.add(roundedDistance);

        }

        return distances;

    }

    @Override
    public List<String> findTimes(List<Double> distances) {

        List<String> times = new ArrayList<>();

        Long time;

        for (Double distance : distances) {

            // THE ALLOWED SPEED IS 50KM/H
            double t = (distance * 3600) / 50;

            time = (long) Math.ceil(t);

            if (time >= 60 && time < 120) {

                Long seconds = time - 60;

                if (seconds != 0) {
                    times.add("1 минута " + seconds + " секунди");
                } else {
                    times.add("1 минута");
                }

            } else if (time >= 120 && time < 180) {
                Long seconds = time - 120;

                if (seconds != 0) {
                    times.add("2 минути " + seconds + " секунди");
                } else {
                    times.add("2 минути");
                }
            } else {
                times.add(time + " секунди");
            }

        }

        return times;
    }

    @Override
    @Transactional
    public Fuel addNewFuel(String name, Float latitude, Float longitude, String imageUrl, String pageLink) {
        if (name.isEmpty())
            return null;

        this.fuelRepository.deleteByName(name);

        return this.fuelRepository.save(new Fuel(name, latitude, longitude, imageUrl, pageLink));
    }

    @Override
    public void deleteById(Long id) {
        this.fuelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Fuel editFuel(Long id, String name, Float latitude, Float longitude, String imageUrl, String pageLink) {
        Fuel fuel = this.fuelRepository.findById(id)
                .orElseThrow(() -> new FuelNotFoundException(id));

        fuel.setName(name);
        fuel.setLatitude(latitude);
        fuel.setLongitude(longitude);
        fuel.setImageUrl(imageUrl);
        fuel.setPageLink(pageLink);

        return this.fuelRepository.save(fuel);
    }

}