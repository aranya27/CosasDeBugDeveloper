package beans.model;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


//@Named(value = "candidato")
@ManagedBean
@RequestScoped
//@SessionScoped
public class Candidato {
    private String nombre = "Escribe tu nombre";
    private String apellido = "Introduce apellido";
    private String sueldoDeseado = "Introduce el sueldo deseadp";
    
    public Candidato() {
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSueldoDeseado() {
        return sueldoDeseado;
    }

    public void setSueldoDeseado(String sueldoDeseado) {
        this.sueldoDeseado = sueldoDeseado;
    }

    
    
}
