package tn.esprit.spring.kaddem.etudiant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Test
    //
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant( "rym", "baccouri", Option.GAMIX);
        etudiant.setNomE("rym");
        etudiant.setPrenomE("baccouri");
        etudiant.setOp(Option.GAMIX);


        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);


        verify(etudiantRepository, times(1)).save(etudiant);


        assertNotNull(result);


        assertEquals(etudiant, result);
    }





    @Test
    public void testRetrieveAllEtudiant() {

        List<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(new Etudiant("rym","baccouri",Option.GAMIX));


        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Verify that the service method returns the expected result
        assertEquals(etudiantList, result);

    }

    @Test
    public void testRetrieveEtudiant() {

        Etudiant etudiants= new Etudiant( "rym", "baccouri", Option.GAMIX);

        // Mock the behavior of the courseRepository to return the sampleCourse when findById is called with numCourse
        when(etudiantRepository.findById(etudiants.getIdEtudiant())).thenReturn(Optional.of(etudiants));


        Etudiant retrieveEtudiant = etudiantService.retrieveEtudiant(etudiants.getIdEtudiant());



        assertEquals(etudiants,retrieveEtudiant);

    }
    //@Test
   // public void testremoveEtudiant() {

     //   Integer idEtudian = Math.toIntExact(1L);

        // Call the removeCourse function with the numCourse
    //    etudiantService.removeEtudiant(idEtudian);

        // Verify that the deleteById method of courseRepository is called with the numCourseToRemove
      //  verify(etudiantRepository, times(1)).deleteById(idEtudian);
 //   }



}
