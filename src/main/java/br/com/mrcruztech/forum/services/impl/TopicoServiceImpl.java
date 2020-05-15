package br.com.mrcruztech.forum.services.impl;

import br.com.mrcruztech.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.mrcruztech.forum.controller.dto.TopicoDTO;
import br.com.mrcruztech.forum.controller.form.TopicoForm;
import br.com.mrcruztech.forum.model.Curso;
import br.com.mrcruztech.forum.model.Topico;
import br.com.mrcruztech.forum.model.Usuario;
import br.com.mrcruztech.forum.repository.CursoRepository;
import br.com.mrcruztech.forum.repository.TopicoRepository;
import br.com.mrcruztech.forum.repository.UsuarioRepository;
import br.com.mrcruztech.forum.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoServiceImpl implements TopicoService {

    @Autowired
    private TopicoRepository topicoRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public Page<Topico> findAllByPageable(String nomeCurso, Pageable page) {

        return topicoRepo.findByCursoNome(nomeCurso, page);
    }

    @Override
    public Page<Topico> findAll(Pageable page) {
        return topicoRepo.findAll(page);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Topico> topico = topicoRepo.findById(id);
        if(topico.isPresent()){
            topicoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public TopicoDTO update(Long id, TopicoForm topicoForm) {
        Optional<Topico> topico = topicoRepo.findById(id);
        if(topico.isPresent()){
            topico.get().setMensagem(topicoForm.getMensagem());
            topico.get().setCurso(cursoRepo.findByNome(topicoForm.getNomeCurso()));
            topico.get().setTitulo(topicoForm.getTitulo());
            return new TopicoDTO(topicoRepo.save(topico.get()));
        }
        return null;
    }

    @Override
    public Topico save(TopicoForm topicoForm) {
        Usuario autor = usuarioRepo.getOne(1l);
        Curso curso = cursoRepo.findByNome(topicoForm.getNomeCurso());

        Topico topico = new Topico(topicoForm.getTitulo(), topicoForm.getMensagem(), curso);
        topico.setAutor(autor);
        return topicoRepo.save(topico);
    }

    @Override
    public DetalhesDoTopicoDto findById(Long id) {
        Optional<Topico> topico = topicoRepo.findById(id);

        if(topico.isPresent()){
            return new DetalhesDoTopicoDto(topico.get());
        }

        return null;
    }
}
