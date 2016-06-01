package com.testeo;

import com.jugando.junit.Calculator;
import com.jugando.junit.Operador;
import com.jugando.junit.Suma;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    @Mock
    private Suma op;

    @Test
    public void evaluatesExpression() {
        Calculator calculator = Mockito.mock(Calculator.class);
        when(calculator.evaluate("1+2+3")).thenReturn(6);
        int sum = calculator.evaluate("1+2+3");
        assertEquals("ESTO DEBERIA SER SEIS", 6, sum);

        calculator.setA(10);
        calculator.setB(10);
        calculator.setOperador(op);
        when(calculator.efectuarOperacion()).thenReturn(99d);
        
        double d = calculator.efectuarOperacion();

        assertEquals( 99d, calculator.efectuarOperacion(),0);
        
        
        
    }

    
    
    
    @Test(expected = IOException.class)
    public void OutputStreamWriter_rethrows_an_exception_from_OutputStream()
            throws IOException {
        throw new IOException();
    }

}
