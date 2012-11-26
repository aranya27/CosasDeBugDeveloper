package recursos;

import jade.content.Concept;
import jade.util.leap.ArrayList;
import java.io.Serializable;


public class Libro  implements Concept{
    private int id;
    private String titulo,autor;
    private ArrayList temas;
    private boolean isPrestado;
    
    public Libro(){
        
    }

    public Libro(int id, String titulo, String autor, ArrayList temas) {
        this.id=id;
        this.titulo = titulo;
        this.autor = autor;
        this.temas = temas;
        this.isPrestado = false;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isPrestado() {
        return isPrestado;
    }

    public void setPrestado(boolean isPrestado) {
        this.isPrestado = isPrestado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList getTemas() {
        return temas;
    }

    public void setTemas(ArrayList temas) {
        this.temas = temas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return getId()+"-"+getTitulo()+"-"+getTemas();
    }
    
    
    
}
