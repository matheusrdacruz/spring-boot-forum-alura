package br.com.mrcruztech.forum.controller;

import br.com.mrcruztech.forum.model.Curso;
import br.com.mrcruztech.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepo;

    @GetMapping
    public List<Curso> lista(){
        return cursoRepo.findAll();
    }

}
