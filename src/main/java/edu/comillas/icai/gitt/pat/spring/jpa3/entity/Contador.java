package edu.comillas.icai.gitt.pat.spring.jpa3.entity;

import jakarta.persistence.*;

@Entity
public class Contador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;

    @Column(nullable = false, unique = true) public String nombre;

    @Column(nullable = false) public Long valor;

    public Long getValor() {
        return this.valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
}
