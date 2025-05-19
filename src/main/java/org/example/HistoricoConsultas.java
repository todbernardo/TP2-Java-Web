package org.example;

import java.util.List;

public interface HistoricoConsultas {

    void addConsulta(Consulta consulta);
    List<Consulta> mostrarConsultas();
}
