package pl.sda.fitapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitapp.domains.Trainer;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    Optional<Trainer> findByEmailAndPassword(String email, String password);
    Optional<Trainer> findByNameAndPassword(String name, String password);
    boolean existsTrainerByEmail(String email);
}
