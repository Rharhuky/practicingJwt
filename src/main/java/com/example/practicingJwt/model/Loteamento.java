package com.example.practicingJwt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "loteamento")
@AllArgsConstructor
@NoArgsConstructor
public class Loteamento extends BaseEntity<Long>{

    private String nome;

//    @Cep custom annotation TODO
    private String endereco;

    @JsonManagedReference
    @OneToMany(mappedBy = "loteamento", cascade = CascadeType.PERSIST)
    private List<Terreno> terrenos;

}
