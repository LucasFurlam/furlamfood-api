package com.furlam.food.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CidadeNaoEncontradoException(Long cidadeId) {
		this(String.format("Não existe um cadastro de cidade com o código %d", cidadeId));
	}

}
