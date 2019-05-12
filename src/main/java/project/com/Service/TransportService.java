package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.DTO.TransportDTO;
import project.com.Entity.Person;
import project.com.Entity.Transport;

import java.util.List;
import java.util.Optional;

/**
 * сервіс для транспорту
 */
@Service
public interface TransportService {

    Transport createTransport(TransportDTO transport);

    Transport updateTransport(TransportDTO transport);

    Transport findTransportById(Long id);

    List<Transport> findAllTransport();

    void deleteTransport(Long id);
}