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
    @ManyToOne@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false) public Usuario usuario;

    //@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne@JoinColumn(name = "contador_id", referencedColumnName = "id", nullable = false)
    public Contador contador;
    @Column(nullable = false)
    public TipoOperacion tipo;
    @Column(nullable = false)
    public Date fecha;

    public void setContador(Contador contador) {
        this.contador=contador;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario=usuario;
    }

    public void setTipo(TipoOperacion tipo) {
        this.tipo=tipo;
    }

    public void setFecha(Date fecha) {
        this.fecha=fecha;
    }
}