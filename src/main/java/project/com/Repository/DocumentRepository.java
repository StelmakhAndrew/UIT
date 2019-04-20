package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}