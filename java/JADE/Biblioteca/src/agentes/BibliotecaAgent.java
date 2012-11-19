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
import jade.util.leap.List;
import java.util.ArrayList;
import ontologia.BibliotecaOntologia;
import ontologia.BibliotecaVocabulario;
import ontologia.ConsultarLibros;
import ontologia.LibrosEncontrados;
import recursos.Libro;
import recursos.Tema;


public class BibliotecaAgent extends Agent implements BibliotecaVocabulario{
    private Codec codec = new SLCodec();
    private Ontology ontology = BibliotecaOntologia.getInstance();
    private ArrayList<Libro> libros = new ArrayList<Libro>();
    
    private void crearLibros(){
        ArrayList<Tema> temas = new ArrayList<Tema>();
        temas.add(new Tema("POO",20));
        temas.add(new Tema("POA",20));
        temas.add(new Tema("PE",20));
        temas.add(new Tema("PBA",20));
        temas.add(new Tema("PL",20));
        libros.add(new Libro(1,"Paradigmas de Programación","Pedro Herrera",temas));
        libros.add(new Libro(2,"Paradigmas de Programación","Pedro Herrera",temas));
        libros.add(new Libro(3,"Paradigmas de Programación","Pedro Herrera",temas));
        
        temas = new ArrayList<Tema>();
        temas.add(new Tema("POO",50));
        temas.add(new Tema("Java",50));
        libros.add(new Libro(4,"Programación en Java","Pedro Herrera",temas));
        libros.add(new Libro(5,"Programación en Java","Pedro Herrera",temas));
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
    
    Object procesarConsultaLibros(ConsultarLibros mo) {
        
        LibrosEncontrados le = new LibrosEncontrados();
        le.setLibros(new ArrayList<Libro>());
        
        System.out.println("titulo = "+mo.getTitulo());
        System.out.println("autor = "+mo.getAutor());
        System.out.println("tema = "+mo.getTema());
        
        //buscar por titulo
        if(mo.getTitulo()!=null){
            for(Libro l : libros){
                if(l.getTitulo().equals(mo.getTitulo())){
                    le.getLibros().add(l);
                }
            }
        }
        
        //buscar por autor
        if(mo.getAutor()!=null){
            for(Libro l : libros){
                if(l.getAutor().equals(mo.getAutor()) && !le.getLibros().contains(l) ){
                    le.getLibros().add(l);
                }
            }
        }
        
        //buscar por tema
        if(mo.getTema()!=null){
            for(Libro l : libros){
                for(Tema t : l.getTemas()){
                    if(t.getTema().equals(mo.getTema()) && !le.getLibros().contains(l) ){
                        le.getLibros().add(l);
                    }
                }
            }
        }
        
        System.out.println("Cantidad de libros encontrados: "+le.getLibros().size());
        
        
        
        return le;
        
        /*
        Account acc = (Account)accounts.get(mo.getAccountId());
        if (acc == null) return newProblem(ACCOUNT_NOT_FOUND);
        if (mo.getAmount() <= 0) return newProblem(ILLEGAL_OPERATION);

        if (mo.getType() != DEPOSIT && mo.getType() != WITHDRAWAL)
            return null;
        if (mo.getType() == DEPOSIT)
            acc.setBalance(acc.getBalance() + mo.getAmount());
        else if (mo.getType() == WITHDRAWAL) {
            if (mo.getAmount() > acc.getBalance())
            return newProblem(NOT_ENOUGH_MONEY);
            acc.setBalance(acc.getBalance() - mo.getAmount());
        }
        Operation op = new Operation();
        op.setType(mo.getType());
        op.setAmount(mo.getAmount());
        op.setAccountId(acc.getId());
        op.setDate(new java.util.Date());
        List l = (List)operations.get(acc.getId());
        l.add(op);
        operations.put(acc.getId(), l);
        return acc;
        * 
        */
   }
    
}
