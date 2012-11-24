package agentes;

import jade.content.AgentAction;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Result;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import ontologia.BibliotecaOntologia;
import ontologia.BibliotecaVocabulario;
import ontologia.ConsultarLibros;
import ontologia.LibrosEncontrados;
import recursos.Tema;


public class AlumnoAgent extends Agent implements BibliotecaVocabulario{
    private Codec codec = new SLCodec();
    private Ontology ontology = BibliotecaOntologia.getInstance();
    private AID server;
    static final int ESPERAR = -1;
    static final int QUITAR = 0;
    private int comando = ESPERAR;
    
    protected void setup(){
        //Registramos lenguaje y ontologia
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        
        // Set this agent main behaviour
        addBehaviour(new EsperarOrdenUsuario(this));
    }
    
    int getOrdenDeUsuario() {
        System.out.print("\n\t---- MENU DE LAS ACCIONES QUE PUEDE HACER EL ALUMNO ----" +
                       "\n\n\t0. Salir y terminar el programa" +
                       "\n\t1. Consultar libros disponibles segun el tema"/* +
                       "\n\t3. Make a withdrawal\n\t4. Get account balance" +
                       "\n\t5. Get list of operations\n> "*/);
      try {
         BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
         String in = buf.readLine();
         return Integer.parseInt(in);
      }
      catch (Exception ex) { ex.printStackTrace(); }
      return ESPERAR;
    }

    

    
    
    class EsperarOrdenUsuario extends OneShotBehaviour {
        
        
        EsperarOrdenUsuario(Agent a){
            super(a);
            comando = ESPERAR;
        }
        
        @Override
        public void action() {
            comando = getOrdenDeUsuario();
            
            switch(comando){
                case QUITAR:
                    System.out.println(getLocalName() + " Se esta apagando...Bye!");
                    doDelete();
                    System.exit(0);
                    break;
                case CONSULTA_LIBROS:
                    consultarLibros();
                    break;
            }
        }   
    }
    
    
    class EsperarRespuestaServidor extends ParallelBehaviour {
        EsperarRespuestaServidor(Agent a){
            super(a, 1);
            
            addSubBehaviour(new ProcesarRespuestaServidor(myAgent));
                    
            addSubBehaviour(new WakerBehaviour(myAgent, 5000) {
                
                protected void handleElapsedTimeout() {
                    System.out.println("\n\tNo response from server. Please, try later!");
                    addBehaviour(new EsperarOrdenUsuario(myAgent));
                }
            });
        }
    }
    
    class ProcesarRespuestaServidor extends SimpleBehaviour{
        
        private boolean finished = false;

        ProcesarRespuestaServidor(Agent a) {
            super(a);
        }
      
        
        public void action() {
            ACLMessage msg = receive(MessageTemplate.MatchSender(server));
            if (msg == null) { 
                block(); 
                return; 
            }
            if (msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD){
                System.out.println("\n\n\tRespuesta del servidor: NO SE ENTENDIÓ!");
            }
            else if (msg.getPerformative() != ACLMessage.INFORM){
                System.out.println("\nMensaje inesperado del servidor");
            }
            else{
                try{
                    ContentElement content = getContentManager().extractContent(msg);
                    
                    if (content instanceof Result) {
                        Result result = (Result) content;
                        
                        System.out.println("SE RECIBIO DEL SERVIDOR: "+result.getValue());
                        
                        
                        if (result.getValue() instanceof LibrosEncontrados) {
                            LibrosEncontrados le = (LibrosEncontrados)result.getValue();
                            if(le.getLibros()!=null){
                                
                                System.out.println("LibrosXML: "+le.getLibros());
                                
                            }else{
                                System.out.println("le.getLibros() es NULO");
                            }
                        }
                    }
                    else {
                        System.out.println("\n\tNo se puede interpretar la respuesta del servidor");
                    }
                    
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            finished = true;
            
        }

        
        public boolean done() {
            return finished;
        }
        
        
        public int onEnd() {
            System.out.println("YA TERMINO");
            addBehaviour(new EsperarOrdenUsuario(myAgent));
            return 0;
      }
        
    }
    
    
    
    String getUserInput(String msg) {
// ---------------------------------

      System.out.print(msg);
      try {
         BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
         String s = buf.readLine();
         
         if(s==null || s.trim().equals("")){
             return null;
         }
         return s;
         
      }
      catch (Exception ex) { ex.printStackTrace(); }
      return null;
   }
    
    void consultarLibros(){
        ConsultarLibros cl = getConsultaDeUsuario();
        
         enviarMensaje(ACLMessage.REQUEST, cl);
    }
    
    private ConsultarLibros getConsultaDeUsuario() {
        ConsultarLibros cl = new ConsultarLibros();
        System.out.print("Escribe los parámetros de búsqueda\n");
        try {
            cl.setAutor(getUserInput("Autor:"));
            cl.setTema(getUserInput("Tema:"));
            cl.setTitulo(getUserInput("Título:"));
        }
        catch (Exception ex) { ex.printStackTrace(); }
        return cl;
    }
    
    private void enviarMensaje(int performative,  AgentAction action) {
        
      if (server == null) lookupServer();
      if (server == null) {
         System.out.println("Unable to localize the server! \nOperation aborted!");
         return;
      }
      ACLMessage msg = new ACLMessage(performative);
      msg.setLanguage(codec.getName());
      msg.setOntology(ontology.getName());
      try {
         getContentManager().fillContent(msg, new Action(server, action));
         msg.addReceiver(server);
         send(msg);
         System.out.println("Contacting server... Please wait!");
         addBehaviour(new EsperarRespuestaServidor(this));
      }
      catch (Exception ex) { ex.printStackTrace(); }
    }
    
    void lookupServer() {
// ---------------------  Search in the DF to retrieve the server AID

      ServiceDescription sd = new ServiceDescription();
      sd.setType(SERVER_AGENT);
      DFAgentDescription dfd = new DFAgentDescription();
      dfd.addServices(sd);
      try {
         DFAgentDescription[] dfds = DFService.search(this, dfd);
         if (dfds.length > 0 ) {
            server = dfds[0].getName();
            System.out.println("Se encontrol Servidor");
         }
         else  System.out.println("\nNo se pudo localixar Servidor!");
      }
      catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("\nFailed searching int the DF!");
      }
   }
}
