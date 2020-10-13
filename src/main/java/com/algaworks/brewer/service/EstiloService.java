package com.algaworks.brewer.service;

import javax.transaction.Transactional;

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
		return estiloRepository.save(estilo);
	}


}
