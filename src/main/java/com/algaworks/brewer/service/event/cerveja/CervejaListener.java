package com.algaworks.brewer.service.event.cerveja;

import com.algaworks.brewer.storage.FotoStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CervejaListener {

    @Autowired
    private FotoStorage fotoStorage;
    
    @EventListener(condition = "#evento.temFoto()")  //indica que o método deve ser executado quando o evento foi publicado, já que o evento não indica os métodos que tem que ser executados.
    public void cervejaSalva(CervejaSalvaEvent evento){
        System.out.println("Nova cerveja salve: " + evento.getCerveja().getNome());
        fotoStorage.salvar(evento.getCerveja().getNomeFoto());
    }
}
