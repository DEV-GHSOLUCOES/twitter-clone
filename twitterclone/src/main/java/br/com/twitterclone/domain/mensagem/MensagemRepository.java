package br.com.twitterclone.domain.mensagem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
	
}