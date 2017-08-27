package com.ufcg.si1.model.queixa;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.ufcg.si1.model.Endereco;

@Entity
@DiscriminatorValue(value = "animal_perdido")
public class QueixaAnimalPerdido extends Queixa {
	private String tipoAnimal;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco localAnimalPerdido;
	
	public QueixaAnimalPerdido(String descricao, QueixaState state, String comentario, String nome, String email,
			String rua, String uf, String cidade, String tipoAnimal) {
		super(descricao, state, comentario, nome, email);
		this.localAnimalPerdido = new Endereco(rua, uf, cidade);
		this.tipoAnimal = tipoAnimal;
	}

	public String getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public Endereco getLocalAnimalPerdido() {
		return localAnimalPerdido;
	}

	public void setLocalAnimalPerdido(Endereco localAnimalPerdido) {
		this.localAnimalPerdido = localAnimalPerdido;
	}

}
