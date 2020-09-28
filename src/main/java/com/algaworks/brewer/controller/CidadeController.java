package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cidades")
public class CidadeController {
    
    @GetMapping(value = "/novo")
    public String novo(){
        return "cidade/cadastroCidade";
    }
}
