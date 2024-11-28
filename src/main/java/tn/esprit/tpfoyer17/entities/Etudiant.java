package tn.esprit.tpfoyer17.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idEtudiant;

    String nomEtudiant;

    String prenomEtudiant;

    long cinEtudiant;

    LocalDate dateNaissance; // Remplacement de java.util.Date par java.time.LocalDate

    @ToString.Exclude
    @ManyToMany(mappedBy = "etudiants")
    Set<Reservation> reservations;
}
