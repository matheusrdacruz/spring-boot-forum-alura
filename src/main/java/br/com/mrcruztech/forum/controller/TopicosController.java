package br.com.mrcruztech.forum.controller;

import br.com.mrcruztech.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.mrcruztech.forum.controller.dto.TopicoDTO;
import br.com.mrcruztech.forum.controller.form.TopicoForm;
import br.com.mrcruztech.forum.model.Topico;
import br.com.mrcruztech.forum.model.Usuario;
import br.com.mrcruztech.forum.repository.CursoRepository;
import br.com.mrcruztech.forum.repository.TopicoRepository;
import br.com.mrcruztech.forum.repository.UsuarioRepository;
import br.com.mrcruztech.forum.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicosController {

//    @Autowired
//    private TopicoRepository topicoRepo;

    @Autowired
    private TopicoService topicoService;

/*    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;*/

    @GetMapping
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable page){
        //Pageable page = PageRequest.of(pagina, qnt, Sort.Direction.ASC, ordem);
        Page<Topico> topicos = null;
        if (nomeCurso == null){
            topicos = topicoService.findAll(page);
            return TopicoDTO.converterLista(topicos);
        }
        topicos = topicoService.findAllByPageable(nomeCurso, page);
        return TopicoDTO.converterLista(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> lista(@PathVariable Long id){

        DetalhesDoTopicoDto dTopicoDto = topicoService.findById(id);
        if(dTopicoDto != null){
            return ResponseEntity.ok(dTopicoDto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> salvar(@RequestBody  @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder){


        Topico topico = topicoService.save(topicoForm);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id){

        if(topicoService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> update(@PathVariable Long id, @RequestBody   @Valid TopicoForm topicoForm){

        TopicoDTO topicoDTO = topicoService.update(id, topicoForm);
        if(topicoDTO != null ){
            return ResponseEntity.ok(topicoDTO);
        }

        return ResponseEntity.notFound().build();
    }
}
