package ma.octo.assignement.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.mapper.UtilisateurMapper;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.UtilisateurService;

@RestController
@RequestMapping("/api/v1/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService service;

    @Autowired
    private CompteService compteService;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UtilisateurDto> loadAllUtilisateurs() {
        List<Utilisateur> all = service.findAll();
        return CollectionUtils.isEmpty(all) ? null : all.stream().map(utilisateurMapper::map).collect(Collectors.toList());
    }

    @GetMapping("/comptes/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Compte> loadAllComptes() {
        List<Compte> all = compteService.findAll();
        return CollectionUtils.isEmpty(all) ? null : all;
    }

}