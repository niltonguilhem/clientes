package br.com.clientes.cadastro.controller.json;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClienteJson {
	@Id
	@Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
	private String cpf;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotBlank(message = "Endereço é obrigatório")
	private String endereco;

	@Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
	private String cep;
}