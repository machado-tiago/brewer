package com.algaworks.brewer.exception;

public class NovoEstiloJaCadastradoException extends RuntimeException{
    public static final long serialVersionUID = 1L;

	public NovoEstiloJaCadastradoException() {
		super("Nome do estilo já cadastrado");
	}
}
