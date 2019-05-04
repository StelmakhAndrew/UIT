package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Person;
import project.com.Entity.Transport;

import java.util.List;
import java.util.Optional;

/**
 * інтерфейс для взаємодії з базою даних. Виконання основни операцій СRUD.
 */
@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    /**
     * @param id
     * Метод для отримання транспорту за його id
     * @return
     */
    @Override
    Optional<Transport> findById(Long id);

    /**
     * метод для отримання всіх транспортів
     * @return
     */
    @Override
    List<Transport> findAll();

    /**
     * метод для видалення транспорту по id
     * @param id
     */
    @Override
    void deleteById(Long id);


}