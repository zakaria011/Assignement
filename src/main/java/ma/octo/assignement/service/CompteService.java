package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.domain.Compte;

public interface CompteService {
	
	public Compte findByNrCompte(String nrCompte);
	public Compte findByRib(String rib);
	public List<Compte> findAll();
	public void save(Compte compte);

}
