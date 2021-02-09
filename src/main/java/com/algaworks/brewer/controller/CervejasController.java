package com.algaworks.brewer.controller;

import javax.validation.Valid;

import com.algaworks.brewer.dto.CervejaFilterDto;
import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.service.CervejaService;
import com.algaworks.brewer.service.EstiloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/cervejas")
public class CervejasController {
    @Autowired
    private EstiloService estiloService;

    @Autowired
    private CervejaService cervejaService;

    @GetMapping(value = "/novo")
    public ModelAndView novo(Cerveja cerveja) {
        ModelAndView mv = new ModelAndView("/cerveja/cadastroCerveja");
        mv.addObject("sabores", Sabor.values());
        mv.addObject("estilos", estiloService.findAll());
        mv.addObject("origens", Origem.values());
        return mv;
    }

    @PostMapping(value = "/novo")
    public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return novo(cerveja);//PARA NÃO TER QUE PASSAR UM MODEL COMO PARÂMETRO, UTILIZAMOS O MODELANDVIEW.
        }
        cervejaService.salvar(cerveja);
        attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
        return new ModelAndView("redirect:/cervejas/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(CervejaFilterDto cervejaFilterDto, BindingResult result, @PageableDefault (size = 2) Pageable pageable){
        ModelAndView mv = new ModelAndView("cerveja/pesquisaCervejas");
        mv.addObject("sabores", Sabor.values());
        mv.addObject("estilos", estiloService.findAll());
        mv.addObject("origens", Origem.values());
        mv.addObject("cervejas", cervejaService.filtrar(cervejaFilterDto, pageable));
        return mv;
    }
}
