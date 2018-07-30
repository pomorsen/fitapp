package pl.sda.fitapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitapp.domains.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Long> {
}
