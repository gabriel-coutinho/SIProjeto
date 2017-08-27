package com.ufcg.si1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.queixa.Queixa;
import com.ufcg.si1.repository.QueixaRepository;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoInvalidoException;

import java.util.List;

@Service("queixaService")
public class QueixaServiceImpl implements QueixaService {
	@Autowired
	private QueixaRepository repositorioQueixas;
	
	public QueixaServiceImpl(QueixaRepository repositorioQueixas) {
		this.repositorioQueixas = repositorioQueixas;
	}
	
    public List<Queixa> findAllQueixas() {
        return repositorioQueixas.findAll();
    }

    public Queixa saveQueixa(Queixa queixa) throws ObjetoInvalidoException {
        if(queixa == null) {
        	throw new ObjetoInvalidoException("Queixa invalida!");
        }
    	queixa.abrir();
    	repositorioQueixas.save(queixa);
    	return queixa;
    }
    
    public Queixa addComentario(Long id, String comentario) throws ObjetoInvalidoException, ObjetoInexistenteException {
    	if (id < 0) {
     	   throw new ObjetoInvalidoException("O id eh invalido!");
        }
        
        Queixa queixa = repositorioQueixas.findOne(id);
        
        if (queixa == null) {
     	   throw new ObjetoInexistenteException("A queixa nao existe!");
        }
        queixa.setComentario(comentario);
        repositorioQueixas.save(queixa);
        return queixa;
    }
    
    public Queixa modificarStatus(Long id, String status) throws ObjetoInvalidoException, ObjetoInexistenteException {
    	if (id < 0) {
      	   throw new ObjetoInvalidoException("O id eh invalido!");
         }
         
         Queixa queixa = repositorioQueixas.findOne(id);
         
         if (queixa == null) {
      	   throw new ObjetoInexistenteException("A queixa nao existe!");
         }
         
         if(status.equalsIgnoreCase("abrir")) {
        	 queixa.abrir();
         } else if(status.equalsIgnoreCase("resolver")) {
        	 queixa.resolver();
         } else if(status.equalsIgnoreCase("fechar")) {
        	 queixa.fechar();
         } else {
        	 throw new ObjetoInexistenteException("Status invalido!");
         }
         repositorioQueixas.save(queixa);
       
         return queixa;
    }

    public Queixa deleteQueixaById(Long id) throws ObjetoInvalidoException, ObjetoInexistenteException{
    	if (id < 0) {
       	   throw new ObjetoInvalidoException("O id eh invalido!");
          }
          
          Queixa queixa = repositorioQueixas.findOne(id);
          
          if (queixa == null) {
         	   throw new ObjetoInexistenteException("A queixa nao existe!");
          }
          
          repositorioQueixas.delete(queixa);
          return queixa;
    }

    @Override
    //este metodo nunca eh chamado, mas se precisar estah aqui
    public int size() {
        return repositorioQueixas.findAll().size();
    }

    public void deleteAllUsers() {
        repositorioQueixas.deleteAll();;
    }

    public Queixa findById(Long id) throws ObjetoInvalidoException, ObjetoInexistenteException {
       if (id < 0) {
    	   throw new ObjetoInvalidoException("O id eh invalido!");
       }
       
       Queixa queixa = repositorioQueixas.findOne(id);
       
       if (queixa == null) {
    	   throw new ObjetoInexistenteException("A queixa nao existe!");
       }
       return queixa;
    }
}
