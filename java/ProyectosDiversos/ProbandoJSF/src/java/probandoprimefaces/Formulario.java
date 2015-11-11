package probandoprimefaces;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean
public class Formulario  implements Serializable {
    private String nombre;
    private Date fecha;
    private boolean esCasado;
    private String conyugue;
    private Escuela escuela1;
    private Escuela escuela2;
    private List<Persona> personas;
    
    @ManagedProperty("#{carService}")
    private CarService service;
    
    private List<Escuela> escuelas;

    @PostConstruct
    public void init() {
        escuelas = service.getListadoEscuelas();
    }
    
    
    public void procesaForm(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        
        if(nombre != null && nombre.equals("admin") ) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre invalido", "Nombre invalido");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        else if(escuela1 == null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escuela fail", "No se ha seleccionado una escuela");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        else{
            HttpServletResponse response =  (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            try {
                response.sendRedirect("exito.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void seDioAceptarEnDialogDeEscuela(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
         
        //if()
        
    }
    
    public void actualizaEsCasado(javax.faces.event.AjaxBehaviorEvent event) {
        esCasado = (boolean)((SelectBooleanCheckbox)event.getComponent()).getValue();
        
        System.out.println("esCasado = "+esCasado);
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Escuela getEscuela1() {
        return escuela1;
    }

    public void setEscuela1(Escuela escuela) {
        this.escuela1 = escuela;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Escuela> getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(List<Escuela> escuelas) {
        this.escuelas = escuelas;
    }

    public CarService getService() {
        return service;
    }

    public void setService(CarService service) {
        this.service = service;
    }

    public Escuela getEscuela2() {
        return escuela2;
    }

    public void setEscuela2(Escuela escuela2) {
        this.escuela2 = escuela2;
    }

    public boolean isEsCasado() {
        return esCasado;
    }

    public void setEsCasado(boolean esCasado) {
        this.esCasado = esCasado;
    }

    public String getConyugue() {
        return conyugue;
    }

    public void setConyugue(String conyugue) {
        this.conyugue = conyugue;
    }
    
    
    
    
}
