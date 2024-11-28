package tn.esprit.tpfoyer17.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements IEtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    // Add an Etudiant
    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return List.of();
    }

    @Override
    public Etudiant getEtudiantById(long idEtudiant) {
        return null;
    }

    @Override
    public void deleteEtudiant(long idEtudiant) {

    }

    // Find an Etudiant by its ID

    public Etudiant findEtudiantById(Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.orElse(null); // Return null if not found
    }

    // Update an existing Etudiant
    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
        // Make sure the Etudiant exists before updating
        if (etudiantRepository.existsById(etudiant.getIdEtudiant())) {
            return etudiantRepository.save(etudiant);
        }
        return null; // Return null if the Etudiant doesn't exist
    }

    // Delete an Etudiant by its ID

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }
}
