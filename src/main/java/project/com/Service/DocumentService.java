package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Document;

@Service
public interface DocumentService {

    void createDocument(Document document);
}