package ma.octo.assignement.service;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;

public interface VersementService {
	public static final int MONTANT_MAXIMAL = 10000;

	public void createTransaction(Versement versement, String ribCompteBeneficiaire) throws CompteNonExistantException, TransactionException;

}
