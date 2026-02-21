package edu.comillas.icai.gitt.pat.spring.jpa3.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
public class Operacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public Long usuario;
    @Column(nullable = false)
    public Long contador;
    @Column(nullable = false)
    public String tipo;
    @Column(nullable = false)
    public Date fecha;

    public void setContador(Contador contador) {
        this.contador=contador.id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario=usuario.id;
    }

    public void setTipo(String tipo) {
        this.tipo=tipo;
    }

    public void setFecha(Date fecha) {
        this.fecha=fecha;
    }
}