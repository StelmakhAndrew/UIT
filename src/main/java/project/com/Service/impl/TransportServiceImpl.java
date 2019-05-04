package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Transport;
import project.com.Repository.TransportRepository;
import project.com.Service.TransportService;

import java.util.List;
import java.util.Optional;

/**
 * реалізація сервісу
 */
@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public void createTransport(Transport transport) {
        transportRepository.save(transport);
    }

    @Override
    public void updateTransport(Transport transport) {
        transportRepository.save(transport);
    }

    @Override
    public Transport findTransportById(Long id) {
        return transportRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transport> findAllTransport() {
        return transportRepository.findAll();
    }

    @Override
    public void deleteTransport(Long id) {
        transportRepository.deleteById(id);
    }
}
