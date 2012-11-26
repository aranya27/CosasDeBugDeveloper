package ontologia;

public interface BibliotecaVocabulario {
    // Vocabulario basico
    public static final int CONSULTA_LIBROS = 1;
    public static final int PEDIR_PRESTADO_LIBRO = 2;
    public static final int REGRESAR_LIBRO = 3;
    public static final int DEVOLUCION_EXITOSA = 4;
    public static final int LIBRO_NO_EXISTE = 5;
    public static final int LIBRO_YA_PRESTADO = 6;
    public static final int TIEMPO_PRESTAMO = 10;
    public static final String SERVER_AGENT = "Server agent";
    
    //-------> Ontology vocabulary
    public static final String LIBRO = "libro";
    public static final String LIBRO_ID = "id";
    public static final String LIBRO_TITULO = "titulo";
    public static final String LIBRO_TEMAS = "temas";
    public static final String LIBRO_AUTOR = "autor";
    
    public static final String TEMA = "tema";
    public static final String TEMA_NOMBRE = "nombretema";
    public static final String TEMA_PORCENTAJE = "porcentaje";
    
    
    public static final String LIBROS_ENCONTRADOS = "LibrosEncontrados";
    public static final String LIBRO_TEMA = "tema";
    public static final String LIBROS = "libros";
    
    public static final String PRESTAMO = "prestamo";
    public static final String TIEMPO = "tiempo";
    
    public static final String PROBLEMA = "problema";
    public static final String PROBLEMA_NUM = "num";
    public static final String PROBLEMA_MSG="msg";
    
    
    
    public static final String CONSULTAR_LIBROS = "ConsultarLibros";
    public static final String PEDIR_PRESTADO = "PedirPrestado";
    public static final String DEVOLVER = "Devolver";
    public static final String INFORMAR_DEVOLUCION = "InformarDevolucion";
    public static final String STATUS_DEVOLUCION = "status";
    
}
