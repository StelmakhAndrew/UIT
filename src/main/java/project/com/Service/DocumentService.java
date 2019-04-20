package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Document;

@Service
public interface DocumentService {

    public void createDocument(Document document);
}