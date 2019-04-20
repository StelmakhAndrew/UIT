package project.com.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Document;
import project.com.Entity.DocumentPackage;
import project.com.Repository.DocumentPackageRepository;
import project.com.Repository.DocumentRepository;
import project.com.Service.DocumentPackageService;

import java.util.List;

@Service
public class  DocumentPackageServiceImpl implements DocumentPackageService {

    @Autowired
    private DocumentPackageRepository documentPackageRepository;

    @Override
    public void createDocumentPackage(DocumentPackage documentPackage) {
        documentPackageRepository.save(documentPackage);
    }

    @Override
    public List<DocumentPackage> findAllByInn(String inn) {
        return documentPackageRepository.findAllByInn(inn);
    }

    @Override
    public DocumentPackage findFirstByInn(String inn) {
        return documentPackageRepository.findFirstByInn(inn);
    }
}
