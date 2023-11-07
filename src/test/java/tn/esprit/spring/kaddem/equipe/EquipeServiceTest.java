package tn.esprit.spring.kaddem.equipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeServiceTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {
        // Arrange
        List<Equipe> equipes = new ArrayList<>();
        when(equipeRepository.findAll()).thenReturn(equipes);

        // Act
        List<Equipe> result = equipeService.retrieveAllEquipes();

        // Assert
        assertEquals(equipes, result);
    }

    @Test
    void testAddEquipe() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("equipe1");
        equipe.setNiveau(Niveau.JUNIOR);

        when(equipeRepository.save(equipe)).thenReturn(equipe);

        // Act
        Equipe result = equipeService.addEquipe(equipe);

        // Assert
        assertNotNull(result);
        assertEquals(equipe, result);
    }

    @Test
    void testDeleteEquipe() {
        // Arrange
        Integer idEquipe = 1;
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(idEquipe);
        equipe.setNomEquipe("equipe1");
        equipe.setNiveau(Niveau.JUNIOR);

        when(equipeRepository.findById(idEquipe)).thenReturn(java.util.Optional.of(equipe));
        doNothing().when(equipeRepository).delete(equipe);

        // Act & Assert
        assertDoesNotThrow(() -> equipeService.deleteEquipe(idEquipe));
        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    void testDeleteEquipeWithInvalidId() {
        // Arrange
        Integer idEquipe = 1;

        when(equipeRepository.findById(idEquipe)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> equipeService.deleteEquipe(idEquipe));
        verify(equipeRepository, never()).delete(any(Equipe.class));
    }

    @Test
    void testUpdateEquipe() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("equipe1");
        equipe.setNiveau(Niveau.JUNIOR);

        when(equipeRepository.save(equipe)).thenReturn(equipe);

        // Act
        Equipe result = equipeService.updateEquipe(equipe);

        // Assert
        assertNotNull(result);
        assertEquals(equipe, result);
    }

//    @Test
//    void testEvoluerEquipes() {
//        // Arrange
//        List<Equipe> equipes = new ArrayList<>();
//        Equipe equipe1 = new Equipe();
//        equipe1.setIdEquipe(1);
//        equipe1.setNomEquipe("equipe1");
//        equipe1.setNiveau(Niveau.JUNIOR);
//        equipes.add(equipe1);
//
//        Equipe equipe2 = new Equipe();
//        equipe2.setIdEquipe(2);
//        equipe2.setNomEquipe("equipe2");
//        equipe2.setNiveau(Niveau.SENIOR);
//        equipes.add(equipe2);
//
//        when(equipeRepository.findAll()).thenReturn(equipes);
//
//        // Act
//        equipeService.evoluerEquipes();
//
//        // Assert
//        assertEquals(Niveau.SENIOR, equipe1.getNiveau());
//        assertEquals(Niveau.EXPERT, equipe2.getNiveau());
//        verify(equipeRepository, times(2)).save(any(Equipe.class));
//    }
}
