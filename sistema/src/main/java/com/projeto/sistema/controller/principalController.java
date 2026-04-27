package com.projeto.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class principalController {
    @GetMapping("/adiministrativo")
    public String acessarPrincipal() {
        return "adiministrativo/home";
    }

}
