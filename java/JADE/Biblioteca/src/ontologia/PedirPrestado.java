/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologia;

import jade.content.AgentAction;
import recursos.Libro;

/**
 *
 * @author armando
 */
public class PedirPrestado  implements AgentAction{
    Libro libro;

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    
    
    
}
