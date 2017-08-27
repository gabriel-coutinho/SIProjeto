package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.si1.model.queixa.Queixa;
import com.ufcg.si1.service.QueixaService;
import com.ufcg.si1.service.QueixaServiceImpl;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@RestController
@CrossOrigin
public class QueixaController {

	@Autowired
	QueixaService queixaService;

	public QueixaController(QueixaService queixaService) {
		this.queixaService = queixaService;
	}
	
	@RequestMapping(value = "/queixa/", method = RequestMethod.GET)
	public ResponseEntity<List<Queixa>> listAllQueixas() {
		List<Queixa> queixas = queixaService.findAllQueixas();

		if (queixas.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Queixa>>(queixas, HttpStatus.OK);
	}

	// -------------------Abrir uma
	// Queixa-------------------------------------------

	@RequestMapping(value = "/queixa/", method = RequestMethod.POST)
	public ResponseEntity<?> abrirQueixa(@RequestBody Queixa queixa) {

		try {
			Queixa queixaCriada = queixaService.saveQueixa(queixa);
			return new ResponseEntity<>(queixaCriada, HttpStatus.CREATED);
		} catch (ObjetoInvalidoException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarQueixa(@PathVariable("id") Long id) {
		try {
			Queixa queixa = queixaService.findById(id);
			return new ResponseEntity<Queixa>(queixa, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<?> updateQueixa(@PathVariable("id") Long id, @RequestBody Queixa queixa) {
//
//		Queixa currentQueixa = queixaService.findById(id);
//
//		if (currentQueixa == null) {
//			return new ResponseEntity(new CustomErrorType("Unable to upate. Queixa with id " + id + " not found."),
//					HttpStatus.NOT_FOUND);
//		}
//
//		currentQueixa.setDescricao(queixa.getDescricao());
//		currentQueixa.setComentario(queixa.getComentario());
//
//		queixaService.updateQueixa(currentQueixa);
//		return new ResponseEntity<Queixa>(currentQueixa, HttpStatus.OK);
//	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		try {
			queixaService.deleteQueixaById(id);			
			return new ResponseEntity<Queixa>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

//	@RequestMapping(value = "/queixa/fechamento", method = RequestMethod.POST)
//	public ResponseEntity<?> fecharQueixa(@RequestBody Queixa queixaAFechar) {
//		queixaAFechar.situacao = Queixa.FECHADA;
//		queixaService.updateQueixa(queixaAFechar);
//		return new ResponseEntity<Queixa>(queixaAFechar, HttpStatus.OK);
//	}
}
