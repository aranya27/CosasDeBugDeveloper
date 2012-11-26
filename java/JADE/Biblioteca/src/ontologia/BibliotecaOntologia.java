package ontologia;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.*;
import recursos.Libro;
import recursos.Tema;


public class BibliotecaOntologia  extends Ontology implements BibliotecaVocabulario {
    
    public static final String ONTOLOGY_NAME = "Biblioteca-Ontologia";
    private static BibliotecaOntologia instance = new BibliotecaOntologia();
    
    public static BibliotecaOntologia getInstance(){
        return instance;
    }
    /*
        * conceptos (libro, !pedido, revista, cds, alumno) 
        * acciones (pedir prestado un libro, regresarlo, apartarlo)
        * predicado (debe libro)//Son meramente preguntas que se hacen entre agentes
     */
    private BibliotecaOntologia(){
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        
        try{
            ConceptSchema cs;
            AgentActionSchema as;
            PredicateSchema ps;
            
            add(new ConceptSchema(LIBRO),Libro.class);
            add(new ConceptSchema(TEMA),Tema.class);
            add(new ConceptSchema(LIBROS_ENCONTRADOS),LibrosEncontrados.class);
            add(new ConceptSchema(PRESTAMO),Prestamo.class);
            add(new ConceptSchema(PROBLEMA),Problema.class);
            add(new AgentActionSchema(CONSULTAR_LIBROS),ConsultarLibros.class);
            add(new AgentActionSchema(PEDIR_PRESTADO),PedirPrestado.class);
            add(new AgentActionSchema(DEVOLVER),Devolver.class);
            add(new PredicateSchema(INFORMAR_DEVOLUCION),InformarDevolucion.class);
            
            
            cs = (ConceptSchema) getSchema(LIBRO);
            cs.add(LIBRO_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            cs.add(LIBRO_TITULO, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            cs.add(LIBRO_TEMAS, (ConceptSchema) getSchema(TEMA), 1, ObjectSchema.UNLIMITED);
            
            cs = (ConceptSchema) getSchema(TEMA);
            cs.add(TEMA_NOMBRE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            cs.add(TEMA_PORCENTAJE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            
            cs = (ConceptSchema) getSchema(LIBROS_ENCONTRADOS);
            cs.add(LIBROS, (ConceptSchema) getSchema(LIBRO), 0, ObjectSchema.UNLIMITED);
            
            cs = (ConceptSchema) getSchema(PRESTAMO);
            cs.add(LIBRO,  (ConceptSchema) getSchema(LIBRO),ObjectSchema.MANDATORY);
            cs.add(TIEMPO, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            
            cs = (ConceptSchema) getSchema(PROBLEMA);
            cs.add(PROBLEMA_NUM,  (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            cs.add(PROBLEMA_MSG, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            
            
            
            
            as = (AgentActionSchema) getSchema(CONSULTAR_LIBROS);
            as.add(LIBRO_TITULO, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            as.add(LIBRO_TEMA, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            as.add(LIBRO_AUTOR, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            
            as = (AgentActionSchema) getSchema(PEDIR_PRESTADO);
            as.add(LIBRO,  (ConceptSchema) getSchema(LIBRO),ObjectSchema.MANDATORY);
            
            as = (AgentActionSchema) getSchema(DEVOLVER);
            as.add(LIBRO,  (ConceptSchema) getSchema(LIBRO),ObjectSchema.MANDATORY);
            
            ps = (PredicateSchema) getSchema(INFORMAR_DEVOLUCION);
            ps.add(STATUS_DEVOLUCION,  (PrimitiveSchema) getSchema(BasicOntology.INTEGER),ObjectSchema.MANDATORY);
            
        }catch (OntologyException oe) {
         oe.printStackTrace();
      }
      
    }
}
