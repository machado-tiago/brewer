package com.algaworks.brewer.controller;

import javax.validation.Valid;

import com.algaworks.brewer.model.Cerveja;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CervejasController {
    
    @GetMapping(value = "/cervejas/novo")
    public String novo(Cerveja cerveja) {
        return "/cerveja/cadastroCerveja";
    }

    @PostMapping(value = "/cervejas/novo")
    public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return novo(cerveja);
        }
        attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
        return "redirect:/cervejas/novo";
    }
    
}
