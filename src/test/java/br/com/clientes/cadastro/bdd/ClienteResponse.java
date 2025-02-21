package br.com.clientes.cadastro.bdd;

import br.com.clientes.cadastro.domain.Cliente;
import lombok.Getter;

import java.util.List;

@Getter
public class ClienteResponse {
    private List<Cliente> clientes;
    private String message;
}
