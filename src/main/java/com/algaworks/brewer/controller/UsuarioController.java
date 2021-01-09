package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    @GetMapping(value = "/novo")
    public String novo(){
        return "usuario/cadastroUsuario";
    }
}
