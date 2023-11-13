package tn.esprit.spring.kaddem.universite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.universite.entities.Departement;
import tn.esprit.spring.kaddem.universite.entities.Universite;
import tn.esprit.spring.kaddem.universite.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.universite.repositories.UniversiteRepository;

import java.util.List;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService{
@Autowired
UniversiteRepository universiteRepository;
@Autowired
DepartementRepository departementRepository;
    public UniversiteServiceImpl() {
        // TODO Auto-generated constructor stub
    }
  public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }

 public    Universite addUniversite (Universite  u){
return  (universiteRepository.save(u));
    }

 public    Universite updateUniversite (Universite  u){
     return  (universiteRepository.save(u));
    }

  public Universite retrieveUniversite (Integer idUniversite){
Universite u = universiteRepository.findById(idUniversite).get();
return  u;
    }
    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement){
        Universite u= universiteRepository.findById(idUniversite).orElse(null);
        Departement d= departementRepository.findById(idDepartement).orElse(null);
        u.getDepartements().add(d);
        universiteRepository.save(u);
    }

    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite){
Universite u=universiteRepository.findById(idUniversite).orElse(null);
return u.getDepartements();
    }
}