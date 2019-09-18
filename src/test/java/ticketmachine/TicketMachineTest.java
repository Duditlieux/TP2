package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante
        
        private static final int WRONG_PRICE = 51; // Une constante

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
                assertNotEquals(WRONG_PRICE, machine.getPrice());
	}
        
        @Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        // S3
        public void testPrintTicket1(){
            assertFalse(machine.printTicket());
            
            machine.insertMoney(PRICE-1);
            assertFalse(machine.printTicket());
        }
        
        
        @Test
        //S4
        public void testPrintTicket2(){
            machine.insertMoney(PRICE+1);
            assertTrue(machine.printTicket());
        }
        
        @Test
        //TOUJOURS S4
        public void testPrintTicket3(){
            machine.insertMoney(PRICE);
            assertTrue(machine.printTicket());
        }
        
        @Test
        //S5
        public void testPrintTicket4(){
            machine.insertMoney(PRICE+1);
            machine.printTicket();
            
            assertEquals(PRICE-PRICE+1, machine.getBalance());
        }
        
        
        @Test
        //S6
        public void testPrintTicket5(){
            machine.insertMoney(500000);
            
            for(int i=0; i<10; i++){
                assertEquals(PRICE*i, machine.getTotal());
                machine.printTicket();
                assertEquals(PRICE*(i+1), machine.getTotal());
            }
        }
        
        
        @Test
        //S7
        public void testRefund1(){            
            machine.insertMoney(5000);
            assertEquals(5000, machine.refund());
            
        }
        
        @Test
        //S8
        public void testRefund2(){   
            assertEquals(0, machine.getBalance());
        }
        
        
        @Test(expected = IllegalArgumentException.class)
        //S9
        public void testBalance1() {
                machine.insertMoney(-1);
	}
        
        
        @Test (expected =IllegalArgumentException.class)
        //S10
        public void negativeTicket(){
            TicketMachine machine = new TicketMachine(-1);
        }
        

        


}
