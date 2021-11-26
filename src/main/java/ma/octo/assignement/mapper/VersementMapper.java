package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.dto.VirementDto;

public class VersementMapper {
	private static VersementDto versementDto;
	private static Versement versement;


    public static VersementDto toDtpmap(Versement versement) {
    	versementDto = new VersementDto();
    	versementDto.setNom_prenom_emetteur(versement.getNom_prenom_emetteur());
    	versementDto.setNrCompteBeneficiaire(versement.getCompteBeneficiaire().getNrCompte());
    	versementDto.setDate(versement.getDateExecution());
    	versementDto.setMotifVersement(versement.getMotifVersement());

        return versementDto;

    }
    
    public static Versement ToBeanmap(VersementDto versementDTO) {
    	versement = new Versement();
    	versement.setNom_prenom_emetteur(versementDTO.getNom_prenom_emetteur());
    	versement.setCompteBeneficiaire(null);
    	versement.setDateExecution(versementDTO.getDate());
    	versement.setMotifVersement(versementDTO.getMotifVersement());

        return versement;

    }
}
