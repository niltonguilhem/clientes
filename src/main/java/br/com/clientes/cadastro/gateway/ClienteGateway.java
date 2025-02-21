package br.com.clientes.cadastro.gateway;

import br.com.clientes.cadastro.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteGateway {

	List<Cliente> buscarClientePorNome(String nome);
	List<Cliente> buscarClientePorCpf(String cpf);
	List<Cliente> buscarClientePorCep(String cep);

	void cadastrarCliente(Cliente cliente);

	Optional<Cliente> atualizarCliente(String cpf, Cliente cliente);

	void removerCliente(String cpf);
}
