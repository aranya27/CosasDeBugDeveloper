/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Result;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.util.leap.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import ontologia.*;
import recursos.Libro;
import recursos.Tema;


public class BibliotecaAgent extends Agent implements BibliotecaVocabulario{
    private Codec codec = new SLCodec();
    private Ontology ontology = BibliotecaOntologia.getInstance();
    private ArrayList libros = new ArrayList();
    SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss:SS");
    
    private void crearLibros(){
        ArrayList temas = new ArrayList();
        temas.add(new Tema("POO",20));
        temas.add(new Tema("POA",20));
        temas.add(new Tema("PE",20));
        temas.add(new Tema("PBA",20));
        temas.add(new Tema("PL",20));
        libros.add(new Libro(1,"Paradigmas de Programación","Pedro Herrera",temas));
        
        temas = new ArrayList();
        temas.add(new Tema("POO",50));
        temas.add(new Tema("Java",50));
        libros.add(new Libro(2,"Programación en Java","Pedro Herrera",temas));
    }
    
    protected void setup() {
      crearLibros();

      // Registramos lenguaje y ontologia
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontology);

      // Establecemos los Behaviours
      SequentialBehaviour sb = new SequentialBehaviour();
      sb.addSubBehaviour(new RegisterInDF(this));
      sb.addSubBehaviour(new ReceiveMessages(this));
      addBehaviour(sb);
   }
    
    
    
    
    class RegisterInDF extends OneShotBehaviour {
        RegisterInDF(Agent a) {
            super(a);
        }

        public void action() {
            ServiceDescription sd = new ServiceDescription();
            sd.setType(SERVER_AGENT);
            sd.setName(getName());
            sd.setOwnership("Prof6802");
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            dfd.addServices(sd);
            try {
                DFAgentDescription[] dfds = DFService.search(myAgent, dfd);
            if (dfds.length > 0 ) {
                DFService.deregister(myAgent, dfd);
            }
            DFService.register(myAgent, dfd);
            mensajito(getLocalName() + " is ready.");
            }
            catch (Exception ex) {
                mensajito("Failed registering with DF! Shutting down...");
                ex.printStackTrace();
                doDelete();
            }
        }
    }
    
    void replyNotUnderstood(ACLMessage msg) {
        try {
            ContentElement content = getContentManager().extractContent(msg);
            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
            getContentManager().fillContent(reply, content);
            send(reply);
            mensajito("Se recibió una petición que no se entendió. La mando  " + msg.getSender().getLocalName());
        }
        catch(Exception ex) { 
            ex.printStackTrace(); 
        }
    }
    
    
    class ReceiveMessages extends CyclicBehaviour {
      public ReceiveMessages(Agent a) {
         super(a);
      }

      public void action() {
          ACLMessage msg = receive();
         if (msg == null) { 
             block(); 
             return; 
         }
         try {
            ContentElement content = getContentManager().extractContent(msg);
            Concept action = ((Action)content).getAction();
            switch (msg.getPerformative()) {
                case (ACLMessage.REQUEST):
                    if (action instanceof ConsultarLibros){
                        mensajito("El agente " + msg.getSender().getLocalName()+" nos hizo una consulta para saber que libros hay");
                        addBehaviour(new HandleConsultarLibros(myAgent, msg));
                    }else if(action instanceof PedirPrestado){
                        mensajito("El agente " + msg.getSender().getLocalName()+" nos pide prestado un libro");
                        addBehaviour(new HandlePrestarLibro(myAgent, msg));
                    }else if(action instanceof Devolver){
                        mensajito("El agente " + msg.getSender().getLocalName()+" nos quere devolver un libro");
                        addBehaviour(new HandleDevolverLibro(myAgent, msg));
                    }else replyNotUnderstood(msg);
                    break;

               default: replyNotUnderstood(msg);
            }
         }
         catch(Exception ex) { 
             ex.printStackTrace(); 
         }
      }
   }

    
    
    
    class HandleConsultarLibros extends OneShotBehaviour{
        ACLMessage request;
        
        public HandleConsultarLibros(Agent a, ACLMessage request) {
            super(a);
            this.request = request;
        }

        @Override
        public void action() {
            try{
                ContentElement content = getContentManager().extractContent(request);
                ConsultarLibros cl = (ConsultarLibros)((Action)content).getAction();
                ACLMessage reply = request.createReply();
                LibrosEncontrados obj = procesarConsultaLibros(cl);
                
                reply.setPerformative(ACLMessage.INFORM);
                Result result = new Result((Action)content, obj);
                getContentManager().fillContent(reply, result);
                send(reply);
                mensajito("Se encontraron "+obj.getLibros().size()+" libros");
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    class HandleDevolverLibro extends OneShotBehaviour{
        ACLMessage request;
        
        public HandleDevolverLibro(Agent a, ACLMessage request) {
            super(a);
            this.request = request;
        }
        
        @Override
        public void action() {
            try{
                ContentElement content = getContentManager().extractContent(request);
                Devolver d = (Devolver)((Action)content).getAction();
                ACLMessage reply = new ACLMessage();
                
                reply.setLanguage(codec.getName());
                reply.setOntology(ontology.getName());
                reply.setSender(getAID());
                reply.addReceiver(request.getSender());
                
                InformarDevolucion id = procesarDevolverLibro(d);
                
                if(id.getStatus()==DEVOLUCION_EXITOSA)
                    reply.setPerformative(ACLMessage.INFORM);
                else
                    reply.setPerformative(ACLMessage.FAILURE);
                
                getContentManager().fillContent(reply, id);
                send(reply);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    class HandlePrestarLibro extends OneShotBehaviour{
        ACLMessage request;
        
        public HandlePrestarLibro(Agent a, ACLMessage request) {
            super(a);
            this.request = request;
        }
        
        @Override
        public void action() {
            try{
                ContentElement content = getContentManager().extractContent(request);
                PedirPrestado pp = (PedirPrestado)((Action)content).getAction();
                ACLMessage reply = request.createReply();
                Object obj = procesarPrestarLibro(pp);
                if(obj instanceof Problema){
                    reply.setPerformative(ACLMessage.FAILURE);
                }else{
                    reply.setPerformative(ACLMessage.INFORM);
                }
                Result result = new Result((Action)content, obj);
                getContentManager().fillContent(reply, result);
                send(reply);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    private boolean libroTieneTema(Libro l, String tema){
        ArrayList temas = l.getTemas();
        
        for(int i=0; i<temas.size(); i++){
            if( ((Tema)temas.get(i)).getNombretema().equals(tema) ){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean libroCumpleConLoQueSePide(Libro l, String titulo, String autor, String tema){
        if(
            (
                autor==null || 
                autor.equals(l.getAutor())
            )
            &&
            (
                titulo==null || 
                titulo.equals(l.getTitulo())
            )
            &&
            (
                tema==null ||
                libroTieneTema(l,tema)
            )
                
        ){
            return true;
        }
        
        return false;
        
    }
    
    InformarDevolucion procesarDevolverLibro(Devolver d){
        Libro libroQueSeQuiereDevolver = d.getLibro();
        Libro laux;
        InformarDevolucion id = new InformarDevolucion();
        boolean devolucionExitosa = false;
        
        for(int i=0; i<libros.size(); i++){
            laux = (Libro)libros.get(i);
            if(laux.getId()==libroQueSeQuiereDevolver.getId()){
                laux.setPrestado(false);
                mensajito("Se devuelve el libro "+laux.getTitulo());
                devolucionExitosa = true;
                break;
            }
        }
        
        if(devolucionExitosa){
            id.setStatus(DEVOLUCION_EXITOSA);
        }else{
            id.setStatus(LIBRO_NO_EXISTE);
        }
        
        return id;
    }
    
    synchronized Object procesarPrestarLibro(PedirPrestado pp){
        Libro libroQueQuiereElAlumno = pp.getLibro();
        Libro laux;
        Libro libroAPrestar = null;
        Object retorno = null;
        
        for(int i=0; i<libros.size(); i++){
            laux = (Libro)libros.get(i);
            if(laux.getId()==libroQueQuiereElAlumno.getId()){
                libroAPrestar = laux;
            }
        }
        
        
        if(libroAPrestar==null){//Si no se encontro el libro que se quiere prestar
            Problema p = new Problema();
            p.setNum(LIBRO_NO_EXISTE);
            p.setMsg("El libro "+libroQueQuiereElAlumno.getTitulo()+" ("+libroQueQuiereElAlumno.getId()+") no existe en el catálogo");
            retorno = p;
        }else if(libroAPrestar.isPrestado()){//Si sí encontramos el libro, pero ya esta prestado
            Problema p = new Problema();
            p.setNum(LIBRO_YA_PRESTADO);
            p.setMsg("El libro "+libroQueQuiereElAlumno.getTitulo()+" ("+libroQueQuiereElAlumno.getId()+") ya se encuentra prestado");
            retorno = p;
        }else{//Todo bien
            Prestamo p = new Prestamo();
            p.setTiempo(TIEMPO_PRESTAMO);
            p.setLibro(libroAPrestar);
            libroAPrestar.setPrestado(true);
            retorno = p;
            mensajito("Se presta el libro "+p.getLibro().getTitulo());
        }
        
        
        return retorno;
    }
    
    LibrosEncontrados procesarConsultaLibros(ConsultarLibros mo) {
        LibrosEncontrados le = new LibrosEncontrados();
        jade.util.leap.ArrayList librosARetornar = new jade.util.leap.ArrayList();
        

        Libro l;
        for(int i=0;i<libros.size(); i++){
            l = (Libro)libros.get(i);
            if(libroCumpleConLoQueSePide(l,mo.getTitulo(),mo.getAutor(),mo.getTema() )){
                librosARetornar.add(l);
            }
        }

        le.setLibros(librosARetornar);
        return le;
        
        
   }
    
    public void mensajito(String msg){
        System.out.println(this.getLocalName()+" "+fmt.format(new Date())+": "+msg);
    }
    
}
