package curso_springboot.curso_jdev.dto;

import curso_springboot.curso_jdev.model.Usuario;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private Integer idade;

    public UsuarioDTO(Usuario entity){
        id = entity.getId();
        nome = entity.getNome();
        idade = entity.getIdade();
    }

    public UsuarioDTO(Long id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Integer getIdade() {
        return idade;
    }
    
}
