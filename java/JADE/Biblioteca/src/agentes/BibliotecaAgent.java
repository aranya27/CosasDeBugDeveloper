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
import ontologia.BibliotecaOntologia;
import ontologia.BibliotecaVocabulario;
import ontologia.ConsultarLibros;
import ontologia.LibrosEncontrados;
import recursos.Libro;
import recursos.Tema;


public class BibliotecaAgent extends Agent implements BibliotecaVocabulario{
    private Codec codec = new SLCodec();
    private Ontology ontology = BibliotecaOntologia.getInstance();
    private ArrayList libros = new ArrayList();
    
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

      // Register language and ontology
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontology);

      // Set this agent main behaviour
      SequentialBehaviour sb = new SequentialBehaviour();
      sb.addSubBehaviour(new RegisterInDF(this));
      sb.addSubBehaviour(new ReceiveMessages(this));
      addBehaviour(sb);
   }
    
    
    
    
    class RegisterInDF extends OneShotBehaviour {
// ---------------------------------------------  Register in the DF for the client agent
//                                                be able to retrieve its AID
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
            System.out.println(getLocalName() + " is ready.");
         }
         catch (Exception ex) {
            System.out.println("Failed registering with DF! Shutting down...");
            ex.printStackTrace();
            doDelete();
         }
        }
      
      
      
    }
    
    void replyNotUnderstood(ACLMessage msg) {
        // -----------------------------------------

            try {
                ContentElement content = getContentManager().extractContent(msg);
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                getContentManager().fillContent(reply, content);
                send(reply);
                System.out.println("Not understood!");
            }
            catch(Exception ex) { ex.printStackTrace(); }
        }
    
    
    class ReceiveMessages extends CyclicBehaviour {
// -----------------------------------------------  Receive requests and queries from client
//                                                  agent and launch appropriate handlers

      public ReceiveMessages(Agent a) {

         super(a);
      }

      public void action() {
          ACLMessage msg = receive();
         if (msg == null) { block(); return; }
         try {
            ContentElement content = getContentManager().extractContent(msg);
            Concept action = ((Action)content).getAction();

            switch (msg.getPerformative()) {

               case (ACLMessage.REQUEST):

                  System.out.println("Request from " + msg.getSender().getLocalName());

                  if (action instanceof ConsultarLibros)
                     addBehaviour(new HandleConsultarLibros(myAgent, msg));
                  else replyNotUnderstood(msg);
                  break;

               /*case (ACLMessage.QUERY_REF):

                  System.out.println("Query from " + msg.getSender().getLocalName());

                  if (action instanceof Information)
                     addBehaviour(new HandleInformation(myAgent, msg));
                  else replyNotUnderstood(msg);
                  break;
*/
               default: replyNotUnderstood(msg);
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
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
                Object obj = procesarConsultaLibros(cl);
                
                reply.setPerformative(ACLMessage.INFORM);
                Result result = new Result((Action)content, obj);
                getContentManager().fillContent(reply, result);
                send(reply);
                System.out.println("Operation processed.");
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /*
    String libroAXML(Libro l){
        StringBuilder sb = new StringBuilder("");
        sb.append("<libro>");
        
        sb.append("<titulo>");
        sb.append(l.getTitulo());
        sb.append("</titulo>");
        
        sb.append("<autor>");
        sb.append(l.getAutor());
        sb.append("</autor>");
        
        sb.append("<temas>");
        for(Tema t : l.getTemas()){
            sb.append("<tema>");
                sb.append("<nombre>");
                sb.append(t.getTema());
                sb.append("</nombre>");
                
                sb.append("<porcentaje>");
                sb.append(t.getPorcentaje());
                sb.append("</porcentaje>");
            sb.append("</tema>");
        }
        sb.append("</temas>");
        
        sb.append("</libro>");
        
        return sb.toString();
    }
    */
    /*private boolean libroTieneTema(Libro l, String tema){
        
        for(Tema t:l.getTemas()){
            if(t.getTema().equals(tema))
                return true;
        }
        
        
        
        return false;
    }*/
    
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
                //libroTieneTema(l,tema)
                true
            )
                
        ){
            return true;
        }
        
        
        return false;
        
    }
    
    Object procesarConsultaLibros(ConsultarLibros mo) {
        StringBuilder sb = new StringBuilder("");
        LibrosEncontrados le = new LibrosEncontrados();
        jade.util.leap.ArrayList librosARetornar = new jade.util.leap.ArrayList();
        
        
        /*System.out.println("titulo = "+mo.getTitulo());
        System.out.println("autor = "+mo.getAutor());
        System.out.println("tema = "+mo.getTema());*/
        
        sb.append("<catalogo>");
        
        //for(Libro l : libros){
        Libro l;
        for(int i=0;i<libros.size(); i++){
            l = (Libro)libros.get(i);
            if(libroCumpleConLoQueSePide(l,mo.getTitulo(),mo.getAutor(),mo.getTema() )){
                librosARetornar.add(l);
            }
        }

        /*
        for(Libro l : librosARetornar){
            sb.append(libroAXML(l));
        }*/
        sb.append("</catalogo>");
        
        //le.setLibros(sb.toString());
        le.setLibros(librosARetornar);
        System.out.println("Libros encontrados (server): "+le.getLibros());
        return le;
        
        
   }
    
}
