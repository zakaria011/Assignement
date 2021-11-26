package ma.octo.assignement.service.impl;

import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.domain.Role;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.exceptions.UnauthorizedException;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.security.JwtTokenProvider;
import ma.octo.assignement.service.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class UtilisateurServiceImpl implements UtilisateurService{

    @Autowired
    private UtilisateurRepository repository;

    // Spring security

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Spring security


    public Optional<Utilisateur> findById(Long id) {
       
        return repository.findById(id);
    }

    public List<Utilisateur> findAll() {
        return repository.findAll();
    }

    public void save(Utilisateur utilisateur) {
        repository.save(utilisateur);
    }

    public void register(Utilisateur utilisateur, Boolean isAdmin) {

        utilisateur.setRoles(new ArrayList<>());

        // Encodage du mot de passe avec bcrypt
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));

        utilisateur.getRoles().add(Role.ROLE_CLIENT);
        if (Boolean.TRUE.equals(isAdmin)) {
            utilisateur.getRoles().add(Role.ROLE_ADMIN);
        }

        repository.save(utilisateur);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, repository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new UnauthorizedException("username ou password invalid !");
        }

    }
}
