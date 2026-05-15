package com.projeto.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema.model.Cliente;
import com.projeto.sistema.repositorios.ClienteRepositorio;

@Controller
@RequestMapping("/Usuario")
public class ClienteController {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping("/cadastroCliente")
    public ModelAndView cadastrar(Cliente cliente) {
        ModelAndView mv = new ModelAndView("Usuario/cadastroCliente");
        mv.addObject("Cliente", cliente);
        return mv;
    }

    @GetMapping("/editarCliente/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        return cadastrar(cliente);
    }

    @GetMapping("/removerCliente/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {

        clienteRepositorio.deleteById(id);

        return listar(); 
    }
    
    @GetMapping("/listarCliente")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/listarCliente");
        mv.addObject("Clientes", clienteRepositorio.findAll());
        return mv;
    }

     @PostMapping("/salvarCliente")
    public ModelAndView salvar(Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(cliente);
        }
        clienteRepositorio.saveAndFlush(cliente);
        return cadastrar(new Cliente());
    }

}
