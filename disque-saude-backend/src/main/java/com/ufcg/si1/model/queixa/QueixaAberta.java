package com.ufcg.si1.model.queixa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.ObjetoInvalidoException;

@Entity
@DiscriminatorValue(value = "aberta")
public class QueixaAberta extends QueixaState{

	@Override
	public QueixaState abrir() throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("A queixa ja esta aberta!");
	}

	@Override
	public QueixaState fechar() throws ObjetoInvalidoException {
		return new QueixaFechada();
	}
	
	@Override
	public QueixaState resolver() throws ObjetoInvalidoException {
		return new QueixaEmAndamento();
	}
	
	@Override
	public QueixaStateEnum tipoQueixa() {
		return QueixaStateEnum.ABERTA;
	}

}
