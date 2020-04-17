package br.com.twitterclone.domain.mensagem;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.twitterclone.domain.usuario.Usuario;

@Entity
public class Mensagem {

	@Id
	@GeneratedValue
	private Integer id;
	
	private LocalDateTime dataHora;
	
	private String texto;
	
	private int curtidas;
	
	
	@ManyToOne
	private Usuario usuario;
	
	public long calcularTempoMinutos() {
		return ChronoUnit.MINUTES.between(dataHora, LocalDateTime.now());
	}
	
	public void curtir() {
		curtidas++;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getCurtidas() {
		return curtidas;
	}
}
