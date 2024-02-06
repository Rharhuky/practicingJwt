package com.example.practicingJwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoteamentoRequest {

    private String loteamento;
    private String endereco;

}
