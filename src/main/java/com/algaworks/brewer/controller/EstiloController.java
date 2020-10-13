package com.algaworks.brewer.controller;

import javax.validation.Valid;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.service.EstiloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/estilos")
public class EstiloController {
    @Autowired
    EstiloService estiloService;

    @GetMapping(value = "/novo")
    public ModelAndView novo(Estilo estilo){
            return new ModelAndView("estilo/cadastroEstilo");
    }

    @PostMapping(value = "/novo")
    public ModelAndView cadastrar(RedirectAttributes attributes, Model model, @Valid Estilo estilo,BindingResult result){
        if (result.hasErrors()) {
            return novo(estilo);
        }
        estiloService.salvar(estilo);
        attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");
        return new ModelAndView("redirect:/estilos/novo");
    } 
}
