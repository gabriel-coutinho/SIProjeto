package com.ufcg.si1.model.queixa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.ObjetoInvalidoException;

@Entity
@DiscriminatorValue(value = "fechada")
public class QueixaFechada extends QueixaState{

	@Override
	public QueixaState abrir() throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("A queixa ja esta fechada!");
	}

	@Override
	public QueixaState fechar() throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("A queixa ja esta fechada!");
	}
	
	@Override
	public QueixaState resolver() throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("A queixa ja esta fechada!");
	}

	@Override
	public QueixaStateEnum tipoQueixa() {
		return QueixaStateEnum.FECHADA;
	}

}
