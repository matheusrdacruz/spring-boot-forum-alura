package br.com.mrcruztech.forum.config.security;

import br.com.mrcruztech.forum.model.Usuario;
import br.com.mrcruztech.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepo.findByEmail(userName);
        if(usuario.isPresent())
            return  usuario.get();

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
