package com.example.practicingJwt.application.controller;

import com.example.practicingJwt.application.repository.LoteamentoRepository;
import com.example.practicingJwt.model.Loteamento;
import com.example.practicingJwt.model.LoteamentoRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/loteamento")
public class LoteamentoController {

    private final LoteamentoRepository loteamentoRepository;

    @GetMapping
    public List<Loteamento> getAll(){
        return this.loteamentoRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Loteamento create(@RequestBody LoteamentoRequest loteamentoRequest){
        var newLoteamento = new Loteamento();
        BeanUtils.copyProperties(loteamentoRequest, newLoteamento);
        return this.loteamentoRepository.save(newLoteamento);
    }

}
