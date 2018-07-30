package pl.sda.fitapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitapp.domains.GymUserr;

@Repository
public interface TrainerRepository extends JpaRepository<GymUserr,Long> {
}
