package com.ufcg.si1.model.queixa;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.ufcg.si1.model.Endereco;

@Entity
@DiscriminatorValue(value = "alimentar")
public class QueixaAlimentar extends Queixa {
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco enderecoEstabelecimento;
	
	public QueixaAlimentar(String descricao, QueixaState state, String comentario, String nome, String email,
			String rua, String uf, String cidade) {
		super(descricao, state, comentario, nome, email);
		this.enderecoEstabelecimento = new Endereco(rua, uf, cidade);
	}

	public Endereco getEnderecoEstabelecimento() {
		return enderecoEstabelecimento;
	}

	public void setEnderecoEstabelecimento(Endereco enderecoEstabelecimento) {
		this.enderecoEstabelecimento = enderecoEstabelecimento;
	}

}
