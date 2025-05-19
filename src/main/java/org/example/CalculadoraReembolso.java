package org.example;

public class CalculadoraReembolso {
    AutorizadorReembolso autorizador;
    Consulta consulta;

    public double reembolsar(double valor, double percentual, Paciente paciente) {

         if (!autorizador.autorizarReembolso(consulta)) {
            throw new RuntimeException("Reembolso não autorizado.");
        }
        return valor * percentual;
    }


}