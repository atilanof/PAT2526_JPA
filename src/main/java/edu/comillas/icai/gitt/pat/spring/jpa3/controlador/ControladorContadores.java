package edu.comillas.icai.gitt.pat.spring.jpa3.controlador;

import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Contador;
import edu.comillas.icai.gitt.pat.spring.jpa3.repositorio.RepoContador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorContadores {
    @Autowired
    RepoContador repoContador;

    @PostMapping("/api/contadores") @ResponseStatus(HttpStatus.CREATED)
    public Contador crea(@RequestBody Contador contadorNuevo) {
        Contador contadorGuardado = repoContador.save(contadorNuevo);
        return contadorGuardado;
    }

    @GetMapping("/api/contadores/{nombre}")
    public Contador lee(@PathVariable String nombre) {
        Contador contador = repoContador.findByNombre(nombre);
        return contador;
    }

    @PutMapping("/api/contadores/{nombre}/incremento/{incremento}")
    public Contador incrementa(@PathVariable String nombre, @PathVariable Long incremento) {
        Contador contador = repoContador.findByNombre(nombre);
        contador.setValor(contador.getValor() + incremento);
        repoContador.save(contador);
        return contador;
    }

    @DeleteMapping("/api/contadores/{nombre}")
    public void borra(@PathVariable String nombre) { }
}