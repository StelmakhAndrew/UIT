package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.DocumentPackage;

import java.util.List;

@Repository
public interface DocumentPackageRepository extends JpaRepository<DocumentPackage, Long> {

    List<DocumentPackage> findAllByInn(String inn);

    DocumentPackage findFirstByInn(String inn);

    List<DocumentPackage> findAll();


}
