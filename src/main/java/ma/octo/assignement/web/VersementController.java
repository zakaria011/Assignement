package ma.octo.assignement.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.VersementMapper;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.AutiService;
import ma.octo.assignement.service.VersementService;

@RestController("/versements")
public class VersementController {
	public static final int MONTANT_MAXIMAL = 10000;

	@Autowired
	VersementService versementService;
	
	@GetMapping("/finall")
	public String findAll() {
		return "gg";
	}
	@PostMapping("/executerVersements")
	@ResponseStatus(HttpStatus.CREATED)
	public void createTransaction(@RequestBody VersementDto versementDto) throws CompteNonExistantException, TransactionException {
		Versement versement = new Versement();
		versement = VersementMapper.ToBeanmap(versementDto);
		versementService.createTransaction(versement,versementDto.getRibCompteBeneficiaire());
	}
}
