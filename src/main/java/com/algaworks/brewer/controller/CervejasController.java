package com.algaworks.brewer.controller;

import javax.validation.Valid;

import com.algaworks.brewer.model.Cerveja;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CervejasController {
    
    @GetMapping(value = "/cervejas/novo")
    public String novo() {
        return "/cerveja/cadastroCerveja";
    }

    @PostMapping(value = "/cervejas/novo")
    public String cadastrar(@ModelAttribute @Valid Cerveja cerveja, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("Erro no preenchimento do formulário!");
        }
        System.out.println(cerveja.getSku());
        return "/cerveja/cadastroCerveja";
    }
}
