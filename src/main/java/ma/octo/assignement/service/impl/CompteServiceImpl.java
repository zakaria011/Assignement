package ma.octo.assignement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.service.CompteService;
@Service
@Transactional
public class CompteServiceImpl implements CompteService {
	@Autowired
	CompteRepository compteRepository;

	@Override
	public Compte findByNrCompte(String nrCompte) {
		
		return  compteRepository.findByNrCompte(nrCompte);
	}

	@Override
	public void save(Compte compte) {
		compteRepository.save(compte);	
	}

	@Override
	public Compte findByRib(String rib) {
		return compteRepository.findByRib(rib);
	}

	@Override
	public List<Compte> findAll() {
		return compteRepository.findAll();
	}
	
	

}
