package ma.octo.assignement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.service.CompteService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class VirementRepositoryTest {

  @Autowired
  private VirementRepository virementRepository;
  
  @Autowired
  private CompteService compteRepository;

  @Test
  public void findOne() {
	  
	  Virement virement = new Virement();
      virement.setCompteEmetteur(compteRepository.findByNrCompte("010000A000001000"));
      virement.setCompteBeneficiaire(compteRepository.findByNrCompte("010000B025001000"));
      virement.setMontantVirement(BigDecimal.valueOf(150.5));
      virement.setMotifVirement("Motif");

      Virement saved = virementRepository.save(virement);

      Optional<Virement> found = virementRepository.findById(saved.getId());
      assertThat(found).isPresent();
  }

  @Test
  public void findAll() {
	  assertThat(virementRepository.findAll()).isNotNull();
      assertThat(virementRepository.findAll()).hasSameClassAs(new ArrayList<Virement>());
  }

  @Test
  public void save() {
	  
	  Virement virement = new Virement();
      virement.setCompteEmetteur(compteRepository.findByNrCompte("010000A000001000"));
      virement.setCompteBeneficiaire(compteRepository.findByNrCompte("010000B025001000"));
      virement.setMontantVirement(BigDecimal.valueOf(150.5));
      virement.setMotifVirement("Motif");

      Virement saved = virementRepository.save(virement);

      assertThat(saved).isNotNull();
  }

  @Test
  public void delete() {
	  
	  Virement virement = new Virement();
      virement.setCompteEmetteur(compteRepository.findByNrCompte("010000A000001000"));
      virement.setCompteBeneficiaire(compteRepository.findByNrCompte("010000B025001000"));
      virement.setMontantVirement(BigDecimal.valueOf(150.5));
      virement.setMotifVirement("Motif");
      Virement saved = virementRepository.save(virement);

      virementRepository.delete(saved);

      Optional<Virement> found = virementRepository.findById(saved.getId());

      assertThat(found).isNotPresent();

  }
}