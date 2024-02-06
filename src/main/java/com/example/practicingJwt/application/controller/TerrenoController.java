package com.example.practicingJwt.application.controller;

import com.example.practicingJwt.application.repository.LoteamentoRepository;
import com.example.practicingJwt.application.repository.TerrenoRepository;
import com.example.practicingJwt.model.Terreno;
import com.example.practicingJwt.model.TerrenoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/terreno")
public class TerrenoController {

    private final TerrenoRepository terrenoRepository;
    private final LoteamentoRepository loteamentoRepository;

    @GetMapping
    public List<Terreno> getAll(){
        return this.terrenoRepository.findAll();
    }

    @PostMapping("/terreno/{id}")
    public Terreno create(@PathVariable Long id , @RequestBody TerrenoRequest terrenoRequest){
        var theLoteamento = this.loteamentoRepository.findById(id).orElseThrow(RuntimeException::new);
        var newTerreno = new Terreno();
        newTerreno.setLoteamento(theLoteamento);
        BeanUtils.copyProperties(terrenoRequest, newTerreno);

        return this.terrenoRepository.save(newTerreno);
    }

}
