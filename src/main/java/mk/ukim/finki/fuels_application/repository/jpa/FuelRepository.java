package mk.ukim.finki.fuels_application.repository.jpa;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Long> {

    Optional<Fuel> findByName(String name);

    Optional<Fuel> findById(Long id);

    void deleteByName(String name);
}
