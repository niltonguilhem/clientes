package br.com.clientes.cadastro.gateway.database.jpa;

import br.com.clientes.cadastro.domain.Cliente;
import br.com.clientes.cadastro.exception.ClienteNaoEncontradoException;
import br.com.clientes.cadastro.exception.ErroAcessarRepositorioException;
import br.com.clientes.cadastro.gateway.ClienteGateway;
import br.com.clientes.cadastro.gateway.database.jpa.entity.ClienteEntity;
import br.com.clientes.cadastro.gateway.database.jpa.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClienteJpaGateway implements ClienteGateway {


	private final ClienteRepository clienteRepository;

	@Override
	public void cadastrarCliente(Cliente cliente) {
		try {
			ClienteEntity clienteEntity = mapToEntity(cliente);
			log.info("Salvando cliente no banco: {}", clienteEntity.getCpf());			clienteRepository.save(clienteEntity);

		}catch (Exception e){
			log.error("Erro ao salvar cliente no banco", e);
			throw new ErroAcessarRepositorioException();
		}

	}

	@Override
	public Optional<Cliente> atualizarCliente(String cpf, Cliente cliente) {
		Optional<ClienteEntity> clienteEntity = clienteRepository.findByCpf(cpf);
		if(clienteEntity.isEmpty()){
			throw new ClienteNaoEncontradoException();
		}
		return Optional.of(mapToDomain(clienteRepository.save(mapToEntity(cliente))));
	}

	@Override
	public void removerCliente(String cpf) {
		try {
			clienteRepository.deleteByCpf(cpf);
		}catch (Exception e){
			throw new ErroAcessarRepositorioException();
		}
	}

	@Override
	public List<Cliente> buscarClientePorNome(String nome) {
		List<ClienteEntity> clientes = clienteRepository.findByNome(nome);

		if(clientes.isEmpty()){
			throw new ClienteNaoEncontradoException();
		}

		return clientes.stream().map(this::mapToDomain).collect(Collectors.toList());
	}

	@Override
	public List<Cliente> buscarClientePorCep(String cep) {
		List<ClienteEntity> clientes = clienteRepository.findByCep(cep);

		if(clientes.isEmpty()){
			throw new ClienteNaoEncontradoException();
		}

		return clientes.stream().map(this::mapToDomain).collect(Collectors.toList());
	}

	@Override
	public List<Cliente> buscarClientePorCpf(String cpf) {
		Optional<ClienteEntity> clientes = clienteRepository.findByCpf(cpf);

		if(clientes.isEmpty()){
			throw new ClienteNaoEncontradoException();
		}

		return clientes.stream().map(this::mapToDomain).collect(Collectors.toList());
	}

	private Cliente mapToDomain(ClienteEntity clienteEntity) {
		return new Cliente(
				clienteEntity.getCpf(),
				clienteEntity.getNome(),
				clienteEntity.getEndereco(),
				clienteEntity.getCep()
		);
	}
	
	private ClienteEntity mapToEntity(Cliente cliente) {
		return new ClienteEntity(
				cliente.getCpf(),
				cliente.getNome(),
				cliente.getEndereco(),
				cliente.getCep());
	}
}
