package br.com.clientes.cadastro.infraestrutura;

import br.com.clientes.cadastro.exception.ClienteNaoEncontradoException;
import br.com.clientes.cadastro.exception.ErroAcessarRepositorioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> tratarErroClienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getHttpStatus())).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ErroAcessarRepositorioException.class)
    public ResponseEntity<ErrorResponse> tratarErroAcessarRepositorioException(ErroAcessarRepositorioException ex) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getHttpStatus())).body(new ErrorResponse(ex.getMessage()));
    }

    public record ErrorResponse(String message) { }
}
