package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EtudiantRepositoryTest {

    @Autowired
    private EtudiantRepository etudiantRepository;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = Etudiant.builder()
                .nomEtudiant("Dupont")
                .prenomEtudiant("Jean")
                .cinEtudiant(12345678L)
                .dateNaissance(LocalDate.of(1990, 1, 1)) // 1er janvier 1990
                .build(); }


    @Test
    void testCreateEtudiant() {
        // Sauvegarde de l'étudiant dans le dépôt
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);

        // Vérifie que l'ID est généré
        assertTrue(savedEtudiant.getIdEtudiant() > 0, "L'ID de l'étudiant n'est pas généré correctement");

        // Vérifie les autres champs
        assertEquals("Dupont", savedEtudiant.getNomEtudiant(), "Le nom de l'étudiant ne correspond pas.");
        assertEquals("Jean", savedEtudiant.getPrenomEtudiant(), "Le prénom de l'étudiant ne correspond pas.");
        assertEquals(12345678L, savedEtudiant.getCinEtudiant(), "Le CIN de l'étudiant ne correspond pas.");
        assertEquals(LocalDate.of(1990, 1, 1), savedEtudiant.getDateNaissance(), "La date de naissance ne correspond pas.");
    }
    @Test
    void testReadEtudiant() {
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        Etudiant foundEtudiant = etudiantRepository.findById(savedEtudiant.getIdEtudiant()).orElse(null);

        assertNotNull(foundEtudiant);
        assertEquals(savedEtudiant.getIdEtudiant(), foundEtudiant.getIdEtudiant());
        assertEquals("Dupont", foundEtudiant.getNomEtudiant());
        assertEquals("Jean", foundEtudiant.getPrenomEtudiant());
    }

    @Test
    void testUpdateEtudiant() {
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        savedEtudiant.setNomEtudiant("Martin");
        savedEtudiant.setPrenomEtudiant("Pierre");

        Etudiant updatedEtudiant = etudiantRepository.save(savedEtudiant);

        assertEquals("Martin", updatedEtudiant.getNomEtudiant());
        assertEquals("Pierre", updatedEtudiant.getPrenomEtudiant());
    }

    @Test
    void testDeleteEtudiant() {
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        Long etudiantId = savedEtudiant.getIdEtudiant();

        etudiantRepository.deleteById(etudiantId);

        Etudiant deletedEtudiant = etudiantRepository.findById(etudiantId).orElse(null);

        assertNull(deletedEtudiant);
    }
}
