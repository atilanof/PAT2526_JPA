package edu.comillas.icai.gitt.pat.spring.jpa3.repositorio;


import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Contador;
import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Operacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoOperacion extends CrudRepository<Operacion, Long> {
    List<Operacion> findByContador(Contador contador);
}
