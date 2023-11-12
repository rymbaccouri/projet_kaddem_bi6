package tn.esprit.spring.kaddem.universite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.universite.entities.Universite;
import tn.esprit.spring.kaddem.universite.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.universite.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.universite.services.UniversiteServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UniversiteTestService {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Mocking the behavior of the repository
        Universite universite1 = new Universite(1, "Universite 1");
        Universite universite2 = new Universite(2, "Universite 2");
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite1, universite2));

        List<Universite> universites = universiteService.retrieveAllUniversites();
        assertEquals(2, universites.size());
    }

    @Test
    public void testAddUniversite() {
        Universite universite = new Universite(1, "Universite 1");
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite addedUniversite = universiteService.addUniversite(universite);
        assertEquals(universite, addedUniversite);
    }

    @Test
    public void testRetrieveUniversite() {
        Universite universite = new Universite(1, "Universite 1");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite retrievedUniversite = universiteService.retrieveUniversite(1);
        assertEquals(universite, retrievedUniversite);
    }

    // Add more test methods for other functions in UniversiteServiceImpl

}