package ma.octo.assignement.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.AutiService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.UtilisateurService;
import ma.octo.assignement.service.VirementService;


@Service
@Transactional
public class VirementServiceImpl implements VirementService {

    Logger LOGGER = LoggerFactory.getLogger(VersementServiceImpl.class);
    
    
    @Autowired
    private CompteService compteService;
    @Autowired
    private VirementRepository virementRepository;
    @Autowired
    private AutiService monservice;
    @Autowired
    private UtilisateurService utilisateurService;

	@Override
	public void createTransaction(Virement virement, String nrCompteEmetteur, String nrCompteRecepteur)
			throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
		Compte c1 = compteService.findByNrCompte(nrCompteEmetteur);
        Compte c2 = compteService.findByNrCompte(nrCompteRecepteur);

        if (c1 == null) {
        	 LOGGER.error("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (c2 == null) {
        	 LOGGER.error("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (virement.getMontantVirement().equals(null)) {
        	 LOGGER.error("Montant vide");
        	 throw new TransactionException("Montant vide");
        } else 
        	if (virement.getMontantVirement().intValue() == 0) {
	        	 LOGGER.error("Montant vide");
	        	 throw new TransactionException("Montant vide");
        	} else 
        		if (virement.getMontantVirement().intValue() < 10) {
	        		 LOGGER.error("Montant minimal de virement non atteint");
	        		 throw new TransactionException("Montant minimal de virement non atteint");
        		} else 
        			if (virement.getMontantVirement().intValue() > MONTANT_MAXIMAL) {
			        	 LOGGER.error("Montant maximal de virement dépassé");
			             throw new TransactionException("Montant maximal de virement dépassé");
        }

        if (virement.getMotifVirement().length() < 0) {
        	LOGGER.error("Motif vide");
            throw new TransactionException("Motif vide");
        }

        if (c1.getSolde().intValue() - virement.getMontantVirement().intValue() < 0) {
            LOGGER.error("Solde insuffisant pour l'utilisateur");
        }

        if (c1.getSolde().intValue() - virement.getMontantVirement().intValue() < 0) {
            LOGGER.error("Solde insuffisant pour l'utilisateur");
        }

        c1.setSolde(c1.getSolde().subtract(virement.getMontantVirement()));
        compteService.save(c1);

        c2.setSolde(c2.getSolde().add(virement.getMontantVirement()));
        compteService.save(c2);

       ;
        virement.setCompteBeneficiaire(c2);
        virement.setCompteEmetteur(c1);
       

        virementRepository.save(virement);

        monservice.auditVirement("Virement depuis " + nrCompteEmetteur + " vers " + nrCompteRecepteur + " d'un montant de " 
        		+ virement.getMontantVirement()
                        .toString());
	}

	@Override
	public List<Virement> findAll() {	
		return virementRepository.findAll();
	}
	
}
