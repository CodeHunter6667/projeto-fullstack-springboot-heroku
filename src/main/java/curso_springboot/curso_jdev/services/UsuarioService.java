package curso_springboot.curso_jdev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import curso_springboot.curso_jdev.dto.UsuarioDTO;
import curso_springboot.curso_jdev.model.Usuario;
import curso_springboot.curso_jdev.repositories.UsuarioRepository;
import curso_springboot.curso_jdev.services.exceptions.UsuarioNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> getAll(){

        List<Usuario> entity = repository.findAll();

        return entity.stream().map(x -> new UsuarioDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscaPorId(Long id){
        Usuario entity = repository.findById(id).orElseThrow(
            () -> new UsuarioNotFoundException("Usuario não encontrado"));
        return new UsuarioDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> procuraPorNome(String nome){
        List<Usuario> result = repository.searchByName(nome);
        return result.stream().map(x -> new UsuarioDTO(x)).toList();
    }

    @Transactional
    public UsuarioDTO novoUsuario(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        copyDtoToEntity(dto, usuario);
        repository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO atualizaUsuario(Long id, UsuarioDTO dto){
        try {
            Usuario usuario = repository.getReferenceById(id);
            copyDtoToEntity(dto, usuario);
            repository.save(usuario);
            return new UsuarioDTO(usuario);
        } catch (EntityNotFoundException e) {
            throw new UsuarioNotFoundException("Usuario não encontrado.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletaUsuairo(Long id){
        if (!repository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuario não encontrado");
        }
        repository.deleteById(id);
    }

    private static void copyDtoToEntity(UsuarioDTO dto, Usuario entity){
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
    }
}
