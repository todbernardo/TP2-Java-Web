import org.example.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class Testes {

    public Consulta helperConsultas(){
        Consulta consulta = new Consulta(0.8, 300);
        return consulta;
    }
    public double margem(double valor1, double valor2, double margem){
        return
    }

    @Test
    public void testReembolso() {
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);

        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(false);

        CalculadoraReembolso calc = new CalculadoraReembolso();
        Paciente paciente = new Paciente();
        Consulta consulta = helperConsultas();

        assertEquals(140, calc.reembolsar(200, 0.7, paciente), 0.01);
        assertEquals(0, calc.reembolsar(0, 0.7, paciente), 0.01);
        assertEquals(70, calc.reembolsar(100, 0.7, paciente), 0.01);
        assertEquals(100, calc.reembolsar(100, 1, paciente), 0.01);
        assertEquals(0, calc.reembolsar(100, 0, paciente), 0.01);
    }
    @Test
    public void testStub1(){
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);

        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(true);

        CalculadoraReembolso calc = new CalculadoraReembolso();
        Paciente paciente = new Paciente();
        Consulta consulta = helperConsultas();
        PlanoSaude plano = () -> 0.5;

        assertEquals(100, calc.reembolsar(200, plano.calcPercent(), paciente), 0.01);
    }
    @Test
    public void testStub2(){
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);

        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(true);

        CalculadoraReembolso calc = new CalculadoraReembolso();
        Paciente paciente = new Paciente();
        Consulta consulta = helperConsultas();
        PlanoSaude plano = () -> 0.8;

        assertEquals(160, calc.reembolsar(200, plano.calcPercent(), paciente), 0.01);
    }
    @Test
    public void testSpy(){
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);

        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(true);

        Auditoria auditoria = mock(Auditoria.class);
        AuditoriaSpy spy = new AuditoriaSpy(auditoria);

        spy.registrarConsulta();
        verify(auditoria).registrarConsulta();
    }
}
