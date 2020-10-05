package com.algaworks.brewer.service;

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


}
