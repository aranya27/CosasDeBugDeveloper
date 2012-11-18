package recursos;

import java.util.ArrayList;

public class Libro {
    private String titulo,autor;
    private ArrayList<Tema> temas;
    
    public Libro(){
        
    }

    public Libro(String titulo, String autor, ArrayList<Tema> temas) {
        this.titulo = titulo;
        this.autor = autor;
        this.temas = temas;
    }
    
    
    
}
