package edu.comillas.icai.gitt.pat.spring.jpa3.repositorio;

import edu.comillas.icai.gitt.pat.spring.jpa3.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RepoUsuario extends CrudRepository<Usuario, Long> {
    Usuario findByCredenciales(String credenciales);
}
