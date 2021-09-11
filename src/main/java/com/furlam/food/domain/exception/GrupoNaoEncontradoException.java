package com.furlam.food.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe cadastro de grupo com o código %d", estadoId));
    }
}
