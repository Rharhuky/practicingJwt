package com.example.practicingJwt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "terreno")
public class Terreno extends BaseEntity<Long>{

    private String localizacao;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "loteamento_id", referencedColumnName = "id")
    private Loteamento loteamento;

}
