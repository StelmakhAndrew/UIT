package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Client;
import project.com.Entity.Crew;

import java.util.List;
import java.util.Optional;

/**
 * інтерфейс для взаємодії з базою даних. Виконання основни операцій СRUD.
 */
@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {

    /**
     * @param id
     * Метод для отримання екіпажу за його id
     * @return
     */
    @Override
    Optional<Crew> findById(Long id);

    /**
     * метод для отримання всіх екіпажів
     * @return
     */
    @Override
    List<Crew> findAll();

    /**
     * для видалення екіпажу
     * @param id
     */
    @Override
    void deleteById(Long id);
}