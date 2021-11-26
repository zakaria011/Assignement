package ma.octo.assignement.web;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.exceptions.UnauthorizedException;
import ma.octo.assignement.mapper.UtilisateurMapper;
import ma.octo.assignement.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @PostMapping("/authenticate")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password) throws UnauthorizedException {
        return utilisateurService.login(username, password);
    }

    @PostMapping("/register")
    public void save(@RequestBody UtilisateurDto utilisateurDto, @RequestParam(name = "isAdmin") Boolean isAdmin) {
        Utilisateur u = utilisateurMapper.map(utilisateurDto);
        utilisateurService.register(u, isAdmin);
    }

}
