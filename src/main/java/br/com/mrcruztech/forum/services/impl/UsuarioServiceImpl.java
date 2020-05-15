package br.com.mrcruztech.forum.services.impl;

import br.com.mrcruztech.forum.controller.dto.UsuarioDto;
import br.com.mrcruztech.forum.controller.form.UsuarioForm;
import br.com.mrcruztech.forum.model.Usuario;
import br.com.mrcruztech.forum.repository.UsuarioRepository;
import br.com.mrcruztech.forum.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UsuarioDto save(UsuarioForm usuarioForm) {

        if(userExists(usuarioForm.getEmail()) || !passwordEquals(usuarioForm.getSenha(), usuarioForm.getConfirmaSenha()) ){
            return null;
        }

        Usuario usuario = new Usuario(usuarioForm.getNome(), usuarioForm.getEmail(), new BCryptPasswordEncoder().encode(usuarioForm.getSenha()));
        return new UsuarioDto(usuarioRepo.save(usuario));

    }

    private boolean passwordEquals(String senha, String confirmaSenha) {
        System.out.println("Senha está correta? "+ senha.equals(confirmaSenha));
        return senha.equals(confirmaSenha);
    }

    private boolean userExists(String email) {
        Optional<Usuario> u = usuarioRepo.findByEmail(email);
        System.out.println("Usuario exite? "+ u.isPresent());
        return u.isPresent();
    }
}
