package mk.ukim.finki.fuels_application.repository.jpa;

import mk.ukim.finki.fuels_application.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<Street,Long> {

    Optional<Street> findByName(String name);

    Optional<Street> findById(Long id);

    void deleteByName(String name);
}
