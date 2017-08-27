package com.ufcg.si1.model.queixa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.ObjetoInvalidoException;

@Entity
@DiscriminatorValue(value = "andamento")
public class QueixaEmAndamento extends QueixaState{

	@Override
	public QueixaState abrir() throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("A queixa já está em andamento!");
	}

	@Override
	public QueixaState fechar() throws ObjetoInvalidoException {
		return new QueixaFechada();
	}
	
	@Override
	public QueixaState resolver() throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("A queixa já está em andamento!");
	}

	@Override
	public QueixaStateEnum tipoQueixa() {
		return QueixaStateEnum.EM_ANDAMENTO;
	}
}