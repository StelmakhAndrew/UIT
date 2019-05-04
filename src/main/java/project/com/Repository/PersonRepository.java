package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Person;

import java.util.List;
import java.util.Optional;

/**
 * інтерфейс для взаємодії з базою даних. Виконання основни операцій СRUD.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * @param id
     * Метод для отримання персони за її id
     * @return
     */
    @Override
    Optional<Person> findById(Long id);

    /**
     * метод для отримання всіх персон
     * @return
     */
    @Override
    List<Person> findAll();

    /**
     * @param id
     * метод для видалення персони за id
     */
    @Override
    void deleteById(Long id);
}