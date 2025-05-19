package org.example;

import java.util.List;

public interface HistoricoConsultasFake {
    void guardarDados(HistoricoConsultas consulta);
    List<Consulta> dadosConsultas();
}
