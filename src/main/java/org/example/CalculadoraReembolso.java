package org.example;

public class CalculadoraReembolso {
    private AutorizadorReembolso autorizador;

    public CalculadoraReembolso(AutorizadorReembolso autorizador) {
        this.autorizador = autorizador;
    }
    public double calc(Consulta consulta, PlanoSaude plano, Paciente paciente) {

         if (!autorizador.autorizarReembolso(consulta)) {
            throw new RuntimeException("Reembolso nao autorizado!");
        }
         double valorTotal = consulta.getValor() * plano.calcPercent();
        return Math.min(valorTotal, 150);
    }
}