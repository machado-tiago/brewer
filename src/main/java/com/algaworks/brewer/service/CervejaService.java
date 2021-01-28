package com.algaworks.brewer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.transaction.Transactional;

import com.algaworks.brewer.dto.CervejaFilterDto;
import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.CervejaRepository;
import com.algaworks.brewer.service.event.cerveja.CervejaSalvaEvent;
import com.algaworks.brewer.storage.FotoStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CervejaService {
    @Autowired
    CervejaRepository cervejaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    FotoStorage fotoStorage;

    @Transactional
    public void salvar(Cerveja cerveja) {
        cervejaRepository.save(cerveja);
        publisher.publishEvent(new CervejaSalvaEvent(cerveja));
    }

	public List<Cerveja> findAll() {
		return cervejaRepository.findAll();
	}

	public Object filtrar(CervejaFilterDto cervejaFilterDto) {
		return cervejaRepository.filtrar(cervejaFilterDto);
	}
}
