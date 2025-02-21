package br.com.clientes.cadastro.usecase;

import br.com.clientes.cadastro.domain.Cliente;
import br.com.clientes.cadastro.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GerenciarClienteUsecase {

	private final ClienteGateway clienteGateway;

	public void cadastrarCliente(Cliente cliente) {
        clienteGateway.cadastrarCliente(cliente);
    }

    public List<Cliente> buscarClientePorNome(String nome){
        return clienteGateway.buscarClientePorNome(nome);
    }

    public List<Cliente> buscarClientePorCpf(String cpf){
        return clienteGateway.buscarClientePorCpf(cpf);
    }

    public List<Cliente> buscarClientePorCep(String cep) {
        return clienteGateway.buscarClientePorCep(cep);
    }

    public Optional<Cliente> atualizarCliente(String cpf, Cliente cliente){
        return clienteGateway.atualizarCliente(cpf, cliente);
    }

    public void removerCliente(String cpf){
        clienteGateway.removerCliente(cpf);
    }
}
