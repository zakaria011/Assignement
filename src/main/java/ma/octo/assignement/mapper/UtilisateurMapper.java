package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurDto map(Utilisateur utilisateur) {
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setFirstname(utilisateur.getFirstname());
    	utilisateurDto.setLastname(utilisateur.getLastname());
    	utilisateurDto.setUsername(utilisateur.getUsername());
    	//password must be hidden
    	utilisateurDto.setPassword(utilisateur.getPassword());
    	utilisateurDto.setGender(utilisateur.getGender());
        return utilisateurDto;
    }

    public Utilisateur map(UtilisateurDto dto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setFirstname(dto.getFirstname());
        utilisateur.setLastname(dto.getLastname());
        utilisateur.setUsername(dto.getUsername());
        utilisateur.setPassword(dto.getPassword());
        utilisateur.setGender(dto.getGender());
        return utilisateur;
    }

}
