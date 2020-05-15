package br.com.mrcruztech.forum.controller.dto;

import br.com.mrcruztech.forum.model.Resposta;

import java.time.LocalDateTime;

public class RespostaDto {

    private Long id;
    private String Mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDto() {
    }

    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        Mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
