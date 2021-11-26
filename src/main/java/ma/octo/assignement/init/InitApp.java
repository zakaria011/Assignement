package ma.octo.assignement.init;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VirementRepository;


@Component
public class InitApp implements ApplicationRunner{
	
	
	@Autowired
    private CompteRepository compteRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private VirementRepository virementRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Load users

        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setUsername("user1");
        utilisateur1.setLastname("last1");
        utilisateur1.setFirstname("first1");
        utilisateur1.setGender("Male");
        utilisateur1.setPassword("pass1");

        utilisateurRepository.save(utilisateur1);

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setUsername("user2");
        utilisateur2.setLastname("last2");
        utilisateur2.setFirstname("first2");
        utilisateur2.setGender("Female");
        utilisateur1.setPassword(encoder.encode("pass2"));

        utilisateurRepository.save(utilisateur2);

        // Load accounts

        Compte compte1 = new Compte();
        compte1.setNrCompte("010000A000001000");
        compte1.setRib("RIB1");
        compte1.setSolde(BigDecimal.valueOf(200000L));
        compte1.setUtilisateur(utilisateur1);

        compteRepository.save(compte1);

        Compte compte2 = new Compte();
        compte2.setNrCompte("010000B025001000");
        compte2.setRib("RIB2");
        compte2.setSolde(BigDecimal.valueOf(500));
        compte2.setUtilisateur(utilisateur2);

        compteRepository.save(compte2);

      

        Virement v = new Virement();
        v.setMontantVirement(BigDecimal.TEN);
        v.setCompteBeneficiaire(compte2);
        v.setCompteEmetteur(compte1);
        v.setDateExecution(new Date());
        v.setMotifVirement("Assignment 2021");

        virementRepository.save(v);
    }
}
