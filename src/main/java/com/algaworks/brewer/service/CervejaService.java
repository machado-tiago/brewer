package com.algaworks.brewer.service;

import java.io.IOException;

import javax.transaction.Transactional;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.CervejaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CervejaService {
    @Autowired
    CervejaRepository cervejaRepository;

    @Transactional
    public void salvar(Cerveja cerveja, MultipartFile file) {
        try {
            cerveja.setFoto(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler foto", e);
        }
        cervejaRepository.save(cerveja);
    }
}
