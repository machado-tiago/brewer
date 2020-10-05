package com.algaworks.brewer.service;

import javax.transaction.Transactional;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.CervejaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CervejaService {
    @Autowired
    CervejaRepository cervejaRepository;

    @Transactional
    public void salvar(Cerveja cerveja){
        cervejaRepository.save(cerveja);
    }
}
