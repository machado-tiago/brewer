package com.algaworks.brewer.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.CervejaRepository;
import com.algaworks.brewer.storage.FotoStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CervejaService {
    @Autowired
    CervejaRepository cervejaRepository;

    @Autowired
    FotoStorage fotoStorage;

    @Transactional
    public void salvar(Cerveja cerveja) {
        try {
            cervejaRepository.save(cerveja);
            Path atualPath = Paths.get(fotoStorage.getTemp().toAbsolutePath().toString(), cerveja.getNomeFoto());
            Files.move(atualPath, Paths.get(fotoStorage.getLocal().toAbsolutePath().toString(), cerveja.getNomeFoto()));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler foto", e);
        }
    }
}
