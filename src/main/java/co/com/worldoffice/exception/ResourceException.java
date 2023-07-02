package co.com.worldoffice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus
public class ResourceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String msj;
    private String tabla;
    private String columna;
    private Object valor;

    public ResourceException(String msj, String tabla, String columna, Object valor) {
        super(String.format("%s %s con %s : '%s' ", msj, tabla, columna, valor));
        this.msj = msj;
        this.tabla = tabla;
        this.columna = columna;
        this.valor = valor;
    }

}