package br.com.mrcruztech.forum.repository;

import br.com.mrcruztech.forum.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCursoNome(String nomeCurso, Pageable page);

    @Query("SELECT t FROM Topico t WHERE t.id = ?1")
    Topico findTopicoById(Long id);
}
