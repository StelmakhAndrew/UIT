package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.DocumentPackage;

import java.util.List;

@Service
public interface DocumentPackageService {

    void createDocumentPackage(DocumentPackage documentPackage);

    List<DocumentPackage> findAllByInn(String inn);

    List<DocumentPackage> findAll();

    DocumentPackage findFirstByInn(String inn);



}
