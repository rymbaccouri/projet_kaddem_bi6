package tn.esprit.spring.kaddem.departement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;



    @Test
    public void testAddDepartement() {
        // Crée un objet Departement
        Departement departement = new Departement("informatique");
        departement.setIdDepart(1); // Remplacez 1 par l'ID approprié si nécessaire

        // Crée un ensemble d'étudiants simulés pour l'entité Departement
        Set<Etudiant> etudiants = new HashSet<>();
        // Ajoutez des étudiants simulés à l'ensemble et configurez-les comme nécessaire

        departement.setEtudiants(etudiants);

        // Configure le mock pour le comportement de save(departement)
        when(departementRepository.save(departement)).thenReturn(departement);

        // Appelle la méthode à tester
        Departement result = departementService.addDepartement(departement);

        // Vérifie si la méthode save a été appelée une fois
        verify(departementRepository, times(1)).save(departement);

        // Vérifie que le résultat n'est pas nul
        assertNotNull(result);

        // Vérifie que le résultat correspond à l'objet Departement créé
        assertEquals(departement, result);
    }


    public void testDeleteDepartement() {
        // Crée un département à supprimer (vous pouvez personnaliser les valeurs)
        Departement departement = new Departement("informatique");
        departement.setIdDepart(1); // Remplacez 1 par l'ID approprié

        // Configure le mock pour le comportement de la méthode findById
        when(departementRepository.findById(1)).thenReturn(java.util.Optional.of(departement));

        // Appelle la méthode de suppression à tester
        departementService.deleteDepartement(1); // Supprime le département avec l'ID 1

        // Vérifie si la méthode deleteById a été appelée une fois avec l'ID du département
        verify(departementRepository, times(1)).deleteById(1);
    }

    @Test
    public void testRetrieveDepartement() {
        // Crée un département simulé pour l'ID de test
        Departement departement = new Departement("Informatique");
        departement.setIdDepart(1); // Remplacez 1 par l'ID approprié

        // Configure le mock pour le comportement de findById
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Appelle la méthode à tester
        Departement result = departementService.retrieveDepartement(1);

        // Vérifie si la méthode findById a été appelée une fois avec l'ID de département
        verify(departementRepository, times(1)).findById(1);

        // Vérifie que le résultat n'est pas nul
        assertNotNull(result);

        // Vérifie que le résultat correspond à l'objet Departement simulé
        assertEquals(departement, result);
    }

    @Test
    public void testRetrieveAllDepartements() {
        // Crée une liste simulée de départements
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement("Informatique"));
        departements.add(new Departement("Mathématiques"));
        // Ajoutez autant de départements que nécessaire à la liste

        // Configure le mock pour le comportement de findAll
        when(departementRepository.findAll()).thenReturn(departements);

        // Appelle la méthode à tester pour récupérer tous les départements
        List<Departement> retrievedDepartements = departementService.retrieveAllDepartements();

        // Vérifie si la méthode findAll a été appelée une fois
        verify(departementRepository, times(1)).findAll();

        // Vérifie que la liste récupérée n'est pas nulle
        assertNotNull(retrievedDepartements);

        // Vérifie que la liste récupérée correspond à la liste simulée
        assertEquals(departements, retrievedDepartements);
    }






}
