package com.ufcg.si1.model.queixa;

import exceptions.ObjetoInvalidoException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ufcg.si1.model.Pessoa;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = QueixaAlimentar.class, name = "ALIMENTAR"),
		@JsonSubTypes.Type(value = QueixaAnimalPerdido.class, name = "ANIMAL_PERDIDO") })
public class Queixa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String descricao;
	@OneToOne(cascade = CascadeType.ALL)
	private QueixaState state;

	@OneToOne(cascade = CascadeType.ALL)
	private Pessoa solicitante;

	private String comentario; // usado na atualizacao da queixa

	public Queixa(String descricao, QueixaState state, String comentario, String nome, String email) {
		this.descricao = descricao;
		this.state = state;
		this.comentario = comentario;
		this.solicitante = new Pessoa(nome, email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void abrir() throws ObjetoInvalidoException {
		this.state = state.abrir();
	}
	
	public void resolver() throws ObjetoInvalidoException {
		this.state = state.resolver();
	}

	public void fechar() throws ObjetoInvalidoException {
		this.state = state.fechar();
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Pessoa getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Pessoa solicitante) {
		this.solicitante = solicitante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Queixa other = (Queixa) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
