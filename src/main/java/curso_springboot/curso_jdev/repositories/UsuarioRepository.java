package curso_springboot.curso_jdev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import curso_springboot.curso_jdev.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    @Query("SELECT obj FROM Usuario obj WHERE UPPER(obj.nome) LIKE UPPER(CONCAT('%', :nome, '%'))")
    List<Usuario> searchByName(String nome);
}
