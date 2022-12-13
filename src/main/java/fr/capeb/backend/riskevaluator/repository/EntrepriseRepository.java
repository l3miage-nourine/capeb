package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
}
