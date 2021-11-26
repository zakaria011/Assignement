package ma.octo.assignement.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.impl.AutiServiceImpl;
import ma.octo.assignement.service.impl.CompteServiceImpl;
import ma.octo.assignement.service.impl.VersementServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class VersementServiceTest {
	
	
	@MockBean
	private CompteServiceImpl compteService;
	
	@MockBean
	
	private AutiServiceImpl autiService;
	
	@Autowired
	
	private VersementService versementService;
	
	@BeforeEach
	void setup() {
		Compte c = new Compte();
        c.setNrCompte("010000A000001000");
        c.setRib("RIB1");
        c.setSolde(BigDecimal.valueOf(500L));
        Mockito.when(compteService.findByRib("RIB1")).thenReturn(c);
       
	}
	
	@Test
	void versementTransaction() {
		String rib = "RIB1";
		
		BigDecimal montant = BigDecimal.valueOf(100L);
		
		BigDecimal solde = BigDecimal.valueOf(500L);
		
		Versement versement = new Versement();
		
		versement.setNom_prenom_emetteur("ALI ALAMI");
		versement.setMotifVersement("DON");
		versement.setMontantVirement(montant);
		
		try {
			versementService.createTransaction(versement, rib);
			BigDecimal newSolde = compteService.findByRib(rib).getSolde();
	        assertThat(newSolde).isEqualTo(solde.add(montant));
		}catch(TransactionException te) {
			BigDecimal newSolde = compteService.findByRib(rib).getSolde();
	        assertThat(newSolde).isEqualTo(solde.add(montant));
		}catch(CompteNonExistantException ce) {
			
		}
	}
	
	
	

}
