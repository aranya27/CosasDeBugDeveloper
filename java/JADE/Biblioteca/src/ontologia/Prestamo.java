/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologia;

import jade.content.Concept;
import recursos.Libro;

/**
 *
 * @author armando
 */
public class Prestamo  implements Concept {
    Libro libro;
    int tiempo;

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    
}
