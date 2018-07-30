package pl.sda.fitapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitapp.domains.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {
}
