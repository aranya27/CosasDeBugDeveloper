package ontologia;

import jade.content.AgentAction;
import recursos.Libro;

public class Devolver implements AgentAction{
    Libro libro;

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    
}
