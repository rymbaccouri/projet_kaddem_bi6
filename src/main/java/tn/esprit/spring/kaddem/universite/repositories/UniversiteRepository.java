package tn.esprit.spring.kaddem.universite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.universite.entities.Universite;

@Repository
public interface UniversiteRepository extends CrudRepository<Universite,Integer> {


}
