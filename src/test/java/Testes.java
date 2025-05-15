import org.example.CalculadoraReembolso;
import org.example.Paciente;
import org.example.PlanoSaude;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Testes {

    CalculadoraReembolso calculadora = new CalculadoraReembolso();

    @Test
    public void testReembolso() {
        assertEquals(140, calculadora.reembolsar(200, 0.7), 0.01);
        assertEquals(0, calculadora.reembolsar(200, 0), 0.01);
        assertEquals(200, calculadora.reembolsar(200, 1), 0.01);
    }
    PlanoSaude stub = new PlanoSaude() {

        @Override
        public double calcPercent(Paciente paciente) {
            return 50;
        }
    };
}
