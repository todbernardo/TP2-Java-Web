import org.example.CalculadoraReembolso;
import org.example.Paciente;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testes {

    @Test
    public void testReembolso() {
        CalculadoraReembolso calc = new CalculadoraReembolso();
        Paciente paciente = new Paciente();

        assertEquals(140, calc.reembolsar(200, 0.7, paciente), 0.01);
        assertEquals(0, calc.reembolsar(0, 0.7, paciente), 0.01);
        assertEquals(70, calc.reembolsar(100, 0.7, paciente), 0.01);
        assertEquals(100, calc.reembolsar(100, 1, paciente), 0.01);
        assertEquals(0, calc.reembolsar(100, 0, paciente), 0.01);
    }
}
