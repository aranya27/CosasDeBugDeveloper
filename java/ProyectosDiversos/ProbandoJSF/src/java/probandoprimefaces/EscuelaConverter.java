package probandoprimefaces;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("escuelaConverter")
public class EscuelaConverter  implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(value != null && !value.trim().equals("")){
            int id;
            try{
                id = Integer.parseInt(value);
                CarService service = (CarService) context.getExternalContext().getApplicationMap().get("carService");
                return service.getEscuelaById(id);
            }catch(Exception e){
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "El id no es un numero guey!"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null) return null;
        return ((Escuela)value).getId()+"";
    }
    
}
