package curso_springboot.curso_jdev.services.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String msg){
        super(msg);
    }
}
