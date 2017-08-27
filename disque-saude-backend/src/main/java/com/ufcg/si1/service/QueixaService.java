package com.ufcg.si1.service;


import java.util.List;

import com.ufcg.si1.model.queixa.Queixa;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoInvalidoException;

public interface QueixaService {

	List<Queixa> findAllQueixas();

    Queixa saveQueixa(Queixa queixa) throws ObjetoInvalidoException;

    Queixa findById(Long id) throws ObjetoInvalidoException, ObjetoInexistenteException;
    
    Queixa addComentario(Long id, String comentario) throws ObjetoInvalidoException, ObjetoInexistenteException;

	Queixa modificarStatus(Long id, String status) throws ObjetoInvalidoException, ObjetoInexistenteException;

	Queixa deleteQueixaById(Long id) throws ObjetoInvalidoException, ObjetoInexistenteException;

    int size();

//	boolean isUserExist(Queixa user);
	
}
