package com.ufcg.si1.model.queixa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import exceptions.ObjetoInvalidoException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = QueixaAberta.class, name = "ABERTA"),
@JsonSubTypes.Type(value = QueixaFechada.class, name = "FECHADA"),
@JsonSubTypes.Type(value = QueixaEmAndamento.class, name = "EM_ANDAMENTO"), })
public abstract class QueixaState{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public abstract QueixaState abrir() throws ObjetoInvalidoException;
	
	public abstract QueixaState resolver() throws ObjetoInvalidoException;
	
	public abstract QueixaState fechar() throws ObjetoInvalidoException;

	public abstract QueixaStateEnum tipoQueixa();
	
}
