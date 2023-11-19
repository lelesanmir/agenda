package com.leonardodossantos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.leonardodossantos.repository.ContatoRepository;

@Service
public class ServiceEmail {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ContatoRepository contatoRepository;

	public void sendEmail(String remetente, String email, String assunto, String corpo) {
		SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setFrom(remetente);
		mensagem.setTo(email);
		mensagem.setText(corpo);
		mensagem.setSubject(assunto);
		javaMailSender.send(mensagem);
	}

}
