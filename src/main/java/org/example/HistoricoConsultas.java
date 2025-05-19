package org.example;

import java.util.List;

public interface HistoricoConsultas {
    void registrarConsulta(Paciente paciente, double valor, double percentual);
    List<Consulta> consultas();
}
