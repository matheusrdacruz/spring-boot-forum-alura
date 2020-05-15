package br.com.mrcruztech.forum.controller;

import br.com.mrcruztech.forum.config.security.TokenService;
import br.com.mrcruztech.forum.controller.dto.TokenDto;
import br.com.mrcruztech.forum.controller.form.LoginForm;
import br.com.mrcruztech.forum.model.Usuario;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> auth(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken dataLogin = loginForm.coverter();
        try {
            Authentication authentication = authManager.authenticate(dataLogin);
            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }
}
