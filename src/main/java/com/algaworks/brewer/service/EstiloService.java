package com.algaworks.brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.algaworks.brewer.exception.NovoEstiloJaCadastradoException;
import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.EstiloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstiloService {
    
    @Autowired
    private EstiloRepository estiloRepository;

	public Object findAll() {
		return estiloRepository.findAll();
	}

	@Transactional
	public Estilo salvar(Estilo estilo){
		Optional <Estilo> estiloOptional = estiloRepository.findByNomeIgnoreCase(estilo.getNome());
		if (estiloOptional.isPresent()) {
			throw new NovoEstiloJaCadastradoException();
		} else {
			return estiloRepository.save(estilo);
		}
	}


}
