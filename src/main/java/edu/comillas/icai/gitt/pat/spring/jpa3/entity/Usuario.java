package edu.comillas.icai.gitt.pat.spring.jpa3.entity;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id //El de la peristencia
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;

    @Column(nullable = false, unique = true) public String email;

    @Column(nullable = false) public String credenciales;
}