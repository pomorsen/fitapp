package pl.sda.fitapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitapp.domains.GymUser;

import java.util.Optional;

@Repository
public interface GymUserRepository extends JpaRepository<GymUser,Long> {
    Optional<GymUser> findByEmailAndPassword(String email, String password);
    Optional<GymUser> findByNameAndPassword(String name, String password);
}
