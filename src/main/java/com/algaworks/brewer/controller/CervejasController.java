package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CervejasController {
    
    @GetMapping(value = "/cervejas/novo")
    public String novo() {
        return "/cerveja/cadastroCerveja";
    }
}
