package com.algaworks.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class FotoStorageRunnable implements Runnable {

    private MultipartFile file;
    private DeferredResult<String> resultado;

    public FotoStorageRunnable(MultipartFile file, DeferredResult<String> resultado) {
        this.file = file;
        this.resultado = resultado;
    }
    
    @Override
    public void run() {
        System.out.println(file.getOriginalFilename());
        resultado.setResult("Foto recebida!");
    }
}
