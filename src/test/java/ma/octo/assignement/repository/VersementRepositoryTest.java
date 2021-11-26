package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class VersementRepositoryTest {

    @Autowired
    private VersementRepository versementRepository;

    @Autowired
    private CompteRepository compteRepository;
    
    

    @Test
    void findOne() {

        Versement versement = new Versement();
        versement.setNom_prenom_emetteur("ALAMI ALI");
        versement.setCompteBeneficiaire(compteRepository.findByRib("RIB1"));
        versement.setMontantVirement(BigDecimal.valueOf(150.5));
        versement.setMotifVersement("DON");

        Versement saved = versementRepository.save(versement);

        Optional<Versement> found = versementRepository.findById(saved.getId());
        assertThat(found).isPresent();
    }

    @Test
    void findAll() {

        assertThat(versementRepository.findAll()).isNotNull();
        assertThat(versementRepository.findAll()).hasSameClassAs(new ArrayList<Versement>());

    }

    @Test
    void save() {

        Versement versement = new Versement();
        versement.setNom_prenom_emetteur("ALAMI ALI");
        versement.setCompteBeneficiaire(compteRepository.findByRib("RIB1"));
        versement.setMontantVirement(BigDecimal.valueOf(150.5));
        versement.setMotifVersement("DON");

        Versement saved = versementRepository.save(versement);

        assertThat(saved).isNotNull();
    }

    @Test
    void delete() {

        Versement versement = new Versement();
        versement.setNom_prenom_emetteur("ALAMI ALI");
        versement.setCompteBeneficiaire(compteRepository.findByRib("RIB1"));
        versement.setMontantVirement(BigDecimal.valueOf(150.5));
        versement.setMotifVersement("DON");

        Versement saved = versementRepository.save(versement);

        versementRepository.delete(saved);

        Optional<Versement> found = versementRepository.findById(saved.getId());

        assertThat(found).isNotPresent();

    }

}