package ontologia;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;


public class BibliotecaOntologia  extends Ontology implements BibliotecaVocabulario {
    
    public static final String ONTOLOGY_NAME = "Bank-Ontology";
    private static BibliotecaOntologia instance = new BibliotecaOntologia();
    
    public static BibliotecaOntologia getInstance(){
        return instance;
    }
    private BibliotecaOntologia(){
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
    }
}
