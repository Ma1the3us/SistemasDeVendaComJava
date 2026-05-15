package com.projeto.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class principalController {
    @GetMapping("/")
    public String acessarPrincipal() {
        return "administrativo/home";
    }

}
