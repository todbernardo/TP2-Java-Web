package org.example;

import java.util.ArrayList;
import java.util.List;

public class HistoricoConsultasFake implements HistoricoConsultas {

    List<Consulta> consultas = new ArrayList<Consulta>();

    @Override
    public void addConsulta(Consulta consulta){
        consultas.add(consulta);
    }
    @Override
    public List<Consulta> mostrarConsultas(){
        return consultas;
    }
}
