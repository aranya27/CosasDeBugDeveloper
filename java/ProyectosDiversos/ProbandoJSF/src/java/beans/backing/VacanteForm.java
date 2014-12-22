package beans.backing;

import beans.model.Candidato;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


//@Named(value = "vacanteForm")
@ManagedBean
@RequestScoped
public class VacanteForm {
    
    @ManagedProperty(value="#{candidato}")
    private Candidato candidato;
    
    public VacanteForm() {
    }
    
    public String enviar(){
        System.out.println("enviar() Nombre="+this.candidato.getNombre());
        System.out.println("enviar() Apellido="+this.candidato.getApellido());
        System.out.println("enviar() Sueldo deseado="+this.candidato.getSueldoDeseado());
        
        
        
        
        if(this.candidato.getNombre().equals("Juan")){
            if(this.candidato.getApellido().equals("Perez")){
                String msg = "Gracias, pero Juan Perez ya trabaja con nosotros";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String clientId = null;
                facesContext.addMessage(clientId, facesMessage);
                return "index";
            }
            
            return "exito";
        }
        else{
            return "fallo";
        }
        
        
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
    
    
}
