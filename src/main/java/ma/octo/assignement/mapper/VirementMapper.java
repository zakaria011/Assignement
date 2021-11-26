package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;

public class VirementMapper {

    private static VirementDto virementDto;

    public static VirementDto map(Virement virement) {
        virementDto = new VirementDto();
        virementDto.setNrCompteEmetteur(virement.getCompteEmetteur().getNrCompte());
        virementDto.setNrCompteBeneficiaire(virement.getCompteBeneficiaire().getNrCompte());
        virementDto.setDate(virement.getDateExecution());
        virementDto.setMotif(virement.getMotifVirement());

        return virementDto;

    }
    
    
    public static Virement map(VirementDto virementDto) {
        Virement virement = new Virement();
        virement.setMotifVirement(virementDto.getMotif());
        virement.setDateExecution(virementDto.getDate());
        virement.setMontantVirement(virementDto.getMontantVirement());

        return virement;

    }
    
    
    
    
    
}
