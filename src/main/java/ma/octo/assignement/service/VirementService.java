package ma.octo.assignement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;

public interface VirementService {
	public static final int MONTANT_MAXIMAL = 10000;

	
	 public void createTransaction( Virement virement, String nrCompteEmetteur, String nrCompteRecepteur)
	            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException;
	 
	 public List<Virement> findAll();
	 
	 

}
