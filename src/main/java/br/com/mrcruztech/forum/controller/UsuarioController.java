package br.com.mrcruztech.forum.controller;

import br.com.mrcruztech.forum.controller.dto.TopicoDTO;
import br.com.mrcruztech.forum.controller.dto.UsuarioDto;
import br.com.mrcruztech.forum.controller.form.UsuarioForm;
import br.com.mrcruztech.forum.model.Usuario;
import br.com.mrcruztech.forum.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDto> save(@RequestBody  UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder){
        UsuarioDto usuarioDto = usuarioService.save(usuarioForm);
        if(usuarioDto == null){
            return ResponseEntity.badRequest().build();
        }
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(usuarioDto.getId()).toUri();

        return ResponseEntity.created(uri).body(usuarioDto);


    }
}
