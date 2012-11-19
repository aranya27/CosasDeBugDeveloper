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
    private BibliotecaOntologia(){
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        
        try{
            
            // ------- Add Concepts
            //add(new ConceptSchema("Libro"),Libro.class);
            
            
            ConceptSchema cs = new ConceptSchema("ArrayList");
            add(cs,ArrayList.class);
            
            
            cs = new ConceptSchema("Libro");
            add(cs, Libro.class);
            cs.add("id", (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            cs.add("titulo", (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            
            
            
            
            cs = new ConceptSchema(LIBROS_ENCONTRADOS);
            add(cs,LibrosEncontrados.class);
            cs.add("LIBROS", (ConceptSchema) getSchema("ArrayList"), ObjectSchema.MANDATORY);
            
            
            // ------- Add AgentActions
            // Consultar libros
            AgentActionSchema as;
            add(as = new AgentActionSchema(CONSULTAR_LIBROS), ConsultarLibros.class);
            as.add(TITULO, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            as.add(TEMA, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            as.add(AUTOR, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            
        }catch (OntologyException oe) {
         oe.printStackTrace();
      }
      
    }
}
