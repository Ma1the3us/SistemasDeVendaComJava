package com.projeto.sistema.controller;

import java.util.Optional;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema.model.Estado;
import com.projeto.sistema.repositorios.EstadoRepositorio;

@Controller
@RequestMapping("/estados")

public class EstadoController {

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrar(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estados/cadastroEstado");
        mv.addObject("estado", estado);
        return mv;
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        Estado estado = estadoRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        return cadastrar(estado);
    }

    @GetMapping("/removerEstado/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {

        estadoRepositorio.deleteById(id);

        return listar(); 
    }

    @GetMapping("/listarEstado")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/estados/listarEstado");
        mv.addObject("estados", estadoRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(estado);
        }
        estadoRepositorio.saveAndFlush(estado);
        return cadastrar(new Estado());
    }
}
