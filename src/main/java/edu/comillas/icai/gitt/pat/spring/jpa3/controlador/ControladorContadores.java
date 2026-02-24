package edu.comillas.icai.gitt.pat.spring.jpa3.controlador;

import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Contador;
import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Usuario;

import edu.comillas.icai.gitt.pat.spring.jpa3.servicio.ServicioContadores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class ControladorContadores {

    @Autowired
    ServicioContadores servicioContadores;
    @PostMapping("/api/contadores") @ResponseStatus(HttpStatus.CREATED)
    public Contador crea(@RequestBody Contador contadorNuevo, @RequestHeader("Authorization") String credenciales) {
        Usuario usuario = servicioContadores.autentica(credenciales);
        return servicioContadores.crea(contadorNuevo, usuario);
    }

    @GetMapping("/api/contadores/{nombre}")
    public Contador lee(@PathVariable String nombre, @RequestHeader("Authorization") String credenciales) {
        Usuario usuario = servicioContadores.autentica(credenciales);
        return servicioContadores.lee(nombre, usuario);
    }

    @PutMapping("/api/contadores/{nombre}/incremento/{incremento}")
    public Contador incrementa(@PathVariable String nombre, @PathVariable Long incremento, @RequestHeader("Authorization") String credenciales) {
        Usuario usuario = servicioContadores.autentica(credenciales);
        Contador cont=servicioContadores.lee(nombre,usuario);
        return servicioContadores.incrementa(cont,incremento,usuario);

    }

    @DeleteMapping("/api/contadores/{nombre}")
    public void borra(@PathVariable String nombre, @RequestHeader("Authorization") String credenciales) {
        Usuario usuario = servicioContadores.autentica(credenciales);
        Contador cont=servicioContadores.lee(nombre,usuario);
        try {
            servicioContadores.borra(cont,usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}