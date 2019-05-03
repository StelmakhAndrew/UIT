package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Crew;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {

}