package tn.esprit.spring.kaddem.etudiant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EtudiantServiceTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EquipeRepository equipeRepository;
    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
/*
    @Test

    public void testAddEtudiant() {

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");
        etudiant.setOp(Option.SIM);


        Contrat contrat = new Contrat();
        Equipe equipe = new Equipe();
        Departement departement = new Departement();


        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);
        Mockito.when(contratRepository.save(Mockito.any(Contrat.class))).thenReturn(contrat);
        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);
        Mockito.when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departement);


        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);


        assertNotNull(savedEtudiant);
        assertEquals("John", savedEtudiant.getNomE());
        assertEquals("Doe", savedEtudiant.getPrenomE());
        assertEquals(Option.SIM, savedEtudiant.getOp());


    }

 */
   @Test
    public void testEditEtudiant() {

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");
        etudiant.setOp(Option.SIM);

        Contrat contrat = new Contrat();
        Equipe equipe = new Equipe();
        Departement departement = new Departement();

        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);
        Mockito.when(contratRepository.save(Mockito.any(Contrat.class))).thenReturn(contrat);
        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);
        Mockito.when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departement);

        Etudiant savedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertNotNull(savedEtudiant);
        assertEquals("John", savedEtudiant.getNomE());
        assertEquals("Doe", savedEtudiant.getPrenomE());
        assertEquals(Option.SIM, savedEtudiant.getOp());
    }


/*
    @Test
    public void testRemoveEtudiant() {

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");
        etudiant.setOp(Option.SIM);


        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class)))
                .thenAnswer(invocation -> {
                    Etudiant etudiantToSave = invocation.getArgument(0);
                    etudiantToSave.setIdEtudiant(1); // Assign a valid ID here.
                    return etudiantToSave;
                });
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);



        Mockito.when(etudiantRepository.findById(savedEtudiant.getIdEtudiant()))
                .thenReturn(Optional.of(savedEtudiant));

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(savedEtudiant.getIdEtudiant());
        assertTrue(optionalEtudiant.isPresent());

        etudiantService.removeEtudiant(savedEtudiant.getIdEtudiant());

        Mockito.verify(etudiantRepository).delete(savedEtudiant);

//        Optional<Etudiant> removedEtudiant = etudiantRepository.findById(savedEtudiant.getIdEtudiant());
//        assertFalse(removedEtudiant.isPresent());



    } */




}
