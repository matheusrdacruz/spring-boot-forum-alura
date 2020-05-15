package br.com.mrcruztech.forum.services;

import br.com.mrcruztech.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.mrcruztech.forum.controller.dto.TopicoDTO;
import br.com.mrcruztech.forum.controller.form.TopicoForm;
import br.com.mrcruztech.forum.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoService {

    Page<Topico> findAllByPageable(String nomeCurso, Pageable page);

    Page<Topico> findAll(Pageable page);

    boolean delete(Long id);

    TopicoDTO update(Long id, TopicoForm topicoForm);

    Topico save(TopicoForm topicoForm);

    DetalhesDoTopicoDto findById(Long id);
}
