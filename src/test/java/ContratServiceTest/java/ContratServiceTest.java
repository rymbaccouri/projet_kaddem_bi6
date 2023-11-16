package ContratServiceTest.java;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContratServiceTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

    @Test
    public void testRetrieveAllContrats() {
        Contrat contrat1 = new Contrat(); // Créez des contrats fictifs pour les tests
        Contrat contrat2 = new Contrat();
        List<Contrat> mockContrats = Arrays.asList(contrat1, contrat2);

        when(contratRepository.findAll()).thenReturn(mockContrats);

        List<Contrat> result = contratService.retrieveAllContrats();

        verify(contratRepository).findAll(); // Vérifie si la méthode findAll a été appelée

        assertEquals(2, result.size()); // Vérifie le nombre d'éléments retournés
        assertEquals(mockContrats, result); // Vérifie si la liste retournée correspond à celle attendue
    }

    @Test
    public void testUpdateContrat() {
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.updateContrat(contrat);

        verify(contratRepository).save(contrat); // Vérifie si la méthode save a été appelée

        assertEquals(contrat, result); // Vérifie si le contrat retourné est celui attendu
    }

    @Test
    public void testAddContrat() {
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.addContrat(contrat);

        verify(contratRepository).save(contrat); // Vérifie si la méthode save a été appelée

        assertEquals(contrat, result); // Vérifie si le contrat retourné est celui attendu
    }

    @Test
    public void testRetrieveContrat() {
        Contrat contrat = new Contrat();
        int idContrat = 1;

        when(contratRepository.findById(idContrat)).thenReturn(Optional.of(contrat));

        Contrat result = contratService.retrieveContrat(idContrat);

        verify(contratRepository).findById(idContrat); // Vérifie si la méthode findById a été appelée

        assertEquals(contrat, result); // Vérifie si le contrat retourné est celui attendu
    }

    @Test
    public void testRemoveContrat() {
        Contrat contrat = new Contrat();
        int idContrat = 1;

        when(contratRepository.findById(idContrat)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(idContrat);

        verify(contratRepository).findById(idContrat); // Vérifie si la méthode findById a été appelée
        verify(contratRepository).delete(contrat); // Vérifie si la méthode delete a été appelée avec le contrat
    }
}
