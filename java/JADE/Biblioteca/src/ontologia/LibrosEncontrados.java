/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologia;

import jade.content.Concept;
import jade.util.leap.ArrayList;

/**
 *
 * @author armando
 */
public class LibrosEncontrados implements Concept {
    /*private String libros;

    public String getLibros() {
        return libros;
    }

    public void setLibros(String libros) {
        this.libros = libros;
    }
    */
    
    private ArrayList libros;

    public ArrayList getLibros() {
        return libros;
    }

    public void setLibros(ArrayList libros) {
        this.libros = libros;
    }
    
    
    
}
