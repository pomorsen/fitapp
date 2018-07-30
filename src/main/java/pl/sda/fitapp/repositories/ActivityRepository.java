package pl.sda.fitapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitapp.domains.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
}
