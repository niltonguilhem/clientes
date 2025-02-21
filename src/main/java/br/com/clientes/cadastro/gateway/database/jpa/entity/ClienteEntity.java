package br.com.clientes.cadastro.gateway.database.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Cliente")
public class ClienteEntity {
	@Id
	private String cpf;
	private String nome;
	private String endereco;
	private String cep;
}
