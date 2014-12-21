package beans.backing;

import beans.model.Candidato;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


//@Named(value = "vacanteForm")
@ManagedBean
@RequestScoped
public class VacanteForm {
    
    @ManagedProperty(value="#{candidato}")
    private Candidato candidato;
    
    public VacanteForm() {
    }
    
    public String enviar(){
        if(this.candidato.getNombre().equals("Juan")){
            return "exito";
        }
        else{
            return null;
        }
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
    
    
}
