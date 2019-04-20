package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Document;
import project.com.Repository.DocumentRepository;
import project.com.Service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void createDocument(Document document) {
        documentRepository.save(document);
    }
}
