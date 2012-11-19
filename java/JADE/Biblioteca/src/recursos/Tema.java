/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.io.Serializable;

/**
 *
 * @author armando
 */
public class Tema  implements Serializable{
    private String tema;
    private int porcentaje;

    public Tema(String tema, int porcentaje) {
        this.tema = tema;
        this.porcentaje = porcentaje;
    }

    
    
    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    
}
