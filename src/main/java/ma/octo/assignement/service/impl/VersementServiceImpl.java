package ma.octo.assignement.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.AutiService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.VersementService;

@Service
@Transactional
public class VersementServiceImpl implements VersementService{
	
    Logger LOGGER = LoggerFactory.getLogger(VersementServiceImpl.class);

	@Autowired
    private CompteService compteService;
	
	@Autowired
    private VersementRepository versementRepository;
	
	@Autowired
	private AutiService monservice;
	

	@Override
	public void createTransaction(Versement versement,String ribCompteBeneficiaire) throws CompteNonExistantException, TransactionException {
		
		Compte compte = compteService.findByRib(ribCompteBeneficiaire);
		if (versement.getNom_prenom_emetteur() == null ||versement.getNom_prenom_emetteur().isBlank()) {
			LOGGER.error("versement annulé");
	            throw new TransactionException("Nom de l'emetteur est requit");
	     }
		if (compte == null) {
			LOGGER.error("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }
		
		if (versement.getMontantVirement().equals(null)) {
			
			LOGGER.error("Montant vide");
            throw new TransactionException("Montant vide");
            
        } else 
        	if (versement.getMontantVirement().intValue() == 0) {
        		LOGGER.error("Montant vide");
	            throw new TransactionException("Montant vide");
        	}else 
        		if (versement.getMontantVirement().intValue() < 10) {
        			LOGGER.error("Montant minimal de virement non atteint");
		            throw new TransactionException("Montant minimal de virement non atteint");
        		}else 
        			if (versement.getMontantVirement().intValue() > MONTANT_MAXIMAL) {
        				LOGGER.error("Montant maximal de virement dépassé");
			            throw new TransactionException("Montant maximal de virement dépassé");
        			}
		
		 if(versement.getMotifVersement().length() < 0) {
			 LOGGER.error("Motif vide");
	            throw new TransactionException("Motif vide");
	      }
		 
		 compte.setSolde(compte.getSolde().add(versement.getMontantVirement()));
		 compteService.save(compte);
		
		 versement.setCompteBeneficiaire(compte);
	     versementRepository.save(versement);
	     
	     monservice.auditVirement("Versement depuis " + versement.getNom_prenom_emetteur() + " vers " + ribCompteBeneficiaire
                + " d'un montant de " + versement.getMontantVirement()
                 .toString());
	}
		

}
