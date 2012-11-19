/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologia;

import jade.content.Concept;
import java.util.ArrayList;
import recursos.Libro;

/**
 *
 * @author armando
 */
public class LibrosEncontrados implements Concept {
    private ArrayList<Libro> libros;

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
    
    
}
