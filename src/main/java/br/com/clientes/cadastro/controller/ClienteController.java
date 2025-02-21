package br.com.clientes.cadastro.controller;

import br.com.clientes.cadastro.controller.json.ClienteJson;
import br.com.clientes.cadastro.domain.Cliente;
import br.com.clientes.cadastro.usecase.GerenciarClienteUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

	private final GerenciarClienteUsecase gerenciarClienteUsecase;

	@PostMapping
	public ResponseEntity<String> cadastrar(@Valid @RequestBody ClienteJson clienteJson) {
		System.out.println("Cliente a ser salvo: " + clienteJson.getCpf());
		gerenciarClienteUsecase.cadastrarCliente(mapToDomain(clienteJson));
		return ResponseEntity.ok("Cliente cadastrado com sucesso!");
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<ClienteJson> buscarClientePorCpf(@PathVariable String cpf) {
		List<Cliente> clientes = gerenciarClienteUsecase.buscarClientePorCpf(cpf);
		List<ClienteJson> clienteJsons = clientes.stream().map(this::mapToJson).collect(Collectors.toList());
		return ResponseEntity.ok(clienteJsons.getFirst());
	}

	@GetMapping("nome/{nome}")
	public ResponseEntity<List<ClienteJson>> buscarClientePorNome(@PathVariable String nome) {
		List<Cliente> clientes = gerenciarClienteUsecase.buscarClientePorNome(nome);
		List<ClienteJson> clienteJsons = clientes.stream().map(this::mapToJson).collect(Collectors.toList());
		return ResponseEntity.ok(clienteJsons);
	}

	@GetMapping("cep/{cep}")
	public ResponseEntity<List<ClienteJson>> buscarClientePorCep(@PathVariable String cep) {
		List<Cliente> clientes = gerenciarClienteUsecase.buscarClientePorCep(cep);
		List<ClienteJson> clienteJsons = clientes.stream().map(this::mapToJson).collect(Collectors.toList());
		return ResponseEntity.ok(clienteJsons);
	}

	@PutMapping("/{cpf}")
	public ResponseEntity<?> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteJson clienteJson) {
		Optional<Cliente> cliente = gerenciarClienteUsecase.atualizarCliente(cpf, mapToDomain(clienteJson));
		return cliente.map(c -> ResponseEntity.ok(mapToJson(c))).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<Void> removerCliente(@PathVariable String cpf) {
		gerenciarClienteUsecase.removerCliente(cpf);
		return ResponseEntity.noContent().build();
	}

	private Cliente mapToDomain(ClienteJson clienteJson) {
		return new Cliente(
				clienteJson.getCpf(),
				clienteJson.getNome(),
				clienteJson.getEndereco(),
				clienteJson.getCep()
		);
	}

	private ClienteJson mapToJson(Cliente cliente) {
		return new ClienteJson(
				cliente.getCpf(),
				cliente.getNome(),
				cliente.getEndereco(),
				cliente.getCep()
		);
	}
}