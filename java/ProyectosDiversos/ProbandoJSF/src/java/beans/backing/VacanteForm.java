package beans.backing;

import beans.helper.ColoniaHelper;
import beans.helper.FacesContextHelper;
import beans.model.Candidato;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;


//@Named(value = "vacanteForm")
@ManagedBean
@RequestScoped
//@ViewScoped
//@SessionScoped
public class VacanteForm {
    
    @ManagedProperty(value="#{candidato}")
    private Candidato candidato;
    
    @ManagedProperty(value="#{coloniaHelper}")
    private ColoniaHelper coloniaHelper;
    
    private String unCampoMas;
    
    public VacanteForm() {
        
    }

    public String getUnCampoMas() {
        return unCampoMas;
    }

    public void setUnCampoMas(String unCampoMas) {
        this.unCampoMas = unCampoMas;
    }
    
    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public ColoniaHelper getColoniaHelper() {
        return coloniaHelper;
    }

    public void setColoniaHelper(ColoniaHelper coloniaHelper) {
        this.coloniaHelper = coloniaHelper;
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

    
    public void codigoPostalListener(ValueChangeEvent valueChangeEvent){
        /*FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        String newCodigoPostal = (String) valueChangeEvent.getNewValue();
        
        if("0318".equals(newCodigoPostal)){
            UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
            String ciudad = "DF";
            ciudadInputText.setValue(ciudad);
            ciudadInputText.setSubmittedValue(ciudad);
            
            UIInput coloniaInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:coloniaId");
            Long coloniaId = 1L;
            coloniaInputText.setValue(coloniaId);
            coloniaInputText.setSubmittedValue(Long.toString(coloniaId));
            
            
        }
        facesContext.renderResponse();
                */
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        String newCodigoPostal = (String) valueChangeEvent.getNewValue();
        
        
        UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
        String ciudad = "DF";
        ciudadInputText.setValue(ciudad);
        ciudadInputText.setSubmittedValue(ciudad);

        UIInput coloniaInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:coloniaId");
        Long coloniaId = this.coloniaHelper.getColoniaPorCP(Long.parseLong(newCodigoPostal ));
        
        coloniaInputText.setValue(coloniaId);
        coloniaInputText.setSubmittedValue(Long.toString(coloniaId));
            
        facesContext.renderResponse();    
        
    }
    
    public void ocultarComentario(ActionEvent actionEvent){
        this.candidato.setComentarioEnviado(false);
        System.out.println("==============comentarioEnviado = "+this.candidato.isComentarioEnviado());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContextHelper.limpiarImmediateFacesMessages(facesContext);
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        req.setAttribute("algo", "x");
    }
    
    public void mostrarComentario(ActionEvent actionEvent){
        this.candidato.setComentarioEnviado(true);
        System.out.println("==============comentarioEnviado = "+this.candidato.isComentarioEnviado());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContextHelper.limpiarImmediateFacesMessages(facesContext);
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        req.setAttribute("algo", "x");
    }
}
