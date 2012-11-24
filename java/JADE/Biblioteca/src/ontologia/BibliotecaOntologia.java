package ontologia;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;
import jade.util.leap.ArrayList;
import recursos.Libro;
import recursos.Tema;


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
            
            
            ConceptSchema cs;
              
            
            cs = new ConceptSchema(LIBROS_ENCONTRADOS);
            add(cs,LibrosEncontrados.class);
            //cs.add(LIBROS, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            //cs.add(LIBROS, (ConceptSchema) getSchema("ArrayList"));
            cs.add(LIBROS, (ConceptSchema) getSchema("Libro"), 1, ObjectSchema.UNLIMITED);
            
            cs = new ConceptSchema("Libro");
            add(cs,Libro.class);
            cs.add("id", (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            cs.add("titulo", (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            cs.add("temas", (ConceptSchema) getSchema("Tema"), 1, ObjectSchema.UNLIMITED);
            
            cs = new ConceptSchema("Tema");
            add(cs,Tema.class);
            cs.add("nombretema", (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            cs.add("porcentaje", (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            
            
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
