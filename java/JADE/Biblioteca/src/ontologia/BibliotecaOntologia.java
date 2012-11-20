package ontologia;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;
import java.util.ArrayList;
import recursos.Libro;


public class BibliotecaOntologia  extends Ontology implements BibliotecaVocabulario {
    
    public static final String ONTOLOGY_NAME = "Bank-Ontology";
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
            
            // ------- Add Concepts
            //add(new ConceptSchema("Libro"),Libro.class);
            
            
            ConceptSchema cs;
              
            
            cs = new ConceptSchema(LIBROS_ENCONTRADOS);
            add(cs,LibrosEncontrados.class);
            cs.add(LIBROS, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            
            
            // ------- Add AgentActions
            // Consultar libros
            AgentActionSchema as;
            add(as = new AgentActionSchema(CONSULTAR_LIBROS), ConsultarLibros.class);
            as.add(TITULO, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            as.add(TEMA, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            as.add(AUTOR, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            
        }catch (OntologyException oe) {
         oe.printStackTrace();
      }
      
    }
}
