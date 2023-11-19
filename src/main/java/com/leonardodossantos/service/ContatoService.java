package com.leonardodossantos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardodossantos.model.Contato;
import com.leonardodossantos.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	ContatoRepository contatoRepository;

	public boolean validarTelefone(String telefone) {
		// Remover caracteres não numéricos do telefone
		String telefoneApenasNumeros = telefone.replaceAll("[^0-9]", "");

		// Verificar se o número de telefone possui um formato aceitável
		// Por exemplo, consideramos um telefone com 10 ou 11 dígitos como válido
		return (telefoneApenasNumeros.length() >= 10 && telefoneApenasNumeros.length() <= 11);
	}

	public String cadastrarContato(Contato contato, Long id) {
		// Verificar se já existe um contato com o mesmo email
		Contato contatoExistente = contatoRepository.finByEmail(contato.getEmail());

		if (contatoExistente != null && !contatoExistente.getId().equals(id)) {
			return "Já existe um contato para este e-mail!";
		} else {
			contatoRepository.save(contato);
			return null;
		}
	}

	public String alterarContato(Contato contato, Long id) {
	    // Verificar se já existe um contato com o mesmo e-mail, excluindo o próprio contato sendo editado
	    Contato contatoExistenteComMesmoEmail = contatoRepository.finByEmail(contato.getEmail());

	    if (contatoExistenteComMesmoEmail != null && !contatoExistenteComMesmoEmail.getId().equals(id)) {
	        return "Já existe um contato com o mesmo e-mail.";
	    } else {
	        Optional<Contato> contatoEmEdicaoOptional = contatoRepository.findById(id);

	        if (contatoEmEdicaoOptional.isPresent()) {
	            Contato contatoEmEdicao = contatoEmEdicaoOptional.get();
	            contatoEmEdicao.setEmail(contato.getEmail());
	            contatoEmEdicao.setNome(contato.getNome());
	            contatoEmEdicao.setTelefone(contato.getTelefone());
	            contatoEmEdicao.setAdicionais(contato.getAdicionais());
	            contatoRepository.save(contatoEmEdicao);
	            return null; // Edição concluída com sucesso
	        } else {
	            return "Contato não encontrado.";
	        }
	    }
	}


}
