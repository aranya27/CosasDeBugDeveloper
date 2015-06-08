package beans.backing;

import beans.model.Persona;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


@ManagedBean
@RequestScoped
public class Ejercicio1 {
    
    @ManagedProperty(value="#{persona}")
    private Persona persona;
    
    public String enviar(){
        return "exito";
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
}
