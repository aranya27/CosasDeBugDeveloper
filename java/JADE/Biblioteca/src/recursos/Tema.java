/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import jade.content.Concept;

/**
 *
 * @author armando
 */
public class Tema  implements Concept{
    private String nombretema;
    private int porcentaje;

    public Tema(){
        
    }
    public Tema(String nombretema, int porcentaje) {
        this.nombretema = nombretema;
        this.porcentaje = porcentaje;
    }

    
    
    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getNombretema() {
        return nombretema;
    }

    public void setNombretema(String nombretema) {
        this.nombretema = nombretema;
    }

    @Override
    public String toString() {
        return getNombretema()+"-"+getPorcentaje();
    }
    
    
}
