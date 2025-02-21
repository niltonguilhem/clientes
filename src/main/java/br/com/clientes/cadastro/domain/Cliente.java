package br.com.clientes.cadastro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	private String cpf;
	private String nome;
	private String endereco;
	private String cep;
}
