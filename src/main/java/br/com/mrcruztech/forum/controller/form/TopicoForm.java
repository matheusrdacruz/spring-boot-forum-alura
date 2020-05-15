package br.com.mrcruztech.forum.controller.form;

import br.com.mrcruztech.forum.model.Curso;
import br.com.mrcruztech.forum.model.Topico;
import br.com.mrcruztech.forum.repository.CursoRepository;
import br.com.mrcruztech.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoForm {
    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String messagem) {
        this.mensagem = messagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico convert(CursoRepository cursoRepo) {
        Curso curso = cursoRepo.findByNome(this.nomeCurso);
        return new Topico(this.titulo, this.mensagem, curso);
    }

    public Topico update(Long id, TopicoRepository topicoRepo, CursoRepository cursoRepo) {
        Topico topico = topicoRepo.getOne(id);
        Curso curso = cursoRepo.findByNome(this.getNomeCurso());
        topico.setTitulo(this.titulo);
        topico.setCurso(curso);
        topico.setMensagem(this.mensagem);

        return topicoRepo.save(topico);
    }
}
