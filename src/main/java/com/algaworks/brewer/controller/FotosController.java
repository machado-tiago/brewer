package com.algaworks.brewer.controller;

import com.algaworks.brewer.dto.FotoDto;
import com.algaworks.brewer.storage.FotoStorage;
import com.algaworks.brewer.storage.FotoStorageRunnable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fotos")
public class FotosController {
    @Autowired
    private FotoStorage fotoStorage;

    @RequestMapping(value = "/fileupload", method = RequestMethod.POST, consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public DeferredResult<FotoDto> fileupload(@RequestParam(required = true, value = "file") MultipartFile file){
        
        DeferredResult<FotoDto> resultado = new DeferredResult<>();//instancia a resposta assíncrona
        Thread thread = new Thread(new FotoStorageRunnable(file,resultado, fotoStorage)); //define uma nova thread
        thread.start();

        return resultado;
    }
    
}
