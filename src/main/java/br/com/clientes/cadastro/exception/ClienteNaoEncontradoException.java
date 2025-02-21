package br.com.clientes.cadastro.exception;

import lombok.Getter;

@Getter
public class ClienteNaoEncontradoException extends SystemBaseException {
	private final String code = "clienteNaoEncontradoException";
	private final String message = "Cliente não encontrado.";
	private final Integer httpStatus = 404;
}
