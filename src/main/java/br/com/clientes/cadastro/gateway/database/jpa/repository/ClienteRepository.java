package br.com.clientes.cadastro.gateway.database.jpa.repository;

import br.com.clientes.cadastro.gateway.database.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<ClienteEntity, String>{
	Optional<ClienteEntity> findByCpf(String cpf);
	List<ClienteEntity> findByNome(String nome);
	List<ClienteEntity> findByCep(String cep);

	void deleteByCpf(String cpf);
}
