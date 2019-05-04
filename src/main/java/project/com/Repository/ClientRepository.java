package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Client;
import project.com.Entity.Person;

import java.util.List;
import java.util.Optional;

/**
 * інтерфейс для взаємодії з базою даних. Виконання основни операцій СRUD.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    /**
     * @param id
     * Метод для отримання Client за його id
     * @return
     */
    @Override
    Optional<Client> findById(Long id);

    /**
     * метод для отримання всіх клієнтів
     * @return
     */
    @Override
    List<Client> findAll();

    /**
     * метод для видалення клієнта по id
     * @param id
     */
    @Override
    void deleteById(Long id);

}