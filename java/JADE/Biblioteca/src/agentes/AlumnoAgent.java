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
import jade.util.leap.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import ontologia.*;
import recursos.Libro;


public class AlumnoAgent extends Agent implements BibliotecaVocabulario{
    private Codec codec = new SLCodec();
    private Ontology ontology = BibliotecaOntologia.getInstance();
    private AID server;
    ArrayList libros;
    Libro libro=null;
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
                       "\n\t1. Consultar el catálogo de libros" +
                       "\n\t2. Pedir prestado un libro" +
                       "\n\t3. Regresar el libro que tengo prestado\n ");
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
                    mensajito(getLocalName() + " Se esta apagando...Bye!");
                    doDelete();
                    System.exit(0);
                    break;
                case CONSULTA_LIBROS:
                    consultarLibros();
                    break;
                case PEDIR_PRESTADO_LIBRO:
                    pedirPrestadoLibro();
                    break;
                case REGRESAR_LIBRO:
                    regresarLibroPrestado();
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
                    mensajito("Np hay respuesta del servidor. Ya pasó mucho tiempo");
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
                mensajito("Respuesta del servidor: NO SE ENTENDIÓ!");
            }
            else if (msg.getPerformative() == ACLMessage.FAILURE){
                try{
                    ContentElement content = getContentManager().extractContent(msg);
                    if (content instanceof Result) {
                         Result result = (Result) content;
                         if (result.getValue() instanceof Problema) {
                             Problema p = (Problema)result.getValue();
                             mensajito("Error devuelto por el bibliotecario: "+p.getMsg());
                             addBehaviour(new EsperarOrdenUsuario(myAgent));
                         }
                         else {
                            mensajito("No se puede interpretar la respuesta del servidor1.");
                        }
                    }else{
                        mensajito("No se puede interpretar la respuesta del servidor..");
                    }
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                try{
                    ContentElement content = getContentManager().extractContent(msg);
                    
                    if (content instanceof Result) {
                        Result result = (Result) content;
                        if (result.getValue() instanceof LibrosEncontrados) {
                            LibrosEncontrados le = (LibrosEncontrados)result.getValue();
                            if(le.getLibros()!=null){
                                libros = le.getLibros();
                                mensajito("Se encontraron los siguientes libros:\n "+libros);
                            }else{
                                mensajito("No se encontraron libros con los criterios seleccionados");
                            }
                        }else if (result.getValue() instanceof Prestamo) {
                            Prestamo le = (Prestamo)result.getValue();
                            libro = le.getLibro();
                            mensajito("Se nos prestó el libro "+libro.getTitulo());
                        }else {
                            mensajito("No se puede interpretar la respuesta del servidor");
                        }
                    }else if (content instanceof InformarDevolucion) {
                        InformarDevolucion id = (InformarDevolucion)content;
                        if(id.getStatus()==DEVOLUCION_EXITOSA){
                            libro=null;
                            mensajito("Devolucion exitosa del libro");
                        }
                        else{
                            mensajito("Hubo un error al devolver libro");
                        }
                    }
                    else {
                        mensajito("No se puede interpretar la respuesta del servidor....");
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
            addBehaviour(new EsperarOrdenUsuario(myAgent));
            return 0;
      }
        
    }
    
    
    
    String getUserInput(String msg) {
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
    
    void regresarLibroPrestado(){
        if(libro==null){
            mensajito("No hay libros para regresar");
            addBehaviour(new EsperarOrdenUsuario(this));
        }
        else{
            devolver(libro);
        }
    }
    
    void pedirPrestadoLibro(){
        if(libro!=null){
            mensajito("Ya tienes un libro prestado, primero debes devolverlo antes de pedir otro prestado");
            addBehaviour(new EsperarOrdenUsuario(this));
        }
        else if(libros==null || libros.size()==0){
            System.out.println("Primero debes preguntar el catálogo de libros a la biblioteca");
            addBehaviour(new EsperarOrdenUsuario(this));
        }else{
            System.out.println("Lista de libros:\n");
            for(int i=0; i<libros.size(); i++){
                System.out.println((i+1)+". "+libros.get(i));
            }
            
            String m=getUserInput("Selecciona el libro que quieres pedir prestado: ");
            
            try{
                pedirPrestadoLibro((Libro)libros.get(Integer.parseInt(m)-1));
            }catch(Exception e){
                mensajito("Opción incorrecta");
                addBehaviour(new EsperarOrdenUsuario(this));
            }
            
        }
    }
    
    void devolver(Libro l){
        Devolver d = new Devolver();
        d.setLibro(libro);
        enviarMensaje(ACLMessage.REQUEST, d);
    }
    
    void pedirPrestadoLibro(Libro l){
        PedirPrestado pp = new PedirPrestado();
        pp.setLibro(l);
        enviarMensaje(ACLMessage.REQUEST, pp);
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
         mensajito("Unable to localize the server! Operation aborted!");
         return;
      }
      ACLMessage msg = new ACLMessage(performative);
      msg.setLanguage(codec.getName());
      msg.setOntology(ontology.getName());
      try {
         getContentManager().fillContent(msg, new Action(server, action));
         msg.addReceiver(server);
         addBehaviour(new EsperarRespuestaServidor(this));
         send(msg);
         mensajito("Contactando la biblioteca.......!");
      }
      catch (Exception ex) { ex.printStackTrace(); }
    }
    
    void lookupServer() {
      ServiceDescription sd = new ServiceDescription();
      sd.setType(SERVER_AGENT);
      DFAgentDescription dfd = new DFAgentDescription();
      dfd.addServices(sd);
      try {
         DFAgentDescription[] dfds = DFService.search(this, dfd);
         if (dfds.length > 0 ) {
            server = dfds[0].getName();
         }
         else  
             mensajito("No se pudo localizar la biblioteca");
      }
      catch (Exception ex) {
         ex.printStackTrace();
         mensajito("Failed searching int the DF!");
      }
   }
    
    
    public void mensajito(String msg){
        System.out.println("\n"+this.getLocalName()+": "+msg);
    }
    
}
