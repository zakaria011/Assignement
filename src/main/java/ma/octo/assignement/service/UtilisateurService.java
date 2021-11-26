package ma.octo.assignement.service;

import java.util.List;
import java.util.Optional;
import ma.octo.assignement.domain.Utilisateur;


public interface UtilisateurService {
	
	public Optional<Utilisateur> findById(Long id);
	
    public List<Utilisateur> findAll();

    public void save(Utilisateur utilisateur);

    public void register(Utilisateur utilisateur, Boolean isAdmin);

    public void delete(Long id);

    public String login(String username, String password);

}
