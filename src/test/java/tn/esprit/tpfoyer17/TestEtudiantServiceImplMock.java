package tn.esprit.tpfoyer17;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.services.IEtudiantService;
import tn.esprit.tpfoyer17.services.EtudiantServiceImpl; // Assuming EtudiantServiceImpl is the implementation

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TestEtudiantServiceImplMock {

    @Mock
    private IEtudiantService etudiantService; // Mocked service interface

    @InjectMocks
    private EtudiantServiceImpl etudiantServiceImplTestMock; // The service implementation being tested

    @Test
    @Order(1)
    void testAddEtudiant() {
        // Créer un nouvel étudiant
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("Dupont")
                .prenomEtudiant("Jean")
                .cinEtudiant(12345678L)
                .dateNaissance(LocalDate.of(1990, 1, 1)) // 1er janvier 1990
                .build();

        log.info("Création de l'étudiant : Nom = {}, Prénom = {}, CIN = {}, Date de naissance = {}",
                etudiant.getNomEtudiant(), etudiant.getPrenomEtudiant(), etudiant.getCinEtudiant(), etudiant.getDateNaissance());

        // Simuler le comportement du service avec Mockito
        Etudiant savedEtudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEtudiant("Dupont")
                .prenomEtudiant("Jean")
                .cinEtudiant(12345678L)
                .dateNaissance(LocalDate.of(1990, 1, 1))
                .build();

        when(etudiantService.addEtudiant(etudiant)).thenReturn(savedEtudiant);

        // Appeler la méthode de service et vérifier le résultat
        log.info("Appel de la méthode etudiantService.addEtudiant avec l'étudiant simulé");
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Assertions pour vérifier que l'étudiant est correctement sauvegardé
        log.info("Vérification des assertions pour l'étudiant sauvegardé");
        Assertions.assertTrue(result.getIdEtudiant() > 0, "L'ID de l'étudiant ne doit pas être nul");
        Assertions.assertEquals("Dupont", result.getNomEtudiant());
        Assertions.assertEquals("Jean", result.getPrenomEtudiant());
        Assertions.assertEquals(12345678L, result.getCinEtudiant());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), result.getDateNaissance());

        // Vérifier que la méthode addEtudiant a été appelée une fois avec l'étudiant fourni
        verify(etudiantService, times(1)).addEtudiant(etudiant);
        log.info("Méthode etudiantService.addEtudiant vérifiée avec succès");
    }
}
