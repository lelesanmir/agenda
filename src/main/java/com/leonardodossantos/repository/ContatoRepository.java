package com.leonardodossantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leonardodossantos.model.Contato;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, Long> {
	@Query("SELECT i FROM Contato i WHERE i.nome = :nome")
	public Contato finByEmail(String nome);

	// Método para buscar todos os contatos ordenados por ID
	@Query("SELECT c FROM Contato c ORDER BY c.id")
	List<Contato> findAllOrderedById();

}
