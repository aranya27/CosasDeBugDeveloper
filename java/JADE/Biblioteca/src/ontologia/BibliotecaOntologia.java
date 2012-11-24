package ontologia;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;
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
            
            
            //Agregamos conceptos, acciones y predicados
            add(new ConceptSchema(LIBRO),Libro.class);
            add(new ConceptSchema(TEMA),Tema.class);
            add(new ConceptSchema(LIBROS_ENCONTRADOS),LibrosEncontrados.class);
            add(new AgentActionSchema(CONSULTAR_LIBROS),ConsultarLibros.class);
            
            
            //Definido todo el cotorreo de los conceptos, acciones y predicados
            
            
            cs = (ConceptSchema) getSchema(LIBRO);
            cs.add(LIBRO_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            cs.add(LIBRO_TITULO, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            cs.add(LIBRO_TEMAS, (ConceptSchema) getSchema(TEMA), 1, ObjectSchema.UNLIMITED);
            
            
            cs = (ConceptSchema) getSchema(TEMA);
            cs.add(TEMA_NOMBRE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            cs.add(TEMA_PORCENTAJE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
            
            
            
            cs = (ConceptSchema) getSchema(LIBROS_ENCONTRADOS);
            add(cs,LibrosEncontrados.class);
            cs.add(LIBROS, (ConceptSchema) getSchema(LIBRO), 1, ObjectSchema.UNLIMITED);
            
            
            
            // ------- Add AgentActions
            as = (AgentActionSchema) getSchema(CONSULTAR_LIBROS);
            as.add(LIBRO_TITULO, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            as.add(LIBRO_TEMA, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            as.add(LIBRO_AUTOR, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            
        }catch (OntologyException oe) {
         oe.printStackTrace();
      }
      
    }
}
