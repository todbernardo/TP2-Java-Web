import org.example.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class Testes {

    // Etapa 9 - Helper para Criação de Consultas
    public Consulta helperConsulta(double valor){
        return new Consulta(valor);
    }
    // Etapa 10 - Comparação com Margem de Erro
    public void assertMargem(double expected, double actual){
        assertEquals(expected, actual, 0.01);
    }
    // Etapa 1 e 2 - Reembolso Básico e Casos de Borda
    @Test
    public void testReembolso() {
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);

        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(true);

        CalculadoraReembolso calc = new CalculadoraReembolso(autorizadorReembolso);
        Paciente paciente = new Paciente();
        HistoricoConsultasFake historico = new HistoricoConsultasFake();

        Consulta consulta = helperConsulta(200);
        PlanoSaude plano = () -> 0.7;
        historico.addConsulta(consulta);
        assertMargem(140, calc.calc(consulta, plano, paciente));

        Consulta consulta2 = helperConsulta(0);
        PlanoSaude plano2 = () -> 0.7;
        historico.addConsulta(consulta2);
        assertMargem(0, calc.calc(consulta2, plano2, paciente));

        Consulta consulta3 = helperConsulta(100);
        PlanoSaude plano3 = () -> 0.7;
        historico.addConsulta(consulta3);
        assertMargem(70, calc.calc(consulta3, plano3, paciente));

        Consulta consulta4 = helperConsulta(100);
        PlanoSaude plano4 = () -> 1;
        historico.addConsulta(consulta4);
        assertMargem(100, calc.calc(consulta4, plano4, paciente));

        Consulta consulta5 = helperConsulta(100);
        PlanoSaude plano5 = () -> 0;
        historico.addConsulta(consulta5);
        assertMargem(0, calc.calc(consulta5, plano5, paciente));
    }
    // Etapa 6 - Stub de Plano de Saúde
    @Test
    public void testStub1(){
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);

        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(true);

        CalculadoraReembolso calc = new CalculadoraReembolso(autorizadorReembolso);
        Paciente paciente = new Paciente();
        HistoricoConsultasFake historico = new HistoricoConsultasFake();

        Consulta consulta = helperConsulta(200);
        historico.addConsulta(consulta);
        PlanoSaude plano = () -> 0.5;

        assertMargem(100, calc.calc(consulta, plano, paciente));

    }
    // Etapa 6 - Stub de Plano de Saúde
    @Test
    public void testStub2(){
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);

        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(true);

        CalculadoraReembolso calc = new CalculadoraReembolso(autorizadorReembolso);
        Paciente paciente = new Paciente();
        HistoricoConsultasFake historico = new HistoricoConsultasFake();

        Consulta consulta = helperConsulta(100);
        historico.addConsulta(consulta);
        PlanoSaude plano = () -> 0.8;


        assertMargem(80, calc.calc(consulta, plano, paciente));
    }
    // Etapa 7 - Spy para Auditoria
    @Test
    public void testSpy(){
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);

        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(true);

        Auditoria auditoria = mock(Auditoria.class);
        AuditoriaSpy spy = new AuditoriaSpy(auditoria);

        spy.registrarConsulta();
        verify(auditoria).registrarConsulta();
    }
    // Etapa 8 - Mock para Autorização
    @Test
    public void testReembolsoNaoAutorizado() {
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);
        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(false);

        CalculadoraReembolso calc = new CalculadoraReembolso(autorizadorReembolso);
        Paciente paciente = new Paciente();
        HistoricoConsultasFake historico = new HistoricoConsultasFake();

        Consulta consulta = helperConsulta(200);
        historico.addConsulta(consulta);
        PlanoSaude plano = () -> 0.7;

        assertThrows(RuntimeException.class, () -> {
            calc.calc(consulta, plano, paciente);
        });
    }
    // Etapa 11 - Regras de Teto
    @Test
    public void testReembolsoLimitado(){
        AutorizadorReembolso autorizadorReembolso = mock(AutorizadorReembolso.class);
        when(autorizadorReembolso.autorizarReembolso(any())).thenReturn(true);

        CalculadoraReembolso calc = new CalculadoraReembolso(autorizadorReembolso);
        Paciente paciente = new Paciente();
        HistoricoConsultasFake historico = new HistoricoConsultasFake();

        Consulta consulta = helperConsulta(1500);
        historico.addConsulta(consulta);
        PlanoSaude plano = () -> 0.5;

        assertMargem(150, calc.calc(consulta, plano, paciente));
    }
}




