package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Crew;
import project.com.Entity.Flight;

import java.util.List;
import java.util.Optional;

/**
 * інтерфейс для взаємодії з базою даних. Виконання основни операцій СRUD.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    /**
     * @param id
     * Метод для отримання маршруту за його id
     * @return
     */
    @Override
    Optional<Flight> findById(Long id);

    /**
     * метод для отримання всіх маршрутів
     * @return
     */
    @Override
    List<Flight> findAll();

    /**
     * @param id
     * метод для видалення маршруту за його id
     */
    @Override
    void deleteById(Long id);
}