package com.algaworks.brewer.storage;

import java.io.IOException;

import com.algaworks.brewer.dto.FotoDto;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class FotoStorageRunnable implements Runnable {

    private MultipartFile file;
    private DeferredResult<FotoDto> resultado;
    private FotoStorage fotoStorage;

    public FotoStorageRunnable(MultipartFile file, DeferredResult<FotoDto> resultado, FotoStorage fotoStorage) {
        this.file = file;
        this.resultado = resultado;
        this.fotoStorage = fotoStorage;
    }

    @Override
    public void run() {
        try {
            this.fotoStorage.salvarTemporariamente(file);
            resultado.setResult(new FotoDto(file.getOriginalFilename(), file.getBytes(), file.getContentType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
