package edu.comillas.icai.gitt.pat.spring.jpa3.servicio;

import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Contador;
import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Operacion;
import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Usuario;
import edu.comillas.icai.gitt.pat.spring.jpa3.repositorio.RepoContador;
import edu.comillas.icai.gitt.pat.spring.jpa3.repositorio.RepoOperacion;
import edu.comillas.icai.gitt.pat.spring.jpa3.repositorio.RepoUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class ServicioContadores {
    @Autowired
    RepoUsuario repoUsuario;
    @Autowired
    RepoContador repoContador;
    @Autowired
    RepoOperacion repoOperacion;
    private Logger logger = LoggerFactory.getLogger(getClass());
    public Usuario autentica(String credenciales) {
        logger.info("ServicioContadores: Intentando crear el usuario: "+credenciales);
        Usuario usuario = repoUsuario.findByCredenciales(credenciales);

        if (usuario == null) {
            logger.info("ServicioContadores: Usuario no encontrado");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }
        return usuario;
    }

    @Transactional
    public Contador crea(Contador contadorNuevo, Usuario usuario) {
        if (repoContador.findByNombre(contadorNuevo.nombre)!=null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Contador no encontrado");
        }
        // Guardar el contador nuevo
        Contador contadorGuardado = repoContador.save(contadorNuevo);

        // Registrar la operación en BD
        Operacion operacion = new Operacion();
        operacion.setContador(contadorGuardado);
        operacion.setUsuario(usuario);
        operacion.setTipo("CREACION");
        operacion.setFecha(new Date());
        repoOperacion.save(operacion);

        return contadorGuardado;
    }


    public Contador lee(String nombre, Usuario usuario) {
        // Buscar el contador por nombre
        Contador contador = repoContador.findByNombre(nombre);

        // Verificar si el contador existe
        if (contador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contador no encontrado");
        }

        // Registrar la operación en BD
        Operacion operacion = new Operacion();
        operacion.setContador(contador);
        operacion.setUsuario(usuario);
        operacion.setTipo("LECTURA");
        operacion.setFecha(new Date());
        repoOperacion.save(operacion);

        return contador;
    }


    public List<Operacion> leeOperaciones(String nombre) {
        List<Operacion> operaciones=null;
        // Buscar el contador por nombre
        Contador contador = repoContador.findByNombre(nombre);

        if(contador!=null) {
            //Una vez que tengo el contador voy a buscar las operaciones que tiene asociadas
            operaciones = repoOperacion.findByContador(contador);
        }else {
            operaciones=null;
        }

        return operaciones;
    }


    @Transactional
    public Contador incrementa(Contador contador, Long incremento, Usuario usuario) {
        // Incrementar el valor del contador
        contador.setValor(contador.getValor() + incremento);
        repoContador.save(contador);

        // Registrar la operación en BD
        Operacion operacion = new Operacion();
        operacion.setContador(contador);
        operacion.setUsuario(usuario);
        operacion.setTipo("INCREMENTO");
        operacion.setFecha(new Date());
        repoOperacion.save(operacion);

        return contador;
    }
    @Transactional(rollbackFor = Exception.class)
    public void borra(Contador contador, Usuario usuario) throws Exception {
        try {
            // Borra el contador
            repoContador.delete(contador);

            // Registrar la operación en BD
            Operacion operacion = new Operacion();
            operacion.setContador(contador);
            operacion.setUsuario(usuario);
            operacion.setTipo("BORRADO");
            operacion.setFecha(new Date());
            repoOperacion.save(operacion);
        } catch (Exception e) {
            // Si ocurre un error, lanzar una excepción
            throw new Exception("Error al realizar la operación de borrado y registrar la operación.", e);
        }
    }
}
