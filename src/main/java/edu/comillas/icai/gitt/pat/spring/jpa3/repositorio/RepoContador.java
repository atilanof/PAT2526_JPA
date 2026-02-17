package edu.comillas.icai.gitt.pat.spring.jpa3.repositorio;

import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Contador;
import org.springframework.data.repository.CrudRepository;

public interface RepoContador extends CrudRepository<Contador, Long> {
    Contador findByNombre(String nombre);
}