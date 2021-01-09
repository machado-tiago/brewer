package com.algaworks.brewer.controller;

import com.algaworks.brewer.model.Cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {
    
    @GetMapping(value="/novo")
    public String novo(Cliente cliente) {
        return "cliente/cadastroCliente";
    }
}
