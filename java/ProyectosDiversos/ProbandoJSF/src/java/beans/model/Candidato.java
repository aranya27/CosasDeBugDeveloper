package beans.model;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;


@Named(value = "candidato")
//@ManagedBean
@RequestScoped
public class Candidato {
    
    private String nombre = "Escribe tu nombre!!";
    
    public Candidato() {
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}
