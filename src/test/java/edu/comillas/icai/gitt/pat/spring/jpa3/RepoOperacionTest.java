package edu.comillas.icai.gitt.pat.spring.jpa3;

import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Contador;
import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Operacion;
import edu.comillas.icai.gitt.pat.spring.jpa3.entity.TipoOperacion;
import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Usuario;
import edu.comillas.icai.gitt.pat.spring.jpa3.repositorio.RepoOperacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class RepoOperacionTest {
    @Autowired
    RepoOperacion repoOperacion;

    @Test
    void jpaTest() {
        // Given ...
        Usuario usuario = new Usuario();
        usuario.id = 1L;
        Contador contador = new Contador();
        contador.id = 200L;
        Operacion operacion = new Operacion();
        operacion.tipo = TipoOperacion.CREACION;
        operacion.fecha = Timestamp.from(Instant.now());
        operacion.setContador(contador);
        operacion.usuario = usuario;
        // When ...
        DataIntegrityViolationException error = null;
        try {
            repoOperacion.save(operacion);
        } catch (DataIntegrityViolationException e) { error = e; }
        // Then ...
        assertNotNull(error);
    }
}
