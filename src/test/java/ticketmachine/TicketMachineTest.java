package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        //S3 on n’imprime pas le ticket si le montant inséré est insuffisant
        public void insertMoneyInsufficient() {
                machine.insertMoney(10);
                assertEquals("Montant insuffisant pour imprimer ticket", false, machine.printTicket());
        }

        @Test
        //S4 on imprime le ticket si le montant inséré est suffisant
        public void insertMoneySufficient() {
                machine.insertMoney(50);
                assertEquals("Montant suffisant pour imprimer ticket", true, machine.printTicket());
        }
        
        @Test 
        //S5 Quand on imprime un ticket la balance est décrémentée du prix du ticket
        public void priceIsCorrectlyUpdate() {
                machine.insertMoney(50);
                machine.printTicket();
                assertEquals("Suite à impression balance mal décrémentée", 0, machine.getBalance());
        }
        
        @Test
        //S6 le montant collecté est mis à jour quand on imprime un ticket (pas avant)
        public void totalIsCorrectlyUpdate() {
                machine.insertMoney(50);
                assertEquals("Montant collecté màj trop top", 0, machine.getTotal());
                machine.printTicket();
                assertEquals("Montant collecté pas màj", 50, machine.getTotal());
        }
        
        @Test
        //S7 refund() rend correctement la monnaie
        public void moneyCorrectlyRefunded() {
               machine.insertMoney(30);
               assertEquals("Rend mal la monnaie", 30, machine.refund());
        }
        
        @Test
        //S8 refund() remet la balance à zéro
        public void refundResetOk() {
               machine.insertMoney(30);
               machine.refund();
               assertEquals("Balance non mis à zéro par refund", 0, machine.getBalance());
        }
        
        @Test
        //S9 on ne peut pas insérer un montant négatif
        public void insertMonyeMustBePositive() {
                try {
                    machine.insertMoney(-10);
                } catch(IllegalArgumentException e) {
                    throw e;
                }
        }
        
        @Test
        //S10 On ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
        public void machineBuildOk() {
            try {
                TicketMachine machineError = new TicketMachine(-10);
            } catch(IllegalArgumentException e) {
                throw e;
            }
            
        }
}
