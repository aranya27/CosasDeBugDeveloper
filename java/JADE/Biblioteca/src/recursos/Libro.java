package recursos;

import java.io.Serializable;
import java.util.ArrayList;

public class Libro implements Serializable{
    private int id;
    private String titulo,autor;
    private ArrayList<Tema> temas;
    private boolean estaPrestado;
    
    public Libro(){
        
    }

    public Libro(int id, String titulo, String autor, ArrayList<Tema> temas) {
        this.id=id;
        this.titulo = titulo;
        this.autor = autor;
        this.temas = temas;
        this.estaPrestado = false;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isEstaPrestado() {
        return estaPrestado;
    }

    public void setEstaPrestado(boolean estaPrestado) {
        this.estaPrestado = estaPrestado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    public void setTemas(ArrayList<Tema> temas) {
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
        return getId()+"-"+getTitulo();
    }
    
    
    
}
