package beans.helper;

import domain.dto.Colonia;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean    
@RequestScoped
public class ColoniaHelper {
    public List<Colonia> getColonias(){
        List<Colonia> colonias = new ArrayList<Colonia>();
        colonias.add(new Colonia(1, "Nápoles", 3810));
        colonias.add(new Colonia(2, "Polanco", 11530));
        colonias.add(new Colonia(3, "Del Valle Centro", 3100));
        
        return colonias;
    }
    
    public long getColoniaPorNombre(String nombreColonia){
        long coloniaId=0;
        List<Colonia> colonias = getColonias();
        for(Colonia colonia : colonias){
            if(colonia.getNombreColonia().equals(nombreColonia)){
                coloniaId = colonia.getColoniaId();
                break;
            }
        }
        return coloniaId;
    }
    
    public long getColoniaPorCP(long codigoPostal){
        long coloniaId=0;
        List<Colonia> colonias = getColonias();
        for(Colonia colonia : colonias){
            if(colonia.getCodigoPostal() == codigoPostal){
                coloniaId = colonia.getColoniaId();
                break;
            }
        }
        return coloniaId;
    }
    
    public List<SelectItem> getSelectItems(){
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<Colonia> colonias = getColonias();
        for(Colonia colonia : colonias){
            SelectItem selectItem = new SelectItem(colonia.getColoniaId(), colonia.getNombreColonia());
            selectItems.add(selectItem);
        }
        return selectItems;
    }
}
