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
import java.text.SimpleDateFormat;
import java.util.Date;
import ontologia.*;
import recursos.Libro;
import recursos.Tema;


public class AlumnoAutAgent extends Agent implements BibliotecaVocabulario{
    private Codec codec = new SLCodec();
    private Ontology ontology = BibliotecaOntologia.getInstance();
    private AID server;
    ArrayList libros;
    Libro libro=null;
    static final int ESPERAR = -1;
    static final int QUITAR = 0;
    private int comando = ESPERAR;
    private String parametro,tipoParametro;
    SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss:SS");
    
    protected void setup(){
        iniciarParametros();
        
        //Registramos lenguaje y ontologia
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        
        // Set this agent main behaviour
        addBehaviour(new AlumnoAutAgent.PedirCatalogo(this));
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

    private void iniciarParametros() {
        Object[] args = getArguments();
        
        this.tipoParametro = args[0].toString();
        this.parametro = args[1].toString();
    }
    
    
    class PedirCatalogo extends OneShotBehaviour{
        PedirCatalogo(Agent a){
            super(a);
            comando = ESPERAR;
        }

        @Override
        public void action() {
            consultarLibros();  
        }
    }
    
    
    class PedirLibro extends OneShotBehaviour{
        PedirLibro(Agent a){
            super(a);
            comando = ESPERAR;
        }

        @Override
        public void action() {
            pedirPrestadoLibro();
        }
        
    }
    
    class EsperarYPedirCatalogo extends WakerBehaviour{
        EsperarYPedirCatalogo(Agent a, long tiempo){
            super(a,tiempo);
        }
        
        protected void handleElapsedTimeout() {
            addBehaviour(new AlumnoAutAgent.PedirCatalogo(myAgent));
        }
    }
    
    
    class EsperarYDevolverLibro extends WakerBehaviour{
        EsperarYDevolverLibro(Agent a, long tiempo){
            super(a,tiempo);
        }
        
        protected void handleElapsedTimeout() {
            regresarLibroPrestado();
        }
    }
    
    
    
    class EsperarRespuestaServidor extends ParallelBehaviour {
        EsperarRespuestaServidor(Agent a){
            super(a, 1);
            
            addSubBehaviour(new AlumnoAutAgent.ProcesarRespuestaServidor(myAgent));
                    
            addSubBehaviour(new WakerBehaviour(myAgent, 5000) {
                
                protected void handleElapsedTimeout() {
                    mensajito("Np hay respuesta del servidor. Ya pasó mucho tiempo");
                    //addBehaviour(new AlumnoAutAgent.EsperarOrdenUsuario(myAgent));
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
                             
                             if(p.getNum()==LIBRO_NO_EXISTE || p.getNum()==LIBRO_YA_PRESTADO){
                                 addBehaviour(new AlumnoAutAgent.PedirLibro(myAgent));
                                 
                             }else{
                                mensajito("ERROR. Se llego a una condicion no esperada. No se puede interpretar el problema");
                             }
                         }
                         else {
                            mensajito("ERROR. Se llego a una condicion no esperada. No se puede interpretar la respuesta del servidor1.");
                            addBehaviour(new AlumnoAutAgent.PedirCatalogo(myAgent));
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
                                addBehaviour(new AlumnoAutAgent.PedirLibro(myAgent));
                            }else{
                                mensajito("No se encontraron libros con los criterios seleccionados");
                                addBehaviour(new AlumnoAutAgent.EsperarYPedirCatalogo(myAgent,TIEMPO_ESPERA));
                            }
                        }else if (result.getValue() instanceof Prestamo) {
                            Prestamo le = (Prestamo)result.getValue();
                            libro = le.getLibro();
                            mensajito("Se nos prestó el libro "+libro.getTitulo());
                            addBehaviour(new AlumnoAutAgent.EsperarYDevolverLibro(myAgent,le.getTiempo()));
                            
                            
                        }else {
                            //===Aqui tal vez tengamos que hacer algo
                            mensajito("No se puede interpretar la respuesta del servidor");
                        }
                    }else if (content instanceof InformarDevolucion) {
                        InformarDevolucion id = (InformarDevolucion)content;
                        if(id.getStatus()==DEVOLUCION_EXITOSA){
                            libro=null;
                            mensajito("Devolucion exitosa del libro");
                            addBehaviour(new AlumnoAutAgent.EsperarYPedirCatalogo(myAgent,TIEMPO_ESPERA));
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
        /*
        public int onEnd() {
            addBehaviour(new AlumnoAutAgent.EsperarOrdenUsuario(myAgent));
            return 0;
      }
        */
    }
    
    
    
    
    void regresarLibroPrestado(){
        if(libro==null){
            mensajito("No hay libros para regresar");
            addBehaviour(new AlumnoAutAgent.EsperarYPedirCatalogo(this,TIEMPO_ESPERA));
        }
        else{
            devolver(libro);
        }
    }
    
    void pedirPrestadoLibro(){
        if(libro!=null){
            mensajito("Ya tienes un libro prestado, primero debes devolverlo antes de pedir otro prestado");
            //addBehaviour(new AlumnoAutAgent.EsperarOrdenUsuario(this));
            //=============HAcer algo aqui
        }
        else if(libros==null || libros.size()==0){
            mensajito("Ya no hay libros para pedir prestado. Tenemos que esperar y luego consultar el catalogo con la biblioteca");
            addBehaviour(new AlumnoAutAgent.EsperarYPedirCatalogo(this,TIEMPO_ESPERA));
        }else{
            Libro libroAPedirPrestado = new Libro();
            
            if(this.tipoParametro.equals("tema")){
                libroAPedirPrestado = getMejorLibroSegunTema(this.parametro);
            }
            else if(this.tipoParametro.equals("titulo")){
                libroAPedirPrestado = getLibroSegunTitulo(this.parametro);
            }
            else if(this.tipoParametro.equals("autor")){
                libroAPedirPrestado = getLibroSegunAutor(this.parametro);
            }
            
            //Quitamos del catalogo interno que tenemos el libro que vamos a pedir prestado
            quitarLibro(libroAPedirPrestado);
            
            //Hacemos el pedido del prestamo
            PedirPrestado pp = new PedirPrestado();
            pp.setLibro(libroAPedirPrestado);
            enviarMensaje(ACLMessage.REQUEST, pp);
            
        }
    }
    
    Libro getLibroSegunAutor(String autor){
        Libro l=null;
        Libro auxLibro;
        
        for(int i=0; i<libros.size(); i++){
            auxLibro = (Libro)libros.get(i);
            if(auxLibro.getAutor().equals(autor)){
                l = auxLibro;
                break;
            }
        }
        
        if(l==null){
            l = (Libro)libros.get(0);
        }
        
        return l;
    }
    
    Libro getLibroSegunTitulo(String titulo){
        Libro l=null;
        Libro auxLibro;
        
        for(int i=0; i<libros.size(); i++){
            auxLibro = (Libro)libros.get(i);
            if(auxLibro.getTitulo().equals(titulo)){
                l = auxLibro;
                break;
            }
        }
        
        if(l==null){
            l = (Libro)libros.get(0);
        }
        
        return l;
    }
    
    Libro getMejorLibroSegunTema(String tema){
        Libro l=null;
        Libro auxLibro;
        int porcentajeTemaMejorActual=-1;
        int auxInt;
        
        for(int i=0; i<libros.size(); i++){
            auxLibro = (Libro)libros.get(i);
            auxInt = getPorcentajeTemaLibro(auxLibro,tema);
            if(auxInt > porcentajeTemaMejorActual){
                l = auxLibro;
                porcentajeTemaMejorActual = auxInt;
            }
        }
        
        return l;
    }
    
    int getPorcentajeTemaLibro(Libro l, String tema){
        int porcentaje = 0;
        Tema auxTema;
        ArrayList temas = l.getTemas();
        
        for(int i=0; i<temas.size(); i++){
            auxTema = (Tema)temas.get(i);
            if(auxTema.getNombretema().equals(tema)){
                porcentaje = auxTema.getPorcentaje();
                break;
            }
        }
        return porcentaje;
    }
    
    void pedirPrestadoLibro(Libro l){
        PedirPrestado pp = new PedirPrestado();
        pp.setLibro(l);
        enviarMensaje(ACLMessage.REQUEST, pp);
    }
    
    void devolver(Libro l){
        Devolver d = new Devolver();
        d.setLibro(libro);
        enviarMensaje(ACLMessage.REQUEST, d);
    }
    
    void quitarLibro(Libro l){
        Libro auxLibro;
        ArrayList nuevosLibros = new ArrayList();
        
        for(int i=0; i<libros.size(); i++){
            auxLibro = (Libro)libros.get(i);
            if( auxLibro.getId()!=l.getId() ){
                nuevosLibros.add(auxLibro);
            }
        }
        
        this.libros = nuevosLibros;
    }
    
    void consultarLibros(){
        ConsultarLibros cl = getConsultaDeCatalogo();
        
        enviarMensaje(ACLMessage.REQUEST, cl);
    }
    
    private ConsultarLibros getConsultaDeCatalogo() {
        ConsultarLibros cl = new ConsultarLibros();
        
        if(this.tipoParametro.equals("tema")){
            cl.setTema(this.parametro);
        }else if(this.tipoParametro.equals("titulo")){
            cl.setTitulo(this.parametro);
        }else if(this.tipoParametro.equals("autor")){
            cl.setAutor(this.parametro);
        }
        
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
         addBehaviour(new AlumnoAutAgent.EsperarRespuestaServidor(this));
         send(msg);
         mensajito("Contactando la biblioteca.......!");
      }
      catch (Exception ex) { ex.printStackTrace(); }
    }
    
    void lookupServer() {
        
        try{
            Thread.sleep(500);
        }catch(Exception e){
            e.printStackTrace();
        }
        
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
        System.out.println(this.getLocalName()+" "+fmt.format(new Date())+": "+msg);
    }
    
}
