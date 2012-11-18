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
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import ontologia.BibliotecaOntologia;
import ontologia.BibliotecaVocabulario;

/**
 *
 * @author armando
 */
public class BibliotecaAgent extends Agent implements BibliotecaVocabulario{
    private Codec codec = new SLCodec();
    private Ontology ontology = BibliotecaOntologia.getInstance();
    
    
    protected void setup() {
// ------------------------

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
    
    
    
    class ReceiveMessages extends CyclicBehaviour {
// -----------------------------------------------  Receive requests and queries from client
//                                                  agent and launch appropriate handlers

      public ReceiveMessages(Agent a) {

         super(a);
      }

      public void action() {
      }
   }

    
}
