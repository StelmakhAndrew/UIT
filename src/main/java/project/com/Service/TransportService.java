package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Person;
import project.com.Entity.Transport;

import java.util.List;
import java.util.Optional;

@Service
public interface TransportService {

    void createTransport(Transport transport);

    void updateTransport(Transport transport);

    Optional<Transport> findTransportById(Long id);

    List<Transport> findAllTransport();

    void deleteTransport(Long id);
}