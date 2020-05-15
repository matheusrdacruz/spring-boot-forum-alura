package br.com.mrcruztech.forum.services;

import br.com.mrcruztech.forum.controller.dto.UsuarioDto;
import br.com.mrcruztech.forum.controller.form.UsuarioForm;

public interface UsuarioService {

    UsuarioDto save(UsuarioForm usuarioForm);
}
